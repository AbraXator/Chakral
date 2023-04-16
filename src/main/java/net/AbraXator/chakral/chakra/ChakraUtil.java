package net.AbraXator.chakral.chakra;

import net.AbraXator.chakral.capability.NecklaceCapProvider;
import net.AbraXator.chakral.items.ChakraItem;
import net.AbraXator.chakral.items.NecklaceItem;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
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

    public static void removeChakra(ItemStack itemStack, int slot){
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

    public static List<ChakraItem> getChakras(ItemStack itemStack){
        CompoundTag tag = itemStack.getTag();
        if(tag == null) return List.of();

        Item item = itemStack.getItem();
        if(item instanceof NecklaceItem necklaceItem){
            List<ChakraItem> list = new ArrayList<>();
            for(int i = 1; i <= 4; i++){
                if(tag.contains("Stone" + i)){
                    list.add(((ChakraItem) ItemStack.of(itemStack.getTagElement("Stone" + i)).getItem()));
                }
            }

            return list;
        }

        return List.of();
    }

    public static List<Chakra> getChakrasFromPlayer(Player player){
        List<Chakra> chakras = new ArrayList<>();
        player.getCapability(NecklaceCapProvider.NECKLACE_CAP).ifPresent(necklaceCap -> {
            getChakras(necklaceCap.getNecklace()).forEach(chakraItem -> {
                chakras.add(chakraItem.getChakra());
            });
        });
        return chakras;
    }
}

