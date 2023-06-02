package net.AbraXator.chakral.client.gui.necklace;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.AbraXator.chakral.Chakral;
import net.AbraXator.chakral.chakra.ChakraType;
import net.AbraXator.chakral.client.gui.MouseUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;

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
    protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1, 1, 1, 1);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        Item goldenStone1 = this.menu.container.getItem(1).getItem();
        Item diamondStone1 = this.menu.container.getItem(2).getItem();
        Item diamondStone2 = this.menu.container.getItem(3).getItem();
        if(!NecklaceSlotterMenu.isDiamond){
            goldenScreen(goldenStone1, pPoseStack, x, y, pMouseX, pMouseY);
            if(!this.menu.container.isEmpty()){
                this.blit(pPoseStack, x + 140, y + 12, 0, 196, 20, 20);
            }
        }else {
            diamondScreen(diamondStone1, diamondStone2, pPoseStack, x, y, pMouseX, pMouseY);
            if(!this.menu.container.isEmpty()){
                this.blit(pPoseStack, x + 16, y + 12, 20, 196, 20, 20);
            }
        }
    }

    public void renderBackground(PoseStack pPoseStack, int x, int y){
        this.blit(pPoseStack, x, y, 0, 0, imageWidth, imageHeight);

    }

    public void goldenScreen(Item stone, PoseStack pPoseStack, int x, int y, int pMouseX, int pMouseY){
        RenderSystem.setShaderTexture(0, TEXTURE_GOLDEN);
        renderBackground(pPoseStack, x, y);

        //SVĚTÝLKA
        this.blit(pPoseStack, x + 70, y + 40, 220, vOffset(stone) * 29, 36, 29);

        //BUTTON
        if(isMouseAboveArea(pMouseX, pMouseY, x, y, 141, 13, 18, 18)){
            this.blit(pPoseStack, x + 140, y + 12, 0, 236, 20, 20);
        }
    }

    public void diamondScreen(Item stone1, Item stone2, PoseStack pPoseStack, int x, int y, int pMouseX, int pMouseY){
        RenderSystem.setShaderTexture(0, TEXTURE_DIAMOND);
        renderBackground(pPoseStack, x, y);

        //SVĚTÝLKA

        this.blit(pPoseStack, x + 88, y + 44, 230, vOffset(stone1) * 30, 26, 30); //LEFT
        this.blit(pPoseStack, x + 62, y + 44, 204, vOffset(stone2) * 30, 26, 30); //RIGHT

        //BUTTON
        if (isMouseAboveArea(pMouseX, pMouseY, x, y, 17, 13, 18, 18)) {
            this.blit(pPoseStack, x + 16, y + 12, 20, 236, 20, 20);
        }
    }

    public int vOffset(Item stone){
        for (ChakraType type : ChakraType.values()){
            if(ForgeRegistries.ITEMS.tags().getTag(type.getTagKey()).contains(stone)){
                return type.getIndex();
            }
        }
        return 0;
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

    private boolean isMouseAboveArea(int pMouseX, int pMouseY, int x, int y, int offsetX, int offsetY, int width, int height) {
        if(this.menu.container.isEmpty()){
            return MouseUtil.isMouseOver(pMouseX, pMouseY, x + offsetX, y + offsetY, width, height);
        }
        return false;
    }

    @Override
    protected void renderLabels(PoseStack pPoseStack, int pMouseX, int pMouseY) {
        this.font.draw(pPoseStack, this.title, (this.imageWidth - this.font.width(this.title)) / 2f, 10, 0x404040);
        //if(MouseUtil.isMouseOver(pMouseX, pMouseY, x + ))
    }
}