package net.AbraXator.chakral.capability;

import net.AbraXator.chakral.chakra.chakralMastery.ChakraMasteryTiers;
import net.minecraft.nbt.CompoundTag;

public class ChakraMasteryCap {
    private ChakraMasteryTiers tiers;

    public ChakraMasteryTiers getTiers(){
        return tiers;
    }

    public void setNecklace(ChakraMasteryTiers tiers){
        this.tiers = tiers;
    }

    public void copyFrom(ChakraMasteryCap cap){
        this.tiers = cap.getTiers();
    }

    public void saveNBT(CompoundTag tag){
        if(tiers != null){
            tag.putString("player.chakra.mastery", tiers.name());
        }else {
            tag.putString("player.chakra.mastery", "");
        }
    }

    public void loadNBT(CompoundTag tag){
        if(tag.getString("player.chakra.mastery").equals("")){
            tiers = null;
        }else{
            tiers = ChakraMasteryTiers.valueOf(tag.getString("player.chakra.mastery").toUpperCase());
        }
    }
}
