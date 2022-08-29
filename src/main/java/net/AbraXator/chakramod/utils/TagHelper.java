package net.AbraXator.chakramod.utils;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

public class TagHelper {
    public static boolean isItemGem(Item stack){
        return ForgeRegistries.ITEMS.tags().getTag(ModTags.Items.GEMS).contains(stack);
    }

}
