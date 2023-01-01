package net.AbraXator.chakral.screen.necklace;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class NecklaceButton extends ImageButton {
    public NecklaceButton(AbstractContainerScreen<?> parentGui, int xIn, int yIn, int widthIn, int heightIn,
                          int textureOffsetX, int textureOffsetY, int yDiffText, ResourceLocation resource){
        super(xIn, yIn, widthIn, heightIn, textureOffsetX, textureOffsetY, yDiffText, resource, (button) -> {
            Minecraft mc = Minecraft.getInstance();
            if(mc.player != null){
                mc.player.containerMenu.setCarried(ItemStack.EMPTY);
            }
        });
    }
}
