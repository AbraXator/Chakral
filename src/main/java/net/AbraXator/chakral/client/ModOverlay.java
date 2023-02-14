package net.AbraXator.chakral.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.AbraXator.chakral.Chakral;
import net.AbraXator.chakral.capability.AdditionalHealthCap;
import net.AbraXator.chakral.capability.AdditionalHealthCapProvider;
import net.AbraXator.chakral.capability.NecklaceCapProvider;
import net.AbraXator.chakral.items.ModItems;
import net.AbraXator.chakral.utils.ItemFromTag;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.minecraftforge.event.level.NoteBlockEvent;

public class ModOverlay {
    public static final ResourceLocation LOCATION = new ResourceLocation(Chakral.MOD_ID, "textures/gui/bonus_hearts.png");

    public static final IGuiOverlay CHAKRA_HEARTS = ((gui, poseStack, partialTick, screenWidth, screenHeight) -> {
        int x = screenWidth / 2;
        int y = screenHeight;
        Minecraft minecraft = gui.getMinecraft();

        setupRenderer(ChakraHeartData.getHealth(), minecraft, gui, poseStack, x, y);
    });

    private static void setupRenderer(float health, Minecraft minecraft, ForgeGui gui, PoseStack poseStack, int x, int y){
        if(!minecraft.options.hideGui && gui.shouldDrawSurvivalElements() && ChakraHeartData.getEnabled()){
            renderHealth(poseStack, x, y, health);
        }
    }

    private static void renderHealth(PoseStack poseStack, int x, int y, float health){
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1,1, 1, 1);
        RenderSystem.setShaderTexture(0, LOCATION);
        float hpDecimal = health - (int)health;
        renderEmptyHearts(poseStack, x, y);
        for(int i = 0; i < health; i++){
            if(i == health - hpDecimal){
                GuiComponent.blit(poseStack, x - 94 + (i * 9), y - 54, 45, 0, 9, 9, 72, 9);
                break;
            }else if(hpDecimal >= 0.5){
                break;
            } else {
                GuiComponent.blit(poseStack, x - 94 + (i * 9), y - 54, 36, 0, 9, 9, 72, 9);
            }
        }
    }

    private static void renderHearts(){}

    private static void renderEmptyHearts(PoseStack poseStack, int x, int y){
        for(int i = 0; i < AdditionalHealthCap.maxHealth; i++){
            GuiComponent.blit(poseStack, x - 94 + (i * 9), y - 54, 0, 0, 9, 9, 72, 9);
        }
    }
}
