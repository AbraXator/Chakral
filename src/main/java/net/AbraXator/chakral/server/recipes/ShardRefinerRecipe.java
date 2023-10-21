package net.AbraXator.chakral.server.recipes;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.AbraXator.chakral.Chakral;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class ShardRefinerRecipe implements Recipe<Container> {
    private final ResourceLocation id;
    private final ItemStack diamond;
    private final ItemStack shard;
    private final NonNullList<ItemStack> stones;

    public ShardRefinerRecipe(ResourceLocation id, ItemStack diamond, ItemStack shard, NonNullList<ItemStack> stones) {
        this.id = id;
        this.diamond = diamond;
        this.shard = shard;
        this.stones = stones;
    }

    @Override
    public boolean matches(Container pContainer, Level pLevel) {
        if(pLevel.isClientSide()){
            return false;
        }
        return pContainer.getItem(1).is(shard.getItem());
    }

    @Override
    public ItemStack assemble(Container p_44001_, RegistryAccess p_267165_) {
        return stones.get(getRandomStone(getStones().size()));
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess p_267052_) {
        return stones.get(getRandomStone(getStones().size()));
    }

    public ItemStack getInputItem() {
        return shard;
    }

    public ItemStack getDiamond() {
        return diamond;
    }

    public ItemStack getShard() {
        return shard;
    }

    public NonNullList<ItemStack> getStones() {
        return stones;
    }

    public int getRandomStone(int bound){
        return new Random().nextInt(bound);
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
        public static final String ID = "refining";
    }

    public static class Serializer implements RecipeSerializer<ShardRefinerRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID =
                new ResourceLocation(Chakral.MOD_ID, "refining");

        @Override
        public ShardRefinerRecipe fromJson(ResourceLocation pRecipeId, JsonObject pJsonObject) {
            ItemStack diamondStack = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pJsonObject, "diamond"));
            ItemStack shardStack = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pJsonObject, "shard"));
            JsonArray stonesObject = GsonHelper.getAsJsonArray(pJsonObject, "stones");
            NonNullList<ItemStack> stones = NonNullList.withSize(4, ItemStack.EMPTY);
            for(int i = 0; i <=stonesObject.size(); i++){
                JsonObject jsonObject = stonesObject.get(i).getAsJsonObject();
                stones.add(ShapedRecipe.itemStackFromJson(jsonObject));
            }
            return new ShardRefinerRecipe(pRecipeId, diamondStack, shardStack, stones);
        }

        @Override
        public @Nullable ShardRefinerRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
            ItemStack diamond = buf.readItem();
            ItemStack shard = buf.readItem();
            NonNullList<ItemStack> stones = NonNullList.withSize(4, ItemStack.EMPTY);
            for(int i = 0; i <= buf.readInt(); i++){
                stones.add(buf.readItem());
            }
            return new ShardRefinerRecipe(id, diamond, shard, stones);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, ShardRefinerRecipe recipe) {
            buf.writeItemStack(recipe.getDiamond(), false);
            buf.writeItemStack(recipe.getShard(), false);
            buf.writeInt(recipe.getStones().size());
            for(ItemStack stack : recipe.getStones()){
                buf.writeItemStack(stack, false);
            }
        }
    }
}