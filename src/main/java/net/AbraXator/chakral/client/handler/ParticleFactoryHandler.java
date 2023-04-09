package net.AbraXator.chakral.client.handler;


import net.AbraXator.chakral.Chakral;
import net.AbraXator.chakral.client.particle.HagstoneFragmentiumParticle;
import net.AbraXator.chakral.particle.ModParticles;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Chakral.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ParticleFactoryHandler {
    @SubscribeEvent
    public void onRegisterParticleProviders(RegisterParticleProvidersEvent event) {
        event.register(ModParticles.HAGSTONE_FRAGMNETIUM.get(), HagstoneFragmentiumParticle.HagstoneFragmentiumProvider::new);
    }
}
