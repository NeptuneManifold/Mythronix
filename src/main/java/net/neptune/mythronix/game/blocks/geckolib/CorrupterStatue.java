package net.neptune.mythronix.game.blocks.geckolib;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import net.neptune.mythronix.game.blocks.tile.CorrupterStatueTile;
import net.neptune.mythronix.game.blocks.tile.ModTileEntities;
import net.neptune.mythronix.game.blocks.tile.PurifierTile;
import net.neptune.mythronix.game.items.ModItems;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

import javax.annotation.Nullable;

public class CorrupterStatue extends Block {

    public CorrupterStatue() {
        super(AbstractBlock.Properties.of(Material.STONE).strength(-1, 1600000).noOcclusion().noDrops().sound(SoundType.STONE));
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
        if(!pLevel.isClientSide) {
            TileEntity tileEntity = pLevel.getBlockEntity(pPos);

            System.out.println(tileEntity);

            if(tileEntity instanceof CorrupterStatueTile){
                if(pPlayer.getItemInHand(pHand).getItem().equals(ModItems.CORRUPTER_HEART.get())){
                    System.out.println("c ok");

                    CorrupterStatueTile.goodItem = true;
                }
            } else {
                throw new IllegalStateException("Blem avec la statue dev de merde");
            }
        }
        return ActionResultType.SUCCESS;
    }

    @Override
    public VoxelShape getShape(BlockState pState, IBlockReader pLevel, BlockPos pPos, ISelectionContext pContext) {
        VoxelShape shape = VoxelShapes.empty();

        shape = VoxelShapes.or(shape,
                Block.box(1.0D, 36.0D, 1.0D, 13.0D, 40.0D, 13.0D).move(0, -36/16.0, 0));
        shape = VoxelShapes.or(shape,
                Block.box(0.0D, 24.0D, 0.0D, 14.0D, 36.0D, 12.0D).move(0, -24/16.0, 0));
        shape = VoxelShapes.or(shape,
                Block.box(1.0D, 14.0D, 1.0D, 13.0D, 24.0D, 13.0D).move(0, -14/16.0, 0));
        shape = VoxelShapes.or(shape,
                Block.box(0.0D, 13.0D, 0.0D, 14.0D, 27.0D, 14.0D).move(0, -13/16.0, 0));
        shape = VoxelShapes.or(shape,
                Block.box(14.0D, 18.0D, 2.0D, 20.0D, 32.0D, 12.0D).move(0, -18/16.0, 0));
        shape = VoxelShapes.or(shape,
                Block.box(-4.0D, 18.0D, 2.0D, 2.0D, 32.0D, 12.0D).move(0, -18/16.0, 0));
        shape = VoxelShapes.or(shape,
                Block.box(14.0D, 32.0D, 4.0D, 18.0D, 42.0D, 10.0D).move(0, -32/16.0, 0));
        shape = VoxelShapes.or(shape,
                Block.box(-2.0D, 32.0D, 4.0D, 2.0D, 42.0D, 10.0D).move(0, -32/16.0, 0));
        shape = VoxelShapes.or(shape,
                Block.box(14.0D, 42.0D, 3.0D, 18.0D, 48.0D, 11.0D).move(0, -42/16.0, 0));
        shape = VoxelShapes.or(shape,
                Block.box(-2.0D, 42.0D, 3.0D, 2.0D, 48.0D, 11.0D).move(0, -42/16.0, 0));

        return shape;
    }
}
