package net.AbraXator.chakral.world;

import net.AbraXator.chakral.Chakral;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class ModPlacedFeature {
    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES =
            DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, Chakral.MOD_ID);

    public static final RegistryObject<PlacedFeature> WHITE_MINERAL_ORE_PLACED = PLACED_FEATURES.register("white_mineral_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.WHITE_MINERAL_ORE.getHolder().get(), List.of(
                    RarityFilter.onAverageOnceEvery(50), InSquarePlacement.spread(),
                    HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(6), VerticalAnchor.absolute(50)),
                    BiomeFilter.biome())));

    public static final RegistryObject<PlacedFeature> TRUE_WHITE_MINERAL_ORE_PLACED = PLACED_FEATURES.register("true_white_mineral_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.TRUE_WHITE_MINERAL_ORE.getHolder().get(), List.of(
                    RarityFilter.onAverageOnceEvery(50), InSquarePlacement.spread(),
                    HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(6), VerticalAnchor.absolute(50)),
                    BiomeFilter.biome())));

    public static final RegistryObject<PlacedFeature> BLUE_GEODE_PLACED = PLACED_FEATURES.register("blue_geode_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.BLUE_GEODE.getHolder().get(), List.of(
                    RarityFilter.onAverageOnceEvery(50), InSquarePlacement.spread(),
                    HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(6), VerticalAnchor.absolute(50)),
                    BiomeFilter.biome())));

    public static final RegistryObject<PlacedFeature> LIGHT_BLUE_GEODE_PLACED = PLACED_FEATURES.register("light_blue_geode_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.LIGHT_BLUE_GEODE.getHolder().get(), List.of(
                    RarityFilter.onAverageOnceEvery(50), InSquarePlacement.spread(),
                    HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(6), VerticalAnchor.absolute(50)),
                    BiomeFilter.biome())));

    public static final RegistryObject<PlacedFeature> GREEN_GEODE_PLACED = PLACED_FEATURES.register("green_geode_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.GREEN_GEODE.getHolder().get(), List.of(
                    RarityFilter.onAverageOnceEvery(50), InSquarePlacement.spread(),
                    HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(6), VerticalAnchor.absolute(50)),
                    BiomeFilter.biome())));

    public static final RegistryObject<PlacedFeature> YELLOW_GEODE_PLACED = PLACED_FEATURES.register("yellow_geode_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.YELLOW_GEODE.getHolder().get(), List.of(
                    RarityFilter.onAverageOnceEvery(50), InSquarePlacement.spread(),
                    HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(6), VerticalAnchor.absolute(50)),
                    BiomeFilter.biome())));

    public static final RegistryObject<PlacedFeature> ORANGE_GEODE_PLACED = PLACED_FEATURES.register("orange_geode_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.ORANGE_GEODE.getHolder().get(), List.of(
                    RarityFilter.onAverageOnceEvery(50), InSquarePlacement.spread(),
                    HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(6), VerticalAnchor.absolute(50)),
                    BiomeFilter.biome())));

    public static final RegistryObject<PlacedFeature> RED_GEODE_PLACED = PLACED_FEATURES.register("red_geode_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.RED_GEODE.getHolder().get(), List.of(
                    RarityFilter.onAverageOnceEvery(50), InSquarePlacement.spread(),
                    HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(6), VerticalAnchor.absolute(50)),
                    BiomeFilter.biome())));

    public static void register(IEventBus eventBus) {
        PLACED_FEATURES.register(eventBus);
    }
}
