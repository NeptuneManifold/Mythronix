package net.neptune.mythronix.worldgen.features;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.GenerationStage;
import net.neptune.mythronix.game.entities.ModEntityTypes;
import net.neptune.mythronix.worldgen.ModConfiguredFeatures;

public class ModBiomeFeatures {

    public static void addManaStoneBlock(BiomeGenerationSettings.Builder pBuilder) {
        pBuilder.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, ModConfiguredFeatures.MANA_ROCK);
    }

    public static void addEtherumLakes(BiomeGenerationSettings.Builder pBuilder) {
        pBuilder.addFeature(GenerationStage.Decoration.LAKES, ModConfiguredFeatures.LAKE_ETHERUM);
    }

    public static void spiritPlainsSpawn(MobSpawnInfo.Builder builder) {
        builder.addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(ModEntityTypes.BOREAL_DEER.get(), 40, 2, 4));
    }

}
