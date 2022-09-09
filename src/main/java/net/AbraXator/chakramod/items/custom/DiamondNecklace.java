package net.AbraXator.chakramod.items.custom;


import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DiamondNecklace extends Item {
    public DiamondNecklace(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        return super.use(pLevel, pPlayer, pUsedHand);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if(pStack.hasTag()){
            if(!pStack.getTag().getString("chakramod.stones").isEmpty()){
                String stones = pStack.getTag().getString("chakramod.stones");
                pTooltipComponents.add(Component.literal(stones));
            }else {
                pTooltipComponents.add(Component.literal("Empty"));
            }
            if(!pStack.getTag().getString("chakramod.stones.two").isEmpty()){
                String stones2 = pStack.getTag().getString("chakramod.stones.two");
                pTooltipComponents.add(Component.literal(stones2));
            }else {
                pTooltipComponents.add(Component.literal("Empty"));
            }
        }else {
            pTooltipComponents.add(Component.literal("Empty").withStyle(ChatFormatting.GRAY));
            pTooltipComponents.add(Component.literal("Empty").withStyle(ChatFormatting.GRAY));
        }
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        if(pStack.hasTag()) {
            if (!pStack.getTag().getString("chakramod.stones").isEmpty()) {
                String stones = pStack.getTag().getString("chakramod.stones");
            } else {
            }
            if (!pStack.getTag().getString("chakramod.stones.two").isEmpty()) {
                String stones2 = pStack.getTag().getString("chakramod.stones.two");
            } else {
            }
        }
    }
}
