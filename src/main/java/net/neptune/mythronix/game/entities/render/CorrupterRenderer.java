package net.neptune.mythronix.game.entities.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.neptune.mythronix.game.entities.CorrupterEntity;
import net.neptune.mythronix.game.entities.TharvyrnEntity;
import net.neptune.mythronix.game.entities.models.CorrupterModel;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import javax.annotation.Nullable;

public class CorrupterRenderer extends GeoEntityRenderer<CorrupterEntity> {

    public CorrupterRenderer(EntityRendererManager ctx) {
        super(ctx, new CorrupterModel());
    }

    @Override
    public RenderType getRenderType(CorrupterEntity animatable, float partialTicks, MatrixStack stack, @Nullable IRenderTypeBuffer renderTypeBuffer, @Nullable IVertexBuilder vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
        return RenderType.entityTranslucent(getTextureLocation(animatable));
    }
}
