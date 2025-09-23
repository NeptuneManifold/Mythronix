package net.neptune.mythronix.game.trees;

import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.neptune.mythronix.worldgen.ModConfiguredFeatures;

import javax.annotation.Nullable;
import java.util.Random;

public class CorruptedTree extends Tree {
    @Nullable
    @Override
    protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getConfiguredFeature(Random pRandom, boolean pLargeHive) {
        return ModConfiguredFeatures.CORRUPTED_TREE;
    }
}
