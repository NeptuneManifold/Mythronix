package net.neptune.mythronix.game.blocks.torches;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import net.minecraft.block.TorchBlock;
import net.minecraft.particles.BasicParticleType;
import net.neptune.mythronix.game.particles.ModParticles;

public class CorruptedTorch extends TorchBlock {
    public CorruptedTorch() {
        super(AbstractBlock.Properties.copy(Blocks.TORCH), ModParticles.CORRUPTED_FIRE.get());
    }
}
