package net.AbraXator.chakral.event;

import net.AbraXator.chakral.Chakral;
import net.AbraXator.chakral.blocks.entity.ModBlockEntities;
import net.AbraXator.chakral.blocks.entity.custom.renderer.MineralEnricherRenderer;
import net.AbraXator.chakral.networking.ModMessages;
import net.AbraXator.chakral.networking.packet.NecklaceC2SPacket;
import net.AbraXator.chakral.networking.packet.StoneFunctionC2SPacket;
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
        public static void onKeyInput(InputEvent.Key event) {
            if(KeyBindings.NECKLACE_EQUIP_KEY.consumeClick()) {
                ModMessages.sendToServer(new NecklaceC2SPacket());
            }
            if(KeyBindings.STONE_FUNCTION_KEY.consumeClick()){
                ModMessages.sendToServer(new StoneFunctionC2SPacket());
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
}
