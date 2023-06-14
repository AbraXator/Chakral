package net.AbraXator.chakral.client.gui.necklace.slotter;

import com.mojang.blaze3d.systems.RenderSystem;
import net.AbraXator.chakral.Chakral;
import net.AbraXator.chakral.client.gui.MouseUtil;
import net.AbraXator.chakral.items.ChakraItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.NotNull;

public class NecklaceSlotterScreen extends AbstractContainerScreen<NecklaceSlotterMenu> {
    private static final ResourceLocation TEXTURE_GOLDEN =
            new ResourceLocation(Chakral.MOD_ID, "textures/gui/container/golden_necklace_slotter.png");
    private static final ResourceLocation TEXTURE_DIAMOND =
            new ResourceLocation(Chakral.MOD_ID, "textures/gui/container/diamond_necklace_slotter.png");
    private boolean isEnabled = this.menu.container.isEmpty();

    public NecklaceSlotterScreen(NecklaceSlotterMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float pMouseX, int pMouseY, int pPartialTicks) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1, 1, 1, 1);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        Item goldenStone1 = this.menu.container.getItem(1).getItem();
        Item diamondStone1 = this.menu.container.getItem(2).getItem();
        Item diamondStone2 = this.menu.container.getItem(3).getItem();
        if(!NecklaceSlotterMenu.isDiamond){
            goldenScreen(goldenStone1, guiGraphics, x, y, pMouseX, pMouseY);
            if(!this.menu.container.isEmpty()){
                guiGraphics.blit(TEXTURE_GOLDEN, x + 140, y + 12, 0, 196, 20, 20);
            }
        }else {
            diamondScreen(diamondStone1, diamondStone2, guiGraphics, x, y, pMouseX, pMouseY);
            if(!this.menu.container.isEmpty()){
                guiGraphics.blit(TEXTURE_DIAMOND, x + 16, y + 12, 20, 196, 20, 20);
            }
        }
    }

    public void renderBackground(GuiGraphics guiGraphics, int x, int y, ResourceLocation location){
        guiGraphics.blit(location, x, y, 0, 0, imageWidth, imageHeight);
    }

    public void goldenScreen(Item stone, GuiGraphics guiGraphics, int x, int y, float pMouseX, int pMouseY){
        renderBackground(guiGraphics, x, y, TEXTURE_GOLDEN);

        //SVĚTÝLKA
        guiGraphics.blit(TEXTURE_GOLDEN, x + 70, y + 40, 220, vOffset(stone) * 29, 36, 29);

        //BUTTON
        if(isMouseAboveArea(pMouseX, pMouseY, x, y, 141, 13, 18, 18)){
            guiGraphics.blit(TEXTURE_GOLDEN, x + 140, y + 12, 0, 236, 20, 20);
        }
    }

    public void diamondScreen(Item stone1, Item stone2, GuiGraphics guiGraphics, int x, int y, float pMouseX, int pMouseY){
        RenderSystem.setShaderTexture(0, TEXTURE_DIAMOND);
        renderBackground(guiGraphics, x, y, TEXTURE_DIAMOND);

        //SVĚTÝLKA

        guiGraphics.blit(TEXTURE_DIAMOND, x + 88, y + 44, 230, vOffset(stone1) * 30, 26, 30); //LEFT
        guiGraphics.blit(TEXTURE_DIAMOND, x + 62, y + 44, 204, vOffset(stone2) * 30, 26, 30); //RIGHT

        //BUTTON
        if (isMouseAboveArea(pMouseX, pMouseY, x, y, 17, 13, 18, 18)) {
            guiGraphics.blit(TEXTURE_DIAMOND, x + 16, y + 12, 20, 236, 20, 20);
        }
    }

    public int vOffset(Item stone){
        if(stone instanceof ChakraItem chakraItem) return chakraItem.getChakra().getType().ordinal() + 1;
        else return 0;
    }

    @Override
    public void render(@NotNull GuiGraphics guiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        renderBackground(guiGraphics);
        super.render(guiGraphics, pMouseX, pMouseY, pPartialTick);
        renderTooltip(guiGraphics, pMouseX, pMouseY);
    }

    @Override
    public boolean mouseClicked(double pMouseX, double pMouseY, int pButton) {
        final int x = (width - imageWidth) / 2;
        final int y = (height - imageHeight) / 2;
        if(isMouseAboveArea((int) pMouseX, (int) pMouseY, x, y, 141, 13, 18, 18) && !NecklaceSlotterMenu.isDiamond){
            NecklaceSlotterMenu.isDiamond = true;
            Minecraft.getInstance().player.playSound(SoundEvents.UI_BUTTON_CLICK.get());
        }
        if(isMouseAboveArea((int) pMouseX, (int) pMouseY, x, y, 17, 13, 18, 18) && NecklaceSlotterMenu.isDiamond){
            NecklaceSlotterMenu.isDiamond = false;
            Minecraft.getInstance().player.playSound(SoundEvents.UI_BUTTON_CLICK.get());
        }
        return super.mouseClicked(pMouseX, pMouseY, pButton);
    }

    private boolean isMouseAboveArea(float pMouseX, int pMouseY, int x, int y, int offsetX, int offsetY, int width, int height) {
        if(this.menu.container.isEmpty()){
            return MouseUtil.isMouseOver(pMouseX, pMouseY, x + offsetX, y + offsetY, width, height);
        }
        return false;
    }

    @Override
    protected void renderLabels(GuiGraphics p_281635_, int p_282681_, int p_283686_) {

    }
}