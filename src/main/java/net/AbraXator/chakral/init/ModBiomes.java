package net.AbraXator.chakral.init;

import net.AbraXator.chakral.utils.ChakralLocation;
import net.AbraXator.chakral.world.biome.ModOverworldBiomes;
import net.AbraXator.chakral.world.biome.ModOverworldRegion;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import terrablender.api.Regions;

public class ModBiomes {
    public static void setupTerraBlender(){
        Regions.register(new ModOverworldRegion(10));
    }

    public static void bootstrapBiomes(BootstapContext<Biome> context) {
        HolderGetter<ConfiguredWorldCarver<?>> carverGetter = context.lookup(Registries.CONFIGURED_CARVER);
        HolderGetter<PlacedFeature> placedFeatureGetter = context.lookup(Registries.PLACED_FEATURE);

        register(context, BiomeKeys.MINRAL_CAVERNS, ModOverworldBiomes.mineralCaverns(placedFeatureGetter, carverGetter));
    }

    private static void register(BootstapContext<Biome> context, ResourceKey<Biome> key, Biome biome) {
        context.register(key, biome);
    }

    public class BiomeKeys {
        public static final ResourceKey<Biome> MINRAL_CAVERNS = register("mineral_caverns");

        private static ResourceKey<Biome> register(String pKey) {
            return ResourceKey.create(Registries.BIOME, new ChakralLocation(pKey));
        }
    }
}
