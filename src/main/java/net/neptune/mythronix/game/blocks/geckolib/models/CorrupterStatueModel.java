package net.neptune.mythronix.game.blocks.geckolib.models;

import net.minecraft.util.ResourceLocation;
import net.neptune.mythronix.Main;
import net.neptune.mythronix.game.blocks.tile.CorrupterStatueTile;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class CorrupterStatueModel extends AnimatedGeoModel<CorrupterStatueTile> {

    ResourceLocation model = new ResourceLocation(Main.MODID, "geo/corrupter_statue.geo.json");
    ResourceLocation texture = new ResourceLocation(Main.MODID, "textures/block/corrupter_statue.png");
    ResourceLocation animations = new ResourceLocation(Main.MODID, "animations/corrupter_statue.animations.json");
    @Override
    public ResourceLocation getModelLocation(CorrupterStatueTile object) {
        return model;
    }

    @Override
    public ResourceLocation getTextureLocation(CorrupterStatueTile object) {
        return texture;
    }

    @Override
    public ResourceLocation getAnimationFileLocation(CorrupterStatueTile animatable) {
        return animations;
    }
}
