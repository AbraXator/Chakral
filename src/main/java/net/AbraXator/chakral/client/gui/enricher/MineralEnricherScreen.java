package net.AbraXator.chakral.client.gui.enricher;

import com.mojang.blaze3d.systems.RenderSystem;
import net.AbraXator.chakral.Chakral;
import net.AbraXator.chakral.client.gui.MouseUtil;
import net.AbraXator.chakral.client.gui.renderer.FluidTankRenderer;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.TooltipFlag;

import java.util.Optional;

public class MineralEnricherScreen extends AbstractContainerScreen<MineralEnricherMenu> {
    public static final ResourceLocation TEXTURE = Chakral.loc("textures/gui/container/mineral_enricher.png");
    private FluidTankRenderer renderer;

    public MineralEnricherScreen(MineralEnricherMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void init() {
        super.init();
        assignFluidRenderer();
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float p_97788_, int p_97789_, int p_97790_) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        guiGraphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight);
        int j = menu.data.get(2);
        int k = menu.getDust();
        guiGraphics.blit(TEXTURE, x + 77, y + 38, 200, (j - 1) * 24, 24, 24);

        switch (k){
            case 1 -> guiGraphics.blit(TEXTURE, x + 100, y + 41, 176, 42, 3, 6);
            case 2 -> guiGraphics.blit(TEXTURE, x + 100, y + 39, 176, 31, 5, 8);
            case 3 -> guiGraphics.blit(TEXTURE, x + 100, y + 37, 176, 18, 7, 10);
            case 4 -> guiGraphics.blit(TEXTURE, x + 100, y + 35, 176, 4, 10, 12);
        }
        if(menu.hasWater()){
            guiGraphics.blit(TEXTURE, x + 86, y + 24, 176, 0, 12, 4);
        }
        renderer.render(guiGraphics.pose(), x + 26, y + 29, menu.getFluidStack());
    }

    private void assignFluidRenderer() {
        renderer = new FluidTankRenderer(5000, true, 16, 40);
    }

    @Override
    protected void renderLabels(GuiGraphics p_281635_, int p_282681_, int p_283686_) {
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        renderFluidAreaTooltip(p_281635_, p_282681_, p_283686_, x, y);
        super.renderLabels(p_281635_, p_282681_, p_283686_);
    }

    private void renderFluidAreaTooltip(GuiGraphics guiGraphics, int pMouseX, int pMouseY, int x, int y) {
        if(isMouseOver(pMouseX, pMouseY, x, y, 21, 29)){
            guiGraphics.renderTooltip(this.font,
                    renderer.getTooltip(menu.getFluidStack(),
                            TooltipFlag.Default.NORMAL),
                    Optional.empty(),
                    pMouseX - x,
                    pMouseY - y);
        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        renderBackground(guiGraphics);
        super.render(guiGraphics, pMouseX, pMouseY, pMouseX);
        renderTooltip(guiGraphics, pMouseX, pMouseY);
    }

    public boolean isMouseOver(double pMouseX, double pMouseY, int x, int y, int offsetX, int offsetY) {
        return MouseUtil.isMouseOver(pMouseX, pMouseY, x + offsetX, y + offsetY, renderer.getWidth(), renderer.getHeight());
    }
}
