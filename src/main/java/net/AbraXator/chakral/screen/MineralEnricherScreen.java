package net.AbraXator.chakral.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.AbraXator.chakral.Chakral;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.resource.ResourceCacheManager;

public class MineralEnricherScreen extends AbstractContainerScreen<MineralEnricherMenu> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(Chakral.MOD_ID, "textures/gui/container/mineral_enricher.png");

    public MineralEnricherScreen(MineralEnricherMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        this.blit(pPoseStack, x, y, 0, 0, imageWidth, imageHeight);
        int j = menu.data.get(2);
         switch (j){
            case 1 -> this.blit(pPoseStack, x + 73, y + 38, 200, 0, 24, 24);
            case 2 -> this.blit(pPoseStack, x + 73, y + 38, 200, 24, 24, 24);
            case 3 -> this.blit(pPoseStack, x + 73, y + 38, 200, 48, 24, 24);
            case 4 -> this.blit(pPoseStack, x + 73, y + 38, 200, 72, 24, 24);
            case 5 -> this.blit(pPoseStack, x + 73, y + 38, 200, 96, 24, 24);
            case 6 -> this.blit(pPoseStack, x + 73, y + 38, 200, 120, 24, 24);
            case 7 -> this.blit(pPoseStack, x + 73, y + 38, 200, 144, 24, 24);
            case 8 -> this.blit(pPoseStack, x + 73, y + 38, 200, 168, 24, 24);
            case 9 -> this.blit(pPoseStack, x + 73, y + 38, 200, 192, 24, 24);
            case 10 -> this.blit(pPoseStack, x + 73, y + 38, 200, 216, 24, 24);
        }
    }

    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        renderBackground(pPoseStack);
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        renderTooltip(pPoseStack, pMouseX, pMouseY);
    }
}
