package net.AbraXator.chakral.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.AbraXator.chakral.Chakral;
import net.AbraXator.chakral.utils.ModTags;
import net.AbraXator.chakral.utils.MouseUtil;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;

public class NecklaceInserterScreen extends AbstractContainerScreen<NecklaceInserterMenu> {
    //public static ItemStack itemStack = NecklaceSlotterBlockEntity.getStoneInSlot();
    private static final ResourceLocation TEXTURE_NETHERITE =
            new ResourceLocation(Chakral.MOD_ID, "textures/gui/container/necklace_inserter_netherite.png");
    private static final ResourceLocation TEXTURE_RAINBOW =
            new ResourceLocation(Chakral.MOD_ID, "textures/gui/container/diamond_necklace_rainbow.png");

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

        Item stone = this.menu.necklaceSlot.getItem(1).getItem();
        /*if(ForgeRegistries.ITEMS.tags().getTag(ModTags.Items.CROWN).contains(stone)){
            this.blit(pPoseStack, x + 70, y + 40, 220, 125, 35, 29);
        }
        if(ForgeRegistries.ITEMS.tags().getTag(ModTags.Items.HEART).contains(stone)){
            this.blit(pPoseStack, x + 70, y + 39, 220, 0, 35, 29);
        }
        if(ForgeRegistries.ITEMS.tags().getTag(ModTags.Items.ROOT).contains(stone)){
            this.blit(pPoseStack, x + 70, y + 40, 220, 63, 35, 29);
        }
        if(ForgeRegistries.ITEMS.tags().getTag(ModTags.Items.THROAT).contains(stone)){
            this.blit(pPoseStack, x + 70, y + 40, 220, 94, 35, 29);
        }
        if(ForgeRegistries.ITEMS.tags().getTag(ModTags.Items.THIRD_EYE).contains(stone)){
            this.blit(pPoseStack, x + 70, y + 40, 220, 32, 35, 29);
        }
        if(ForgeRegistries.ITEMS.tags().getTag(ModTags.Items.SACRAL).contains(stone)){
            this.blit(pPoseStack, x + 70, y + 40, 220, 187, 35, 29);
        }
        if(ForgeRegistries.ITEMS.tags().getTag(ModTags.Items.SOLAR).contains(stone)){
            this.blit(pPoseStack, x + 70, y + 40, 220, 156, 35, 29);
        }*/

        if(this.menu.isRainbow){
            if(isMouseAboveArea((int) pMouseX, (int) pMouseY, x, y, 16, 13, 20, 20)){
                this.blit(pPoseStack, x + 16, y + 11, 20, 250, 20, 20);
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
