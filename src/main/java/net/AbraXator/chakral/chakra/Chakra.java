package net.AbraXator.chakral.chakra;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public abstract class Chakra {
    public final Item stone;
    public final ChakraType type;
    public final String description;

    protected Chakra(Item stone, ChakraType type, String description){
        this.stone = stone;
        this.type = type;
        this.description = description;
    }

    //Item getItem();

    //ItemStack getNecklace();

    //List<Item> stones();

    //boolean isUpgraded(ChakraType type){
    //    return stones().contains(type.getTier4(type));
    //}

    public void onEquip(Player player, Level level){

    }

    //void onEquipUpgraded();

    //void onUnequip();

    //public void onUnequipUpgraded();
}
