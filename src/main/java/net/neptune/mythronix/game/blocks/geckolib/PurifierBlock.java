package net.neptune.mythronix.game.blocks.geckolib;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.network.NetworkHooks;
import net.neptune.mythronix.game.blocks.tile.ModTileEntities;
import net.neptune.mythronix.game.blocks.tile.PurifierTile;
import net.neptune.mythronix.menus.containers.PurifierContainer;

import javax.annotation.Nullable;
import java.util.stream.Stream;

public class PurifierBlock extends Block {
    public PurifierBlock() {
        super(Properties.of(Material.METAL).noOcclusion()
                .sound(SoundType.METAL).strength(2.5F,2.5F)
                .harvestTool(ToolType.PICKAXE).harvestLevel(0));
    }

    @Override
    public ActionResultType use(BlockState pState, World pLevel, BlockPos pPos, PlayerEntity pPlayer, Hand pHand, BlockRayTraceResult pHit) {
        if(!pLevel.isClientSide) {
            TileEntity tileEntity = pLevel.getBlockEntity(pPos);

            if(tileEntity instanceof PurifierTile){
                INamedContainerProvider containerProvider = createContainerProvider(pLevel, pPos);

                NetworkHooks.openGui(((ServerPlayerEntity) pPlayer), containerProvider, tileEntity.getBlockPos());
            } else {
                throw new IllegalStateException("Blem avec le container fdp");
            }
        }
        return ActionResultType.SUCCESS;
    }

    private INamedContainerProvider createContainerProvider(World worldIn, BlockPos pos) {
        return new INamedContainerProvider() {
            @Override
            public ITextComponent getDisplayName() {
                return new TranslationTextComponent("screen.mt.purifier");
            }

            @Nullable
            @Override
            public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
                return new PurifierContainer(i, worldIn, pos, playerInventory, playerEntity);
            }
        };
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return ModTileEntities.PURIFIER_TILE.get().create();
    }

    @Override
    public BlockRenderType getRenderShape(BlockState p_149645_1_) {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }

    @Override
    public VoxelShape getShape(BlockState pState, IBlockReader pLevel, BlockPos pPos, ISelectionContext pContext) {
        return Stream.of(
                Stream.of(
                        Block.box(12, -1, 11, 14, 8, 13),
                        Block.box(2, -1, 7, 4, 8, 9),
                        Block.box(11, -1, 3, 13, 8, 5)
                ).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get(),
        Stream.of(
                Block.box(6.4087, 5.1, 0, 9.5913, 7.1, 16),
                Block.box(6.4087, 5.1, 0, 9.5913, 7.1, 16),
                Block.box(6.4087, 5.1, 0, 9.5913, 7.1, 16),
                Block.box(6.4087, 5.1, 0, 9.5913, 7.1, 16),
                Block.box(6.4087, 5.1, 0, 9.5913, 7.1, 16),
                Block.box(0, 5.1, 6.4087, 16, 7.1, 9.5913),
                Block.box(0, 5.1, 6.4087, 16, 7.1, 9.5913),
                Block.box(0, 5.1, 6.4087, 16, 7.1, 9.5913)
        ).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get(),
        Stream.of(
                Block.box(6.24063, 8.1, 1, 9.0418, 9.8603, 15.08243),
                Block.box(6.24063, 8.1, 1, 9.0418, 9.8603, 15.08243),
                Block.box(6.24063, 8.1, 1, 9.0418, 9.8603, 15.08243),
                Block.box(6.24063, 8.1, 1, 9.0418, 9.8603, 15.08243),
                Block.box(6.24063, 8.1, 1, 9.0418, 9.8603, 15.08243),
                Block.box(0.6, 8.1, 6.64063, 14.68243, 9.8603, 9.4418),
                Block.box(0.6, 8.1, 6.64063, 14.68243, 9.8603, 9.4418),
                Block.box(0.6, 8.1, 6.64063, 14.68243, 9.8603, 9.4418)
        ).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get(),
        Stream.of(
                Block.box(6.4087, 7, 0, 9.5913, 11, 1),
                Block.box(6.4087, 7, 0, 9.5913, 11, 1),
                Block.box(6.4087, 7, 0, 9.5913, 11, 1),
                Block.box(6.4087, 7, 0, 9.5913, 11, 1),
                Block.box(6.4087, 7, 0, 9.5913, 11, 1),
                Block.box(6.4087, 7, 15, 9.5913, 11, 16),
                Block.box(6.4087, 7, 15, 9.5913, 11, 16),
                Block.box(6.4087, 7, 15, 9.5913, 11, 16),
                Block.box(6.4087, 7, 15, 9.5913, 11, 16),
                Block.box(6.4087, 7, 15, 9.5913, 11, 16),
                Block.box(0, 7, 6.4087, 1, 11, 9.5913),
                Block.box(0, 7, 6.4087, 1, 11, 9.5913),
                Block.box(0, 7, 6.4087, 1, 11, 9.5913),
                Block.box(15, 7, 6.4087, 16, 11, 9.5913),
                Block.box(15, 7, 6.4087, 16, 11, 9.5913),
                Block.box(15, 7, 6.4087, 16, 11, 9.5913)
        ).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get()
).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();
    }
}
