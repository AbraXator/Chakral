package net.AbraXator.chakramod.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.AbraXator.chakramod.ChakraMod;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ModBookScreen extends Screen {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(ChakraMod.MOD_ID, "textures/gui/container/necklace_slotter.png");

    public ModBookScreen(Component pTitle) {
        super(pTitle);
    }

    @Override
    public void renderBackground(PoseStack pPoseStack) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - 500) / 2;
        int y = (height - 500) / 2;
        this.blit(pPoseStack, x, y, 0, 0, 500, 500);
    }

    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        renderBackground(pPoseStack);
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
    }
}
