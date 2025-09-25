package net.neptune.mythronix.game.blocks.geckolib.models;

import net.minecraft.util.ResourceLocation;
import net.neptune.mythronix.Main;
import net.neptune.mythronix.game.blocks.tile.UnCorrupterTile;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class UnCorrupterModel extends AnimatedGeoModel<UnCorrupterTile> {

    ResourceLocation model = new ResourceLocation(Main.MODID, "geo/uncorrupter_catalyser.geo.json");
    ResourceLocation texture = new ResourceLocation(Main.MODID, "textures/block/uncorrupter_catalyser.png");
    ResourceLocation animations = new ResourceLocation(Main.MODID, "animations/uncorrupter_catalyser.animations.json");

    @Override
    public ResourceLocation getModelLocation(UnCorrupterTile object) {
        return model;
    }

    @Override
    public ResourceLocation getTextureLocation(UnCorrupterTile object) {
        return texture;
    }

    @Override
    public ResourceLocation getAnimationFileLocation(UnCorrupterTile animatable) {
        return animations;
    }
}
