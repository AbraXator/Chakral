package net.AbraXator.chakral.world.noise;

import net.AbraXator.chakral.Chakral;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

public class ModNoises {
    public static final ResourceKey<NormalNoise.NoiseParameters> COLORED_MINERAL_NOISE = Chakral.createKey(Registries.NOISE, "colored_mineral_noise");
    public static final ResourceKey<NormalNoise.NoiseParameters> BUDDING_NOISE = Chakral.createKey(Registries.NOISE, "budding_noise");
    public static final ResourceKey<NormalNoise.NoiseParameters> MINERAL_RICH_DIRT_NOISE = Chakral.createKey(Registries.NOISE, "mineral_rich_dirt_noise");

    public static void bootstrap(BootstapContext<NormalNoise.NoiseParameters> context){
        context.register(COLORED_MINERAL_NOISE, new NormalNoise.NoiseParameters(-3, 2, 1, 0, -1, -2));
        context.register(BUDDING_NOISE, new NormalNoise.NoiseParameters(1, -1));
        context.register(MINERAL_RICH_DIRT_NOISE, new NormalNoise.NoiseParameters(-4, 1, 0, 0, -2, 1));
    }
}
