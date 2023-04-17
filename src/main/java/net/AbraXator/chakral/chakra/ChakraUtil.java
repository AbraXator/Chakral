package net.AbraXator.chakral.chakra;

import net.AbraXator.chakral.capability.NecklaceCapProvider;
import net.AbraXator.chakral.client.gui.necklace.NecklaceInserterMenu;
import net.AbraXator.chakral.items.ChakraItem;
import net.AbraXator.chakral.items.NecklaceItem;
import net.AbraXator.chakral.items.StoneHoldingItem;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ChakraUtil {
    public static void addChakra(ItemStack necklace, ItemStack stone, int slot){
        if(necklace.getItem() instanceof NecklaceItem necklaceItem){
            CompoundTag tag = necklace.getTag();

            if(tag == null){
                tag = new CompoundTag();
                necklace.setTag(tag);
            }

            tag.put("Stone" + slot, stone.serializeNBT());
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

    public static Map<ItemStack, Integer> stoneIndexInSlot(ItemStack itemStack) {
        Map<ItemStack, Integer> map = new LinkedHashMap<>();

        if (itemStack.getItem() instanceof StoneHoldingItem stoneHoldingItem) {
            for (int i = 1; i <= stoneHoldingItem.stonesAmount(); i++) {
                ItemStack stone = ItemStack.of(itemStack.getTagElement("Stone" + i));
                map.put(stone, i);
            }

            return map;
        }

        return map;
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

