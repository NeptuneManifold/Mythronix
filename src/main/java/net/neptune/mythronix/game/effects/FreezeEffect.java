package net.neptune.mythronix.game.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.util.DamageSource;

public class FreezeEffect extends Effect {

    public FreezeEffect() {
        super(EffectType.HARMFUL, 0x9ee8e7);
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        if(pLivingEntity.getHealth() >= pLivingEntity.getMaxHealth() / 2){
            pLivingEntity.hurt(DamageSource.MAGIC, 0.5F);
        }
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }
}
