package net.neptune.mythronix.capability;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class ManaLevelStorage implements Capability.IStorage<IManaLevel>{
    @Nullable
    @Override
    public INBT writeNBT(Capability<IManaLevel> capability, IManaLevel instance, Direction side) {
        CompoundNBT tag = new CompoundNBT();

        tag.putInt("manaLevel", instance.getManaLevel());
        tag.putInt("manaXp", instance.getManaXp());

        return tag;
    }

    @Override
    public void readNBT(Capability<IManaLevel> capability, IManaLevel instance, Direction side, INBT nbt) {
        CompoundNBT tag = (CompoundNBT) nbt;

        instance.setManaLevel(tag.getInt("manaLevel"));
        instance.setManaXp(tag.getInt("manaXP"));
    }
}
