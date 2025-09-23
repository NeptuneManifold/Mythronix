package net.neptune.mythronix.game.blocks.deco;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

import java.util.Random;

public class CorrupterAltarBlock extends Block {
    public static final DirectionProperty FACING = HorizontalBlock.FACING;

    public CorrupterAltarBlock() {
        super(AbstractBlock.Properties.of(Material.STONE).strength(2.5f,2.5f)
                .harvestTool(ToolType.PICKAXE).harvestLevel(1).requiresCorrectToolForDrops().noOcclusion().sound(SoundType.STONE).randomTicks());

        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING);
    }

    @Override
    public void animateTick(BlockState pState, World pLevel, BlockPos pPos, Random pRand) {
        super.animateTick(pState, pLevel, pPos, pRand);

        pLevel.addParticle(ParticleTypes.ENCHANT, true, pPos.getX(), pPos.getY(), pPos.getZ(), 10,10,10);
    }
}
