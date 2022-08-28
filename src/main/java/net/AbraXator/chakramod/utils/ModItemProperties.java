package net.AbraXator.chakramod.utils;

import net.AbraXator.chakramod.items.ModItems;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ModItemProperties {
    public static void addCustomProperties(){
        changeColors(ModItems.GOLDEN_NECKLACE.get());
    }

    private static void changeColors(Item item) {
        ItemProperties.register(item, new ResourceLocation("crown"), (itemStack, p_234979_, p_234980_, p_234981_) -> {
            List<String> listCrown = new ArrayList<>();
            Collections.addAll(listCrown, "Amethyst Quartz", "Lepidolite", "Labbradorite", "Moon Stone");
            String nbtData = itemStack.hasTag() ? itemStack.getTag().getString("chakramod.stones") : "";
            nbtData = nbtData.replace("[", "").replace("]", "");
            return itemStack != null && itemStack.hasTag() && listCrown.contains(nbtData) ? 1.0F : 0.0F;
        });
        ItemProperties.register(item, new ResourceLocation("heart"), (itemStack, p_234979_, p_234980_, p_234981_) -> {
            List<String> listHeart = new ArrayList<>();
            Collections.addAll(listHeart, "Rhodonite", "Amazonite", "Green Opal", "Malachite");
            String nbtData = itemStack.hasTag() ? itemStack.getTag().getString("chakramod.stones") : "";
            nbtData = nbtData.replace("[", "").replace("]", "");
            return itemStack != null && itemStack.hasTag() && listHeart.contains(nbtData) ? 1.0F : 0.0F;
        });
        ItemProperties.register(item, new ResourceLocation("root"), (itemStack, p_234979_, p_234980_, p_234981_) -> {
            List<String> listRoot = new ArrayList<>();
            Collections.addAll(listRoot, "Mahogany", "Black Onyx", "Tourmeline" , "Heliotrope");
            String nbtData = itemStack.hasTag() ? itemStack.getTag().getString("chakramod.stones") : "";
            nbtData = nbtData.replace("[", "").replace("]", "");
            return itemStack != null && itemStack.hasTag() && listRoot.contains(nbtData) ? 1.0F : 0.0F;
        });
        ItemProperties.register(item, new ResourceLocation("sacral"), (itemStack, p_234979_, p_234980_, p_234981_) -> {
            List<String> listSacral = new ArrayList<>();
            Collections.addAll(listSacral, "Garnet Spessartine", "Golden Tigers Eye", "Stillbite", "Carnelian");
            String nbtData = itemStack.hasTag() ? itemStack.getTag().getString("chakramod.stones") : "";
            nbtData = nbtData.replace("[", "").replace("]", "");
            return itemStack != null && itemStack.hasTag() && listSacral.contains(nbtData) ? 1.0F : 0.0F;
        });
        ItemProperties.register(item, new ResourceLocation("solar"), (itemStack, p_234979_, p_234980_, p_234981_) -> {
            List<String> listSoral = new ArrayList<>();
            Collections.addAll(listSoral, "Citrine", "Heliolite", "Amber", "Fire Opal");
            String nbtData = itemStack.hasTag() ? itemStack.getTag().getString("chakramod.stones") : "";
            nbtData = nbtData.replace("[", "").replace("]", "");
            return itemStack != null && itemStack.hasTag() && listSoral.contains(nbtData) ? 1.0F : 0.0F;
        });
        ItemProperties.register(item, new ResourceLocation("third_eye"), (itemStack, p_234979_, p_234980_, p_234981_) -> {
            List<String> listThirdEye = new ArrayList<>();
            Collections.addAll(listThirdEye, "Hag Stone", "Azurite", "Sugilite", "Dumortierite");
            String nbtData = itemStack.hasTag() ? itemStack.getTag().getString("chakramod.stones") : "";
            nbtData = nbtData.replace("[", "").replace("]", "");
            return itemStack != null && itemStack.hasTag() && listThirdEye.contains(nbtData) ? 1.0F : 0.0F;
        });
        ItemProperties.register(item, new ResourceLocation("throat"), (itemStack, p_234979_, p_234980_, p_234981_) -> {
            List<String> listThroat = new ArrayList<>();
            Collections.addAll(listThroat, "Blue Lace Agate", "Aquamarine", "Blue Howlite", "Kyanite");
            String nbtData = itemStack.hasTag() ? itemStack.getTag().getString("chakramod.stones") : "";
            nbtData = nbtData.replace("[", "").replace("]", "");
            return itemStack != null && itemStack.hasTag() && listThroat.contains(nbtData) ? 1.0F : 0.0F;
        });
    }
}
