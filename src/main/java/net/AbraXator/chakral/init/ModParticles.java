package net.AbraXator.chakral.init;

import net.AbraXator.chakral.Chakral;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(
            ForgeRegistries.PARTICLE_TYPES, Chakral.MOD_ID);

    public static final RegistryObject<SimpleParticleType> HAGSTONE_FRAGMNETIUM = PARTICLES.register("hagstone_fragmentium", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> STEM_SPORE = PARTICLES.register("stem_vapor", () -> new SimpleParticleType(false));

    public static void register(IEventBus eventBus) {
        PARTICLES.register(eventBus);
    }
}
