package net.AbraXator.chakramod.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.AbraXator.chakramod.ChakraMod;
import net.AbraXator.chakramod.utils.ModTags;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;

public class ShardRefinerScreen extends AbstractContainerScreen<ShardRefinerMenu> {
    //public static ItemStack itemStack = StoneBenchBlockEntity.getStoneInSlot();
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(ChakraMod.MOD_ID, "textures/gui/container/mineral_refiner.png");

    public ShardRefinerScreen(ShardRefinerMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
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
        switch (this.menu.data.get(0)){
            case 5 -> this.blit(pPoseStack, x + 15, y + 33, 219, 0, 36, 36);
            case 4 -> this.blit(pPoseStack, x + 15, y + 37, 219, 41, 29, 30);
            case 3 -> this.blit(pPoseStack, x + 15, y + 41, 219, 82, 26, 27);
            case 2 -> this.blit(pPoseStack, x + 15, y + 45, 219, 123, 23, 24);
            case 1 -> this.blit(pPoseStack, x + 15, y + 49, 219, 164, 20, 21);
            case 0 -> this.blit(pPoseStack, x, y, 0, 0, imageWidth, imageHeight);
        }
        if(this.menu.isCrafting()){
            int j = this.menu.dataToInt();
            this.blit(pPoseStack, x + 106, y + 36, 177, j, menu.getScaledProgress(), 16);
        }
    }

    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        renderBackground(pPoseStack);
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        renderTooltip(pPoseStack, pMouseX, pMouseY);
    }
}