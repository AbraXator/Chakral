package net.AbraXator.chakramod.utils;

import net.minecraft.ChatFormatting;
import net.minecraft.world.phys.Vec3;
import org.w3c.dom.css.RGBColor;

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

    public ChatFormatting getColorFromChakra(ChakraType type){
        switch (type){
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
