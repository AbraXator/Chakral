package net.AbraXator.chakral.capability;

import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

public class NecklaceCap {
    private NonNullList<ItemStack> stones;

    public NonNullList<ItemStack> getStones(){
        return stones;
    }

    public void setStones(NonNullList<ItemStack> stones){
        this.stones = stones;
    }

    public void setStone(ItemStack stone, int listIndex){
        this.stones.set(listIndex, stone);
    }

    public void copyFrom(NecklaceCap source){
        this.stones = source.stones;
    }

    public void saveNBT(CompoundTag tag){
        if(stones == null){
            return;
        }else {
            for(int i = 0; i < stones.size(); i++){
                tag.put("Stone" + i, stones.get(i).serializeNBT());
            }
        }
    }

    public void loadNBT(CompoundTag tag){
        for(int i = 0; i < 4; i++){
            stones.set(i, tag.get("Stone" + i) != null ? ItemStack.of(tag.getCompound("Stone" + i)) : ItemStack.EMPTY);
        }
    }
}
