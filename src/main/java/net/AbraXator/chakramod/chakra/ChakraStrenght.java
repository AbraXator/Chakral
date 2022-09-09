package net.AbraXator.chakramod.chakra;

import net.minecraft.ChatFormatting;

public enum ChakraStrenght {
    FAINT,
    WEAKENED,
    POWERFUL,
    ENLIGHTENED;

    public ChatFormatting getColorFromChakra(ChakraStrenght chakra){
        return switch (chakra){
            case ENLIGHTENED -> ChatFormatting.AQUA;
            case POWERFUL -> ChatFormatting.RED;
            case WEAKENED -> ChatFormatting.DARK_GREEN;
            case FAINT -> ChatFormatting.DARK_GRAY;
        };
    }
}
