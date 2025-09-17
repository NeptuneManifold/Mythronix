package net.neptune.mythronix.game.entities.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.IronGolemRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.IronGolemCracksLayer;
import net.minecraft.client.renderer.entity.layers.IronGolenFlowerLayer;
import net.minecraft.client.renderer.entity.model.IronGolemModel;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.neptune.mythronix.Main;
import net.neptune.mythronix.game.entities.CorruptedGolemEntity;
import net.neptune.mythronix.game.entities.models.CorruptedIronGolemModel;

@OnlyIn(Dist.CLIENT)
public class CorruptedGolemRenderer extends MobRenderer<CorruptedGolemEntity, CorruptedIronGolemModel<CorruptedGolemEntity>> {
    private static final ResourceLocation GOLEM_LOCATION = new ResourceLocation(Main.MODID,"textures/entity/corrupted_golem.png");

    public CorruptedGolemRenderer(EntityRendererManager p_i46133_1_) {
        super(p_i46133_1_, new CorruptedIronGolemModel<>(), 0.7F);
    }

    public ResourceLocation getTextureLocation(CorruptedGolemEntity pEntity) {
        return GOLEM_LOCATION;
    }

    protected void setupRotations(CorruptedGolemEntity pEntityLiving, MatrixStack pMatrixStack, float pAgeInTicks, float pRotationYaw, float pPartialTicks) {
        super.setupRotations(pEntityLiving, pMatrixStack, pAgeInTicks, pRotationYaw, pPartialTicks);
        if (!((double)pEntityLiving.animationSpeed < 0.01D)) {
            float f = 13.0F;
            float f1 = pEntityLiving.animationPosition - pEntityLiving.animationSpeed * (1.0F - pPartialTicks) + 6.0F;
            float f2 = (Math.abs(f1 % 13.0F - 6.5F) - 3.25F) / 3.25F;
            pMatrixStack.mulPose(Vector3f.ZP.rotationDegrees(6.5F * f2));
        }
    }
}
