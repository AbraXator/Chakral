package net.AbraXator.chakral.event;

import net.AbraXator.chakral.Chakral;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegisterEvent;

@Mod.EventBusSubscriber(modid = Chakral.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class WorldEvents {
    @SubscribeEvent
    public static void registerWorldGen(RegisterEvent event){}
}
