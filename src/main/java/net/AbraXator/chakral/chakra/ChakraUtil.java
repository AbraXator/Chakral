package net.AbraXator.chakral.chakra;

import net.AbraXator.chakral.items.custom.Gem;
import net.AbraXator.chakral.items.custom.NecklaceItem;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.List;

public class ChakraUtil {
    public static void addChakra(ItemStack stack, Chakra chakra, int slot){
        Item item = stack.getItem();
        if(item instanceof NecklaceItem necklaceItem){
            CompoundTag tag = stack.getTag();

            if(tag == null){
                tag = new CompoundTag();
                stack.setTag(tag);
            }

            tag.put("Stone" + slot, stack.serializeNBT());
        }
    }

    public static void removeAugment(ItemStack itemStack, int slot){
        CompoundTag tag = itemStack.getTag();
        if(tag == null){
            return;
        }

        Item item = itemStack.getItem();
        if(item instanceof NecklaceItem necklaceItem){
            if(tag.contains("Stone" + slot)){
                tag.remove("Stone" + slot);
            }
        }
    }

    public static Chakra getChakra(ItemStack itemStack, int index){
        CompoundTag tag = itemStack.getTag();
        if(tag == null) return null;

        Item item = itemStack.getItem();
        if(item instanceof NecklaceItem necklaceItem){
            if(tag.contains("Stone" + index)){
                return ((IChakraProvider) tag.get("Stone" + index)).getChakra();
            }
        }

        return null;
    }
}

