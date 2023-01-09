package net.AbraXator.chakral.screen.necklace;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.AbraXator.chakral.Chakral;
import net.AbraXator.chakral.utils.MouseUtil;
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
    protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1, 1, 1, 1);
        if(this.menu.isRainbow){
            RenderSystem.setShaderTexture(0, TEXTURE_RAINBOW);
        }else {
            RenderSystem.setShaderTexture(0, TEXTURE_NETHERITE);
        }
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        this.blit(pPoseStack, x, y, 0, 0, imageWidth, imageHeight);

        if(this.menu.isRainbow){
            if(isMouseAboveArea(pMouseX, pMouseY, x, y, 16, 11, 20, 20)){
                this.blit(pPoseStack, x + 16, y + 11, 196, 40, 20, 20);
            }
        }else {
            if(isMouseAboveArea(pMouseX, pMouseY, x, y, 140, 11, 20, 20)){
                this.blit(pPoseStack, x + 140, y + 11, 176, 40, 20, 20);
            }
        }
        RenderSystem.setShaderTexture(0, INVENTIORY);
        this.blit(pPoseStack, x, y + 125, 0, 0, imageWidth, INVENTORY_Y);
    }

    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        renderBackground(pPoseStack);
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        renderTooltip(pPoseStack, pMouseX, pMouseY);
    }

    @Override
    public boolean mouseClicked(double pMouseX, double pMouseY, int pButton) {
        final int x = (width - imageWidth) / 2;
        final int y = (height - imageHeight) / 2;
        if(isMouseAboveArea((int) pMouseX, (int) pMouseY, x, y, 140, 11, 20, 20)){
            this.menu.isRainbow = true;
        }
        if(isMouseAboveArea((int) pMouseX, (int) pMouseY, x, y, 16, 11, 20, 20)){
            this.menu.isRainbow = false;
        }
        return super.mouseClicked(pMouseX, pMouseY, pButton);
    }

    private boolean isMouseAboveArea(int pMouseX, int pMouseY, int x, int y, int offsetX, int offsetY, int width, int height) {
        return MouseUtil.isMouseOver(pMouseX, pMouseY, x + offsetX, y + offsetY, width, height);
    }

    @Override
    protected void renderLabels(PoseStack pPoseStack, int pMouseX, int pMouseY) {
        this.font.draw(pPoseStack, this.title, (this.imageWidth - this.font.width(this.title)) / 2f, 10, 0x404040);
    }

    //public void drawTexturedRect(int x, int y, int u, int v, int width, int height) {
    //    float f = 1F / (float)textureXSize;
    //    float f1 = 1F / (float)textureYSize;
    //    BufferBuilder bufferBuilder = Tesselator.getInstance().getBuilder();
    //    bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
    //    bufferBuilder.vertex(x, (y + height), 0).uv((u * f), (v + height) * f1).endVertex();
    //    bufferBuilder.vertex((x + width), (y + height), 0).uv((u + width) * f, (v + height) * f1);
    //    bufferBuilder.vertex((x + width), (y), 0).uv((u + width) * f, v * f1);
    //    bufferBuilder.vertex(x, y, 0).uv(u * f, v * f1);
    //    BufferUploader.drawWithShader(bufferBuilder.end());
    //}
}