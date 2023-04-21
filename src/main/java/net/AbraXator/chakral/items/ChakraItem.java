package net.AbraXator.chakral.items;

import net.AbraXator.chakral.chakra.Chakra;
import net.AbraXator.chakral.chakra.ChakraStrength;
import net.AbraXator.chakral.chakra.ChakraType;
import net.AbraXator.chakral.chakra.IChakraProvider;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.components.ChatComponent;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Supplier;

public class ChakraItem extends Item implements IChakraProvider {
    private ChakraType type;
    private ChakraStrength strength;
    private Supplier<Chakra> chakra;

    public ChakraItem(Properties pProperties, ChakraType type, ChakraStrength strength, Supplier<Chakra> chakra) {
        super(pProperties);
        this.type = type;
        this.strength = strength;
        this.chakra = chakra;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        Component typeComponent = Component.translatable("chakra_type");
        Component strengthComponent = Component.translatable("chakra_strength");
        pTooltipComponents.add(Component.literal(typeComponent.getString() + ": " + type.getLocalizedName().getString()).withStyle(type.getColorFromChakra(type)));
        pTooltipComponents.add(Component.literal(strengthComponent.getString() + ": " + strength.getLocalizedName().getString()).withStyle(ChakraStrength.getColorFromChakra(strength)));
        Component moreInfo = Component.literal("<Hold shift for more info>").withStyle(ChatFormatting.GOLD);
        pTooltipComponents.add(moreInfo);
        if (Screen.hasShiftDown()) {
            Component gemProperties = Component.translatable("tooltip.chakral." + pStack.getDescriptionId().replaceAll("item.chakral.", "")).withStyle(type.getColorFromChakra(type));
            if (gemProperties == null) {
                pTooltipComponents.remove(moreInfo);
                pTooltipComponents.add(Component.literal("WIP").withStyle(ChatFormatting.GOLD));
            } else {
                pTooltipComponents.remove(moreInfo);
                pTooltipComponents.add(gemProperties);
            }
        }

        if(getChakra().getColor() != null){
            String originalName = pStack.getHoverName().getString();
            pStack.setHoverName(Component.literal(originalName).withStyle(getChakra().getColor()));
        }
    }

    @Override
    public Chakra getChakra() {
        return this.chakra.get();
    }
}