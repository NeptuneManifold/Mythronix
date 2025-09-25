package net.neptune.mythronix.game.recipes.types;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistryEntry;
import net.neptune.mythronix.game.blocks.ModBlocks;
import net.neptune.mythronix.game.recipes.ModRecipeTypes;
import net.neptune.mythronix.game.recipes.types.interfaces.IUnCorrupterRecipe;

import javax.annotation.Nullable;

public class UnCorrupterRecipe implements IUnCorrupterRecipe {
    private final ResourceLocation id;
    private final ItemStack output;
    private final NonNullList<Ingredient> recipeItems;

    public UnCorrupterRecipe(ResourceLocation id, ItemStack output, NonNullList<Ingredient> recipeItems){
        this.id = id;
        this.output = output;
        this.recipeItems = recipeItems;
    }
    @Override
    public boolean matches(IInventory pInv, World pLevel) {
        if(recipeItems.get(0).test(pInv.getItem(0))){
            return recipeItems.get(1).test(pInv.getItem(1));
        }
        return false;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return recipeItems;
    }

    @Override
    public ItemStack assemble(IInventory pInv) {
        return output;
    }

    @Override
    public ItemStack getResultItem() {
        return output.copy();
    }

    public ItemStack getIcon(){
        return new ItemStack(ModBlocks.UNCCORUPTER_CATALYSER.get());
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return ModRecipeTypes.UNCORRUPTER_RECIPE_SERIALIZER.get();
    }

    public static class UnCorrupterRecipeType implements IRecipeType<UnCorrupterRecipe> {
        @Override
        public String toString() {
            return UnCorrupterRecipe.TYPE_ID.toString();
        }
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<UnCorrupterRecipe> {
        @Override
        public UnCorrupterRecipe fromJson(ResourceLocation pRecipeId, JsonObject pJson) {
            ItemStack output = ShapedRecipe.itemFromJson(JSONUtils.getAsJsonObject(pJson, "output"));

            JsonArray ingredients = JSONUtils.getAsJsonArray(pJson, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.withSize(2, Ingredient.EMPTY);

            for(int i =0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            return new UnCorrupterRecipe(pRecipeId, output, inputs);
        }

        @Nullable
        @Override
        public UnCorrupterRecipe fromNetwork(ResourceLocation pRecipeId, PacketBuffer pBuffer) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(pBuffer.readInt(), Ingredient.EMPTY);

            for(int i = 0; i < inputs.size(); i++){
                inputs.set(i, Ingredient.fromNetwork(pBuffer));
            }

            ItemStack output = pBuffer.readItem();

            return new UnCorrupterRecipe(pRecipeId, output, inputs);
        }

        @Override
        public void toNetwork(PacketBuffer pBuffer, UnCorrupterRecipe pRecipe) {
            pBuffer.writeInt(pRecipe.getIngredients().size());

            for(Ingredient ing : pRecipe.getIngredients()){
                ing.toNetwork(pBuffer);
            }
            pBuffer.writeItemStack(pRecipe.getResultItem(), false);
        }
    }
}
