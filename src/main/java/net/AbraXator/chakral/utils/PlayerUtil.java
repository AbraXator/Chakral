package net.AbraXator.chakral.utils;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class PlayerUtil {
    public static void addItemToInventory(Player player, ItemStack stack){
        if(player.getInventory().getFreeSlot() == -1){
            player.drop(stack, false);
        }else {
            player.addItem(stack);
        }
    }
}
