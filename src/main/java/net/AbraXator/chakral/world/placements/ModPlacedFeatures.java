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
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.awt.geom.RectangularShape;
import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> BLACK_MINERAL_BLOB = Chakral.createKey(Registries.PLACED_FEATURE, "black_mineral_blob");

    public static void bootstrap(BootstapContext<PlacedFeature> context){
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatureGetter = context.lookup(Registries.CONFIGURED_FEATURE);

        final Holder<ConfiguredFeature<?, ?>> BLACK_MINERAL_BLOB = configuredFeatureGetter.getOrThrow(ModConfiguredFeatures.BLACK_MINERAL_BLOB);

        registerFeature(context, ModPlacedFeatures.BLACK_MINERAL_BLOB, BLACK_MINERAL_BLOB, List.of(
                CountPlacement.of(200),
                InSquarePlacement.spread(),
                PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
                EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12),
                RandomOffsetPlacement.vertical(ConstantInt.of(1)),
                BiomeFilter.biome()));
    }

    private static void registerFeature(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> placedFeatureKey, Holder<ConfiguredFeature<?, ?>> configuredFeatureHolder, List<PlacementModifier> placementModifiers) {
        context.register(placedFeatureKey, new PlacedFeature(configuredFeatureHolder, placementModifiers));
    }
}
