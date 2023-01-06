package net.AbraXator.chakral.capability;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

public class NecklaceCap {
    private ItemStack necklace;

    public ItemStack getNecklace(){
        return necklace;
    }

    public void setNecklace(ItemStack necklace){
        this.necklace = necklace;
    }

    public void copyFrom(NecklaceCap source){
        this.necklace = source.necklace;
    }

    public void saveNBT(CompoundTag tag){
        if(necklace != null) {
            tag.put("player.necklace", necklace.serializeNBT());
        }else {
            tag.put("player.necklace", ItemStack.EMPTY.serializeNBT());
        }
    }

    public void loadNBT(CompoundTag tag){
        necklace = ItemStack.of(tag.getCompound("player.necklace"));
    }
}
