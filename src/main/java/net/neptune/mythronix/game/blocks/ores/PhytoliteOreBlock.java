package net.neptune.mythronix.game.blocks.ores;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class PhytoliteOreBlock extends Block {
    public PhytoliteOreBlock(AbstractBlock.Properties properties) {
        super(properties);
    }

    @Override
    public void playerDestroy(World pLevel, PlayerEntity pPlayer, BlockPos pPos, BlockState pState, @Nullable TileEntity pTe, ItemStack pStack) {
        pLevel.setBlock(pPos, Blocks.OAK_LEAVES.defaultBlockState(), 32);
        pLevel.addParticle(ParticleTypes.COMPOSTER, pPos.getX(), pPos.getY(), pPos.getZ(), 1,1,1);
        super.destroy(pLevel, pPos, pState);
    }
}
