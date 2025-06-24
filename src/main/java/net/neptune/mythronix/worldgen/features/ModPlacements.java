package net.neptune.mythronix.worldgen.features;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;
import net.neptune.mythronix.Main;

public class ModPlacements {

    public static final Placement<ChanceConfig> ETHERUM_LAKE = register("etherum_lake", new EtherumLake());


    private static <T extends IPlacementConfig, G extends Placement<T>> G register(String key, G placement) {
        placement.setRegistryName(new ResourceLocation(Main.MODID, key));
        ForgeRegistries.DECORATORS.register(placement);
        return placement;
    }
}
