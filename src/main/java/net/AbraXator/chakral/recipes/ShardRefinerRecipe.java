package net.AbraXator.chakral.recipes;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.AbraXator.chakral.Chakral;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class ShardRefinerRecipe implements Recipe<Container> {
    private final ResourceLocation id;
    private final ItemStack output;
    private final NonNullList<Ingredient> input;
    private final int tier;

    public ShardRefinerRecipe(ResourceLocation id, ItemStack output, NonNullList<Ingredient> input, int tier) {
        this.id = id;
        this.output = output;
        this.input = input;
        this.tier = tier;
    }


    @Override
    public boolean matches(Container pContainer, Level pLevel) {
        if(pLevel.isClientSide()){
            return false;
        }
        return input.get(0).test(pContainer.getItem(1));
    }

    public boolean matchesTier(int entityTier){
        return entityTier == tier;
    }

    @Override
    public ItemStack assemble(Container pContainer) {
        return output;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    public NonNullList<Ingredient> getInputItem() {
        return input;
    }

    @Override
    public ItemStack getResultItem() {
        return output.copy();
    }

    public int getTier() {
        return tier;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<ShardRefinerRecipe>{
        private Type(){}
        public static final Type INSTANCE = new Type();
        public static final String ID = "shard_refining";
    }

    public static class Serializer implements RecipeSerializer<ShardRefinerRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID =
                new ResourceLocation(Chakral.MOD_ID, "shard_refining");

        @Override
        public ShardRefinerRecipe fromJson(ResourceLocation pRecipeId, JsonObject pSerializedRecipe) {
            JsonArray ingredients = GsonHelper.getAsJsonArray(pSerializedRecipe, "ingredients");
            int tier = GsonHelper.getAsInt(pSerializedRecipe, "tier");
            NonNullList<Ingredient> input = NonNullList.withSize(1, Ingredient.EMPTY);

            for (int i = 0; i < input.size(); i++) {
                input.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "output"));
            return new ShardRefinerRecipe(pRecipeId, output, input, tier);
        }

        @Override
        public @Nullable ShardRefinerRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(buf.readInt(), Ingredient.EMPTY);
            int tier = buf.readInt();

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromNetwork(buf));
            }

            ItemStack output = buf.readItem();
            return new ShardRefinerRecipe(id, output, inputs, tier);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, ShardRefinerRecipe recipe) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(buf.readInt(), Ingredient.EMPTY);
            int tier = recipe.tier;

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromNetwork(buf));
            }

            ItemStack output = buf.readItem();
        }
    }
}
