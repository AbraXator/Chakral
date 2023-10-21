package net.AbraXator.chakral.server.init;

import com.mojang.serialization.Codec;
import net.AbraXator.chakral.Chakral;
import net.AbraXator.chakral.client.particle.TravelingParticle;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(
            ForgeRegistries.PARTICLE_TYPES, Chakral.MOD_ID);

    public static final RegistryObject<ParticleType<TravelingParticle>> HAGSTONE_FRAGMNETIUM = PARTICLES.register("hagstone_fragmentium", () -> new ParticleType<>(false, TravelingParticle.DESERIALIZER) {
        @Override
        public Codec<TravelingParticle> codec() {
            return TravelingParticle.codec(this);
        }
    });
    public static final RegistryObject<SimpleParticleType> STEMSHROOM_SPORE = PARTICLES.register("stemshroom_spore", () -> new SimpleParticleType(false));
    public static final RegistryObject<ParticleType<TravelingParticle>> LIGHT_RAY = PARTICLES.register("light_ray", () -> new ParticleType<>(false, TravelingParticle.DESERIALIZER) {
        @Override
        public Codec<TravelingParticle> codec() {
            return TravelingParticle.codec(this);
        }
    });

    public static void register(IEventBus eventBus) {
        PARTICLES.register(eventBus);
    }
}
