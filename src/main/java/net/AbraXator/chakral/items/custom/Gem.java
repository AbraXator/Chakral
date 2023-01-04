package net.AbraXator.chakral.items.custom;

import net.AbraXator.chakral.chakra.ChakraStrenght;
import net.AbraXator.chakral.chakra.ChakraType;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Gem extends Item {
    public ChakraType chakraType;
    public ChakraStrenght chakraStrenght;
    public String name;

    public Gem(Properties pProperties, ChakraType chakra, String name, ChakraStrenght chakraStrenght) {
        super(pProperties);
        this.chakraType = chakra;
        this.chakraStrenght = chakraStrenght;
        this.name = name;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.literal("Type: " + chakraType).withStyle(chakraType.getColorFromChakra(chakraType)));
        pTooltipComponents.add(Component.literal("Strength: " + chakraStrenght).withStyle(ChakraStrenght.getColorFromChakra(chakraStrenght)));
        Component moreInfo = Component.literal("<Hold shift for more info>").withStyle(ChatFormatting.GOLD);
        pTooltipComponents.add(moreInfo);
        if(Screen.hasShiftDown()){
            Component gemProperties = Component.translatable("tooltip.chakral." + name).withStyle(chakraType.getColorFromChakra(chakraType));
            if(gemProperties == null){
                pTooltipComponents.remove(moreInfo);
                pTooltipComponents.add(Component.literal("WIP").withStyle(ChatFormatting.GOLD));
            }else {
                pTooltipComponents.remove(moreInfo);
                pTooltipComponents.add(gemProperties);
            }
        }
    }
}
