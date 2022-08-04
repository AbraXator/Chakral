package net.AbraXator.chakramod.event;

import net.AbraXator.chakramod.ChakraMod;
import net.AbraXator.chakramod.renderer.CrystalFishRenderer;
import net.AbraXator.chakramod.renderer.MineralSnailRenderer;
import net.AbraXator.chakramod.renderer.model.CrystalFishModel;
import net.AbraXator.chakramod.renderer.model.MineralSnailModel;
import net.AbraXator.chakramod.entity.ModEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ChakraMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class ClientModEvents {

    private ClientModEvents(){}

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
}
