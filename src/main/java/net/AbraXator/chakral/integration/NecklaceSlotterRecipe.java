package net.AbraXator.chakral.integration;

import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Unmodifiable;

import java.util.List;

public class NecklaceSlotterRecipe implements INecklaceSlotterRecipe{
    private final ItemStack necklace;
    private final List<ItemStack> stone;

    public NecklaceSlotterRecipe(ItemStack necklace, List<ItemStack> stone){
        this.necklace = necklace.copy();
        this.stone = List.copyOf(stone);
    }

    @Override
    public @Unmodifiable ItemStack necklace() {
        return null;
    }

    @Override
    public @Unmodifiable List<ItemStack> stone() {
        return null;
    }
}
