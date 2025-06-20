package net.neptune.mythronix.game.blocks.geckolib.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.ResourceLocation;
import net.neptune.mythronix.game.blocks.geckolib.models.PurifierModel;
import net.neptune.mythronix.game.blocks.tile.PurifierTile;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

import javax.annotation.Nullable;

public class PurifierRenderer extends GeoBlockRenderer<PurifierTile> {

    public PurifierRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn, new PurifierModel());
    }

    @Override
    public RenderType getRenderType(PurifierTile animatable, float partialTicks, MatrixStack stack,
                                    @Nullable IRenderTypeBuffer renderTypeBuffer, @Nullable IVertexBuilder vertexBuilder,
                                    int packedLightIn, ResourceLocation textureLocation) {
        return RenderType.entityTranslucent(getTextureLocation(animatable));
    }
}
