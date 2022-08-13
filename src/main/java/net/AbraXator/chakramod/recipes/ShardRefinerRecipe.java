//package net.AbraXator.chakramod.recipes;
//
//import com.google.gson.JsonArray;
//import com.google.gson.JsonElement;
//import com.google.gson.JsonObject;
//import net.AbraXator.chakramod.ChakraMod;
//import net.AbraXator.chakramod.items.ModItems;
//import net.AbraXator.chakramod.utils.ModTags;
//import net.minecraft.core.NonNullList;
//import net.minecraft.network.FriendlyByteBuf;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.tags.TagKey;
//import net.minecraft.util.GsonHelper;
//import net.minecraft.world.SimpleContainer;
//import net.minecraft.world.item.Item;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.item.Items;
//import net.minecraft.world.item.crafting.*;
//import net.minecraft.world.level.Level;
//import net.minecraftforge.registries.ForgeRegistries;
//import org.jetbrains.annotations.Nullable;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//
//public class ShardRefinerRecipe implements Recipe<SimpleContainer> {
//    private final ResourceLocation id;
//    private final ItemStack output;
//    private final NonNullList<Ingredient> recipeItems;
//
//    public ShardRefinerRecipe(ResourceLocation id, ItemStack output,
//                              NonNullList<Ingredient> recipeItems){
//        this.id = id;
//        this.output = output;
//        this.recipeItems = recipeItems;
//    }
//
//    @Override
//    public boolean matches(SimpleContainer pContainer, Level pLevel) {
//        return false;
//    }
//
//    @Override
//    public ItemStack assemble(SimpleContainer pContainer) {
//        return output;
//    }
//
//    @Override
//    public boolean canCraftInDimensions(int pWidth, int pHeight) {
//        return true;
//    }
//
//    @Override
//    public ItemStack getResultItem() {
//        return output.copy();
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
//            Random random = new Random();
//            ItemStack shard = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(jsonObject, "shard"));
//            JsonArray outputArray = GsonHelper.getAsJsonArray(jsonObject, "output");
//            double chance = GsonHelper.getAsDouble(jsonObject, "chance");
//            double l = Math.random();
//            List<ItemStack> list = new ArrayList<>();
//            ItemStack stack;
//            ItemStack output;
//            TagKey<Item> tag;
//            if(shard.is(Items.AMETHYST_SHARD)){
//                tag = ModTags.Items.CROWN;
//            }if(shard.is(ModItems.GREEN_SHARD.get())){
//                tag = ModTags.Items.HEART;
//            }if(shard.is(Items.AMETHYST_SHARD)){
//                tag = ModTags.Items.CROWN;
//            }if(shard.is(Items.AMETHYST_SHARD)){
//                tag = ModTags.Items.CROWN;
//            }if(shard.is(Items.AMETHYST_SHARD)){
//                tag = ModTags.Items.CROWN;
//            }if(shard.is(Items.AMETHYST_SHARD)){
//                tag = ModTags.Items.CROWN;
//            }
//            for(JsonElement element : outputArray){
//                String name = element.getAsString().toLowerCase().replace(" ", "_");
//                if(name.matches("lapis_lazuli") && name.matches("amethyst_shard") && name.matches("nether_quartz")){
//                    stack = ForgeRegistries.ITEMS.getValue(new ResourceLocation("minecraft:", name)).getDefaultInstance();
//                }else {
//                    stack = ForgeRegistries.ITEMS.getValue(new ResourceLocation(ChakraMod.MOD_ID, name)).getDefaultInstance();
//                }
//            }
//            if(l <= chance){
//                output = ForgeRegistries.ITEMS.tags().getTag(tag)
//            }
//
//            return new ShardRefinerRecipe(pRecipeId, output);
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
