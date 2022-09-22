package net.AbraXator.chakral.chakra;

import net.minecraft.ChatFormatting;
import net.minecraft.util.StringRepresentable;

public enum ChakraStrenght implements StringRepresentable {
    FAINT("faint"),
    WEAKENED("weakened"),
    POWERFUL("powerful"),
    ENLIGHTENED("enlightened");

    public ChatFormatting getColorFromChakra(ChakraStrenght chakra){
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

    public String toString() {
        return this.name;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }
}
