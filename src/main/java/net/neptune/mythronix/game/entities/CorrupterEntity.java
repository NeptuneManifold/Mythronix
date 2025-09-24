package net.neptune.mythronix.game.entities;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.BossInfo;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerBossInfo;
import net.minecraftforge.common.ToolType;
import net.neptune.mythronix.game.effects.ModEffects;
import net.neptune.mythronix.game.items.ModItems;
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

public class CorrupterEntity extends MonsterEntity implements IMob, IAnimatable {

    private AnimationFactory factory = GeckoLibUtil.createFactory(this);
    private static final DataParameter<Boolean> DEATH_ANIM =
            EntityDataManager.defineId(CorrupterEntity.class, DataSerializers.BOOLEAN);
    private static final int ATTACK_ANIM_TICKS = 20; // durée anim en ticks
    private static final DataParameter<Integer> ATTACK_TIMER =
            EntityDataManager.defineId(CorrupterEntity.class, DataSerializers.INT);

    public CorrupterEntity(EntityType<? extends MonsterEntity> entity, World world) {
        super(entity, world);
        this.setPersistenceRequired();
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false; // jamais de despawn à cause de la distance
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes(){
        return MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 150D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 2D)
                .add(Attributes.ATTACK_DAMAGE, 10D);
    }

    private final ServerBossInfo bossInfo = new ServerBossInfo(
            new TranslationTextComponent("entity.mt.corrupter"), // texte affiché
            BossInfo.Color.PURPLE, // couleur de la barre
            BossInfo.Overlay.PROGRESS // style (PROGRESS, NOTCHED_10, NOTCHED_12, etc.)
    );

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (source.getEntity() instanceof PlayerEntity) {
            PlayerEntity p = (PlayerEntity) source.getEntity();

            if(!p.getItemInHand(Hand.MAIN_HAND).getItem().equals(ModItems.SWORD_OF_LIFE.get())){
                if(p.getItemInHand(Hand.MAIN_HAND).getItem() instanceof HoeItem && ! p.getItemInHand(Hand.MAIN_HAND).getItem().equals(ModItems.CORRUPTED_HOE.get())){

                    ItemStack corropAxe = new ItemStack(ModItems.CORRUPTED_HOE.get(), 1);

                    corropAxe.getOrCreateTag().put("oldItem", p.getItemInHand(Hand.MAIN_HAND).getStack().serializeNBT());

                    p.setItemInHand(Hand.MAIN_HAND, corropAxe);

                } else if(p.getItemInHand(Hand.MAIN_HAND).getItem() instanceof PickaxeItem && ! p.getItemInHand(Hand.MAIN_HAND).getItem().equals(ModItems.CORRUPTED_PICKAXE.get())){

                    ItemStack corropAxe = new ItemStack(ModItems.CORRUPTED_PICKAXE.get(), 1);

                    corropAxe.getOrCreateTag().put("oldItem", p.getItemInHand(Hand.MAIN_HAND).getStack().serializeNBT());

                    p.setItemInHand(Hand.MAIN_HAND, corropAxe);

                } else if(p.getItemInHand(Hand.MAIN_HAND).getItem() instanceof ShovelItem && ! p.getItemInHand(Hand.MAIN_HAND).getItem().equals(ModItems.CORRUPTED_SHOVEL.get())){

                    ItemStack corropAxe = new ItemStack(ModItems.CORRUPTED_SHOVEL.get(), 1);

                    corropAxe.getOrCreateTag().put("oldItem", p.getItemInHand(Hand.MAIN_HAND).getStack().serializeNBT());

                    p.setItemInHand(Hand.MAIN_HAND, corropAxe);

                } else if(p.getItemInHand(Hand.MAIN_HAND).getItem() instanceof AxeItem && ! p.getItemInHand(Hand.MAIN_HAND).getItem().equals(ModItems.CORRUPTED_AXE.get())){

                    ItemStack corropAxe = new ItemStack(ModItems.CORRUPTED_AXE.get(), 1);

                    corropAxe.getOrCreateTag().put("oldItem", p.getItemInHand(Hand.MAIN_HAND).getStack().serializeNBT());

                    p.setItemInHand(Hand.MAIN_HAND, corropAxe);

                } else if(p.getItemInHand(Hand.MAIN_HAND).getItem() instanceof SwordItem && ! p.getItemInHand(Hand.MAIN_HAND).getItem().equals(ModItems.CORRUPTED_SWORD.get())){

                    ItemStack corropSword = new ItemStack(ModItems.CORRUPTED_SWORD.get(), 1);

                    corropSword.getOrCreateTag().put("oldItem", p.getItemInHand(Hand.MAIN_HAND).getStack().serializeNBT());

                    p.setItemInHand(Hand.MAIN_HAND, corropSword);
                } else if(p.getItemInHand(Hand.MAIN_HAND).isEmpty()){
                    p.addEffect(new EffectInstance(ModEffects.CORRUPTION.get(), 60,1));
                }
            }

        }

        return super.hurt(source, amount);
    }

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
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(2, new MoveTowardsTargetGoal(this, 0.9D, 20.0F));
        this.goalSelector.addGoal(3, new LookAtGoal(this, PlayerEntity.class, 10.0F));
        this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        super.registerGoals();
    }

    @Override
    protected int getExperienceReward(PlayerEntity pPlayer) {
        return 30 + this.level.random.nextInt(40);
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.SKELETON_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.SKELETON_DEATH;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.SKELETON_AMBIENT;
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

    @Override
    protected void playStepSound(BlockPos pPos, BlockState pBlock) {
        this.playSound(SoundEvents.SKELETON_STEP, 0.02F, 0.5F);
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "controller",2, this::predicate));
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> e) {

        int timer = this.getAttackTimer();
        if (timer > 0) {
            if (timer == ATTACK_ANIM_TICKS) {
                e.getController().setAnimation(new AnimationBuilder().addAnimation("animation.corrupter.attack", ILoopType.EDefaultLoopTypes.PLAY_ONCE));
            }
            return PlayState.CONTINUE;
        } else if(e.isMoving()){
            e.getController().setAnimation(new AnimationBuilder().addAnimation("animation.corrupter.walk", ILoopType.EDefaultLoopTypes.LOOP));
        }  else if(this.isPlayingDeath()) {
            e.getController().setAnimation(new AnimationBuilder().addAnimation("animation.corrupter.die", ILoopType.EDefaultLoopTypes.LOOP));
        } else {
            e.getController().setAnimation(new AnimationBuilder().addAnimation("animation.corrupter.idle", ILoopType.EDefaultLoopTypes.LOOP));
        }

        return PlayState.CONTINUE;
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }
}
