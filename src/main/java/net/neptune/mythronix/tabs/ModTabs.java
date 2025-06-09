package net.neptune.mythronix.tabs;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.neptune.mythronix.game.blocks.ModBlocks;
import net.neptune.mythronix.game.items.ModItems;

public class ModTabs {
    public static ItemGroup mythronix = new ItemGroup("mythronix") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.MAGIC_STICK.get());
        }
    };
}
