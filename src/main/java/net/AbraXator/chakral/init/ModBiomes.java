package net.AbraXator.chakral.init;

import net.AbraXator.chakral.Chakral;
import net.AbraXator.chakral.utils.ChakralLocation;
import net.AbraXator.chakral.world.biome.ModOverworldBiomes;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static net.AbraXator.chakral.world.biome.ModOverworldBiomes.defaultAmbientBuilder;

public class ModBiomes {
    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, Chakral.MOD_ID);

    public static final ResourceKey<Biome> MINRAL_CAVERNS = registerBiomeKey("mineral_caverns");

    private static ResourceKey<Biome> registerBiomeKey(String pKey) {
        return ResourceKey.create(Registries.BIOME, new ChakralLocation(pKey));
    }

    public static void bootstrap(BootstapContext<Biome> context) {
        HolderGetter<ConfiguredWorldCarver<?>> carverGetter = context.lookup(Registries.CONFIGURED_CARVER);
        HolderGetter<PlacedFeature> placedFeatureGetter = context.lookup(Registries.PLACED_FEATURE);

        context.register(MINRAL_CAVERNS, ModOverworldBiomes.biomeWithDefaults(defaultAmbientBuilder(), new MobSpawnSettings.Builder(), new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter)).build());
    }

    public static void registerBiomes(){
        BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(MINRAL_CAVERNS, 1));
    }

    public static void register(IEventBus eventBus){
        registerBiomes();
        BIOMES.register(eventBus);
    }
}
