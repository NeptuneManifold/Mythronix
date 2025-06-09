package net.neptune.mythronix.capability;

import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.neptune.mythronix.game.ModCapabilities;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ManaLevelProvider implements ICapabilitySerializable<INBT> {
    private final IManaLevel manaLevel = new ManaLevelImplementation();
    private final LazyOptional<IManaLevel> manaLevelOptional = LazyOptional.of(() -> manaLevel);
    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if(cap == ModCapabilities.MANA_LEVEL){
            return manaLevelOptional.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public INBT serializeNBT() {
        return ModCapabilities.MANA_LEVEL.getStorage().writeNBT(ModCapabilities.MANA_LEVEL, manaLevel, null);
    }

    @Override
    public void deserializeNBT(INBT nbt) {
        ModCapabilities.MANA_LEVEL.getStorage().readNBT(ModCapabilities.MANA_LEVEL, manaLevel, null, nbt);
    }
}
