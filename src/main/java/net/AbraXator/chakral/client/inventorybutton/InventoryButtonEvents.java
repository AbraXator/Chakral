package net.AbraXator.chakral.client.inventorybutton;

import net.AbraXator.chakral.Chakral;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.util.Tuple;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Chakral.MOD_ID, value = Dist.CLIENT)
public class InventoryButtonEvents {
    private int lastLeft;

    /*@SubscribeEvent
    public static void editInventory(ScreenEvent.Render.Pre event){
        if(event.getScreen() instanceof AbstractContainerScreen){
            AbstractContainerScreen<?> screen = ((AbstractContainerScreen<?>) event.getScreen());
            if(lastLeft != screen.getGuiLeft()){
                int diffLeft = screen.getGuiLeft() - lastLeft;
                lastLeft = screen.getGuiLeft();
                screen.children().stream().filter(IShiftingWidget.class::isInstance).map(IShiftingWidget.class::cast).forEach(iShiftingWidget -> iShiftingWidget.shiftLeft(diffLeft));
            }
            //if(event.getScreen() instanceof CreativeModeInventoryScreen){
            //    CreativeModeTab creativeModeTab = CreativeModeTab.Type.INVENTORY;
            //}
        }
    }*/

    public static void handleGUIInitPost(ScreenEvent.Init.Post event){
        Screen screen = event.getScreen();

        if(screen instanceof InventoryScreen || screen instanceof CreativeModeInventoryScreen){
            AbstractContainerScreen<?> gui = (AbstractContainerScreen<?>) screen;
            boolean isCreative = screen instanceof CreativeModeInventoryScreen;
            Tuple<Integer, Integer> offset = ChakralNexusScreen.getButtonOffset(isCreative);
            int x = offset.getA();
            int y = offset.getB();
            int size = isCreative ? 10 : 14;
            int textureOffsetX = isCreative ? 64 : 50;
            int yOffset = isCreative ? 68 : 83;
            event.addListener(new ChakraInventoryButton(gui, gui.getGuiLeft() + x, gui.getGuiTop() + y + yOffset, size, size, textureOffsetX, 0, size, ChakralNexusScreen.INVENTORY));
        }
    }
}
