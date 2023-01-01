package net.AbraXator.chakral.items.custom;

import net.AbraXator.chakral.items.ModItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class NetheriteNecklace extends Item {
    public NetheriteNecklace(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        super.use(pLevel, pPlayer, pUsedHand);
        ItemStack necklace = pPlayer.getItemInHand(InteractionHand.MAIN_HAND);
        CompoundTag tag = new CompoundTag();
        tag.put("Stone3", ModItems.GARNET_SPESSARTINE.get().getDefaultInstance().serializeNBT());
        necklace.setTag(tag);
        return InteractionResultHolder.success(necklace);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if (pStack.hasTag()) {
            CompoundTag tag = pStack.getTag();
            Component stone1 = tag.get("Stone1") != null || ItemStack.of(tag.getCompound("Stone1")).is(Items.AIR)
                    ? ItemStack.of(tag.getCompound("Stone1")).getDisplayName() : Component.literal("Empty");
            Component stone2 = tag.get("Stone2") != null || ItemStack.of(tag.getCompound("Stone2")).is(Items.AIR)
                    ? ItemStack.of(tag.getCompound("Stone2")).getDisplayName() : Component.literal("Empty");
            Component stone3 = tag.get("Stone3") != null || ItemStack.of(tag.getCompound("Stone3")).is(Items.AIR)
                    ? ItemStack.of(tag.getCompound("Stone3")).getDisplayName() : Component.literal("Empty");
            pTooltipComponents.add(stone1);
            pTooltipComponents.add(stone2);
            pTooltipComponents.add(stone3);
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    }
}
