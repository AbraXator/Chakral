package net.AbraXator.chakral.chakra;

import net.AbraXator.chakral.utils.ModTags;
import net.minecraft.ChatFormatting;
import net.minecraft.tags.TagKey;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.tags.ITag;

public enum ChakraStrenght implements StringRepresentable {
    FAINT("faint"),
    WEAKENED("weakened"),
    POWERFUL("powerful"),
    ENLIGHTENED("enlightened");

    public static ChatFormatting getColorFromChakra(ChakraStrenght chakra){
        return switch (chakra){
            case ENLIGHTENED -> ChatFormatting.AQUA;
            case POWERFUL -> ChatFormatting.RED;
            case WEAKENED -> ChatFormatting.DARK_GREEN;
            case FAINT -> ChatFormatting.DARK_GRAY;
        };
    }

    private final String name;

    private ChakraStrenght(String p_156018_) {
        this.name = p_156018_;
    }

    public static TagKey<Item> getTag(ChakraStrenght strenght){
        return switch (strenght){
            case FAINT -> ModTags.Items.FAINT;
            case WEAKENED -> ModTags.Items.WEAKENED;
            case POWERFUL -> ModTags.Items.POWERFUL;
            case ENLIGHTENED -> ModTags.Items.ENLIGHTENED;
        };
    }

    public String toString() {
        return this.name;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }
}
