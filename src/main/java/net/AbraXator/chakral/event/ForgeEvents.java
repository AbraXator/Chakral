package net.AbraXator.chakral.event;

import net.AbraXator.chakral.Chakral;
import net.AbraXator.chakral.chakra.ChakraType;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Chakral.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ForgeEvents {

    @SubscribeEvent
    public static void onItemTooltip(ItemTooltipEvent itemTooltipEvent) {
        ChakraType chakraType = null;
        //Component chakraComponent;
        if(itemTooltipEvent.getItemStack().is(Items.AMETHYST_SHARD)){
            chakraType = ChakraType.CROWN;
            //chakraComponent = Component.literal(chakraType.ChakraToString(chakraType)).withStyle(chakraType.getColorFromChakra(chakraType));
            itemTooltipEvent.getToolTip().add(Component.literal("Gems: " + chakraType).withStyle(chakraType.getColorFromChakra(chakraType)));
            itemTooltipEvent.getToolTip().add(Component.literal("<Hold shift for more info>").withStyle(ChatFormatting.GOLD));
        }
        if(itemTooltipEvent.getItemStack().is(Items.LAPIS_LAZULI)){
            chakraType = ChakraType.THIRD_EYE;
            //chakraComponent = Component.literal(chakraType.ChakraToString(chakraType)).withStyle(chakraType.getColorFromChakra(chakraType));
            itemTooltipEvent.getToolTip().add(Component.literal("Gems: " + chakraType).withStyle(chakraType.getColorFromChakra(chakraType)));
            itemTooltipEvent.getToolTip().add(Component.literal("<Hold shift for more info>").withStyle(ChatFormatting.GOLD));
        }
        if(itemTooltipEvent.getItemStack().is(Items.QUARTZ)){
            chakraType = ChakraType.CROWN;
            //chakraComponent = Component.literal(chakraType.ChakraToString(chakraType)).withStyle(chakraType.getColorFromChakra(chakraType));
            itemTooltipEvent.getToolTip().add(Component.literal("Gems: " + chakraType).withStyle(chakraType.getColorFromChakra(chakraType)));
            itemTooltipEvent.getToolTip().add(Component.literal("<Hold shift for more info>").withStyle(ChatFormatting.GOLD));
        }
    }
}
