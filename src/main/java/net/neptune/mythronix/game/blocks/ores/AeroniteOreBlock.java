package net.neptune.mythronix.game.blocks.ores;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class AeroniteOreBlock extends Block {
    public AeroniteOreBlock(AbstractBlock.Properties properties) {
        super(properties);
    }

    @Override
    public void playerDestroy(World pLevel, PlayerEntity pPlayer, BlockPos pPos, BlockState pState, @Nullable TileEntity pTe, ItemStack pStack) {
        pPlayer.addEffect(new EffectInstance(Effects.SLOW_FALLING, 200));
        super.playerDestroy(pLevel, pPlayer, pPos, pState, pTe, pStack);
    }
}
