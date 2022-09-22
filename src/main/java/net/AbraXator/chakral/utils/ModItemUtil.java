package net.AbraXator.chakral.utils;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItemUtil {
    public static Item getItemFromString(String itemName){
        return ForgeRegistries.ITEMS.getValue(new ResourceLocation("chakral", itemName.replace("[", "").replace("]", "").replace(" ", "_").toLowerCase()));
    }
}
