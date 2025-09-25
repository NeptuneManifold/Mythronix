package net.neptune.mythronix.game.blocks.geckolib.models;

import net.minecraft.util.ResourceLocation;
import net.neptune.mythronix.Main;
import net.neptune.mythronix.game.blocks.tile.CorrupterDoorTile;
import net.neptune.mythronix.game.blocks.tile.CorrupterStatueTile;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class CorrupterDoorModel extends AnimatedGeoModel<CorrupterDoorTile> {

    ResourceLocation model = new ResourceLocation(Main.MODID, "geo/corrupter_door.geo.json");
    ResourceLocation texture = new ResourceLocation(Main.MODID, "textures/block/corrupter_door.png");
    ResourceLocation animations = new ResourceLocation(Main.MODID, "animations/corrupter_door.animations.json");
    @Override
    public ResourceLocation getModelLocation(CorrupterDoorTile object) {
        return model;
    }

    @Override
    public ResourceLocation getTextureLocation(CorrupterDoorTile object) {
        return texture;
    }

    @Override
    public ResourceLocation getAnimationFileLocation(CorrupterDoorTile animatable) {
        return animations;
    }
}
