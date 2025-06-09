package net.neptune.mythronix.worldgen.flower;

import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.neptune.mythronix.worldgen.ModConfiguredFeatures;
import net.neptune.mythronix.worldgen.biomes.ModBiomes;

import java.util.List;
import java.util.function.Supplier;

public class ModFlowerGeneration {
    public static void generateFlowers(final BiomeLoadingEvent e){
        RegistryKey<Biome> key = RegistryKey.create(Registry.BIOME_REGISTRY, e.getName());

        if(e.getName().toString().contains(ModBiomes.MAGIC_FOREST.get().getRegistryName().toString())){
            List<Supplier<ConfiguredFeature<?,?>>> base =
                    e.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION);

            base.add(() -> ModConfiguredFeatures.LYS_FLOWER);
        }
    }
}
