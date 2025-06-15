package net.neptune.mythronix.game.blocks.ores;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.neptune.mythronix.game.effects.ModEffects;

import javax.annotation.Nullable;

public class CryoniteOreBlock extends Block {
    public CryoniteOreBlock(AbstractBlock.Properties properties) {
        super(properties);
    }

    @Override
    public void playerDestroy(World pLevel, PlayerEntity pPlayer, BlockPos pPos, BlockState pState, @Nullable TileEntity pTe, ItemStack pStack) {
        pPlayer.addEffect(new EffectInstance(ModEffects.FREEZE.get(), 300));
        super.playerDestroy(pLevel, pPlayer, pPos, pState, pTe, pStack);
    }
}
