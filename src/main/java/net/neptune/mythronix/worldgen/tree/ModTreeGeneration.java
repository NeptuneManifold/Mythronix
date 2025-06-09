package net.neptune.mythronix.worldgen.tree;

import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.neptune.mythronix.worldgen.ModConfiguredFeatures;
import net.neptune.mythronix.worldgen.biomes.ModBiomes;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

public class ModTreeGeneration {
    public static void generateTrees(final BiomeLoadingEvent e){
        RegistryKey<Biome> key = RegistryKey.create(Registry.BIOME_REGISTRY, e.getName());
        Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(key);

        if(e.getName().toString().contains(ModBiomes.MAGIC_FOREST.get().getRegistryName().toString())){

            List<Supplier<ConfiguredFeature<?,?>>> base =
                    e.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION);

            base.add(() -> ModConfiguredFeatures.ARCANE_TREE
                    .decorated(Features.Placements.HEIGHTMAP_SQUARE)
                    .decorated(Placement.COUNT_EXTRA.configured(
                            new AtSurfaceWithExtraConfig(1,0.25F,2))));

            base.add(() -> ModConfiguredFeatures.FIRE_TREE
                    .decorated(Features.Placements.HEIGHTMAP_SQUARE)
                    .decorated(Placement.COUNT_EXTRA.configured(
                            new AtSurfaceWithExtraConfig(1,0.25F,2))));
            base.add(() -> ModConfiguredFeatures.WATER_TREE
                    .decorated(Features.Placements.HEIGHTMAP_SQUARE)
                    .decorated(Placement.COUNT_EXTRA.configured(
                            new AtSurfaceWithExtraConfig(1,0.25F,2))));
            base.add(() -> ModConfiguredFeatures.WIND_TREE
                    .decorated(Features.Placements.HEIGHTMAP_SQUARE)
                    .decorated(Placement.COUNT_EXTRA.configured(
                            new AtSurfaceWithExtraConfig(1,0.25F,2))));
            base.add(() -> ModConfiguredFeatures.NATURE_TREE
                    .decorated(Features.Placements.HEIGHTMAP_SQUARE)
                    .decorated(Placement.COUNT_EXTRA.configured(
                            new AtSurfaceWithExtraConfig(1,0.25F,2))));
            base.add(() -> ModConfiguredFeatures.ICE_TREE
                    .decorated(Features.Placements.HEIGHTMAP_SQUARE)
                    .decorated(Placement.COUNT_EXTRA.configured(
                            new AtSurfaceWithExtraConfig(1,0.25F,2))));
        }
    }
}
