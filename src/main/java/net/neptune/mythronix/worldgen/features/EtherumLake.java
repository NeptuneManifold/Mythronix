package net.neptune.mythronix.worldgen.features;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.WorldDecoratingHelper;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.Placement;

import java.util.Random;
import java.util.stream.Stream;

public class EtherumLake extends Placement<ChanceConfig> {
    public EtherumLake() {
        super(ChanceConfig.CODEC);
    }

    @Override
    public Stream<BlockPos> getPositions(WorldDecoratingHelper pHelper, Random pRandom, ChanceConfig pConfig, BlockPos pPos) {
        if (pRandom.nextInt(pConfig.chance) == 0) {
            int i = pRandom.nextInt(16) + pPos.getX();
            int j = pRandom.nextInt(16) + pPos.getZ();
            int k = pRandom.nextInt(pHelper.getGenDepth());
            return Stream.of(new BlockPos(i, k, j));
        } else {
            return Stream.empty();
        }
    }
}
