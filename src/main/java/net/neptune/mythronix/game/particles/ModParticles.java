package net.neptune.mythronix.game.particles;

import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.neptune.mythronix.Main;

public class ModParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, Main.MODID);

    public static final RegistryObject<BasicParticleType> CORRUPTED_FIRE = PARTICLES.register("corrupted_fire", () -> new BasicParticleType(false));

    public static void register(IEventBus bus){
        PARTICLES.register(bus);
    }
}
