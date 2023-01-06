package net.AbraXator.chakral.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.AbraXator.chakral.Chakral;
import net.AbraXator.chakral.capability.AdditionalHealthCapProvider;
import net.AbraXator.chakral.capability.NecklaceCapProvider;
import net.AbraXator.chakral.items.ModItems;
import net.AbraXator.chakral.utils.ItemFromTag;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class BonusHeartsRenderer implements IGuiOverlay {
    public static final ResourceLocation LOCATION = new ResourceLocation(Chakral.MOD_ID, "textures/gui/bonus_hearts.png");
    protected Minecraft minecraft;
    public int MAX_HEARTS = 10;
    boolean above;

    public BonusHeartsRenderer(boolean above) {
        minecraft = Minecraft.getInstance();
        this.above = above;
    }

    @Override
    public void render(ForgeGui gui, PoseStack poseStack, float partialTick, int screenWidth, int screenHeight) {
        //TODO: ADD ABOVE TO CONFIG
        if(above == above && !minecraft.options.hideGui && gui.shouldDrawSurvivalElements()){
            gui.setupOverlayRenderState(true, false);
            renderHealth();
        }
    }

    public void renderHealth(){
        RenderSystem.setShaderTexture(0, LOCATION);
        RenderSystem.enableBlend();
        Player player = ((Player) minecraft.getCameraEntity());
        player.getCapability(NecklaceCapProvider.NECKLACE_CAP).ifPresent(necklaceCap -> {
            Item item = ItemFromTag.stoneFromTag(necklaceCap.getNecklace(), 1);
            if(item.equals(ModItems.AMAZONITE.get())){
                player.getCapability(AdditionalHealthCapProvider.ADD_HEALTH_CAP).ifPresent(additionalHealthCap -> {
                    int k = additionalHealthCap.getHealth();
                });
            }
        });
    }

    private void renderUnit(ForgeGui gui, PoseStack poseStack, int x, int y){
        //gui.blit(poseStack, x, y, UnitType.);
    }

    public enum UnitType{
        NORMAL(0),
        HALF(8),
        DRAINED(16);

        int XAxis;

        UnitType(int XAxis){
            this.XAxis = XAxis;
        }

        public int[] getUV(UnitType type){
            return new int[]{type.XAxis, 0};
        }
    }
}
