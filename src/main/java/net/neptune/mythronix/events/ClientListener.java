package net.neptune.mythronix.events;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.FlameParticle;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.neptune.mythronix.Main;
import net.neptune.mythronix.game.blocks.geckolib.render.CorrupterStatueRenderer;
import net.neptune.mythronix.game.blocks.geckolib.render.PurifierRenderer;
import net.neptune.mythronix.game.blocks.tile.ModTileEntities;
import net.neptune.mythronix.game.particles.ModParticles;

@Mod.EventBusSubscriber(modid = Main.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientListener {

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerRenderers(final FMLClientSetupEvent e){
        ClientRegistry.bindTileEntityRenderer(ModTileEntities.PURIFIER_TILE.get(), PurifierRenderer::new);
        ClientRegistry.bindTileEntityRenderer(ModTileEntities.CORRUPTER_STATUE_TILE.get(), CorrupterStatueRenderer::new);
    }

    @SubscribeEvent
    public static void onRegisterParticleFactories(ParticleFactoryRegisterEvent event) {
        Minecraft.getInstance().particleEngine.register(
                ModParticles.CORRUPTED_FIRE.get(),
                FlameParticle.Factory::new
        );
    }
}
