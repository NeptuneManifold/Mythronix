package net.neptune.mythronix.game.blocks;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PyrrothiteOreBlock extends Block {
    public PyrrothiteOreBlock(Properties prop) {
        super(prop);
    }

    @Override
    public void stepOn(World w, BlockPos pos, Entity entity) {
        entity.hurt(DamageSource.ON_FIRE, 1);
        super.stepOn(w, pos, entity);
    }
}
