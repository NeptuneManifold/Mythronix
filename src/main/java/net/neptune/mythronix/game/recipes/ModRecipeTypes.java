package net.neptune.mythronix.game.recipes;

import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.neptune.mythronix.Main;
import net.neptune.mythronix.game.recipes.types.PurifierRecipe;

public class ModRecipeTypes {
    public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZER = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Main.MODID);

    public static final RegistryObject<PurifierRecipe.Serializer> PURIFIER_RECIPE_SERIALIZER =
            RECIPE_SERIALIZER.register("purify", PurifierRecipe.Serializer::new);

    public static final IRecipeType<PurifierRecipe> PURIFIER_RECIPE = new PurifierRecipe.PurifierRecipeType();

    public static void register(IEventBus bus){
        RECIPE_SERIALIZER.register(bus);

        Registry.register(Registry.RECIPE_TYPE, PurifierRecipe.TYPE_ID, PURIFIER_RECIPE);
    }
}
