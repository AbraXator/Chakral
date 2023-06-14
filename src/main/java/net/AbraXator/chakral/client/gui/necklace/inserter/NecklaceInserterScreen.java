package net.AbraXator.chakral.client.gui.necklace.inserter;

import com.mojang.blaze3d.systems.RenderSystem;
import net.AbraXator.chakral.Chakral;
import net.AbraXator.chakral.client.gui.MouseUtil;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class NecklaceInserterScreen extends AbstractContainerScreen<NecklaceInserterMenu> {
    //public static ItemStack itemStack = NecklaceSlotterBlockEntity.getStoneInSlot();
    private static final ResourceLocation TEXTURE_NETHERITE =
            new ResourceLocation(Chakral.MOD_ID, "textures/gui/container/necklace_inserter_netherite.png");
    private static final ResourceLocation TEXTURE_RAINBOW =
            new ResourceLocation(Chakral.MOD_ID, "textures/gui/container/necklace_inserter_rainbow.png");
    private static final ResourceLocation INVENTIORY =
            new ResourceLocation(Chakral.MOD_ID, "textures/gui/container/inventory.png");
    private static final int INVENTORY_Y = 85;

    public NecklaceInserterScreen(NecklaceInserterMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1, 1, 1, 1);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        if(this.menu.isRainbow){
            guiGraphics.blit(TEXTURE_RAINBOW, x, y, 0, 0, imageWidth, imageHeight);
        }else {
            guiGraphics.blit(TEXTURE_NETHERITE, x, y, 0, 0, imageWidth, imageHeight);
        }

        if(this.menu.isRainbow){
            if(isMouseAboveArea(pMouseX, pMouseY, x, y, 16, 11, 20, 20)){
                guiGraphics.blit(TEXTURE_RAINBOW, x + 16, y + 11, 196, 40, 20, 20);
            }
        }else {
            if(isMouseAboveArea(pMouseX, pMouseY, x, y, 140, 11, 20, 20)){
                guiGraphics.blit(TEXTURE_NETHERITE, x + 140, y + 11, 176, 40, 20, 20);
            }
        }
        guiGraphics.blit(INVENTIORY, x, y + 125, 0, 0, imageWidth, INVENTORY_Y);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        renderBackground(guiGraphics);
        super.render(guiGraphics, pMouseX, pMouseY, pPartialTick);
        renderTooltip(guiGraphics, pMouseX, pMouseY);
    }

    @Override
    public boolean mouseClicked(double pMouseX, double pMouseY, int pButton) {
        final int x = (width - imageWidth) / 2;
        final int y = (height - imageHeight) / 2;
        if(menu.isAbleToChange()){
            if(isMouseAboveArea((int) pMouseX, (int) pMouseY, x, y, 140, 11, 20, 20)){
                this.menu.isRainbow = true;
            }
            if(isMouseAboveArea((int) pMouseX, (int) pMouseY, x, y, 16, 11, 20, 20)){
                this.menu.isRainbow = false;
            }
        }
        return super.mouseClicked(pMouseX, pMouseY, pButton);
    }

    private boolean isMouseAboveArea(int pMouseX, int pMouseY, int x, int y, int offsetX, int offsetY, int width, int height) {
        if(menu.container.isEmpty()) {
            return MouseUtil.isMouseOver(pMouseX, pMouseY, x + offsetX, y + offsetY, width, height);
        }
        return false;
    }

    @Override
    protected void renderLabels(GuiGraphics p_281635_, int p_282681_, int p_283686_) {

    }
}