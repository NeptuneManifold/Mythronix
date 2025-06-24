package net.neptune.mythronix.game.entities.models;

import net.minecraft.util.ResourceLocation;
import net.neptune.mythronix.Main;
import net.neptune.mythronix.game.entities.BorealDeerEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class BorealDeerModel extends AnimatedGeoModel<BorealDeerEntity> {
    private final ResourceLocation model = new ResourceLocation(Main.MODID, "geo/boreal_deer.geo.json");
    private final ResourceLocation animation = new ResourceLocation(Main.MODID, "animations/deer.animations.json");
    private final ResourceLocation texture = new ResourceLocation(Main.MODID, "textures/entity/boreal_deer.png");


    @Override
    public ResourceLocation getModelLocation(BorealDeerEntity object) {
        return model;
    }

    @Override
    public ResourceLocation getTextureLocation(BorealDeerEntity object) {
        return texture;
    }

    @Override
    public ResourceLocation getAnimationFileLocation(BorealDeerEntity animatable) {
        return animation;
    }
}
