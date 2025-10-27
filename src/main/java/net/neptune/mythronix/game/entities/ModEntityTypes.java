package net.neptune.mythronix.game.entities;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.neptune.mythronix.Main;
import net.neptune.mythronix.game.entities.shoot.CorruptionOrbEntity;

public class ModEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, Main.MODID);

    public static final RegistryObject<EntityType<BorealDeerEntity>> BOREAL_DEER =
            ENTITY_TYPES.register("boreal_deer",
                    () -> EntityType.Builder.of(BorealDeerEntity::new,
                            EntityClassification.CREATURE).sized(0.6F,1.6F)
                            .build(new ResourceLocation(Main.MODID, "boreal_deer").toString()));

    public static final RegistryObject<EntityType<CorruptedGolemEntity>> CORRUPTED_GOLEM =
            ENTITY_TYPES.register("corrupted_golem",
                    () -> EntityType.Builder.of(CorruptedGolemEntity::new,
                            EntityClassification.MONSTER).sized(1.4f, 1.7f)
                            .build(new ResourceLocation(Main.MODID, "corrupted_golem").toString()));

    public static final RegistryObject<EntityType<TharvyrnEntity>> THARVYRN =
            ENTITY_TYPES.register("tharvyrn",
                    () -> EntityType.Builder.of(TharvyrnEntity::new,
                            EntityClassification.MONSTER).sized(1F,0.3F)
                            .build(new ResourceLocation(Main.MODID, "tharvyrn").toString()));

    public static final RegistryObject<EntityType<CorrupterEntity>> CORRUPTER =
            ENTITY_TYPES.register("corrupter",
                    () -> EntityType.Builder.of(CorrupterEntity::new,
                            EntityClassification.MONSTER).sized(1f,3f)
                            .build(new ResourceLocation(Main.MODID, "corrupter").toString()));

    public static final RegistryObject<EntityType<FallenGuardianEntity>> FALLEN_GUARDIAN =
            ENTITY_TYPES.register("fallen_guardian",
                    () -> EntityType.Builder.of(FallenGuardianEntity::new,
                            EntityClassification.MONSTER).sized(1f,1.8f)
                            .build(new ResourceLocation(Main.MODID, "fallen_guardian").toString()));

    public static final RegistryObject<EntityType<CorruptionOrbEntity>> CORRUPTION_ORB =
            ENTITY_TYPES.register("corruption_orb",
                    () -> EntityType.Builder.<CorruptionOrbEntity>of(CorruptionOrbEntity::new,
                            EntityClassification.MISC).sized(0.25F, 0.25F)
                            .clientTrackingRange(10)
                            .setShouldReceiveVelocityUpdates(true)
                            .updateInterval(10).build(new ResourceLocation(Main.MODID, "corruption_orb").toString()));

    public static void register(IEventBus bus){
        ENTITY_TYPES.register(bus);
    }
}
