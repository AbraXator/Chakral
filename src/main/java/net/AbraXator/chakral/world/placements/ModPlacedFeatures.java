package net.AbraXator.chakral.world.placements;

import net.AbraXator.chakral.Chakral;
import net.AbraXator.chakral.world.feature.ModConfiguredFeatures;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> BLACK_MINERAL_BLOB = Chakral.createKey(Registries.PLACED_FEATURE, "black_mineral_blob");
    public static final ResourceKey<PlacedFeature> MINERAL_CRYSTAL = Chakral.createKey(Registries.PLACED_FEATURE, "mineral_crystal");

    public static void bootstrap(BootstapContext<PlacedFeature> context){
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatureGetter = context.lookup(Registries.CONFIGURED_FEATURE);

        final Holder<ConfiguredFeature<?, ?>> BLACK_MINERAL_BLOB = configuredFeatureGetter.getOrThrow(ModConfiguredFeatures.BLACK_MINERAL_BLOB);
        final Holder<ConfiguredFeature<?, ?>> MINERAL_CRYSTAL = configuredFeatureGetter.getOrThrow(ModConfiguredFeatures.MINERAL_CRYSTAL);

        registerFeature(context, ModPlacedFeatures.BLACK_MINERAL_BLOB, BLACK_MINERAL_BLOB, List.of(
                CountPlacement.of(150),
                InSquarePlacement.spread(),
                PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
                EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.matchesTag(BlockTags.BASE_STONE_OVERWORLD), 12),
                RandomOffsetPlacement.vertical(ConstantInt.of(1)),
                BiomeFilter.biome()));
        registerFeature(context, ModPlacedFeatures.MINERAL_CRYSTAL, MINERAL_CRYSTAL, List.of(
                CountPlacement.of(150),
                InSquarePlacement.spread(),
                PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
                EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_OR_WATER_PREDICATE, 12),
                RandomOffsetPlacement.vertical(ConstantInt.of(1)),
                BiomeFilter.biome()));
    }

    private static void registerFeature(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> placedFeatureKey, Holder<ConfiguredFeature<?, ?>> configuredFeatureHolder, List<PlacementModifier> placementModifiers) {
        context.register(placedFeatureKey, new PlacedFeature(configuredFeatureHolder, placementModifiers));
    }
}
