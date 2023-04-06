package net.AbraXator.chakral.items.custom;

import net.AbraXator.chakral.items.ModItems;
import net.AbraXator.chakral.utils.KeyBindings;
import net.minecraft.ChatFormatting;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class NecklaceItem extends Item {
    public NecklaceItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        MutableComponent key = Component.literal(KeyBindings.NECKLACE_EQUIP_KEY.getKey().getDisplayName().getString().toUpperCase()).withStyle(ChatFormatting.BOLD);
        ItemStack necklace = this.asItem().getDefaultInstance();
        List<ChatFormatting> necklaceFormatting = necklaceFormatting(necklace);
        MutableComponent necklaceComponent = Component.literal(necklace.getDisplayName().getString());
        MutableComponent component = Component.translatable("necklace.equip", necklaceComponent, key).withStyle(ChatFormatting.GOLD);
        pTooltipComponents.add(component);
    }

    public List<ChatFormatting> necklaceFormatting(ItemStack necklace){
        if(necklace.is(ModItems.GOLDEN_NECKLACE.get())){
            return List.of(ChatFormatting.GOLD);
        }
        if(necklace.is(ModItems.DIAMOND_NECKLACE.get())){
            return List.of(ChatFormatting.AQUA);
        }
        if(necklace.is(ModItems.NETHERITE_NECKLACE.get())){
            return List.of(ChatFormatting.DARK_GRAY);
        }
        if(necklace.is(ModItems.RAINBOW_NECKLACE.get())){
            return List.of(ChatFormatting.RED, ChatFormatting.GOLD, ChatFormatting.YELLOW, ChatFormatting.GREEN, ChatFormatting.BLUE, ChatFormatting.LIGHT_PURPLE);
        }
        return List.of(ChatFormatting.WHITE);
    }
}
