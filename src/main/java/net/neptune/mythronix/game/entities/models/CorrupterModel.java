package net.neptune.mythronix.game.entities.models;

import net.minecraft.util.ResourceLocation;
import net.neptune.mythronix.Main;
import net.neptune.mythronix.game.entities.CorrupterEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class CorrupterModel extends AnimatedGeoModel<CorrupterEntity> {

    private final ResourceLocation model = new ResourceLocation(Main.MODID, "geo/corrupter.geo.json");
    private final ResourceLocation animation = new ResourceLocation(Main.MODID, "animations/corrupter.animations.json");
    private final ResourceLocation texture = new ResourceLocation(Main.MODID, "textures/entity/corrupter.png");

    @Override
    public ResourceLocation getModelLocation(CorrupterEntity object) {
        return model;
    }

    @Override
    public ResourceLocation getTextureLocation(CorrupterEntity object) {
        return texture;
    }

    @Override
    public ResourceLocation getAnimationFileLocation(CorrupterEntity animatable) {
        return animation;
    }
}
