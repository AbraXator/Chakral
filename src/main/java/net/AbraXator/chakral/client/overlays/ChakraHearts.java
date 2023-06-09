package net.AbraXator.chakral.client.overlays;

import com.mojang.blaze3d.systems.RenderSystem;
import net.AbraXator.chakral.Chakral;
import net.AbraXator.chakral.capability.AdditionalHealthCap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class ChakraHearts implements IGuiOverlay {
    public static final ResourceLocation LOCATION = new ResourceLocation(Chakral.MOD_ID, "textures/gui/bonus_hearts.png");

    @Override
    public void render(ForgeGui gui, GuiGraphics guiGraphics, float partialTick, int screenWidth, int screenHeight) {
        int x = screenWidth / 2;
        int y = screenHeight;
        setupRenderer(ChakraHeartData.getHealth(), Minecraft.getInstance(), gui, guiGraphics, x, y);
    }

    private static void setupRenderer(float health, Minecraft minecraft, ForgeGui gui, GuiGraphics guiGraphics, int x, int y){
        if(!minecraft.options.hideGui && gui.shouldDrawSurvivalElements() && ChakraHeartData.getEnabled()){
            renderHealth(guiGraphics, x, y, health);
        }
    }

    private static void renderHealth(GuiGraphics guiGraphics, int x, int y, float health){
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1,1, 1, 1);
        RenderSystem.setShaderTexture(0, LOCATION);
        renderHeartsTexture(guiGraphics, 0, x, y); //EMPTY HEARTS
        blink(guiGraphics, x, y);
        renderHearts(guiGraphics, health, x, y);
    }

    private static void renderHearts(GuiGraphics guiGraphics, float health, int x, int y){
        float hpDecimal = health - (int)health;
        for(int i = 0; i < health; i++){
            if(i == health - hpDecimal){
                renderHeartTexture(guiGraphics, 46, x, y, i);
                break;
            }else if(hpDecimal >= 0.5){
                break;
            } else {
                renderHeartTexture(guiGraphics, 37, x, y, i);
            }
        }
    }

    private static void blink(GuiGraphics guiGraphics, int x, int y){
        if(ChakraHeartData.isBlink()){
            renderHeartsTexture(guiGraphics, 9, x, y);
            //renderHeartsTexture(guiGraphics, 0, x, y);
        }
        ChakraHeartData.setBlink(false);
    }

    private static void renderHeartsTexture(GuiGraphics guiGraphics, int u, int x, int y){
        for(int i = 0; i < AdditionalHealthCap.maxHealth; i++){
            renderHeartTexture(guiGraphics, u, x, y, i);
        }
    }

    private static void renderHeartTexture(GuiGraphics guiGraphics, int u, int x, int y, int i){
        guiGraphics.blit(LOCATION, x - 94 + (i * 9), y - 54, u, 0, 9, 9, 72, 9);
    }
}
