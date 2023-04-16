package net.AbraXator.chakral.client.overlays;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.AbraXator.chakral.Chakral;
import net.AbraXator.chakral.capability.AdditionalHealthCap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class ChakraHearts implements IGuiOverlay {
    public static final ResourceLocation LOCATION = new ResourceLocation(Chakral.MOD_ID, "textures/gui/bonus_hearts.png");

    @Override
    public void render(ForgeGui gui, PoseStack poseStack, float partialTick, int screenWidth, int screenHeight) {
        int x = screenWidth / 2;
        int y = screenHeight;
        setupRenderer(ChakraHeartData.getHealth(), Minecraft.getInstance(), gui, poseStack, x, y);
    }

    private static void setupRenderer(float health, Minecraft minecraft, ForgeGui gui, PoseStack poseStack, int x, int y){
        if(!minecraft.options.hideGui && gui.shouldDrawSurvivalElements() && ChakraHeartData.getEnabled()){
            renderHealth(poseStack, x, y, health);
        }
    }

    private static void renderHealth(PoseStack poseStack, int x, int y, float health){
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1,1, 1, 1);
        RenderSystem.setShaderTexture(0, LOCATION);
        renderHeartsTexture(poseStack, 0, x, y); //EMPTY HEARTS
        blink(poseStack, x, y);
        renderHearts(poseStack, health, x, y);
    }

    private static void renderHearts(PoseStack poseStack, float health, int x, int y){
        float hpDecimal = health - (int)health;
        for(int i = 0; i < health; i++){
            if(i == health - hpDecimal){
                renderHeartTexture(poseStack, 46, x, y, i);
                break;
            }else if(hpDecimal >= 0.5){
                break;
            } else {
                renderHeartTexture(poseStack, 37, x, y, i);
            }
        }
    }

    private static void blink(PoseStack poseStack, int x, int y){
        if(ChakraHeartData.isBlink()){
            renderHeartsTexture(poseStack, 9, x, y);
            //renderHeartsTexture(poseStack, 0, x, y);
        }
        ChakraHeartData.setBlink(false);
    }

    private static void renderHeartsTexture(PoseStack poseStack, int u, int x, int y){
        for(int i = 0; i < AdditionalHealthCap.maxHealth; i++){
            renderHeartTexture(poseStack, u, x, y, i);
        }
    }

    private static void renderHeartTexture(PoseStack poseStack, int u, int x, int y, int i){
        GuiComponent.blit(poseStack, x - 94 + (i * 9), y - 54, u, 0, 9, 9, 72, 9);
    }
}
