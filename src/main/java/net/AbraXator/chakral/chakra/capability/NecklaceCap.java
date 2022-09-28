package net.AbraXator.chakral.chakra.capability;

import net.minecraft.nbt.CompoundTag;

public class NecklaceCap {
    private String necklace;

    public String getNecklace(){
        return necklace;
    }

    public void setNecklace(String necklace){
        this.necklace = necklace;
    }

    public void copyFrom(NecklaceCap source){
        this.necklace = source.necklace;
    }

    public void saveNBT(CompoundTag tag){
        tag.putString("player.necklace", necklace);
    }

    public void loadNBT(CompoundTag tag){
        necklace = tag.getString("player.necklace");
    }
}
