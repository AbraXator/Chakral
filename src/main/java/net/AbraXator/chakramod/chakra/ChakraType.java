package net.AbraXator.chakramod.chakra;

import net.minecraft.ChatFormatting;

//TODO: change some chakra names
public enum ChakraType {
    CROWN,
    THIRD_EYE,
    THROAT,
    HEART,
    SOLAR,
    SACRAL,
    ROOT;

    public ChakraType getChakraFromString(String string){
        if(string.matches("crown")){
            return CROWN;
        }
        if(string.matches("third_eye")){
            return THIRD_EYE;
        }
        if(string.matches("throat")){
            return THROAT;
        }
        if(string.matches("heart")){
            return HEART;
        }
        if(string.matches("solar")){
            return SOLAR;
        }
        if(string.matches("sacral")){
            return SACRAL;
        }
        if(string.matches("root")){
            return ROOT;
        }
        return null;
    }

    public String ChakraToString(ChakraType chakraType){
        switch (chakraType){
            case CROWN: return "CROWN";
            case THIRD_EYE: return "THIRD_EYE";
            case THROAT: return "THROAT";
            case HEART: return "HEART";
            case SOLAR: return "SOLAR";
            case SACRAL: return "SACRAL";
            case ROOT: return "ROOT";
            default: return "";
        }
    }

    public ChatFormatting getColorFromChakra(ChakraType chakraType){
        switch (chakraType){
            case CROWN: return ChatFormatting.DARK_PURPLE;
            case THIRD_EYE: return ChatFormatting.BLUE;
            case THROAT: return ChatFormatting.AQUA;
            case HEART: return ChatFormatting.GREEN;
            case SOLAR: return ChatFormatting.YELLOW;
            case SACRAL: return ChatFormatting.GOLD;
            case ROOT: return ChatFormatting.RED;
            default: return ChatFormatting.DARK_GRAY;
        }
    }
}
