package net.AbraXator.chakral.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.AbraXator.chakral.Chakral;
import net.AbraXator.chakral.utils.MouseUtil;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class NecklaceInserterScreen extends AbstractContainerScreen<NecklaceInserterMenu> {
    //public static ItemStack itemStack = NecklaceSlotterBlockEntity.getStoneInSlot();
    private static final ResourceLocation TEXTURE_NETHERITE =
            new ResourceLocation(Chakral.MOD_ID, "textures/gui/container/necklace_inserter_netherite.png");
    private static final ResourceLocation TEXTURE_RAINBOW =
            new ResourceLocation(Chakral.MOD_ID, "textures/gui/container/necklace_inserter_rainbow.png");

    private final int textureXSize;
    private final int textureYSize;

    public NecklaceInserterScreen(NecklaceInserterMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
        this.leftPos = 0;
        this.topPos = 0;
        this.imageWidth = 176;
        this.imageHeight = 210;
        this.width = 176;
        this.height = 210;
        this.textureXSize = 320;
        this.textureYSize = 320;
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
        blit(pPoseStack, x, y, 0, 0, imageWidth, imageHeight, textureXSize, textureYSize);

        Item stone = this.menu.container.getItem(1).getItem();

        if(this.menu.isRainbow){
            if(isMouseAboveArea((int) pMouseX, (int) pMouseY, x, y, 16, 13, 20, 20)){
                this.blit(pPoseStack, x + 16, y + 11, 14, 198, 22, 22);
            }
        }else {
            if(isMouseAboveArea((int) pMouseX, (int) pMouseY, x, y, 140, 11, 20, 20)){
                this.blit(pPoseStack, x + 140, y + 11, 0, 250, 20, 20);
            }
        }
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
        if(isMouseAboveArea((int) pMouseX, (int) pMouseY, x, y, 16, 13, 20, 20)){
            this.menu.isRainbow = false;
        }
        return super.mouseClicked(pMouseX, pMouseY, pButton);
    }

    private boolean isMouseAboveArea(int pMouseX, int pMouseY, int x, int y, int offsetX, int offsetY, int width, int height) {
        return MouseUtil.isMouseOver(pMouseX, pMouseY, x + offsetX, y + offsetY, width, height);
    }
}
