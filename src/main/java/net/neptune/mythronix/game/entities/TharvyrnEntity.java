package net.neptune.mythronix.game.entities;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.pathfinding.FlyingPathNavigator;
import net.minecraft.pathfinding.Path;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.neptune.mythronix.game.entities.fly.TharvyrnFlyingMovementController;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.util.EnumSet;
import java.util.Random;

public class TharvyrnEntity extends FlyingEntity implements IAnimatable, IMob {


    public AnimationFactory factory = GeckoLibUtil.createFactory(this);

    public TharvyrnEntity(EntityType<? extends TharvyrnEntity> type, World world) {
        super(type, world);
        this.moveControl = new TharvyrnFlyingMovementController(this, 10, false);
        this.navigation = new FlyingPathNavigator(this, world);
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10D)
                .add(Attributes.FLYING_SPEED, 1.3D)
                .add(Attributes.ATTACK_DAMAGE, 1.5D)
                .add(Attributes.MOVEMENT_SPEED, 1D);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new FlyingMeleeAttackGoal(this, 1.2D, true));
        this.goalSelector.addGoal(2, new RandomFlyingGoal(this, 1.0D, 10));
        this.goalSelector.addGoal(2, new LeapAtTargetGoal(this, 1));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.goalSelector.addGoal(3,new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(4,new LookRandomlyGoal(this));
    }

    @Override
    protected int getExperienceReward(PlayerEntity player)
    {
        return 3 + this.level.random.nextInt(4);
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        this.playSound(SoundEvents.PLAYER_HURT, 1.0F, 1.7F);
        return null;
    }

    @Override
    public boolean doHurtTarget(Entity target) {
        return super.doHurtTarget(target);
    }

    @Override
    protected SoundEvent getDeathSound() {
        this.playSound(SoundEvents.PLAYER_DEATH, 0.7F, 2.0F);
        return null;
    }

    @Override
    public void tick() {
        super.tick();

        Random rand = new Random();

        int particle = rand.nextInt(1000);

        if(particle == 1){
            this.level.addParticle(
                    ParticleTypes.ENCHANT, true, this.getX(), this.getY(), this.getZ(), 0.1d, 0.1d, 0.1d);
        }
    }

    private <E extends IAnimatable>PlayState predicate(AnimationEvent<E> e){
        if(e.isMoving()){
            e.getController().setAnimation(new AnimationBuilder().addAnimation("animation.tharvyrn.fly", ILoopType.EDefaultLoopTypes.LOOP));
        } else if (!isAlive()){
            e.getController().setAnimation(new AnimationBuilder().addAnimation("animation.tharvyrn.die", ILoopType.EDefaultLoopTypes.HOLD_ON_LAST_FRAME));
        } else {
            e.getController().setAnimation(new AnimationBuilder().addAnimation("animation.tharvyrn.idle", ILoopType.EDefaultLoopTypes.LOOP));
        }

        return PlayState.CONTINUE;
    }
    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "controller", 2, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }



    public class FlyingMeleeAttackGoal extends Goal {
        private final FlyingEntity mob;
        private final double speedModifier;
        private final boolean followEvenIfNotSeen;
        private Path path;
        private int delayCounter;

        public FlyingMeleeAttackGoal(FlyingEntity mob, double speed, boolean followEvenIfNotSeen) {
            this.mob = mob;
            this.speedModifier = speed;
            this.followEvenIfNotSeen = followEvenIfNotSeen;
        }

        @Override
        public boolean canUse() {
            LivingEntity target = this.mob.getTarget();
            if (target == null || !target.isAlive()) {
                return false;
            }
            this.path = this.mob.getNavigation().createPath(target, 0);
            return this.path != null;
        }

        @Override
        public boolean canContinueToUse() {
            LivingEntity target = this.mob.getTarget();
            return target != null && target.isAlive();
        }

        @Override
        public void start() {
            this.mob.getNavigation().moveTo(this.path, this.speedModifier);
        }

        @Override
        public void stop() {
            this.mob.getNavigation().stop();
        }

        @Override
        public void tick() {
            LivingEntity target = this.mob.getTarget();
            if (target == null) return;

            // Toujours regarder la cible
            this.mob.getLookControl().setLookAt(target, 30.0F, 30.0F);

            // Distance au carré
            double distanceSq = this.mob.distanceToSqr(target.getX(), target.getY(), target.getZ());

            // Rafraîchir la navigation toutes les 10 ticks
            if (--this.delayCounter <= 0) {
                this.delayCounter = 10;
                this.mob.getNavigation().moveTo(target, this.speedModifier);
            }

            // Attaque si assez proche
            double attackReach = this.mob.getBbWidth() * 2.0F + target.getBbWidth();
            if (distanceSq <= attackReach * attackReach) {
                this.mob.doHurtTarget(target);
            }
        }
    }

    public class RandomFlyingGoal extends Goal {
        private final FlyingEntity mob;
        private final double speed;
        private final int interval;
        private double x;
        private double y;
        private double z;

        public RandomFlyingGoal(FlyingEntity mob, double speed, int interval) {
            this.mob = mob;
            this.speed = speed;
            this.interval = interval;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        @Override
        public boolean canUse() {
            if (this.mob.getMoveControl().hasWanted()) {
                return false;
            }

            Random random = this.mob.getRandom();
            if (random.nextInt(this.interval) != 0) {
                return false;
            }

            // Position random autour du mob
            double x = this.mob.getX() + (random.nextDouble() * 2 - 1) * 10;
            double y = this.mob.getY() + (random.nextDouble() * 2 - 1) * 5;
            double z = this.mob.getZ() + (random.nextDouble() * 2 - 1) * 10;

            this.x = x;
            this.y = y;
            this.z = z;

            return true;
        }

        @Override
        public boolean canContinueToUse() {
            return !this.mob.getNavigation().isDone();
        }

        @Override
        public void start() {
            this.mob.getMoveControl().setWantedPosition(this.x, this.y, this.z, this.speed);
        }
    }

}
