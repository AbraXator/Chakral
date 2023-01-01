package net.AbraXator.chakral.items.custom;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DiamondNecklace extends NecklaceItem {
    public DiamondNecklace(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        if (pStack.hasTag()) {
            CompoundTag tag = pStack.getTag();
            Component stone1 = tag.get("Stone1") != null || ItemStack.of(tag.getCompound("Stone1")).is(Items.AIR)
                    ? ItemStack.of(tag.getCompound("Stone1")).getDisplayName() : Component.literal("Empty");
            Component stone2 = tag.get("Stone2") != null || ItemStack.of(tag.getCompound("Stone2")).is(Items.AIR)
                    ? ItemStack.of(tag.getCompound("Stone2")).getDisplayName() : Component.literal("Empty");
            pTooltipComponents.add(stone1);
            pTooltipComponents.add(stone2);
        }
    }
}
