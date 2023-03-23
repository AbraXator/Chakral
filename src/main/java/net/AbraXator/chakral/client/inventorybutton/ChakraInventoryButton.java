package net.AbraXator.chakral.client.inventorybutton;

import com.mojang.blaze3d.vertex.PoseStack;
import net.AbraXator.chakral.networking.ModMessages;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Tuple;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkHooks;

import java.awt.*;

public class ChakraInventoryButton extends ImageButton {
    private final AbstractContainerScreen<?> parentGUI;

    public ChakraInventoryButton(AbstractContainerScreen parentGUI, int pX, int pY, int pWidth, int pHeight, int pXTexStart, int pYTexStart, ResourceLocation pResourceLocation) {
        super(pX, pY, pWidth, pHeight, pXTexStart, pYTexStart, pResourceLocation, pButton -> {
            Minecraft minecraft = Minecraft.getInstance();
            if(minecraft.player != null){
                ItemStack itemStack = minecraft.player.containerMenu.getCarried();
                minecraft.player.containerMenu.setCarried(ItemStack.EMPTY);

                if(parentGUI instanceof ChakralNexusScreen){
                }
                else{
                    if(parentGUI instanceof InventoryScreen inventoryScreen){
                        RecipeBookComponent recipeBookComponent = inventoryScreen.getRecipeBookComponent();

                        if(recipeBookComponent.isVisible()){
                            recipeBookComponent.toggleVisibility();
                        }
                    }

                }
            }
        });
        this.parentGUI = parentGUI;
    }

    @Override
    public void renderWidget(PoseStack p_268099_, int p_267992_, int p_267950_, float p_268076_) {
        Tuple<Integer, Integer> offsets = ChakralNexusScreen.getButtonOffset(parentGUI instanceof CreativeModeInventoryScreen);
        this.setX(parentGUI.getGuiLeft() + offsets.getA());
        int yOffset = parentGUI instanceof CreativeModeInventoryScreen ? 68 : 83;
        this.setY(parentGUI.getGuiTop() + offsets.getB() + yOffset);

        if(parentGUI instanceof CreativeModeInventoryScreen gui){
            this.active = gui.isInventoryOpen();
            if(!gui.isInventoryOpen()){
                return;
            }
        }
        super.renderWidget(p_268099_, p_267992_, p_267950_, p_268076_);
    }
}
