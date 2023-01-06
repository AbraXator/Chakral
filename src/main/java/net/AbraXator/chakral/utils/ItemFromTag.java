package net.AbraXator.chakral.utils;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ItemFromTag {
    public static Item stoneFromTag(ItemStack item, int number){
        String s = "Stone" + number;
        return item != null && item.hasTag() && item.getTag().get(s) != null ? ItemStack.of(item.getTag().getCompound(s)).getItem() : ItemStack.EMPTY.getItem();
    }
}
