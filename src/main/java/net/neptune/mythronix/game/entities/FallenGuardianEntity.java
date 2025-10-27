package net.neptune.mythronix.game.entities;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.BossInfo;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerBossInfo;
import net.neptune.mythronix.game.entities.shoot.CorruptionOrbEntity;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class FallenGuardianEntity extends MonsterEntity implements IAnimatable {

    AnimationFactory factory = GeckoLibUtil.createFactory(this);
    private static final DataParameter<Boolean> DEATH_ANIM =
            EntityDataManager.defineId(FallenGuardianEntity.class, DataSerializers.BOOLEAN);
    private static final int ATTACK_ANIM_TICKS = 20; // durée anim en ticks
    private static final DataParameter<Integer> ATTACK_TIMER =
            EntityDataManager.defineId(FallenGuardianEntity.class, DataSerializers.INT);

    protected FallenGuardianEntity(EntityType<? extends FallenGuardianEntity> type, World world) {
        super(type, world);

        this.setPersistenceRequired();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FallenGuardianEntity.CorruptionAttackGoal(this));
        this.goalSelector.addGoal(2, new MoveTowardsTargetGoal(this, 0.9D, 20.0F));
        this.goalSelector.addGoal(3, new LookAtGoal(this, PlayerEntity.class, 10.0F));
        this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        super.registerGoals();
    }

    @Override
    public boolean doHurtTarget(Entity target) {
        boolean flag = super.doHurtTarget(target);
        if (flag && !this.level.isClientSide) {
            this.setAttackTimer(ATTACK_ANIM_TICKS); // synchronise vers client
        }
        return flag;
    }

    public boolean isPlayingDeath() {
        return this.entityData.get(DEATH_ANIM);
    }

    public void setPlayingDeath(boolean value) {
        this.entityData.set(DEATH_ANIM, value);
    }

    @Override
    public void tick() {
        super.tick();
        this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());

        if (!this.level.isClientSide) {
            int t = this.getAttackTimer();
            if (t > 0) {
                this.setAttackTimer(t - 1);
            }
        }
    }

    @Override
    protected void tickDeath() {
        ++this.deathTime;

        // on lance l'anim à la première tick de la mort
        if (this.deathTime == 1) {
            // on met le flag synchronisé (les clients recevront la valeur et démarreront l'anim)
            if (!this.level.isClientSide) {
                this.setPlayingDeath(true);
                // stop movement/navigation côté serveur
                if (this.getNavigation() != null) this.getNavigation().stop();
                this.getMoveControl().setWantedPosition(this.getX(), this.getY(), this.getZ(), 0.0D);
            }

            // son de mort (optionnel)
            this.playSound(SoundEvents.GENERIC_DEATH, 1.0F, 1.0F);
        }

        // durée totale de l'animation en ticks (ex : 60 = 3s)
        int deathAnimDuration = 30;

        // après la durée, supprimer l'entité côté serveur
        if (this.deathTime >= deathAnimDuration) {
            if (!this.level.isClientSide) {
                this.remove(); // en 1.16.5 on utilise remove() simple
            }
        }
    }

    @Override
    public void die(DamageSource pCause) {
        super.die(pCause);
    }

    private final ServerBossInfo bossInfo = new ServerBossInfo(
            new TranslationTextComponent("entity.mt.fallen_guardian"), // texte affiché
            BossInfo.Color.PURPLE, // couleur de la barre
            BossInfo.Overlay.PROGRESS // style (PROGRESS, NOTCHED_10, NOTCHED_12, etc.)
    );

    @Override
    public void startSeenByPlayer(ServerPlayerEntity player) {
        super.startSeenByPlayer(player);
        this.bossInfo.addPlayer(player);
    }

    @Override
    public void stopSeenByPlayer(ServerPlayerEntity player) {
        super.stopSeenByPlayer(player);
        this.bossInfo.removePlayer(player);
    }

    @Override
    protected int getExperienceReward(PlayerEntity pPlayer) {
        return 25 + this.level.random.nextInt(30);
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.GENERIC_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.GENERIC_DEATH;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DEATH_ANIM, false);
        this.entityData.define(ATTACK_TIMER, 0);
    }

    public int getAttackTimer() {
        return this.entityData.get(ATTACK_TIMER);
    }

    public void setAttackTimer(int val) {
        this.entityData.set(ATTACK_TIMER, val);
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 75D)
                .add(Attributes.ATTACK_DAMAGE, 2D)
                .add(Attributes.MOVEMENT_SPEED, 1D);
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false; // jamais de despawn à cause de la distance
    }


    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "controller",2, this::predicate));
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> e) {

        int timer = this.getAttackTimer();
        if (timer > 0) {
            if (timer == ATTACK_ANIM_TICKS) {

                System.out.println("attack");
                e.getController().setAnimation(new AnimationBuilder().addAnimation("animations.guardian.attack", ILoopType.EDefaultLoopTypes.LOOP));
            }
        } else if(e.isMoving()){
            System.out.println("move");
            e.getController().setAnimation(new AnimationBuilder().addAnimation("animations.guardian.walk", ILoopType.EDefaultLoopTypes.LOOP));
        }  else if(this.isPlayingDeath()) {
            System.out.println("death");
            e.getController().setAnimation(new AnimationBuilder().addAnimation("animations.guardian.death", ILoopType.EDefaultLoopTypes.HOLD_ON_LAST_FRAME));
        } else {
            System.out.println("idle");
            e.getController().setAnimation(new AnimationBuilder().addAnimation("animations.guardian.idle", ILoopType.EDefaultLoopTypes.LOOP));
        }

        return PlayState.CONTINUE;
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    public class CorruptionAttackGoal extends Goal {
        private final FallenGuardianEntity guardian;
        private int attackStep;
        private int attackTime;
        private int lastSeen;

        public CorruptionAttackGoal(FallenGuardianEntity fallenGuardianEntity) {
            this.guardian = fallenGuardianEntity;
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            LivingEntity target = this.guardian.getTarget();
            return target != null && target.isAlive();
        }

        @Override
        public void start() {
            this.attackStep = 0;
        }

        @Override
        public void stop() {
            this.lastSeen = 0;
        }

        @Override
        public void tick() {
            LivingEntity target = this.guardian.getTarget();

            if (target == null) return;

            double distanceSq = this.guardian.distanceToSqr(target);
            boolean canSee = this.guardian.getSensing().canSee(target);

            if (canSee) {
                this.lastSeen++;
            } else {
                this.lastSeen = 0;
            }

            if (distanceSq < 64.0D && canSee) {
                World w = this.guardian.level;
                this.attackTime--;

                if (this.attackTime <= 0) {
                    this.attackTime = 40;

                    Vector3d look = this.guardian.getViewVector(1.0F);
                    double dX = target.getX() - (this.guardian.getX() + look.x * 4.0D);
                    double dY = target.getY(0.5D) - (0.5D + this.guardian.getY(0.5D));
                    double dZ = target.getZ() - (this.guardian.getZ() + look.z * 4.0D);

                    CorruptionOrbEntity orb = new CorruptionOrbEntity(w, this.guardian, dX, dY, dZ);

                    orb.setPos(
                            this.guardian.getX() + look.x * 2.0D,
                            this.guardian.getY(0.5D) + 0.5D,
                            this.guardian.getZ() + look.z * 2.0D
                    );

                    w.addFreshEntity(orb);

                    this.guardian.playSound(SoundEvents.BLAZE_SHOOT, 1.0F, 0.8F + w.random.nextFloat() * 0.4F);
                }

                this.guardian.getLookControl().setLookAt(target, 10.0F, 10.0F);
            } else {
                this.guardian.getNavigation().moveTo(target,1.0D);
            }
        }
    }
}
