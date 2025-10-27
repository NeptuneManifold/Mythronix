package net.neptune.mythronix.events;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.FlameParticle;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.neptune.mythronix.Main;
import net.neptune.mythronix.game.particles.ModParticles;

@Mod.EventBusSubscriber(modid = Main.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientListener {

    @SubscribeEvent
    public static void onRegisterParticleFactories(ParticleFactoryRegisterEvent event) {
        Minecraft.getInstance().particleEngine.register(
                ModParticles.CORRUPTED_FIRE.get(),
                FlameParticle.Factory::new
        );
    }
}
