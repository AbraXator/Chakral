//package net.AbraXator.chakramod.recipes;//package net.AbraXator.chakramod.recipes;
//
//import com.google.gson.JsonArray;
//import com.google.gson.JsonElement;
//import com.google.gson.JsonObject;
//import net.AbraXator.chakramod.ChakraMod;
//import net.AbraXator.chakramod.utils.ModTags;
//import net.minecraft.core.NonNullList;
//import net.minecraft.network.FriendlyByteBuf;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.util.GsonHelper;
//import net.minecraft.world.SimpleContainer;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.item.crafting.*;
//import net.minecraft.world.level.Level;
//import net.minecraftforge.registries.ForgeRegistries;
//import org.jetbrains.annotations.Nullable;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class ShardRefinerRecipe implements Recipe<SimpleContainer> {
//    private final ResourceLocation id;
//    private final ItemStack necklace;
//    private final List<ItemStack> stones;
//
//    public ShardRefinerRecipe(ResourceLocation id, ItemStack necklace,
//                              List<ItemStack> stones){
//        this.id = id;
//        this.necklace = necklace;
//        this.stones = stones;
//    }
//
//    @Override
//    public boolean matches(SimpleContainer pContainer, Level pLevel) {
//        return false;
//    }
//
//    @Override
//    public ItemStack assemble(SimpleContainer pContainer) {
//        return necklace;
//    }
//
//    @Override
//    public boolean canCraftInDimensions(int pWidth, int pHeight) {
//        return true;
//    }
//
//    @Override
//    public ItemStack getResultItem() {
//        return necklace.copy();
//    }
//
//    @Override
//    public ResourceLocation getId() {
//        return id;
//    }
//
//    @Override
//    public RecipeSerializer<?> getSerializer() {
//        return null;
//    }
//
//    @Override
//    public RecipeType<?> getType() {
//        return Type.INSTANCE;
//    }
//
//    public static class Type implements RecipeType<ShardRefinerRecipe>{
//        private Type(){}
//        public static final Type INSTANCE = new Type();
//        public static final String ID = "shard_refiner";
//    }
//
//    public static class Serializer implements RecipeSerializer<ShardRefinerRecipe>{
//        public static final Serializer INSTANCE = new Serializer();
//        public static final ResourceLocation ID = new ResourceLocation(ChakraMod.MOD_ID, "shard_refiner");
//
//        @Override
//        public ShardRefinerRecipe fromJson(ResourceLocation pRecipeId, JsonObject jsonObject) {
//            ItemStack necklace = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(jsonObject, "necklace"));
//            JsonArray stonesArray = GsonHelper.getAsJsonArray(jsonObject, "stones");
//            JsonElement element;
//            List<String> stonesString = new ArrayList<>();
//            List<ItemStack> stones = ForgeRegistries.ITEMS.tags().getTag(ModTags.Items.GEMS).stream().toList();
//            for(int i = 0; i < stonesArray.size(); i++){
//                element = stonesArray.get(i);
//                stonesString.add(i, element.toString());
//
//            }
//            return new ShardRefinerRecipe(pRecipeId, necklace, stones);
//        }
//
//        @Override
//        public @Nullable ShardRefinerRecipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {
//            NonNullList<Ingredient> inputs = NonNullList.withSize(pBuffer.readInt(), Ingredient.EMPTY);
//
//            for(int i = 0; i < inputs.size(); i++){
//                inputs.set(i, Ingredient.fromNetwork(pBuffer));
//            }
//
//            ItemStack output = pBuffer.readItem();
//            return new ShardRefinerRecipe(pRecipeId, output, inputs);
//        }
//
//        @Override
//        public void toNetwork(FriendlyByteBuf pBuffer, ShardRefinerRecipe pRecipe) {
//            pBuffer.writeInt(pRecipe.getIngredients().size());
//            for(Ingredient ing : pRecipe.getIngredients()){
//                ing.toNetwork(pBuffer);
//            }
//            pBuffer.writeItemStack(pRecipe.getResultItem(), false);
//        }
//    }
//}
//