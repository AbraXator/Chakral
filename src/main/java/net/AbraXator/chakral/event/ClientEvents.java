package net.AbraXator.chakral.event;

import net.AbraXator.chakral.Chakral;
import net.AbraXator.chakral.renderer.CrystalFishRenderer;
import net.AbraXator.chakral.renderer.MineralSnailRenderer;
import net.AbraXator.chakral.renderer.model.CrystalFishModel;
import net.AbraXator.chakral.renderer.model.MineralSnailModel;
import net.AbraXator.chakral.entity.ModEntity;
import net.AbraXator.chakral.utils.KeyBindings;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Chakral.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class ClientEvents {
    @Mod.EventBusSubscriber(modid = Chakral.MOD_ID, value = Dist.CLIENT)
    public static class ClientForgeEvents {
        @SubscribeEvent
        public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event){
            event.registerLayerDefinition(MineralSnailModel.LAYER_LOCATION, MineralSnailModel::createBodyLayer);
            event.registerLayerDefinition(CrystalFishModel.LAYER_LOCATION, CrystalFishModel::createBodyLayer);
        }

        @SubscribeEvent
        public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event){
            event.registerEntityRenderer(ModEntity.MINERAL_SNAIL.get(), MineralSnailRenderer::new);
            event.registerEntityRenderer(ModEntity.CRYSTAL_FISH.get(), CrystalFishRenderer::new);
        }

        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {
            if(KeyBindings.STONE_FUNCTION_KEY.consumeClick()) {

            }
        }
    }

    @Mod.EventBusSubscriber(modid = Chakral.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {
        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(KeyBindings.STONE_FUNCTION_KEY);
        }
    }
}
