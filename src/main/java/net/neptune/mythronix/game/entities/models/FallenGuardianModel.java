package net.neptune.mythronix.game.entities.models;

import net.minecraft.util.ResourceLocation;
import net.neptune.mythronix.Main;
import net.neptune.mythronix.game.entities.BorealDeerEntity;
import net.neptune.mythronix.game.entities.FallenGuardianEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class FallenGuardianModel extends AnimatedGeoModel<FallenGuardianEntity> {
    private final ResourceLocation model = new ResourceLocation(Main.MODID, "geo/fallen_guardian.geo.json");
    private final ResourceLocation animation = new ResourceLocation(Main.MODID, "animations/guardian.animations.json");
    private final ResourceLocation texture = new ResourceLocation(Main.MODID, "textures/entity/fallen_guardian.png");


    @Override
    public ResourceLocation getModelLocation(FallenGuardianEntity object) {
        return model;
    }

    @Override
    public ResourceLocation getTextureLocation(FallenGuardianEntity object) {
        return texture;
    }

    @Override
    public ResourceLocation getAnimationFileLocation(FallenGuardianEntity animatable) {
        return animation;
    }
}
