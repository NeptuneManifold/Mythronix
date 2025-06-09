package net.neptune.mythronix.game.recipes.types.interfaces;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.neptune.mythronix.Main;

public interface IPurifierRecipe extends IRecipe<IInventory> {
    ResourceLocation TYPE_ID = new ResourceLocation(Main.MODID, "purify");

    @Override
    default IRecipeType<?> getType(){
        return Registry.RECIPE_TYPE.getOptional(TYPE_ID).get();
    }

    @Override
    default boolean canCraftInDimensions(int pWidth, int pHeight){
        return true;
    }

    @Override
    default boolean isSpecial() {
        return true;
    }
}
