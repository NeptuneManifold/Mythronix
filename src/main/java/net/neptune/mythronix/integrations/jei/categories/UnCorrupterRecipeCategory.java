package net.neptune.mythronix.integrations.jei.categories;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import net.neptune.mythronix.Main;
import net.neptune.mythronix.game.blocks.ModBlocks;
import net.neptune.mythronix.game.recipes.types.UnCorrupterRecipe;

public class UnCorrupterRecipeCategory implements IRecipeCategory<UnCorrupterRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(Main.MODID, "uncorrupting");
    public static final ResourceLocation TEXTURE =
            new ResourceLocation(Main.MODID, "textures/gui/uncorrupter.png");

    private final IDrawable background;
    private final IDrawable icon;

    public UnCorrupterRecipeCategory(IGuiHelper guiHelper) {
        this.background = guiHelper.createDrawable(TEXTURE,0,0,176,85);
        this.icon = guiHelper.createDrawableIngredient(new ItemStack(ModBlocks.UNCCORUPTER_CATALYSER.get()));
    }

    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    public Class<? extends UnCorrupterRecipe> getRecipeClass() {
        return UnCorrupterRecipe.class;
    }

    @Override
    public String getTitle() {
        return new TranslationTextComponent("jei." + Main.MODID + ".uncorrupting").getString();
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setIngredients(UnCorrupterRecipe unCorrupterRecipe, IIngredients iIngredients) {
        iIngredients.setInputIngredients(unCorrupterRecipe.getIngredients());
        iIngredients.setOutput(VanillaTypes.ITEM, unCorrupterRecipe.getResultItem());
    }

    @Override
    public void setRecipe(IRecipeLayout iRecipeLayout, UnCorrupterRecipe unCorrupterRecipe, IIngredients iIngredients) {
        iRecipeLayout.getItemStacks().init(0, true, 14,35);
        iRecipeLayout.getItemStacks().init(1, true, 52,35);

        iRecipeLayout.getItemStacks().init(2, true, 135,35);

        iRecipeLayout.getItemStacks().set(iIngredients);
    }
}
