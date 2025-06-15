package net.neptune.mythronix.worldgen.features;

import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Features;
import net.neptune.mythronix.worldgen.ModConfiguredFeatures;

public class ModBiomeFeatures {

    public static void addManaStoneBlock(BiomeGenerationSettings.Builder pBuilder) {
        pBuilder.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, ModConfiguredFeatures.MANA_ROCK);
    }

}
