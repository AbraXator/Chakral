package net.AbraXator.chakramod.items.custom;


import net.AbraXator.chakramod.utils.Chakras;
import net.AbraXator.chakramod.utils.ModTags;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GoldenNecklace extends Item {
    public GoldenNecklace(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {

        return super.use(pLevel, pPlayer, pUsedHand);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if(pStack.hasTag()){
            String stones = pStack.getTag().getString("chakramod.stones");
            pTooltipComponents.add(Component.literal(stones).withStyle(ChatFormatting.DARK_AQUA));
        }

        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        if(pStack.hasTag()){
            String nbtData = pStack.getTag().getString("chakramod.stones");
            nbtData = nbtData.replace("[", "").replace("]", "");
            switch (nbtData){
                case "amazonite":
                    Chakras.amazonite(pEntity);
                case "rhodonite":
                    Chakras.rhodonite(((Player) pEntity));
            }
        }
    }
}
