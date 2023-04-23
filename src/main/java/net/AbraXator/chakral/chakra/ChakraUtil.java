package net.AbraXator.chakral.chakra;

import net.AbraXator.chakral.capability.NecklaceCapProvider;
import net.AbraXator.chakral.items.ChakraItem;
import net.AbraXator.chakral.items.NecklaceItem;
import net.AbraXator.chakral.items.StoneHoldingItem;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentUtils;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static net.AbraXator.chakral.chakra.ChakraRegistry.CHAKRAS;

public class ChakraUtil {
    public static void addChakra(ItemStack necklace, ItemStack stone, int slot){
        if(necklace.getItem() instanceof NecklaceItem){
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
            ItemStack stone = ItemStack.of(tag.getCompound("Stone" + index));
            if(stone.getItem() instanceof ChakraItem chakraItem){
                return chakraItem.getChakra();
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
                if(ItemStack.of(itemStack.getTagElement("Stone" + i)).getItem() instanceof ChakraItem chakraItem){
                    list.add(chakraItem);
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

    public static Map<Chakra, ResourceLocation> createIds(){
        Map<Chakra, ResourceLocation> map = new LinkedHashMap<>();
        CHAKRAS.getEntries().forEach(chakraRegistryObject -> {
            map.put(chakraRegistryObject.get(), chakraRegistryObject.getId());
        });
        return map;
    }

    public static ResourceLocation getIdForChakra(Chakra chakra){
        return createIds().get(chakra);
    }

    public static int getColorOfNecklace(ItemStack itemStack, int index){
        if(getChakra(itemStack, index) != null){
            Chakra chakra = getChakra(itemStack, index);
            List<Style> colorList = chakra.getColors();
            Style colorStyle = colorList.size() % 2 == 0 ? colorList.get(colorList.size() / 2) : colorList.get((colorList.size() / 2) - 1);
            return colorStyle.getColor().getValue();
        } else {
            return -1;
        }
    }

    public static Component getNameWithChakraColor(Chakra chakra, ItemStack pStack){
        List<Style> colors = chakra.getColors();
        String originalName = pStack.getItem().getName(pStack).getString();
        if(colors != null){
            int len = originalName.length();
            int temp = 0;
            int chars = Math.round(((float) (len / colors.size())));
            if(chars != 0) {
                List<Component> list = new ArrayList<>();
                int i = 0;
                int listGetter = 0;
                for (; i < len; i += chars) {
                    int end = i + chars;
                    listGetter = i / chars;
                    if(end > len) {
                        while (!(end == len)) {
                            end--;
                        }
                    }
                    if (!(listGetter >= colors.size())) {
                        list.add(Component.literal(originalName.substring(i, end)).withStyle(colors.get(listGetter)));
                    }
                    temp++;
                }

                Component newName = ComponentUtils.formatList(list, Component.empty());
                if(newName.getString().length() < len){
                    while(listGetter != colors.size() - 1){
                        listGetter--;
                    }
                    list.add(Component.literal(originalName.substring(newName.getString().length(), len)).withStyle(colors.get(listGetter)));
                    newName = ComponentUtils.formatList(list, Component.empty());
                }
                return newName;
            }
        }

        return Component.translatable("tooltip.chakral.empty");
    }
}

