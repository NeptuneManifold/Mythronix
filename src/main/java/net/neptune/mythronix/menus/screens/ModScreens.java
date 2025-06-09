package net.neptune.mythronix.menus.screens;

import net.minecraft.client.gui.ScreenManager;
import net.neptune.mythronix.menus.containers.ModContainers;

public class ModScreens {
    public static void register() {
        ScreenManager.register(ModContainers.PURIFIER_CONTAINER.get(), PurifierScreen::new);

        ManaLevelScreen.register();
    }
}
