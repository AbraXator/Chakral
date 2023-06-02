package net.AbraXator.chakral.client.gui.chakralnexus;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.AbraXator.chakral.Chakral;
import net.AbraXator.chakral.chakra.Chakra;
import net.AbraXator.chakral.chakra.ChakraStrength;
import net.AbraXator.chakral.chakra.ChakraUtil;
import net.AbraXator.chakral.client.gui.MouseUtil;
import net.AbraXator.chakral.config.ChakralClientConfig;
import net.AbraXator.chakral.items.NecklaceItem;
import net.AbraXator.chakral.utils.ChakralLocation;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Tuple;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec2;
import org.jline.reader.Widget;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class ChakralNexusScreen extends AbstractContainerScreen<ChakralNexusMenu> implements GuiEventListener {
    public static final ResourceLocation CHAKRAL_NEXUS_LOCATION =
            new ChakralLocation("textures/gui/container/chakral_nexus.png");
    public static boolean hasSideTip;

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
        RenderSystem.setShaderTexture(0, CHAKRAL_NEXUS_LOCATION);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        var map = posMap.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
        //imageWidth = 176;
        //imageHeight = 166;
        //resize(Minecraft.getInstance(), 256, 256);
        blit(pPoseStack, x, y, 0, 0, imageWidth, imageHeight);
        blit(pPoseStack, x + 130, y + 10, 204, 192, 22, 63);
        renderChakras(pPoseStack, pPartialTick, pMouseX, pMouseY, x, y, map);
    }

    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        renderBackground(pPoseStack);
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        renderTooltip(pPoseStack, pMouseX, pMouseY);
    }

    private void renderChakras(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY, int x, int y, Map<Vec2, ChakraStrength> map){
        ItemStack item = this.menu.getItemInSlot(0);
        if(!(item.getItem() instanceof NecklaceItem)) return;
        ChakraUtil.getChakras(item).forEach(chakraItem -> {
            Vec2 pos = posMap.get(chakraItem.getChakra().getStrenght());
            ChakralLocation chakralLocation = new ChakralLocation("textures/gui/button/" + chakraItem.getDescriptionId().replace("item.chakral.", "") + "_button.png");
            addWidget(new ChakralButton((int) (x + pos.x), (int) (y + pos.y), 19, 16, chakraItem.getChakra(), chakralLocation, pButton -> {
                AbstractWidget guiComponent = chakraItem.getChakra().openInfoSidePanel(x, y);
                if(guiComponent != null) addWidget(guiComponent);
            }));
        });
    }

    public Optional<Chakra> mouseAboveChakra(ItemStack necklace, int x, int y, int pMouseX, int pMouseY, Map<Vec2, ChakraStrength> map){
        AtomicReference<Optional<ChakraStrength>> strength = new AtomicReference<>(Optional.empty());
        map.forEach((vec2, chakraStrength1) -> {
            boolean flag = MouseUtil.isMouseOver(pMouseX, pMouseY, x, y, (int) vec2.x, (int) vec2.y, 19, 16);
            if(flag){
                strength.set(Optional.of(chakraStrength1));
            }
        });
        if(strength.get().isEmpty()) return Optional.empty();
        else return ChakraUtil.getChakra(necklace, strength.get().get());
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

    public class ChakralButton extends Button {
        private final Chakra chakra;
        private final ResourceLocation textureLocation;

        public ChakralButton(int pX, int pY, int pWidth, int pHeight, Chakra chakra, ChakralLocation textureLocation, Button.OnPress onPress) {
            super(pX, pY, pWidth, pHeight, CommonComponents.EMPTY, onPress, DEFAULT_NARRATION);
            this.chakra = chakra;
            this.textureLocation = textureLocation;
        }

        @Override
        public void render(PoseStack poseStack, int pMouseX, int pMouseY, float pPartialTick) {
            RenderSystem.setShaderTexture(0, textureLocation);
            blit(poseStack, this.getX(), this.getY(), 0, 0, this.getWidth(), this.getHeight());
            if(this.isHoveredOrFocused()){
                RenderSystem.setShaderTexture(0, CHAKRAL_NEXUS_LOCATION);
                blit(poseStack, this.getX(), this.getY(), 234, 85, this.getWidth(), this.getHeight());
            }
            super.renderWidget(poseStack, pMouseX, pMouseY, pPartialTick);
        }
    }
}
