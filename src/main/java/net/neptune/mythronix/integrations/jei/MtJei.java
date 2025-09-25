package net.neptune.mythronix.integrations.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.ResourceLocation;
import net.neptune.mythronix.Main;
import net.neptune.mythronix.game.recipes.ModRecipeTypes;
import net.neptune.mythronix.game.recipes.types.PurifierRecipe;
import net.neptune.mythronix.game.recipes.types.UnCorrupterRecipe;
import net.neptune.mythronix.integrations.jei.categories.PurifierRecipeCategory;
import net.neptune.mythronix.integrations.jei.categories.UnCorrupterRecipeCategory;

import java.util.Objects;
import java.util.stream.Collectors;

@JeiPlugin
public class MtJei implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(Main.MODID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {

        registration.addRecipeCategories(
                new PurifierRecipeCategory(registration.getJeiHelpers().getGuiHelper()));

        registration.addRecipeCategories(
                new UnCorrupterRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager rm = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();
        registration.addRecipes(rm.getAllRecipesFor(ModRecipeTypes.PURIFIER_RECIPE).stream().filter(r ->
                r instanceof PurifierRecipe).collect(Collectors.toList()), PurifierRecipeCategory.UID);

        registration.addRecipes(rm.getAllRecipesFor(ModRecipeTypes.UNCORRUPTER_RECIPE).stream().filter(r ->
                r instanceof UnCorrupterRecipe).collect(Collectors.toList()), UnCorrupterRecipeCategory.UID);
    }
}
