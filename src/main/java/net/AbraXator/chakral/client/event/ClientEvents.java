package net.AbraXator.chakral.client.event;

import net.AbraXator.chakral.Chakral;
import net.AbraXator.chakral.client.gui.refiner.ShardRefinerScreen;
import net.AbraXator.chakral.client.overlays.ChakraHearts;
import net.AbraXator.chakral.client.renderer.entity.ChakralBoatRenderer;
import net.AbraXator.chakral.server.chakra.ChakraUtil;
import net.AbraXator.chakral.server.chakra.chakras.DumortieriteChakra;
import net.AbraXator.chakral.server.entity.boat.ChakralBoat;
import net.AbraXator.chakral.server.entity.dwider.DwiderModel;
import net.AbraXator.chakral.server.entity.stemspore.MenacingStemshroomSporeModel;
import net.AbraXator.chakral.server.init.ModBlocks;
import net.AbraXator.chakral.server.init.ModItems;
import net.AbraXator.chakral.server.init.ModKeyBindings;
import net.AbraXator.chakral.server.networking.ModPacketHandler;
import net.AbraXator.chakral.server.networking.packet.NecklaceC2SPacket;
import net.AbraXator.chakral.server.networking.packet.StoneFunctionC2SPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GrassColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.*;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Arrays;

@Mod.EventBusSubscriber(modid = Chakral.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {
    @Mod.EventBusSubscriber(modid = Chakral.MOD_ID, value = Dist.CLIENT)
    public static class ClientForgeEvents {
        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {
            if (ModKeyBindings.NECKLACE_EQUIP_KEY.consumeClick()) {
                ModPacketHandler.sendToServer(new NecklaceC2SPacket());
            }
            if (ModKeyBindings.STONE_FUNCTION_KEY.consumeClick()) {
                ModPacketHandler.sendToServer(new StoneFunctionC2SPacket());
            }
        }

        @SubscribeEvent
        public static void renderLevel(RenderLevelStageEvent event) {
            if (event.getStage() == RenderLevelStageEvent.Stage.AFTER_PARTICLES) {
                Player player = Minecraft.getInstance().player;
                if (player == null) {
                    return;
                }
                ChakraUtil.getChakrasFromPlayer(player).forEach(chakra -> {
                    if (chakra instanceof DumortieriteChakra dumortieriteChakra) {
                        dumortieriteChakra.render(event);
                    }
                });
            }
        }

        @SubscribeEvent
        public static void onOpenScreen(ScreenEvent.Opening event) {
            if (event.getNewScreen() instanceof ShardRefinerScreen && (Minecraft.getInstance().player != null && Minecraft.getInstance().player.isShiftKeyDown())) {
                event.setCanceled(true);
            }
        }
    }

    @Mod.EventBusSubscriber(modid = Chakral.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {
        @SubscribeEvent
        public static void registerGUIOverlays(RegisterGuiOverlaysEvent event){
            event.registerAboveAll("chakra_health", new ChakraHearts());
        }
    }

    @SubscribeEvent
    public static void layerDefitions(EntityRenderersEvent.RegisterLayerDefinitions event){
        event.registerLayerDefinition(DwiderModel.LAYER_LOCATION, DwiderModel::createBodyLayer);
        event.registerLayerDefinition(MenacingStemshroomSporeModel.LAYER_LOCATION, MenacingStemshroomSporeModel::createBodyLayer);
        Arrays.stream(ChakralBoat.Type.values()).forEach(type -> {
            event.registerLayerDefinition(ChakralBoatRenderer.createBoatModelName(type), BoatModel::createBodyModel);
            event.registerLayerDefinition(ChakralBoatRenderer.createChestBoatModelName(type), ChestBoatModel::createBodyModel);
        });
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
}