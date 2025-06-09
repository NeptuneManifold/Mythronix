package net.neptune.mythronix.events;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.neptune.mythronix.Main;
import net.neptune.mythronix.game.blocks.geckolib.render.PurifierRenderer;
import net.neptune.mythronix.game.blocks.tile.ModTileEntities;

@Mod.EventBusSubscriber(modid = Main.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientListener {

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerRenderers(final FMLClientSetupEvent e){
        ClientRegistry.bindTileEntityRenderer(ModTileEntities.PURIFIER_TILE.get(), PurifierRenderer::new);
    }
}
