package net.AbraXator.chakramod.integration;

import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Unmodifiable;

import java.util.List;

public interface INecklaceSlotterRecipe {
    @Unmodifiable
    ItemStack necklace();

    @Unmodifiable
    List<ItemStack> stone();
}
