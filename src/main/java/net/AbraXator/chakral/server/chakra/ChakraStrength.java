package net.AbraXator.chakral.server.chakra;

import net.AbraXator.chakral.server.init.ModTags;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.TagKey;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.Item;

public enum ChakraStrength implements StringRepresentable {
    FAINT("faint", 1, Component.translatable("chakra_strength.faint")),
    WEAKENED("weakened", 2, Component.translatable("chakra_strength.weakened")),
    POWERFUL("powerful", 3, Component.translatable("chakra_strength.powerful")),
    ENLIGHTENED("enlightened", 4, Component.translatable("chakra_strength.enlightened"));

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
    private Component localizedName;

    ChakraStrength(String p_156018_, int index, Component localizedName) {
        this.name = p_156018_;
        this.index = index;
        this.localizedName = localizedName;
    }

    public static TagKey<Item> getTag(ChakraStrength strenght){
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

    public Component getLocalizedName() {
        return localizedName;
    }

    public int getIndex() {
        return index;
    }

    public boolean is(ChakraStrength strenght){
        return this.equals(strenght);
    }
}
