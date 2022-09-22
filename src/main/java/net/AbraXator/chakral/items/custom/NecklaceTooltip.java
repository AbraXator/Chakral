package net.AbraXator.chakral.items.custom;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;

public class NecklaceTooltip {
    private final NonNullList<ItemStack> items;

    public NecklaceTooltip(NonNullList<ItemStack> pItems, int pWeight) {
        this.items = pItems;
    }

    public NonNullList<ItemStack> getItems() {
        return this.items;
    }
}
