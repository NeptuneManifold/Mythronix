package net.neptune.mythronix.game.items.corrupted;

import net.minecraft.item.IItemTier;
import net.minecraft.item.SwordItem;
import net.neptune.mythronix.game.items.ModItemTier;
import net.neptune.mythronix.game.items.ModItems;
import net.neptune.mythronix.tabs.ModTabs;

public class CorruptedSwordItem extends SwordItem {

    public CorruptedSwordItem() {
        super(ModItemTier.CORRUPTED, 0, 0f, new Properties().stacksTo(1).tab(ModTabs.mythronix));
    }
}
