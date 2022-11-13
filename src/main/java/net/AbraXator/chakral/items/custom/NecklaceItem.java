package net.AbraXator.chakral.items.custom;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.stream.Stream;

public class NecklaceItem extends Item {
    public NecklaceItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if (pStack.hasTag()) {
            CompoundTag tag = pStack.getTag();
            Component stone1 = tag.get("Stone1") != null ? ItemStack.of(tag.getCompound("Stone1")).getDisplayName() : Component.literal("");
            Component stone2 = tag.get("Stone2") != null ? ItemStack.of(tag.getCompound("Stone2")).getDisplayName() : Component.literal("");
            Component stone3 = tag.get("Stone3") != null ? ItemStack.of(tag.getCompound("Stone3")).getDisplayName() : Component.literal("");
            Component stone4 = tag.get("Stone4") != null ? ItemStack.of(tag.getCompound("Stone4")).getDisplayName() : Component.literal("");
            pTooltipComponents.add(stone1);
            pTooltipComponents.add(stone2);
            pTooltipComponents.add(stone3);
            pTooltipComponents.add(stone4);
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    }
}
