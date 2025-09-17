package net.neptune.mythronix.game.items;

import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.neptune.mythronix.Main;
import net.neptune.mythronix.game.entities.ModEntityTypes;
import net.neptune.mythronix.game.fluids.ModFluids;
import net.neptune.mythronix.tabs.ModTabs;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Main.MODID);

    public static final RegistryObject<Item> MAGIC_STICK = newItemBase("magic_stick", ModTabs.mythronix);

    public static final RegistryObject<Item> BOREAL_DUST = newItemBase("boreal_dust", ModTabs.mythronix);
    public static final RegistryObject<Item> CORRUPTED_HEART = newItemBase("corrupted_ingot", ModTabs.mythronix);
    public static final RegistryObject<Item> CORRUPTED_INGOT = newItemBase("corrupted_heart", ModTabs.mythronix);

    public static final RegistryObject<Item> IMPURE_MANA = newItemBase("impure_mana", ModTabs.mythronix);
    public static final RegistryObject<Item> SOLIDIFIED_MANA = newItemBase("solidified_mana", ModTabs.mythronix);

    public static final RegistryObject<Item> PYRROTHITE_INGOT = newItemBase("pyrrothite_ingot", ModTabs.mythronix);
    public static final RegistryObject<Item> AQUALITE_INGOT = newItemBase("aqualite_ingot", ModTabs.mythronix);
    public static final RegistryObject<Item> PHYTOLITE_INGOT = newItemBase("phytolite_ingot", ModTabs.mythronix);
    public static final RegistryObject<Item> CRYONITE_INGOT = newItemBase("cryonite_ingot", ModTabs.mythronix);
    public static final RegistryObject<Item> ACANITE_INGOT = newItemBase("arcanite_ingot", ModTabs.mythronix);
    public static final RegistryObject<Item> AERONITE_INGOT = newItemBase("aeronite_ingot", ModTabs.mythronix);

    public static final RegistryObject<Item> RUBY = newItemBase("ruby", ModTabs.mythronix);

    public static final RegistryObject<Item> ETHERUM_BUCKET = ITEMS.register("etherum_bucket", () ->
            new BucketItem(() -> ModFluids.ETHERUM_FLUID.get(), new Item.Properties().tab(ModTabs.mythronix).stacksTo(1)));

    public static final RegistryObject<Item> BOREAL_DEER_SPAWN_EGG = ITEMS.register("boreal_deer_spawn_egg", () ->
            new ModSpawnEggItem(ModEntityTypes.BOREAL_DEER, 0x7400ff, 0x4200ff,
                    new Item.Properties().stacksTo(64).tab(ModTabs.mythronix)));

    public static final RegistryObject<Item> CORRUPTED_GOLEM_SPAWN_EGG = ITEMS.register("corrupted_golem_spawn_egg", () ->
            new ModSpawnEggItem(ModEntityTypes.CORRUPTED_GOLEM, 0x5200A3 ,0xD9D9D9,
                    new Item.Properties().stacksTo(64).tab(ModTabs.mythronix)));

    public static RegistryObject<Item> newItemBase(String id, ItemGroup tab){
        return ITEMS.register(id, () -> new Item(new Item.Properties().tab(tab).stacksTo(64)));
    }

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}
