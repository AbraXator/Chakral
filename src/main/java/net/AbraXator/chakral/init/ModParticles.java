package net.AbraXator.chakral.init;

import com.mojang.serialization.Codec;
import net.AbraXator.chakral.Chakral;
import net.AbraXator.chakral.client.particle.LightRayParticleOption;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Function;

public class ModParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(
            ForgeRegistries.PARTICLE_TYPES, Chakral.MOD_ID);

    public static final RegistryObject<SimpleParticleType> HAGSTONE_FRAGMNETIUM = PARTICLES.register("hagstone_fragmentium", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> MENACING_STEMSHROOM_SPORE = PARTICLES.register("menacing_stemshroom_spore", () -> new SimpleParticleType(false));
    public static final RegistryObject<ParticleType<LightRayParticleOption>> LIGHT_RAY = PARTICLES.register("light_ray", () -> new ParticleType<LightRayParticleOption>(false, LightRayParticleOption.DESERIALIZER) {
        @Override
        public Codec<LightRayParticleOption> codec() {
            return LightRayParticleOption.CODEC;
        }
    });

    public static void register(IEventBus eventBus) {
        PARTICLES.register(eventBus);
    }
}
