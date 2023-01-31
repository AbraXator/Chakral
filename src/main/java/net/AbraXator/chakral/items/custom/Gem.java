package net.AbraXator.chakral.items.custom;

import net.AbraXator.chakral.chakra.*;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

public class Gem extends Item {
    private ChakraType type;
    private ChakraStrength strength;

    public Gem(Properties pProperties, ChakraType type, ChakraStrength strength) {
        super(pProperties);
        this.type = type;
        this.strength = strength;
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
    }
}