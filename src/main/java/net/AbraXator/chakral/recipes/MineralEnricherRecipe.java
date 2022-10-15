package net.AbraXator.chakral.recipes;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.AbraXator.chakral.Chakral;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class MineralEnricherRecipe implements Recipe<SimpleContainer> {
    private final ResourceLocation id;
    private final ItemStack dust;
    private final int dustAmount;
    private final ItemStack output;
    NonNullList<Ingredient> recipeItems;

    public MineralEnricherRecipe(ResourceLocation id, ItemStack dust, int dustAmount, ItemStack output, NonNullList<Ingredient> recipeItems){
        this.id = id;
        this.dust = dust;
        this.dustAmount = dustAmount;
        this.output = output;
        this.recipeItems = recipeItems;
    }

    @Override
    public boolean matches(SimpleContainer pContainer, Level pLevel) {
        if(pLevel.isClientSide()){
            return false;
        }

        return recipeItems.get(0).test(pContainer.getItem(0));
    }

    @Override
    public ItemStack assemble(SimpleContainer pContainer) {
        return output;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    public ItemStack getDust(){
        return dust.copy();
    }

    public int getDustAmount(){
        return dustAmount;
    }

    @Override
    public ItemStack getResultItem() {
        return output.copy();
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return null;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<MineralEnricherRecipe>{
        private Type(){}
        public static final Type INSTANCE = new Type();
        public static final String ID = "mineral_enriching";
    }

    public static class Serializer implements RecipeSerializer<MineralEnricherRecipe>{
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID =
                new ResourceLocation(Chakral.MOD_ID, "mineral_enriching");

        @Override
        public MineralEnricherRecipe fromJson(ResourceLocation pRecipeId, JsonObject pSerializedRecipe) {
            JsonArray ingredients = GsonHelper.getAsJsonArray(pSerializedRecipe, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.withSize(1, Ingredient.EMPTY);
            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            ItemStack dust = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "dust"));
            int dustAmount = GsonHelper.getAsInt(pSerializedRecipe, "dustAmount");
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "output"));

            return new MineralEnricherRecipe(pRecipeId, dust, dustAmount, output, inputs);
        }

        @Override
        public @Nullable MineralEnricherRecipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf buf) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(buf.readInt(), Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromNetwork(buf));
            }

            ItemStack dust = buf.readItem();
            int dustAmount = buf.readInt();
            ItemStack output = buf.readItem();
            return new MineralEnricherRecipe(pRecipeId, dust, dustAmount, output, inputs);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, MineralEnricherRecipe pRecipe) {
            buf.writeInt(pRecipe.getIngredients().size());
            for (Ingredient ing : pRecipe.getIngredients()) {
                ing.toNetwork(buf);
            }
            buf.writeItemStack(pRecipe.getDust(), false);
            buf.writeInt(pRecipe.getDustAmount());
            buf.writeItemStack(pRecipe.getResultItem(), false);
        }
    }
}
