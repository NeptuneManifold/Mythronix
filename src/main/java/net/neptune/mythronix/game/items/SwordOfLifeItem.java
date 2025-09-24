package net.neptune.mythronix.game.items;

import net.minecraft.item.IItemTier;
import net.minecraft.item.Rarity;
import net.minecraft.item.SwordItem;
import net.neptune.mythronix.tabs.ModTabs;

public class SwordOfLifeItem extends SwordItem {

    public SwordOfLifeItem() {
        super(ModItemTier.LIFE, 1, 1.6f, new Properties().tab(ModTabs.mythronix)
                .stacksTo(1).rarity(Rarity.UNCOMMON).durability(300));
    }


}
