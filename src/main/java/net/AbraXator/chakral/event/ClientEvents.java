package net.AbraXator.chakral.event;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.AbraXator.chakral.Chakral;
import net.AbraXator.chakral.blocks.ModBlocks;
import net.AbraXator.chakral.blocks.entity.ModBlockEntities;
import net.AbraXator.chakral.blocks.entity.custom.renderer.MineralEnricherRenderer;
import net.AbraXator.chakral.chakra.chakras.Dumortierite;
import net.AbraXator.chakral.client.EdgeClient;
import net.AbraXator.chakral.entity.DwiderEntity;
import net.AbraXator.chakral.entity.DwiderModel;
import net.AbraXator.chakral.entity.DwiderRenderer;
import net.AbraXator.chakral.entity.ModEntities;
import net.AbraXator.chakral.items.ModItems;
import net.AbraXator.chakral.items.custom.Gem;
import net.AbraXator.chakral.items.custom.GoldenNecklace;
import net.AbraXator.chakral.items.custom.NecklaceItem;
import net.AbraXator.chakral.networking.ModMessages;
import net.AbraXator.chakral.networking.packet.NecklaceC2SPacket;
import net.AbraXator.chakral.networking.packet.StoneFunctionC2SPacket;
import net.AbraXator.chakral.screen.ModMenuTypes;
import net.AbraXator.chakral.screen.refiner.ShardRefinerScreen;
import net.AbraXator.chakral.utils.KeyBindings;
import net.AbraXator.chakral.utils.ModTags;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.level.GrassColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.*;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Chakral.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class ClientEvents {
    @Mod.EventBusSubscriber(modid = Chakral.MOD_ID, value = Dist.CLIENT)
    public static class ClientForgeEvents {
        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {
            if(KeyBindings.NECKLACE_EQUIP_KEY.consumeClick()) {
                ModMessages.sendToServer(new NecklaceC2SPacket());
            }
            if(KeyBindings.STONE_FUNCTION_KEY.consumeClick()){
                ModMessages.sendToServer(new StoneFunctionC2SPacket());
            }
        }

        public static void renderLevelLast(RenderLevelStageEvent event){
            if(event.getStage().equals(RenderLevelStageEvent.Stage.AFTER_SOLID_BLOCKS)){
            }
        }

        @SubscribeEvent
        public static void onOpenScreen(ScreenEvent.Opening event){
            if(event.getNewScreen() instanceof ShardRefinerScreen screen && (Minecraft.getInstance().player != null && Minecraft.getInstance().player.isShiftKeyDown())){
                event.setCanceled(true);
            }
        }

        @SubscribeEvent
        public static void editInventory(ScreenEvent.Render.Post event){
            ResourceLocation TEXTURE = new ResourceLocation("textures/gui/container/image.png");
            if(event.getScreen() instanceof InventoryScreen screen) {
                int x = (screen.width - 176) / 2;
                int y = (screen.height - 166) / 2;
                RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
                RenderSystem.setShaderColor(1, 1, 1, 1);
                RenderSystem.setShaderTexture(0, TEXTURE);
                screen.blit(event.getPoseStack(), x, y, 0, 0, 16, 16);
            }
        }
    }

    @Mod.EventBusSubscriber(modid = Chakral.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {
        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(KeyBindings.STONE_FUNCTION_KEY);
            event.register(KeyBindings.NECKLACE_EQUIP_KEY);
        }

        @SubscribeEvent
        public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event){
            event.registerBlockEntityRenderer(ModBlockEntities.MINERAL_ENRICHER_BLOCK_ENTITY.get(),
                    MineralEnricherRenderer::new);
        }
    }

    @SubscribeEvent
    public static void layerDefitions(EntityRenderersEvent.RegisterLayerDefinitions event){
        event.registerLayerDefinition(DwiderModel.LAYER_LOCATION, DwiderModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void entityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.DWIDER.get(), DwiderRenderer::new);
    }

    @SubscribeEvent
    public static void colorEvent(RegisterColorHandlersEvent.Block event){
        event.register((pState, pLevel, pPos, pTintIndex) -> pLevel != null && pPos != null ? BiomeColors.getAverageGrassColor(pLevel, pPos) : GrassColor.get(0.5D, 1.0D),
                ModBlocks.MINERAL_RICH_GRASS.get());
    }

    @SubscribeEvent
    public static void colorEvent(RegisterColorHandlersEvent.Item event){
        event.register((pStack, pTintIndex) -> GrassColor.get(0.5D, 1.0D),
                ModBlocks.MINERAL_RICH_GRASS.get());
    }
}
