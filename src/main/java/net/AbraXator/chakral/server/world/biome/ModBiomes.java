package net.AbraXator.chakral.server.world.biome;

import com.github.alexthe666.citadel.server.world.ExpandedBiomes;
import net.AbraXator.chakral.Chakral;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.List;

public class ModBiomes {
    public static final ResourceKey<Biome> MINRAL_CAVERNS = Chakral.createKey(Registries.BIOME, "mineral_caverns");
    public static final ResourceKey<Biome> AMETHYST_CAVERNS = Chakral.createKey(Registries.BIOME, "amethyst_caverns");

    public static final List<ResourceKey<Biome>> MOD_CAVE_BIOMES = List.of(MINRAL_CAVERNS, AMETHYST_CAVERNS);

    public static void bootstrap(BootstapContext<Biome> context) {
        HolderGetter<ConfiguredWorldCarver<?>> carverGetter = context.lookup(Registries.CONFIGURED_CARVER);
        HolderGetter<PlacedFeature> placedFeatureGetter = context.lookup(Registries.PLACED_FEATURE);

        context.register(MINRAL_CAVERNS, ModOverworldBiomes.mineralCaverns(placedFeatureGetter, carverGetter));
        context.register(AMETHYST_CAVERNS, ModOverworldBiomes.amethystCaverns(placedFeatureGetter, carverGetter));
    }

    public static void init(){
        ExpandedBiomes.addExpandedBiome(MINRAL_CAVERNS, LevelStem.OVERWORLD);
        ExpandedBiomes.addExpandedBiome(AMETHYST_CAVERNS, LevelStem.OVERWORLD);
    }

    /*public static void setupBiomes(){
        Regions.register(new ModOverworldRegion());
        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, Chakral.MOD_ID, ModSurfaceRuleData.overworld());
    }*/
}
