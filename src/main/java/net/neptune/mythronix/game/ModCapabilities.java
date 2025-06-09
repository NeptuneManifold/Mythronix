package net.neptune.mythronix.game;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.neptune.mythronix.Main;
import net.neptune.mythronix.capability.IManaLevel;
import net.neptune.mythronix.capability.ManaLevelImplementation;
import net.neptune.mythronix.capability.ManaLevelStorage;

public class ModCapabilities {
    @CapabilityInject(IManaLevel.class)
    public static Capability<IManaLevel> MANA_LEVEL = null;
    public static final ResourceLocation MANA_LEVEL_ID = new ResourceLocation(Main.MODID, "mana_level");

    public static void register(){
        CapabilityManager.INSTANCE.register(IManaLevel.class, new ManaLevelStorage(), ManaLevelImplementation::new);
    }
}
