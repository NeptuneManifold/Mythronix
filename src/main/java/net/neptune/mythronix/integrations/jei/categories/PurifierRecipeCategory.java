package net.neptune.mythronix.integrations.jei.categories;

import com.mojang.blaze3d.matrix.MatrixStack;
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
import net.neptune.mythronix.game.recipes.types.PurifierRecipe;

public class PurifierRecipeCategory implements IRecipeCategory<PurifierRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(Main.MODID, "purify");
    public static final ResourceLocation TEXTURE =
            new ResourceLocation(Main.MODID, "textures/gui/purifier.png");

    private final IDrawable background;
    private final IDrawable icon;

    public PurifierRecipeCategory(IGuiHelper guiHelper) {
        this.background = guiHelper.createDrawable(TEXTURE,0,0,176,85);
        this.icon = guiHelper.createDrawableIngredient(new ItemStack(ModBlocks.PURIFIER.get()));
    }

    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    public Class<? extends PurifierRecipe> getRecipeClass() {
        return PurifierRecipe.class;
    }

    @Override
    public String getTitle() {
        return new TranslationTextComponent("jei." + Main.MODID + ".purify").getString();
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
    public void setIngredients(PurifierRecipe purifierRecipe, IIngredients iIngredients) {
        iIngredients.setInputIngredients(purifierRecipe.getIngredients());
        iIngredients.setOutput(VanillaTypes.ITEM, purifierRecipe.getResultItem());
    }

    @Override
    public void setRecipe(IRecipeLayout iRecipeLayout, PurifierRecipe purifierRecipe, IIngredients iIngredients) {
        iRecipeLayout.getItemStacks().init(0, true, 8,36);
        iRecipeLayout.getItemStacks().init(1, true, 80,8);
        iRecipeLayout.getItemStacks().init(2, true, 80,63);

        iRecipeLayout.getItemStacks().init(3, false, 152,36);

        iRecipeLayout.getItemStacks().set(iIngredients);

    }
}
