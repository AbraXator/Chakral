package net.AbraXator.chakral.chakra;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

import java.util.List;

public abstract class Chakra {
    public final Item stone;
    public final ChakraType type;
    public ItemStack necklace;
    public int cooldown;
    public int maxCooldown;

    protected Chakra(Item stone, ChakraType type){
        this.stone = stone;
        this.type = type;
    }

    public Item getItem(){
        return stone;
    }

    public ChakraType getType(){
        return type;
    }

    public ItemStack getNecklace(){
        return necklace;
    }

    public List<Item> stones(){
        if (necklace != null && necklace.hasTag()) {
            CompoundTag tag = necklace.getTag();
            Item stone1 = tag.get("Stone1") != null || ItemStack.of(tag.getCompound("Stone1")).is(Items.AIR)
                    ? ItemStack.of(tag.getCompound("Stone1")).getItem() : Items.AIR;
            Item stone2 = tag.get("Stone2") != null || ItemStack.of(tag.getCompound("Stone2")).is(Items.AIR)
                    ? ItemStack.of(tag.getCompound("Stone2")).getItem() : Items.AIR;
            Item stone3 = tag.get("Stone3") != null || ItemStack.of(tag.getCompound("Stone3")).is(Items.AIR)
                    ? ItemStack.of(tag.getCompound("Stone3")).getItem() : Items.AIR;
            Item stone4 = tag.get("Stone4") != null || ItemStack.of(tag.getCompound("Stone4")).is(Items.AIR)
                    ? ItemStack.of(tag.getCompound("Stone4")).getItem() : Items.AIR;
            List<Item> list = List.of(stone1, stone2, stone3, stone4);
            return list;
        }
        return null;
    }

    public boolean isEnabled(Chakra chakra){
        return chakra.stones() != null && chakra.getItem() != null && stones().contains(chakra.getItem());
    }

    public void setMaxCooldown(int maxCooldown){
        this.maxCooldown = maxCooldown;
    }

    public void setCooldown(int cooldown){
        this.cooldown = cooldown;
    }

    public void onFunctionKeyPress(Player player, Level level){}

    public void onFunctionKeyPressUpgraded(Player player, Level level){}

    boolean isUpgraded(ChakraType type){
        return stones().contains(type.getTier4(type));
    }

    public void tick(Player player, Level level){}

    public void onEquip(Player player, Level level){}

    public void onEquipUpgraded(Player player, Level level){}

    public void onUnequip(Player player, Level level){}

    public void onUnequipUpgraded(Player player, Level level){}
}
