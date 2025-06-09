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
import net.neptune.mythronix.game.recipes.types.interfaces.IPurifierRecipe;

import javax.annotation.Nullable;

public class PurifierRecipe implements IPurifierRecipe {

    private final ResourceLocation id;
    private final ItemStack output;
    private final NonNullList<Ingredient> recipeItems;

    public PurifierRecipe(ResourceLocation id, ItemStack output, NonNullList<Ingredient> recipeItems){
        this.id = id;
        this.output = output;
        this.recipeItems = recipeItems;
    }

    @Override
    public boolean matches(IInventory pInv, World pLevel) {
        if(recipeItems.get(0).test(pInv.getItem(0))){
            if(recipeItems.get(1).test(pInv.getItem(1))){
                return recipeItems.get(2).test(pInv.getItem(2));
            }
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
        return new ItemStack(ModBlocks.PURIFIER.get());
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return ModRecipeTypes.PURIFIER_RECIPE_SERIALIZER.get();
    }

    public static class PurifierRecipeType implements IRecipeType<PurifierRecipe> {
        @Override
        public String toString() {
            return PurifierRecipe.TYPE_ID.toString();
        }
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<PurifierRecipe> {



        @Override
        public PurifierRecipe fromJson(ResourceLocation pRecipeId, JsonObject pJson) {
            ItemStack output = ShapedRecipe.itemFromJson(JSONUtils.getAsJsonObject(pJson, "output"));

            JsonArray ingredients = JSONUtils.getAsJsonArray(pJson, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.withSize(3, Ingredient.EMPTY);

            for(int i = 0; i < inputs.size(); i++){
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            return new PurifierRecipe(pRecipeId, output, inputs);
        }

        @Nullable
        @Override
        public PurifierRecipe fromNetwork(ResourceLocation pRecipeId, PacketBuffer pBuffer) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(pBuffer.readInt(), Ingredient.EMPTY);

            for(int i = 0; i < inputs.size(); i++){
                inputs.set(i, Ingredient.fromNetwork(pBuffer));
            }

            ItemStack ouput = pBuffer.readItem();

            return new PurifierRecipe(pRecipeId, ouput, inputs);
        }

        @Override
        public void toNetwork(PacketBuffer pBuffer, PurifierRecipe pRecipe) {
            pBuffer.writeInt(pRecipe.getIngredients().size());

            for(Ingredient ing : pRecipe.getIngredients()){
                ing.toNetwork(pBuffer);
            }
            pBuffer.writeItemStack(pRecipe.getResultItem(), false);
        }
    }
}
