package net.AbraXator.chakral.chakra;

import net.AbraXator.chakral.items.ModItems;
import net.AbraXator.chakral.utils.ModTags;
import net.minecraft.ChatFormatting;
import net.minecraft.tags.TagKey;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public enum ChakraStrength implements StringRepresentable {
    FAINT("faint", 0),
    WEAKENED("weakened", 1),
    POWERFUL("powerful", 2),
    ENLIGHTENED("enlightened", 3);

    public static ChatFormatting getColorFromChakra(ChakraStrength chakra){
        return switch (chakra){
            case ENLIGHTENED -> ChatFormatting.AQUA;
            case POWERFUL -> ChatFormatting.RED;
            case WEAKENED -> ChatFormatting.DARK_GREEN;
            case FAINT -> ChatFormatting.DARK_GRAY;
        };
    }

    private final String name;
    private final int index;

    ChakraStrength(String p_156018_, int index) {
        this.name = p_156018_;
        this.index = index;
    }

    public static TagKey<Item> getTag(ChakraStrength strenght){
        return switch (strenght){
            case FAINT -> ModTags.Items.FAINT;
            case WEAKENED -> ModTags.Items.WEAKENED;
            case POWERFUL -> ModTags.Items.POWERFUL;
            case ENLIGHTENED -> ModTags.Items.ENLIGHTENED;
        };
    }

    public int getIndex() {
        return index;
    }


    public String toString() {
        return this.name;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }

    public boolean is(ChakraStrength strenght){
        return this.equals(strenght);
    }
}
