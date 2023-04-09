package net.AbraXator.chakral.particle;

import net.AbraXator.chakral.Chakral;
import net.AbraXator.chakral.chakra.chakras.DefaultChakra;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(
            ForgeRegistries.PARTICLE_TYPES, Chakral.MOD_ID);

    public static final RegistryObject<SimpleParticleType> HAGSTONE_FRAGMNETIUM = PARTICLES.register("hagstone_fragmentium", () -> new SimpleParticleType(false));

    public static void register(IEventBus eventBus) {
        PARTICLES.register(eventBus);
    }
}
