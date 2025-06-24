package net.neptune.mythronix.events;

import net.minecraft.entity.EntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.neptune.mythronix.Main;
import net.neptune.mythronix.game.entities.BorealDeerEntity;
import net.neptune.mythronix.game.entities.ModEntityTypes;
import net.neptune.mythronix.game.items.ModSpawnEggItem;

@Mod.EventBusSubscriber(modid = Main.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

     @SubscribeEvent
    public static void addEntityAttributes(EntityAttributeCreationEvent e){
         e.put(ModEntityTypes.BOREAL_DEER.get(), BorealDeerEntity.setCustomAttributes().build());
     }

     @SubscribeEvent
    public static void onRegisterEntities(RegistryEvent.Register<EntityType<?>> e){
         ModSpawnEggItem.initSpawnEggs();
     }
}
