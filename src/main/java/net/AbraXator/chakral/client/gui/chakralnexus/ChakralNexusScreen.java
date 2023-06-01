package net.AbraXator.chakral.client.gui.chakralnexus;

import com.electronwill.nightconfig.core.CommentedConfig;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.AbraXator.chakral.Chakral;
import net.AbraXator.chakral.chakra.Chakra;
import net.AbraXator.chakral.chakra.ChakraStrength;
import net.AbraXator.chakral.chakra.ChakraUtil;
import net.AbraXator.chakral.chakra.IChakra;
import net.AbraXator.chakral.config.ChakralClientConfig;
import net.AbraXator.chakral.items.ChakraItem;
import net.AbraXator.chakral.items.NecklaceItem;
import net.AbraXator.chakral.utils.ChakralLocation;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.commands.arguments.coordinates.Vec2Argument;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Tuple;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec2;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ChakralNexusScreen extends AbstractContainerScreen<ChakralNexusMenu> {
    public static final ResourceLocation BACKGROUND_TEXTURE =
            new ChakralLocation("textures/gui/container/chakral_nexus.png");
    public static final ResourceLocation INVENTORY =
            new ChakralLocation("textures/gui/container/chakral_nexus.png");

    private final Map<ChakraStrength, Vec2> posMap = Util.make(new LinkedHashMap<>(), (map) -> {
        map.put(ChakraStrength.FAINT, new Vec2(55, 34));
        map.put(ChakraStrength.WEAKENED, new Vec2(78, 56));
        map.put(ChakraStrength.POWERFUL, new Vec2(101, 33));
        map.put(ChakraStrength.ENLIGHTENED, new Vec2(78, 11));
    });

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
        ChakraUtil.getChakras(item).forEach(chakraItem -> {
            Vec2 pos = posMap.get(chakraItem.getChakra().getStrenght());
            ChakralLocation chakralLocation = new ChakralLocation("textures/gui/button/" + chakraItem.getDescriptionId().replace("item.chakral.", "") + "_button.png");
            RenderSystem.setShaderTexture(0, chakralLocation);
            blit(pPoseStack, (int) (x + pos.x), (int) (y + pos.y), 0, 0, 19, 16);
        });

        Chakra selectedChakra = mouseAboveChakra(item, pMouseX, pMouseY);
        if(selectedChakra != null){
            selectedChakra.openInfoSidePanel(this, pPoseStack, x, y);
        }
    }

    public Chakra mouseAboveChakra(ItemStack necklace, int pMouseX, int pMouseY){
        Map<Vec2, ChakraStrength> map = posMap.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
        ChakraStrength strength = map.get(new Vec2(pMouseX, pMouseY));
        if(strength == null) return null;
        else return ChakraUtil.getChakra(necklace, strength.getIndex());
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
}
