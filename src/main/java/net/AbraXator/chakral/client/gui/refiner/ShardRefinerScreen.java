package net.AbraXator.chakral.client.gui.refiner;

import com.mojang.blaze3d.systems.RenderSystem;
import net.AbraXator.chakral.Chakral;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ShardRefinerScreen extends AbstractContainerScreen<ShardRefinerMenu> {
    //public static ItemStack itemStack = NecklaceSlotterBlockEntity.getStoneInSlot();
    public static final ResourceLocation TEXTURE =
            new ResourceLocation(Chakral.MOD_ID, "textures/gui/container/mineral_refiner.png");

    public ShardRefinerScreen(ShardRefinerMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float pMouseX, int pMouseY, int pPartialTicks) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1, 1, 1, 1);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        guiGraphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight);
        int i = this.menu.data.get(0);
        int k = this.menu.data.get(3);
        if(i == 15 || i == 14 || i == 13){
            guiGraphics.blit(TEXTURE, x + 15, y + 33, 219, 0, 36, 36);
        }
        if(i == 12 || i == 11 || i == 10){
            guiGraphics.blit(TEXTURE, x + 15, y + 37, 219, 41, 29, 30);
        }
        if(i == 9 || i == 8 || i == 7){
            guiGraphics.blit(TEXTURE, x + 15, y + 41, 219, 82, 26, 27);
        }
        if(i == 6 || i == 5 || i == 4){
            guiGraphics.blit(TEXTURE, x + 15, y + 45, 219, 123, 23, 24);
        }
        if(i == 3 || i == 2 || i == 1){
            guiGraphics.blit(TEXTURE, x + 15, y + 49, 219, 164, 20, 21);
        }
        if(this.menu.isCrafting()){
            int j = this.menu.dataToInt();
            guiGraphics.blit(TEXTURE, x + 106, y + 36, 177, j, menu.getScaledProgress(), 16);
        }
        switch (this.menu.data.get(3)){
            case 0 -> guiGraphics.blit(TEXTURE,  x + 133, y + 16, 0, 166, 36, 54);
            case 1 -> guiGraphics.blit(TEXTURE,  x + 133, y + 16, 36, 166, 36, 54);
            case 2 -> guiGraphics.blit(TEXTURE,  x + 133, y + 16, 74, 166, 36, 54);
        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        renderBackground(guiGraphics);
        super.render(guiGraphics, pMouseX, pMouseY, pPartialTick);
        renderTooltip(guiGraphics, pMouseX, pMouseY);
    }
}