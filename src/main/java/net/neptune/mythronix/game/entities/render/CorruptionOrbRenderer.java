package net.neptune.mythronix.game.entities.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.neptune.mythronix.Main;
import net.neptune.mythronix.game.entities.shoot.CorruptionOrbEntity;

@OnlyIn(Dist.CLIENT)
public class CorruptionOrbRenderer extends SpriteRenderer<CorruptionOrbEntity> {

    public CorruptionOrbRenderer(EntityRendererManager manager) {
        super(manager, Minecraft.getInstance().getItemRenderer());

        System.out.println("CorruptionOrbRendered");
    }
}