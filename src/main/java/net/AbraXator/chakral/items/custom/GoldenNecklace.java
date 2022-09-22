package net.AbraXator.chakral.items.custom;


import net.AbraXator.chakral.chakra.Chakras;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GoldenNecklace extends Item {
    public boolean USED = false;
    public static boolean REVERT = false;

    public GoldenNecklace(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if(pStack.hasTag()){
            String stones = pStack.getTag().getString("chakral.stones");
            pTooltipComponents.add(Component.literal(stones).withStyle(ChatFormatting.DARK_AQUA));
        }else {
            pTooltipComponents.add(Component.literal("Empty").withStyle(ChatFormatting.GRAY));
        }
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    //public void inventoryNotTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected){
    //    if(InventoryUtil.hasPlayerStackInInventory(((Player) pEntity), pStack.getItem())) {
    //        System.out.println("***********************************************************************************");
    //        if (pStack.hasTag()) {
    //            String nbtData = pStack.getTag().getString("chakral.stones");
    //            nbtData = nbtData.replace("[", "").replace("]", "").toLowerCase();
    //            switch (nbtData){
    //                case "amber":
    //                    Chakras.amber(((Player) pEntity), pStack, pLevel);
    //                    System.out.println("AMBER");
    //            }
    //        }
    //    }
    //}

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {


        if(pStack.hasTag()){
            String nbtData = pStack.getTag().getString("chakral.stones");
            nbtData = nbtData.replace("[", "").replace("]", "").replace(" ", "_").toLowerCase();
            switch (nbtData){
                case "hag_stone":
                    Chakras.hag_stone((Player) pEntity);
            }
            if(nbtData.equals("blue_lace_agate") && !USED){
                Chakras.blue_lace_agate(((Player) pEntity));
                USED = true;
            }
            if(nbtData.equals("amazonite") && !USED){
                Chakras.amazonite(((Player) pEntity));
            }
            if(nbtData.equals("heliolite") && !USED){
                Chakras.heliolite(((Player) pEntity), pLevel);
            }
        }else {
            USED = false;
        }
    }
}
