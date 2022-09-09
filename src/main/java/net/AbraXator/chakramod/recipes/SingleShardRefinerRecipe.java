package net.AbraXator.chakramod.recipes;

import com.google.gson.JsonObject;
import net.AbraXator.chakramod.ChakraMod;
import net.AbraXator.chakramod.blocks.ModBlocks;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class SingleShardRefinerRecipe extends SingleItemRecipe {

    public SingleShardRefinerRecipe(RecipeType<?> pType, RecipeSerializer<?> pSerializer, ResourceLocation pId, String pGroup, Ingredient pIngredient, ItemStack pResult) {
        super(pType, pSerializer, pId, pGroup, pIngredient, pResult);
    }

    @Override
    public boolean matches(Container pContainer, Level pLevel) {
        return false;
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(Container pContainer) {
        return super.getRemainingItems(pContainer);
    }

    @Override
    public ItemStack getToastSymbol() {
        return new ItemStack(ModBlocks.SHARD_REFINER.get());
    }
}
