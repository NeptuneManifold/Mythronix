package net.neptune.mythronix.game.blocks.geckolib.models;

import net.minecraft.util.ResourceLocation;
import net.neptune.mythronix.Main;
import net.neptune.mythronix.game.blocks.tile.PurifierTile;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class PurifierModel extends AnimatedGeoModel<PurifierTile> {
    ResourceLocation model = new ResourceLocation(Main.MODID, "geo/purifier.geo.json");
    ResourceLocation texture = new ResourceLocation(Main.MODID, "textures/block/purifier.png");
    ResourceLocation animations = new ResourceLocation(Main.MODID, "animations/purifier.animations.json");
    @Override
    public ResourceLocation getModelLocation(PurifierTile object) {
        return model;
    }

    @Override
    public ResourceLocation getTextureLocation(PurifierTile object) {
        return texture;
    }

    @Override
    public ResourceLocation getAnimationFileLocation(PurifierTile animatable) {
        return animations;
    }
}
