package net.AbraXator.chakral.capability;

import net.minecraft.nbt.CompoundTag;

public class AdditionalHealthCap {
    private float health = maxHealth;
    public static int maxHealth = 10;

    public float getHealth(){
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void copyFrom(AdditionalHealthCap source){
        this.health = source.health;
    }

    public void saveNBT(CompoundTag tag){
        if(health <= maxHealth){
            tag.putFloat("player.addHealth", health);
        }else {
            tag.putFloat("player.addHealth", maxHealth);
        }
    }

    public void loadNBT(CompoundTag tag){
        health = tag.getInt("player.addHealth");
    }
}
