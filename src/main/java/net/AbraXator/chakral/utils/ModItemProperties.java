package net.AbraXator.chakral.utils;

import net.AbraXator.chakral.chakra.ChakraType;
import net.AbraXator.chakral.items.ModItems;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.tags.ITag;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class ModItemProperties {
    public static void addCustomProperties(){
        for(ChakraType chakraType : ChakraType.values()){
            for(NecklaceType necklaceType : NecklaceType.values()){
                changeColors(necklaceType.getItem().get(), necklaceType.getNumber(), chakraType);
            }
        }
    }

    private static void changeColors(Item item, int number, ChakraType chakraType){
        ItemProperties.register(item, new ResourceLocation(chakraType.getSerializedName()), (itemStack, p_234979_, p_234980_, p_234981_) -> {
            ITag<Item> list = ForgeRegistries.ITEMS.tags().getTag(chakraType.getTagKey());
            return list.contains(evaluateItem(itemStack, number)) ? 1.0F : 0.0F;
        });
    }

    public static Item evaluateItem(ItemStack itemStack, int number){
        String s = "Stone" + number;
        return itemStack != null && itemStack.hasTag() && itemStack.getTag().get(s) != null ? ItemStack.of(itemStack.getTag().getCompound(s)).getItem() : ItemStack.EMPTY.getItem();
    }
}
