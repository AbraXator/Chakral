package net.AbraXator.chakral.screen.necklace_slotter;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.AbraXator.chakral.Chakral;
import net.AbraXator.chakral.items.ModItems;
import net.AbraXator.chakral.utils.ModTags;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;

public class NecklaceSlotterScreen extends AbstractContainerScreen<NecklaceSlotterGoldenMenu> {
    //public static ItemStack itemStack = NecklaceSlotterBlockEntity.getStoneInSlot();
    private static final ResourceLocation TEXTURE_GOLDEN =
            new ResourceLocation(Chakral.MOD_ID, "textures/gui/container/necklace_slotter.png");
    private static final ResourceLocation TEXTURE_DIAMOND =
            new ResourceLocation(Chakral.MOD_ID, "textures/gui/container/diamond_necklace_slotter.png");

    public NecklaceSlotterScreen(NecklaceSlotterGoldenMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1, 1, 1, 1);
        if(this.menu.getSlot(0).getItem().is(ModItems.DIAMOND_NECKLACE.get())){
            RenderSystem.setShaderTexture(0, TEXTURE_DIAMOND);
        }else {
            RenderSystem.setShaderTexture(0, TEXTURE_GOLDEN);
        }
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        this.blit(pPoseStack, x, y, 0, 0, imageWidth, imageHeight);
        //if(this.menu.getSlot(0).getItem().is(ModItems.GOLDEN_NECKLACE.get())){
        //    this.blit(pPoseStack, x + 70, y + 40, 220, 218, 35, 29);
        //}
        //if(this.menu.getSlot(0).getItem().is(ModItems.DIAMOND_NECKLACE.get())){
        //    this.blit(pPoseStack, x + 66, y + 40, 176, 0, 44, 33);
        //}


        Item stone = this.menu.necklaceSlot.getItem(1).getItem();
        if(ForgeRegistries.ITEMS.tags().getTag(ModTags.Items.CROWN).contains(stone)){
            this.blit(pPoseStack, x + 70, y + 40, 220, 125, 35, 29);
        }
        if(ForgeRegistries.ITEMS.tags().getTag(ModTags.Items.HEART).contains(stone)){
            this.blit(pPoseStack, x + 70, y + 39, 220, 0, 35, 29);
        }
        if(ForgeRegistries.ITEMS.tags().getTag(ModTags.Items.ROOT).contains(stone)){
            this.blit(pPoseStack, x + 70, y + 40, 220, 63, 35, 29);
        }
        if(ForgeRegistries.ITEMS.tags().getTag(ModTags.Items.THORAT).contains(stone)){
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
        }
    }

    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        renderBackground(pPoseStack);
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        renderTooltip(pPoseStack, pMouseX, pMouseY);
    }
}