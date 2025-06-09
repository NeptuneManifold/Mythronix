package net.neptune.mythronix.events;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.neptune.mythronix.Main;
import net.neptune.mythronix.capability.ManaLevelProvider;
import net.neptune.mythronix.capability.ManaLevelUtils;
import net.neptune.mythronix.capability.commands.ManaLevelCommands;
import net.neptune.mythronix.game.ModCapabilities;
import net.neptune.mythronix.menus.screens.ManaLevelScreen;

import static net.neptune.mythronix.game.ModCapabilities.MANA_LEVEL_ID;

@Mod.EventBusSubscriber(modid = Main.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEvents {

    @SubscribeEvent
    public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if(!(event.getObject() instanceof PlayerEntity)) return;

        event.addCapability(MANA_LEVEL_ID, new ManaLevelProvider());
    }

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        event.getOriginal().getCapability(ModCapabilities.MANA_LEVEL).ifPresent(oldManaLevel -> {
            PlayerEntity player = event.getPlayer();
            player.getCapability(ModCapabilities.MANA_LEVEL).ifPresent(newManaLevel -> {
                newManaLevel.set(oldManaLevel);
                ManaLevelUtils.update(player);
            });
        });
    }


    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        Entity ent = event.getEntity();
        if(!ent.level.isClientSide()) {
            PlayerEntity player = (PlayerEntity) ent;

            ManaLevelUtils.update(player);
        }
    }

    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        new ManaLevelCommands(event.getDispatcher());
    }

    @SubscribeEvent
    public static void onRenderGameOverlay(RenderGameOverlayEvent event) {
        if(event.getType() == RenderGameOverlayEvent.ElementType.ALL){
            ManaLevelScreen.render(event.getMatrixStack());
        }
    }
}
