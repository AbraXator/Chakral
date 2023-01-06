package net.AbraXator.chakral.capability;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

public class AdditionalHealthCap {
    private int health = 0;
    public int maxHealth = 10;

    public int getHealth(){
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void copyFrom(AdditionalHealthCap source){
        this.health = source.health;
    }

    public void saveNBT(CompoundTag tag){
        if(health <= maxHealth){
            tag.putInt("player.addHealth", health);
        }else {
            tag.putInt("player.addHealth", maxHealth);
        }
    }

    public void loadNBT(CompoundTag tag){
        health = tag.getInt("player.addHealth");
    }
}
