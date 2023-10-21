package net.AbraXator.chakral.client.gui.necklace;

import net.AbraXator.chakral.server.chakra.ChakraStrength;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.tags.ITag;

public class NecklaceSlotterUtil {
    public static boolean mayPlace(ChakraStrength strenght, ItemStack stack){
        return ForgeRegistries.ITEMS.tags().getTag(ChakraStrength.getTag(strenght)).contains(stack.getItem());
    }

    public static final void setChanged(ItemStack stone, ItemStack necklace, Item wantedNecklace, String tagName, ChakraStrength strenght){
        ITag<Item> list = ForgeRegistries.ITEMS.tags().getTag(ChakraStrength.getTag(strenght));
        if(list.contains(stone.getItem()) && necklace.is(wantedNecklace)){
            CompoundTag nbt = necklace.getOrCreateTag();
            nbt.put(tagName, stone.serializeNBT());
            necklace.setTag(nbt);
        }
    }

    public static final void onTake(ItemStack necklace, Item wantedNecklace, String tagName){
        if (necklace.is(wantedNecklace)) {
            necklace.addTagElement(tagName, ItemStack.EMPTY.serializeNBT());
        }
    }
}
