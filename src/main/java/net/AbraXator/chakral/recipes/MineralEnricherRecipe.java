package net.AbraXator.chakral.recipes;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.serialization.JsonOps;
import net.AbraXator.chakral.Chakral;
import net.minecraft.client.Minecraft;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

public class MineralEnricherRecipe implements Recipe<SimpleContainer> {
    private final ResourceLocation id;
    private final Ingredient input;
    private final ItemStack output;
    private final ItemStack dust;
    private final FluidStack fluidStack;

    public MineralEnricherRecipe(ResourceLocation id, Ingredient input, ItemStack output, ItemStack dust, FluidStack fluidStack){
        this.id = id;
        this.input = input;
        this.output = output;
        this.dust = dust;
        this.fluidStack = fluidStack;
    }

    @Override
    public boolean matches(SimpleContainer pContainer, Level pLevel) {
        return Arrays.stream(input.getItems()).toList().get(0).is(pContainer.getItem(2).getItem());
    }

    @Override
    public ItemStack assemble(SimpleContainer p_44001_, RegistryAccess p_267165_) {
        return output;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess p_267052_) {
        return output.copy();
    }

    public ItemStack getInput(){
        return input.getItems()[1].copy();
    }

    public ItemStack getDust() {
        return dust.copy();
    }

    public FluidStack getFluidStack() {
        return fluidStack.copy();
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
                new ResourceLocation(Chakral.MOD_ID, "enriching");

        @Override
        public MineralEnricherRecipe fromJson(ResourceLocation pRecipeId, JsonObject pSerializedRecipe) {
            Ingredient input = Ingredient.fromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "input"));
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "output"));
            ItemStack dust = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "dust"));
            FluidStack fluidStack = fluidFromJson(pSerializedRecipe.get("fluid").getAsJsonObject());

            return new MineralEnricherRecipe(pRecipeId, input, output, dust, fluidStack);
        }

        @Override
        public @Nullable MineralEnricherRecipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf buf) {
            Ingredient input = Ingredient.fromNetwork(buf);
            ItemStack output = buf.readItem();
            ItemStack dust = buf.readItem();
            FluidStack fluidStack = buf.readFluidStack();
            return new MineralEnricherRecipe(pRecipeId, input, output, dust, fluidStack);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, MineralEnricherRecipe pRecipe) {
            pRecipe.input.toNetwork(buf);
            buf.writeItemStack(pRecipe.getInput(), false);
            buf.writeItemStack(pRecipe.getResultItem(Minecraft.getInstance().level.getServer().registryAccess()), false);
            buf.writeItemStack(pRecipe.getDust(), false);
            buf.writeFluidStack(pRecipe.getFluidStack());
        }

        private FluidStack fluidFromJson(JsonObject jsonObject){
            return FluidStack.CODEC.decode(JsonOps.INSTANCE, jsonObject).result().orElseThrow().getFirst();
        }
    }
}
