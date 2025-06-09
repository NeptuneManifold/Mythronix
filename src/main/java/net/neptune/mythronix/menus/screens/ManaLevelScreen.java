package net.neptune.mythronix.menus.screens;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.neptune.mythronix.Main;
import net.neptune.mythronix.capability.ManaLevelUtils;
import net.neptune.mythronix.game.ModCapabilities;

public class ManaLevelScreen {
    private static final ResourceLocation TEXTURE = new ResourceLocation(Main.MODID, "textures/gui/manabar.png");

    public static void render(MatrixStack pMatrixStack) {
        Minecraft mc = Minecraft.getInstance();
        PlayerEntity player = mc.player;
        if(player == null || !player.isAlive()) return;

        player.getCapability(ModCapabilities.MANA_LEVEL).ifPresent(manaLevel -> {
            float manaLevelXp = manaLevel.getManaXpToNextLevel();
            float manaLevelMaxXp = manaLevel.getTotalXpToNextLevel();
            if(manaLevelMaxXp == 0) return;
            float xpBarWidth = manaLevelXp / manaLevelMaxXp;

            mc.getTextureManager().bind(TEXTURE);

            AbstractGui.blit(pMatrixStack, 10, 10, 17, 22, 119, 25, 153, 102);
            AbstractGui.blit(pMatrixStack, 10, 10, 17, 55, (int) (xpBarWidth * 119), 25, 153, 102);
            AbstractGui.drawString(pMatrixStack,Minecraft.getInstance().font, String.valueOf(ManaLevelUtils.getManaLevel(player)), 18,19,0xffFFFFFF);
        });
    }

    public static void register(){
        MinecraftForge.EVENT_BUS.register(new ManaLevelScreen());
    }
}
