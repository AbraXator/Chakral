package net.AbraXator.chakral.client.gui.chakralnexus;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.AbraXator.chakral.Chakral;
import net.AbraXator.chakral.chakra.Chakra;
import net.AbraXator.chakral.chakra.ChakraUtil;
import net.AbraXator.chakral.config.ChakralClientConfig;
import net.AbraXator.chakral.items.custom.ChakraItem;
import net.AbraXator.chakral.items.custom.NecklaceItem;
import net.AbraXator.chakral.utils.ChakralLocation;
import net.AbraXator.chakral.utils.MouseUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.model.ModelUtils;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Tuple;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ChakralNexusScreen extends AbstractContainerScreen<ChakralNexusMenu> {
    public static final ResourceLocation BACKGROUND_TEXTURE =
            new ChakralLocation("textures/gui/container/chakral_nexus.png");
    public static final ResourceLocation INVENTORY =
            new ChakralLocation("textures/gui/container/chakral_nexus.png");

    public ChakralNexusScreen(ChakralNexusMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.setShaderTexture(0, BACKGROUND_TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        //imageWidth = 176;
        //imageHeight = 166;
        //resize(Minecraft.getInstance(), 256, 256);
        this.blit(pPoseStack, x, y, 0, 0, imageWidth, imageHeight);
        this.blit(pPoseStack, x + 130, y + 10, 204, 192, 22, 63);
        renderChakras(pPoseStack, pPartialTick, pMouseX, pMouseY, x, y);
    }

    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        renderBackground(pPoseStack);
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        renderTooltip(pPoseStack, pMouseX, pMouseY);
    }

    private void renderChakras(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY, int x, int y){
        ItemStack item = this.menu.getItemInSlot(0);
        if(!(item.getItem() instanceof NecklaceItem)) return;

        getLinkedPosChakras(item).forEach((chakraItem, integerIntegerTuple) -> {
            if(chakraItem.getItem() instanceof ChakraItem chakraItem1){
                ChakralLocation chakralLocation = new ChakralLocation("textures/gui/button/" + chakraItem1.getDescriptionId().replace("item.chakral.", "") + "_button.png");
                RenderSystem.setShaderTexture(0, chakralLocation);
                //imageWidth = 19;
                //imageHeight = 16;
                //resize(Minecraft.getInstance(), imageWidth, imageHeight);
                blit(pPoseStack, x + integerIntegerTuple.getA(), y + integerIntegerTuple.getB(), 0, 0, 19, 16);
            }
        });
    }

    private Map<ItemStack, Tuple<Integer, Integer>> getLinkedPosChakras(ItemStack necklace){
        CompoundTag tag = necklace.getTag();
        if(tag == null) return null;

        Item item = necklace.getItem();
        Map<ItemStack, Tuple<Integer, Integer>> map = new LinkedHashMap<>();

        map.put((ItemStack.of(tag.getCompound("Stone1"))), new Tuple<>(55, 34));
        map.put((ItemStack.of(tag.getCompound("Stone2"))), new Tuple<>(77, 55));
        map.put((ItemStack.of(tag.getCompound("Stone3"))), new Tuple<>(101, 33));
        map.put((ItemStack.of(tag.getCompound("Stone4"))), new Tuple<>(78, 11));

        return map;
    }

    public static Tuple<Integer, Integer> getButtonOffset(boolean isCreative) {
        ChakralClientConfig.Client client = ChakralClientConfig.CLIENT;
        ChakralClientConfig.Client.ButtonCorner corner = client.buttonCorner.get();
        int x = 0;
        int y = 0;

        if (isCreative) {
            x += corner.getCreativeXoffset() + client.creativeButtonXOffset.get();
            y += corner.getCreativeYoffset() + client.creativeButtonYOffset.get();
        } else {
            x += corner.getXoffset() + client.buttonXOffset.get();
            y += corner.getYoffset() + client.buttonYOffset.get();
        }
        return new Tuple<>(x, y);
    }

    @Override
    protected void renderLabels(PoseStack pPoseStack, int pMouseX, int pMouseY) {
        this.font.draw(pPoseStack, this.title, (this.imageWidth - this.font.width(this.title)) / 2f, 10, 0x404040);
    }

    private List<Component> tooltip(ItemStack itemStack){
        List<Component> list = new ArrayList<>();

        try {
            if (itemStack.is(ItemStack.EMPTY.getItem())) {
                return list;
            }

            Component displayName = itemStack.getDisplayName().copy().withStyle(ChatFormatting.WHITE, ChatFormatting.BOLD);
            Component leftClick = Component.translatable("chakral.tooltip.left_click").withStyle(ChatFormatting.YELLOW);
            Component moreInfo = Component.translatable("chakral.tooltip.hold_shift").withStyle(ChatFormatting.GOLD);
            list.add(displayName);
            list.add(leftClick);
            list.add(moreInfo);
        } catch (RuntimeException e) {
            Chakral.LOGGER.error("Failed to get tooltip for fluid: " + e);
        }

        return list;
    }

    //public boolean isMouseOver(double pMouseX, double pMouseY, int x, int y, int offsetX, int offsetY) {
    //    return MouseUtil.isMouseOver(pMouseX, pMouseY, x + offsetX, y + offsetY, renderer.getWidth(), renderer.getHeight());
    //}
}
