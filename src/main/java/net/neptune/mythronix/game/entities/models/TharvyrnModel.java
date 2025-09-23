package net.neptune.mythronix.game.entities.models;

import net.minecraft.util.ResourceLocation;
import net.neptune.mythronix.Main;
import net.neptune.mythronix.game.entities.TharvyrnEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class TharvyrnModel extends AnimatedGeoModel<TharvyrnEntity> {

    private final ResourceLocation model = new ResourceLocation(Main.MODID, "geo/tharvyrn.geo.json");
    private final ResourceLocation animation = new ResourceLocation(Main.MODID, "animations/tharvyrn.animations.json");
    private final ResourceLocation texture = new ResourceLocation(Main.MODID, "textures/entity/tharvyrn.png");

    @Override
    public ResourceLocation getModelLocation(TharvyrnEntity object) {
        return model;
    }

    @Override
    public ResourceLocation getTextureLocation(TharvyrnEntity object) {
        return texture;
    }

    @Override
    public ResourceLocation getAnimationFileLocation(TharvyrnEntity animatable) {
        return animation;
    }
}
