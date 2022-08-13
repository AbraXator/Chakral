package net.AbraXator.chakramod.integration.shard_refiner;

import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Unmodifiable;

import java.util.List;

public interface IJeiShardRefinerRecipe {
    @Unmodifiable
    List<ItemStack> fuel();

    @Unmodifiable
    List<ItemStack> input();

    List<ItemStack> output();
}
