package net.AbraXator.chakramod.items.custom;

import net.AbraXator.chakramod.ChakraMod;
import net.AbraXator.chakramod.utils.ChakraType;
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

    public Gem(Properties pProperties, ChakraType chakra) {
        super(pProperties);
        this.chakraType = chakra;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        //Component chakraComponent = Component.literal(chakraType.ChakraToString(chakraType)).withStyle(chakraType.getColorFromChakra(chakraType));
        pTooltipComponents.add(Component.literal("Chakra: " + chakraType).withStyle(chakraType.getColorFromChakra(chakraType)));
        pTooltipComponents.add(Component.literal("<Hold shift for more info>").withStyle(ChatFormatting.GOLD));
        if(Screen.hasShiftDown()){

        }
    }
}
