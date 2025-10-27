package net.neptune.mythronix.events;

import net.minecraft.entity.EntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.neptune.mythronix.Main;
import net.neptune.mythronix.game.entities.*;
import net.neptune.mythronix.game.items.ModSpawnEggItem;

@Mod.EventBusSubscriber(modid = Main.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

     @SubscribeEvent
    public static void addEntityAttributes(EntityAttributeCreationEvent e){
         e.put(ModEntityTypes.BOREAL_DEER.get(), BorealDeerEntity.setCustomAttributes().build());
         e.put(ModEntityTypes.CORRUPTED_GOLEM.get(), CorruptedGolemEntity.setCustomAttributes().build());
         e.put(ModEntityTypes.THARVYRN.get(), TharvyrnEntity.setCustomAttributes().build());
         e.put(ModEntityTypes.CORRUPTER.get(), CorrupterEntity.setCustomAttributes().build());
         e.put(ModEntityTypes.FALLEN_GUARDIAN.get(), FallenGuardianEntity.setCustomAttributes().build());
     }

     @SubscribeEvent
    public static void onRegisterEntities(RegistryEvent.Register<EntityType<?>> e){
         ModSpawnEggItem.initSpawnEggs();
     }
}
