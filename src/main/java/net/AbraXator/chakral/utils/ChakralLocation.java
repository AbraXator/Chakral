package net.AbraXator.chakral.utils;

import net.AbraXator.chakral.Chakral;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nullable;

public class ChakralLocation extends ResourceLocation {
    public ChakralLocation(String p_249394_) {
        super(Chakral.MOD_ID, p_249394_);
    }

    public static String asString(String path){
        return  (Chakral.MOD_ID + ":" + path);
    }
}
