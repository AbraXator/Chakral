package net.AbraXator.chakral.world.noise;

import net.AbraXator.chakral.Chakral;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

public class ModNoises {
    public static final ResourceKey<NormalNoise.NoiseParameters> PURPLE_NOISE = Chakral.createKey(Registries.NOISE, "purple_noise");
    public static final ResourceKey<NormalNoise.NoiseParameters> BLUE_NOISE = Chakral.createKey(Registries.NOISE, "blue_noise");
    public static final ResourceKey<NormalNoise.NoiseParameters> LIGHT_BLUE_NOISE = Chakral.createKey(Registries.NOISE, "light_blue_noise");
    public static final ResourceKey<NormalNoise.NoiseParameters> GREEN_NOISE = Chakral.createKey(Registries.NOISE, "green_noise");
    public static final ResourceKey<NormalNoise.NoiseParameters> YELLOW_NOISE = Chakral.createKey(Registries.NOISE, "yellow_noise");
    public static final ResourceKey<NormalNoise.NoiseParameters> ORANGE_NOISE = Chakral.createKey(Registries.NOISE, "orange_noise");
    public static final ResourceKey<NormalNoise.NoiseParameters> RED_NOISE = Chakral.createKey(Registries.NOISE, "red_noise");

    public static void bootstrap(BootstapContext<NormalNoise.NoiseParameters> context){
        context.register(PURPLE_NOISE, mineralNoiseParameters());
        context.register(BLUE_NOISE, mineralNoiseParameters());
        context.register(LIGHT_BLUE_NOISE, mineralNoiseParameters());
        context.register(GREEN_NOISE, mineralNoiseParameters());
        context.register(YELLOW_NOISE, mineralNoiseParameters());
        context.register(ORANGE_NOISE, mineralNoiseParameters());
        context.register(RED_NOISE, mineralNoiseParameters());
    }

    private static NormalNoise.NoiseParameters mineralNoiseParameters(){
        return new NormalNoise.NoiseParameters(-8, 2, 1, 0, -1, -2);
    }
}
