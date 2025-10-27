package net.neptune.mythronix.game.entities.shoot;

import net.minecraft.entity.*;
import net.minecraft.entity.projectile.AbstractFireballEntity;
import net.minecraft.entity.projectile.DamagingProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.network.NetworkHooks;
import net.neptune.mythronix.game.effects.ModEffects;
import net.neptune.mythronix.game.entities.ModEntityTypes;
import net.neptune.mythronix.game.items.ModItems;

public class CorruptionOrbEntity extends DamagingProjectileEntity implements IRendersAsItem {

    @Override
    public ItemStack getItem() {
        return new ItemStack(ModItems.CORRUPTION_ORB_ITEM.get());
    }

    public CorruptionOrbEntity(FMLPlayMessages.SpawnEntity packet, World world) {
        this(ModEntityTypes.CORRUPTION_ORB.get(), world);
    }

    public CorruptionOrbEntity(EntityType<CorruptionOrbEntity> type, World level) {
        super(type, level);
    }

    public CorruptionOrbEntity(World level, LivingEntity shooter, double accelX, double accelY, double accelZ) {
        this(ModEntityTypes.CORRUPTION_ORB.get(), level);
        this.setOwner(shooter);
        this.shoot(accelX, accelY, accelZ, 1.5F, 1.0F);
    }


    @Override
    protected void onHitBlock(BlockRayTraceResult p_230299_1_) {
        super.onHitBlock(p_230299_1_);
        if(!this.level.isClientSide) {
            this.remove();
        }
    }

    @Override
    protected void onHitEntity(EntityRayTraceResult result) {
        super.onHitEntity(result);

        if(!this.level.isClientSide) {
            if(result.getEntity() instanceof LivingEntity) {

                LivingEntity living = (LivingEntity) result.getEntity();
                living.hurt(new DamageSource("corruption"), 1f);
                living.addEffect(new EffectInstance(ModEffects.CORRUPTION.get(), 60,0));
            }
            this.remove();
        }
    }

    @Override
    protected boolean shouldBurn() {
        return false;
    }

    @Override
    protected void defineSynchedData() {
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
