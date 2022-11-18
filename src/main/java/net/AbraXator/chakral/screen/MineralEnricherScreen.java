package net.AbraXator.chakral.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.AbraXator.chakral.Chakral;
import net.AbraXator.chakral.screen.renderer.FluidTankRenderer;
import net.AbraXator.chakral.utils.MouseUtil;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.fluids.FluidStack;

import java.util.Optional;

public class MineralEnricherScreen extends AbstractContainerScreen<MineralEnricherMenu> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(Chakral.MOD_ID, "textures/gui/container/mineral_enricher.png");
    private FluidTankRenderer renderer;

    public MineralEnricherScreen(MineralEnricherMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void init() {
        super.init();
        assignFluidRenderer();
    }

    private void assignFluidRenderer() {
        renderer = new FluidTankRenderer(5000, true, 16, 40);
    }

    @Override
    protected void renderLabels(PoseStack pPoseStack, int pMouseX, int pMouseY) {
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        renderFluidAreaTooltip(pPoseStack, pMouseX, pMouseY, x, y);
    }

    private void renderFluidAreaTooltip(PoseStack pPoseStack, int pMouseX, int pMouseY, int x, int y) {
        if(isMouseOver(pMouseX, pMouseY, x, y, 21, 29)){
            renderTooltip(pPoseStack, renderer.getTooltip(menu.getFluidStack(), TooltipFlag.Default.NORMAL),
                    Optional.empty(), pMouseX - x, pMouseY - y);
        }
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
        int k = menu.getDust();
         switch (j){
            case 1 -> this.blit(pPoseStack, x + 72, y + 38, 200, 0, 24, 24);
            case 2 -> this.blit(pPoseStack, x + 72, y + 38, 200, 24, 24, 24);
            case 3 -> this.blit(pPoseStack, x + 72, y + 38, 200, 48, 24, 24);
            case 4 -> this.blit(pPoseStack, x + 72, y + 38, 200, 72, 24, 24);
            case 5 -> this.blit(pPoseStack, x + 72, y + 38, 200, 96, 24, 24);
            case 6 -> this.blit(pPoseStack, x + 72, y + 38, 200, 120, 24, 24);
            case 7 -> this.blit(pPoseStack, x + 72, y + 38, 200, 144, 24, 24);
            case 8 -> this.blit(pPoseStack, x + 72, y + 38, 200, 168, 24, 24);
            case 9 -> this.blit(pPoseStack, x + 72, y + 38, 200, 192, 24, 24);
            case 10 -> this.blit(pPoseStack, x + 72, y + 38, 200, 216, 24, 24);
        }
        switch (k){
             case 1 -> this.blit(pPoseStack, x + 95, y + 41, 176, 42, 3, 6);
             case 2 -> this.blit(pPoseStack, x + 95, y + 39, 176, 31, 5, 8);
             case 3 -> this.blit(pPoseStack, x + 95, y + 37, 176, 18, 7, 10);
             case 4 -> this.blit(pPoseStack, x + 95, y + 35, 176, 4, 10, 12);
        }
        if(menu.hasWater()){
            this.blit(pPoseStack, x + 79, y + 24, 176, 0, 12, 4);
        }
        renderer.render(pPoseStack, x + 21, y + 29, menu.getFluidStack());
    }

    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        renderBackground(pPoseStack);
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        renderTooltip(pPoseStack, pMouseX, pMouseY);
    }

    public boolean isMouseOver(double pMouseX, double pMouseY, int x, int y, int offsetX, int offsetY) {
        return MouseUtil.isMouseOver(pMouseX, pMouseY, x + offsetX, y + offsetY, renderer.getWidth(), renderer.getHeight());
    }
}
