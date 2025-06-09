package net.neptune.mythronix.game.items;

import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.neptune.mythronix.Main;
import net.neptune.mythronix.game.fluids.ModFluids;
import net.neptune.mythronix.tabs.ModTabs;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Main.MODID);

    public static final RegistryObject<Item> MAGIC_STICK = newItemBase("magic_stick", ModTabs.mythronix);

    public static final RegistryObject<Item> IMPURE_MANA = newItemBase("impure_mana", ModTabs.mythronix);
    public static final RegistryObject<Item> SOLIDIFIED_MANA = newItemBase("solidified_mana", ModTabs.mythronix);

    public static final RegistryObject<Item> ETHERUM_BUCKET = ITEMS.register("etherum_bucket", () ->
            new BucketItem(() -> ModFluids.ETHERUM_FLUID.get(), new Item.Properties().tab(ModTabs.mythronix).stacksTo(1)));


    public static RegistryObject<Item> newItemBase(String id, ItemGroup tab){
        return ITEMS.register(id, () -> new Item(new Item.Properties().tab(tab)));
    }

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}
