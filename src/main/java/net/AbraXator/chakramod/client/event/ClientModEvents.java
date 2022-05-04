package net.AbraXator.chakramod.client.event;

import net.AbraXator.chakramod.ChakraMod;
import net.AbraXator.chakramod.client.renderer.MineralSnailRenderer;
import net.AbraXator.chakramod.client.renderer.model.MineralSnailModel;
import net.AbraXator.chakramod.entity.ModEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ChakraMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class ClientModEvents {

    private ClientModEvents(){}

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event){
        event.registerLayerDefinition(MineralSnailModel.LAYER_LOCATION, MineralSnailModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event){
        event.registerEntityRenderer(ModEntity.MINERAL_SNAIL.get(), MineralSnailRenderer::new);
    }
}
