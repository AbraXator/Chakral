package net.AbraXator.chakral.server.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ModServerConfig {
    public final ForgeConfigSpec.DoubleValue caveBiomeMeanWidth;
    public final ForgeConfigSpec.IntValue caveBiomeMeanSeparation;
    public final ForgeConfigSpec.DoubleValue caveBiomeWidthRandomness;
    public final ForgeConfigSpec.DoubleValue caveBiomeSpacingRandomness;

    public ModServerConfig(final ForgeConfigSpec.Builder builder) {
        builder.push("world-gen");
        caveBiomeMeanWidth = builder.comment("Average radius (in blocks) of an Chakral cave biome.").translation("cave_biome_mean_width").defineInRange("cave_biome_mean_width", 320D, 10.0D, Double.MAX_VALUE);
        caveBiomeMeanSeparation = builder.comment("Average separation (in blocks) between each Chakral cave biome.").translation("cave_biome_mean_separation").defineInRange("cave_biome_mean_separation", 4000, 50, Integer.MAX_VALUE);
        caveBiomeWidthRandomness = builder.comment("How irregularly shaped Chakral cave biomes can generate. 0 = all biomes nearly circular. 1 = biomes completely squiggly in shape.").translation("cave_biome_width_randomness").defineInRange("cave_biome_width_randomness", 0.15D, 0, 1D);
        caveBiomeSpacingRandomness = builder.comment("Average spacing in between Chakral cave biomes. 0 = all biomes nearly perfectly equidistant. 1 = biomes completely randomly spread out, sometimes next to eachother.").translation("cave_biome_spacing_randomness").defineInRange("cave_biome_spacing_randomness", 0.2D, 0, 1D);
        builder.pop();
    }
}
