package net.neptune.mythronix.worldgen;

import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.DarkOakFoliagePlacer;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.trunkplacer.DarkOakTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;
import net.neptune.mythronix.game.blocks.ModBlocks;
import net.neptune.mythronix.game.fluids.ModFluids;
import net.neptune.mythronix.worldgen.features.ModPlacements;

import java.util.OptionalInt;

public class ModConfiguredFeatures {
    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> FIRE_TREE =
            register("fire_tree", Feature.TREE.configured((
                    new BaseTreeFeatureConfig.Builder(
                            new SimpleBlockStateProvider(ModBlocks.FIRE_LOG.get().defaultBlockState()),
                            new SimpleBlockStateProvider(ModBlocks.FIRE_LEAVES.get().defaultBlockState()),
                            new DarkOakFoliagePlacer(FeatureSpread.fixed(1), FeatureSpread.fixed(1)),
                            new DarkOakTrunkPlacer(6,2,1),
                            new ThreeLayerFeature(1,1,0,1,2, OptionalInt.empty())))
                    .maxWaterDepth(Integer.MAX_VALUE).heightmap(Heightmap.Type.MOTION_BLOCKING)
                    .ignoreVines().build()
            ));
    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> CORRUPTED_TREE =
            register("corrupted_tree", Feature.TREE.configured((
                    new BaseTreeFeatureConfig.Builder(
                            new SimpleBlockStateProvider(ModBlocks.CORRUPTED_LOG.get().defaultBlockState()),
                            new SimpleBlockStateProvider(ModBlocks.CORRUPTED_LEAVES.get().defaultBlockState()),
                            new BlobFoliagePlacer(FeatureSpread.fixed(3), FeatureSpread.fixed(2), 3),
                            new StraightTrunkPlacer(3,2,1),
                            new TwoLayerFeature(1,1,1, OptionalInt.empty())))
                    .maxWaterDepth(Integer.MAX_VALUE).heightmap(Heightmap.Type.MOTION_BLOCKING)
                    .ignoreVines().build()
            ));
    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> WATER_TREE =
            register("water_tree", Feature.TREE.configured((
                    new BaseTreeFeatureConfig.Builder(
                            new SimpleBlockStateProvider(ModBlocks.WATER_LOG.get().defaultBlockState()),
                            new SimpleBlockStateProvider(ModBlocks.WATER_LEAVES.get().defaultBlockState()),
                            new DarkOakFoliagePlacer(FeatureSpread.fixed(1), FeatureSpread.fixed(1)),
                            new DarkOakTrunkPlacer(6,2,1),
                            new ThreeLayerFeature(1,1,0,1,2, OptionalInt.empty())))
                    .maxWaterDepth(Integer.MAX_VALUE).heightmap(Heightmap.Type.MOTION_BLOCKING)
                    .ignoreVines().build()
            ));
    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> WIND_TREE =
            register("wind_tree", Feature.TREE.configured((
                    new BaseTreeFeatureConfig.Builder(
                            new SimpleBlockStateProvider(ModBlocks.WIND_LOG.get().defaultBlockState()),
                            new SimpleBlockStateProvider(ModBlocks.WIND_LEAVES.get().defaultBlockState()),
                            new DarkOakFoliagePlacer(FeatureSpread.fixed(1), FeatureSpread.fixed(1)),
                            new DarkOakTrunkPlacer(6,2,1),
                            new ThreeLayerFeature(1,1,0,1,2, OptionalInt.empty())))
                    .maxWaterDepth(Integer.MAX_VALUE).heightmap(Heightmap.Type.MOTION_BLOCKING)
                    .ignoreVines().build()
            ));
    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> ARCANE_TREE =
            register("arcane_tree", Feature.TREE.configured((
                    new BaseTreeFeatureConfig.Builder(
                            new SimpleBlockStateProvider(ModBlocks.ARCANE_LOG.get().defaultBlockState()),
                            new SimpleBlockStateProvider(ModBlocks.ARCANE_LEAVES.get().defaultBlockState()),
                            new DarkOakFoliagePlacer(FeatureSpread.fixed(1), FeatureSpread.fixed(1)),
                            new DarkOakTrunkPlacer(6,2,1),
                            new ThreeLayerFeature(1,1,0,1,2, OptionalInt.empty())))
                    .maxWaterDepth(Integer.MAX_VALUE).heightmap(Heightmap.Type.MOTION_BLOCKING)
                    .ignoreVines().build()
            ));
    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> NATURE_TREE =
            register("nature_tree", Feature.TREE.configured((
                    new BaseTreeFeatureConfig.Builder(
                            new SimpleBlockStateProvider(ModBlocks.NATURE_LOG.get().defaultBlockState()),
                            new SimpleBlockStateProvider(ModBlocks.NATURE_LEAVES.get().defaultBlockState()),
                            new DarkOakFoliagePlacer(FeatureSpread.fixed(1), FeatureSpread.fixed(1)),
                            new DarkOakTrunkPlacer(6,2,1),
                            new ThreeLayerFeature(1,1,0,1,2, OptionalInt.empty())))
                    .maxWaterDepth(Integer.MAX_VALUE).heightmap(Heightmap.Type.MOTION_BLOCKING)
                    .ignoreVines().build()
            ));
    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> ICE_TREE =
            register("ice_tree", Feature.TREE.configured((
                    new BaseTreeFeatureConfig.Builder(
                            new SimpleBlockStateProvider(ModBlocks.ICE_LOG.get().defaultBlockState()),
                            new SimpleBlockStateProvider(ModBlocks.ICE_LEAVES.get().defaultBlockState()),
                            new DarkOakFoliagePlacer(FeatureSpread.fixed(1), FeatureSpread.fixed(1)),
                            new DarkOakTrunkPlacer(6,2,1),
                            new ThreeLayerFeature(1,1,0,1,2, OptionalInt.empty())))
                    .maxWaterDepth(Integer.MAX_VALUE).heightmap(Heightmap.Type.MOTION_BLOCKING)
                    .ignoreVines().build()
            ));

    public static final ConfiguredFeature<?,?> LYS_FLOWER = Feature.FLOWER.configured((
            new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(ModBlocks.LYS_FLOWER.get().defaultBlockState()),
                    SimpleBlockPlacer.INSTANCE)).tries(6).build())
            .decorated(Features.Placements.HEIGHTMAP_SQUARE).count(3);

    public static final ConfiguredFeature<?, ?> MANA_ROCK =
            register("mana_rock", Feature.FOREST_ROCK.configured(new
                    BlockStateFeatureConfig(ModBlocks.MANA_STONE.get().defaultBlockState()))
                    .decorated(Features.Placements.HEIGHTMAP_SQUARE).count(1));

    public static final ConfiguredFeature<?, ?> LAKE_ETHERUM =
            register("lake_etherum", Feature.LAKE.configured(new BlockStateFeatureConfig(ModFluids.ETHERUM_BLOCK.get().defaultBlockState()))
                    .decorated(ModPlacements.ETHERUM_LAKE.configured(new ChanceConfig(10))));

    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String key, ConfiguredFeature<FC, ?> feature){
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, key, feature);
    }
}