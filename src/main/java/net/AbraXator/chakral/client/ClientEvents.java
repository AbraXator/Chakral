package net.AbraXator.chakral.client;

import net.AbraXator.chakral.Chakral;
import net.AbraXator.chakral.chakra.ChakraUtil;
import net.AbraXator.chakral.chakra.chakras.AmethystQuartzChakra;
import net.AbraXator.chakral.chakra.chakras.DumortieriteChakra;
import net.AbraXator.chakral.client.particle.HagstoneFragmentiumParticle;
import net.AbraXator.chakral.client.particle.LightRayParticle;
import net.AbraXator.chakral.client.particle.StemshroomSporeParticle;
import net.AbraXator.chakral.entity.stemspore.MenacingStemshroomSporeModel;
import net.AbraXator.chakral.entity.stemspore.MenacingStemshroomSporeRenderer;
import net.AbraXator.chakral.init.*;
import net.AbraXator.chakral.client.renderer.MineralEnricherRenderer;
import net.AbraXator.chakral.client.overlays.ChakraHearts;
import net.AbraXator.chakral.entity.dwider.DwiderModel;
import net.AbraXator.chakral.entity.dwider.DwiderRenderer;
import net.AbraXator.chakral.networking.ModMessages;
import net.AbraXator.chakral.networking.packet.NecklaceC2SPacket;
import net.AbraXator.chakral.networking.packet.StoneFunctionC2SPacket;
import net.AbraXator.chakral.client.gui.refiner.ShardRefinerScreen;
import net.AbraXator.chakral.utils.ChakralLocation;
import net.AbraXator.chakral.utils.KeyBindings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
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

        @SubscribeEvent
        public static void renderLevel(RenderLevelStageEvent event){
            /*if(event.getStage() != RenderLevelStageEvent.Stage.AFTER_TRANSLUCENT_BLOCKS){
                return;
            }*/

            Player player = Minecraft.getInstance().player;
            if(player == null){
                return;
            }
            ChakraUtil.getChakrasFromPlayer(player).forEach(chakra -> {
                if(chakra instanceof DumortieriteChakra dumortieriteChakra){
                    for (BlockPos block : dumortieriteChakra.getBlocks()) {
                        dumortieriteChakra.render(event, block);
                    }
                }
                if(chakra instanceof AmethystQuartzChakra quartzChakra){
                    GameRenderer gameRenderer = Minecraft.getInstance().gameRenderer;
                    gameRenderer.loadEffect(new ChakralLocation("shaders/novice.json"));
                }
            });
        }

        @SubscribeEvent
        public static void onOpenScreen(ScreenEvent.Opening event){
            if(event.getNewScreen() instanceof ShardRefinerScreen && (Minecraft.getInstance().player != null && Minecraft.getInstance().player.isShiftKeyDown())){
                event.setCanceled(true);
            }
        }

        @SubscribeEvent
        public static void onRenderGuiOverlay(RenderGuiOverlayEvent.Pre event) {
        }
    }

    @Mod.EventBusSubscriber(modid = Chakral.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {
        @SubscribeEvent
        public static void registerGUIOverlays(RegisterGuiOverlaysEvent event){
            event.registerAboveAll("chakra_health", new ChakraHearts());
        }

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
        event.registerLayerDefinition(MenacingStemshroomSporeModel.LAYER_LOCATION, MenacingStemshroomSporeModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void entityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.DWIDER.get(), DwiderRenderer::new);
        event.registerEntityRenderer(ModEntities.MENACING_STEMSHROOM_SPORE.get(), MenacingStemshroomSporeRenderer::new);
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
        event.register((pStack, pTintIndex) -> pTintIndex == 1 ? ChakraUtil.getColorOfNecklace(pStack, pTintIndex) : -1,
                 ModItems.GOLDEN_NECKLACE.get());
        event.register((pStack, pTintIndex) -> {
            if(pTintIndex == 1) return ChakraUtil.getColorOfNecklace(pStack, pTintIndex);
            if(pTintIndex == 2) return ChakraUtil.getColorOfNecklace(pStack, pTintIndex);
            else return -1;
        }, ModItems.DIAMOND_NECKLACE.get());
    }

    @SubscribeEvent
    public static void onRegisterParticleProviders(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ModParticles.HAGSTONE_FRAGMNETIUM.get(), HagstoneFragmentiumParticle.Provider::new);
        event.registerSpriteSet(ModParticles.STEMSHROOM_SPORE.get(), StemshroomSporeParticle.Provider::new);
        event.registerSpriteSet(ModParticles.LIGHT_RAY.get(), LightRayParticle.Provider::new);
    }
}