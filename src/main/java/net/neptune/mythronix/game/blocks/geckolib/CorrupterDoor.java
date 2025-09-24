package net.neptune.mythronix.game.blocks.geckolib;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.neptune.mythronix.game.blocks.tile.CorrupterStatueTile;
import net.neptune.mythronix.game.blocks.tile.ModTileEntities;
import net.neptune.mythronix.game.items.ModItems;

import javax.annotation.Nullable;

public class CorrupterDoor extends Block {
    public CorrupterDoor() {
        super(AbstractBlock.Properties.of(Material.STONE).sound(SoundType.STONE).strength(-1f, 1600000f).noDrops().noOcclusion());
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return ModTileEntities.CORRUPTER_STATUE_TILE.get().create();
    }

    @Override
    public BlockRenderType getRenderShape(BlockState p_149645_1_) {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }

    @Override
    public ActionResultType use(BlockState pState, World pLevel, BlockPos pPos, PlayerEntity pPlayer, Hand pHand, BlockRayTraceResult pHit) {
        if (!pLevel.isClientSide) {
            TileEntity tileEntity = pLevel.getBlockEntity(pPos);

            if (tileEntity instanceof CorrupterStatueTile) {
                if (pPlayer.getItemInHand(pHand).getItem().equals(ModItems.CORRUPTER_HEART.get())) {

                    ((CorrupterStatueTile) tileEntity).setGoodItem(true);
                    tileEntity.setChanged();
                }
            } else {
                throw new IllegalStateException("Blem avec la statue dev de merde");
            }
        }
        return ActionResultType.SUCCESS;
    }
}
