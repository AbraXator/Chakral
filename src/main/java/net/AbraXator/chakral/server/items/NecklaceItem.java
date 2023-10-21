package net.AbraXator.chakral.server.items;

import net.AbraXator.chakral.server.chakra.ChakraUtil;
import net.AbraXator.chakral.server.init.ModItems;
import net.AbraXator.chakral.server.init.ModKeyBindings;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class NecklaceItem extends Item implements StoneHoldingItem {
    public final int amountOfStones;

    public NecklaceItem(Properties pProperties, int amountOfStones) {
        super(pProperties);
        this.amountOfStones = amountOfStones;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        MutableComponent key = Component.literal(ModKeyBindings.NECKLACE_EQUIP_KEY.getKey().getDisplayName().getString().toUpperCase()).withStyle(ChatFormatting.BOLD);
        List<ChatFormatting> necklaceFormatting = necklaceFormatting(pStack);
        MutableComponent necklaceComponent = Component.literal(pStack.getDisplayName().getString());
        MutableComponent component = Component.translatable("necklace.equip", necklaceComponent, key).withStyle(ChatFormatting.GOLD);
        pTooltipComponents.add(component);
        ChakraUtil.stoneIndexInSlot(pStack).forEach((itemStack, integer) -> {
            if(itemStack.getItem() instanceof ChakraItem chakraItem){
                pTooltipComponents.add(ChakraUtil.getNameWithChakraColor(chakraItem.getChakra(), itemStack));
            }
        });
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

    @Override
    public int stonesAmount() {
        return amountOfStones;
    }
}
