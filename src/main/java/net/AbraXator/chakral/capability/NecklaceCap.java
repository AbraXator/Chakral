package net.AbraXator.chakral.capability;

import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

public class NecklaceCap {
    private NonNullList<ItemStack> stones;
    private ItemStack necklace;

    public NonNullList<ItemStack> getStones(){
        return stones;
    }

    public ItemStack getNecklace() {
        return !(necklace == null) ? necklace : ItemStack.EMPTY;
    }

    public void setStones(NonNullList<ItemStack> stones){
        this.stones = stones;
    }

    public void setNecklace(ItemStack necklace) {
        this.necklace = necklace;
    }

    public void setStone(ItemStack stone, int listIndex){
        if(stones == null){
            stones = NonNullList.withSize(4, ItemStack.EMPTY);
        }else {
            this.stones.set(listIndex, stone);
        }
    }

    public void copyFrom(NecklaceCap source){
        this.stones = source.stones;
        this.necklace = source.necklace;
    }

    public void saveNBT(CompoundTag tag){
        saveStones(tag);
    }

    private void saveStones(CompoundTag tag){
        if(stones == null){
            stones = NonNullList.withSize(4, ItemStack.EMPTY);
        }else {
            for(int i = 0; i < stones.size(); i++){
                tag.put("Stone" + i, stones.get(i).serializeNBT());
            }
        }
    }

    private void setNecklace(CompoundTag tag){
        if(necklace == null){
            necklace = ItemStack.EMPTY;
        }else {
            tag.put("Necklace", necklace.serializeNBT());
        }
    }

    public void loadNBT(CompoundTag tag){
        necklace = necklace == null ? ItemStack.EMPTY : ItemStack.of(tag.getCompound("Necklace"));
    }

    private void loadStones(CompoundTag tag){
        if(stones == null){
            stones = NonNullList.withSize(4, ItemStack.EMPTY);
        }else {
            for(int i = 0; i < 4; i++){
                stones.set(i, tag.get("Stone" + i) != null ? ItemStack.of(tag.getCompound("Stone" + i)) : ItemStack.EMPTY);
            }
        }
    }
}
