package net.AbraXator.chakral.client.gui.chakralnexus.inventorybutton;

import net.AbraXator.chakral.client.gui.chakralnexus.ChakralNexusMenu;
import net.AbraXator.chakral.client.gui.chakralnexus.ChakralNexusScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Tuple;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.item.ItemStack;

public class ChakraInventoryButton extends ImageButton {
    private final AbstractContainerScreen<?> parentGUI;

    public ChakraInventoryButton(AbstractContainerScreen parentGUI, int pX, int pY, int pWidth, int pHeight, int pXTexStart, int pYTexStart, ResourceLocation pResourceLocation) {
        super(pX, pY, pWidth, pHeight, pXTexStart, pYTexStart, pResourceLocation, pButton -> {
            Minecraft minecraft = Minecraft.getInstance();
            if(minecraft.player != null){
                ItemStack itemStack = minecraft.player.containerMenu.getCarried();
                minecraft.player.containerMenu.setCarried(ItemStack.EMPTY);
                if(parentGUI instanceof ChakralNexusScreen){
                    InventoryScreen inventoryScreen = new InventoryScreen(minecraft.player);
                    minecraft.setScreen(inventoryScreen);
                    minecraft.player.containerMenu.setCarried(itemStack);
                    //PACKET
                }
                else{
                    if(parentGUI instanceof InventoryScreen inventoryScreen){
                        RecipeBookComponent recipeBookComponent = inventoryScreen.getRecipeBookComponent();
                        minecraft.player.openMenu(new SimpleMenuProvider((pContainerId, pPlayerInventory, pPlayer) -> new ChakralNexusMenu(pContainerId, pPlayerInventory), Component.translatable("chakral.screen.chakral_nexus")));
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
    public void renderWidget(GuiGraphics p_283502_, int p_281473_, int p_283021_, float p_282518_) {
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
        super.renderWidget(p_283502_, p_281473_, p_283021_, p_282518_);
    }
}
