package net.AbraXator.chakral.world.placements;

import net.AbraXator.chakral.Chakral;
import net.AbraXator.chakral.world.feature.ModConfiguredFeatures;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> MINERAL_CRYSTAL = Chakral.createKey(Registries.PLACED_FEATURE, "mineral_crystal");
    public static final ResourceKey<PlacedFeature> BLACK_MINERAL_BLOB = Chakral.createKey(Registries.PLACED_FEATURE, "black_mineral_blob");
    public static final ResourceKey<PlacedFeature> PURPLE_MINERAL_BLOB = Chakral.createKey(Registries.PLACED_FEATURE, "purple_mineral_blob");
    public static final ResourceKey<PlacedFeature> BLUE_MINERAL_BLOB = Chakral.createKey(Registries.PLACED_FEATURE, "blue_mineral_blob");
    public static final ResourceKey<PlacedFeature> LIGHT_BLUE_MINERAL_BLOB = Chakral.createKey(Registries.PLACED_FEATURE, "light_blue_mineral_blob");
    public static final ResourceKey<PlacedFeature> GREEN_MINERAL_BLOB = Chakral.createKey(Registries.PLACED_FEATURE, "green_mineral_blob");
    public static final ResourceKey<PlacedFeature> YELLOW_MINERAL_BLOB = Chakral.createKey(Registries.PLACED_FEATURE, "yellow_mineral_blob");
    public static final ResourceKey<PlacedFeature> ORANGE_MINERAL_BLOB = Chakral.createKey(Registries.PLACED_FEATURE, "orange_mineral_blob");
    public static final ResourceKey<PlacedFeature> RED_MINERAL_BLOB = Chakral.createKey(Registries.PLACED_FEATURE, "red_mineral_blob");

    public static final ResourceKey<PlacedFeature> BLACK_MINERAL_ORE_COAL = Chakral.createKey(Registries.PLACED_FEATURE, "black_mineral_ore_coal");
    public static final ResourceKey<PlacedFeature> BLACK_MINERAL_ORE_COAL_BURIED = Chakral.createKey(Registries.PLACED_FEATURE, "black_mineral_ore_coal_buried");
    public static final ResourceKey<PlacedFeature> BLACK_MINERAL_ORE_IRON = Chakral.createKey(Registries.PLACED_FEATURE, "black_mineral_ore_iron");
    public static final ResourceKey<PlacedFeature> BLACK_MINERAL_ORE_IRON_SMALL = Chakral.createKey(Registries.PLACED_FEATURE, "black_mineral_ore_iron_small");
    public static final ResourceKey<PlacedFeature> BLACK_MINERAL_ORE_GOLD = Chakral.createKey(Registries.PLACED_FEATURE, "black_mineral_ore_gold");
    public static final ResourceKey<PlacedFeature> BLACK_MINERAL_ORE_GOLD_BURIED = Chakral.createKey(Registries.PLACED_FEATURE, "black_mineral_ore_gold_buried");
    public static final ResourceKey<PlacedFeature> BLACK_MINERAL_ORE_REDSTONE = Chakral.createKey(Registries.PLACED_FEATURE, "black_mineral_ore_redstone");
    public static final ResourceKey<PlacedFeature> BLACK_MINERAL_ORE_DIAMOND_SMALL = Chakral.createKey(Registries.PLACED_FEATURE, "black_mineral_ore_diamond_small");
    public static final ResourceKey<PlacedFeature> BLACK_MINERAL_ORE_DIAMOND_LARGE = Chakral.createKey(Registries.PLACED_FEATURE, "black_mineral_ore_diamond_large");
    public static final ResourceKey<PlacedFeature> BLACK_MINERAL_ORE_DIAMOND_BURIED = Chakral.createKey(Registries.PLACED_FEATURE, "black_mineral_ore_buried");
    public static final ResourceKey<PlacedFeature> BLACK_MINERAL_ORE_LAPIS = Chakral.createKey(Registries.PLACED_FEATURE, "black_mineral_ore_lapis");
    public static final ResourceKey<PlacedFeature> BLACK_MINERAL_ORE_LAPIS_BURIED = Chakral.createKey(Registries.PLACED_FEATURE, "black_mineral_ore_lapis_buried");
    public static final ResourceKey<PlacedFeature> BLACK_MINERAL_ORE_EMERALD = Chakral.createKey(Registries.PLACED_FEATURE, "black_mineral_ore_emerald");
    public static final ResourceKey<PlacedFeature> BLACK_MINERAL_ORE_COPPER_SMALL = Chakral.createKey(Registries.PLACED_FEATURE, "black_mineral_ore_copper_small");
    public static final ResourceKey<PlacedFeature> BLACK_MINERAL_ORE_COPPER_LARGE = Chakral.createKey(Registries.PLACED_FEATURE, "black_mineral_ore_copper_large");
    public static final ResourceKey<PlacedFeature> BUDDING_AMETHYST = Chakral.createKey(Registries.PLACED_FEATURE, "budding_amethyst");



    public static void bootstrap(BootstapContext<PlacedFeature> context){
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatureGetter = context.lookup(Registries.CONFIGURED_FEATURE);

        final Holder<ConfiguredFeature<?, ?>> MINERAL_CRYSTAL = configuredFeatureGetter.getOrThrow(ModConfiguredFeatures.MINERAL_CRYSTAL);
        final Holder<ConfiguredFeature<?, ?>> BLACK_MINERAL_BLOB = configuredFeatureGetter.getOrThrow(ModConfiguredFeatures.BLACK_MINERAL_BLOB);
        final Holder<ConfiguredFeature<?, ?>> PURPLE_MINERAL_BLOB = configuredFeatureGetter.getOrThrow(ModConfiguredFeatures.PURPLE_MINERAL_BLOB);
        final Holder<ConfiguredFeature<?, ?>> BLUE_MINERAL_BLOB = configuredFeatureGetter.getOrThrow(ModConfiguredFeatures.BLUE_MINERAL_BLOB);
        final Holder<ConfiguredFeature<?, ?>> LIGHT_BLUE_MINERAL_BLOB = configuredFeatureGetter.getOrThrow(ModConfiguredFeatures.LIGHT_BLUE_MINERAL_BLOB);
        final Holder<ConfiguredFeature<?, ?>> GREEN_MINERAL_BLOB = configuredFeatureGetter.getOrThrow(ModConfiguredFeatures.GREEN_MINERAL_BLOB);
        final Holder<ConfiguredFeature<?, ?>> YELLOW_MINERAL_BLOB = configuredFeatureGetter.getOrThrow(ModConfiguredFeatures.YELLOW_MINERAL_BLOB);
        final Holder<ConfiguredFeature<?, ?>> ORANGE_MINERAL_BLOB = configuredFeatureGetter.getOrThrow(ModConfiguredFeatures.ORANGE_MINERAL_BLOB);
        final Holder<ConfiguredFeature<?, ?>> RED_MINERAL_BLOB = configuredFeatureGetter.getOrThrow(ModConfiguredFeatures.RED_MINERAL_BLOB);

        final Holder<ConfiguredFeature<?, ?>> BLACK_MINERAL_ORE_COAL = configuredFeatureGetter.getOrThrow(ModConfiguredFeatures.BLACK_MINERAL_ORE_COAL);
        final Holder<ConfiguredFeature<?, ?>> BLACK_MINERAL_ORE_COAL_BURIED = configuredFeatureGetter.getOrThrow(ModConfiguredFeatures.BLACK_MINERAL_ORE_COAL_BURIED);
        final Holder<ConfiguredFeature<?, ?>> BLACK_MINERAL_ORE_IRON = configuredFeatureGetter.getOrThrow(ModConfiguredFeatures.BLACK_MINERAL_ORE_IRON);
        final Holder<ConfiguredFeature<?, ?>> BLACK_MINERAL_ORE_IRON_SMALL = configuredFeatureGetter.getOrThrow(ModConfiguredFeatures.BLACK_MINERAL_ORE_IRON_SMALL);
        final Holder<ConfiguredFeature<?, ?>> BLACK_MINERAL_ORE_GOLD = configuredFeatureGetter.getOrThrow(ModConfiguredFeatures.BLACK_MINERAL_ORE_GOLD);
        final Holder<ConfiguredFeature<?, ?>> BLACK_MINERAL_ORE_GOLD_BURIED = configuredFeatureGetter.getOrThrow(ModConfiguredFeatures.BLACK_MINERAL_ORE_GOLD_BURIED);
        final Holder<ConfiguredFeature<?, ?>> BLACK_MINERAL_ORE_REDSTONE = configuredFeatureGetter.getOrThrow(ModConfiguredFeatures.BLACK_MINERAL_ORE_REDSTONE);
        final Holder<ConfiguredFeature<?, ?>> BLACK_MINERAL_ORE_DIAMOND_SMALL = configuredFeatureGetter.getOrThrow(ModConfiguredFeatures.BLACK_MINERAL_ORE_DIAMOND_SMALL);
        final Holder<ConfiguredFeature<?, ?>> BLACK_MINERAL_ORE_DIAMOND_LARGE = configuredFeatureGetter.getOrThrow(ModConfiguredFeatures.BLACK_MINERAL_ORE_DIAMOND_LARGE);
        final Holder<ConfiguredFeature<?, ?>> BLACK_MINERAL_ORE_DIAMOND_BURIED = configuredFeatureGetter.getOrThrow(ModConfiguredFeatures.BLACK_MINERAL_ORE_DIAMOND_BURIED);
        final Holder<ConfiguredFeature<?, ?>> BLACK_MINERAL_ORE_LAPIS = configuredFeatureGetter.getOrThrow(ModConfiguredFeatures.BLACK_MINERAL_ORE_LAPIS);
        final Holder<ConfiguredFeature<?, ?>> BLACK_MINERAL_ORE_LAPIS_BURIED = configuredFeatureGetter.getOrThrow(ModConfiguredFeatures.BLACK_MINERAL_ORE_LAPIS_BURIED);
        final Holder<ConfiguredFeature<?, ?>> BLACK_MINERAL_ORE_EMERALD = configuredFeatureGetter.getOrThrow(ModConfiguredFeatures.BLACK_MINERAL_ORE_EMERALD);
        final Holder<ConfiguredFeature<?, ?>> BLACK_MINERAL_ORE_COPPER_LARGE = configuredFeatureGetter.getOrThrow(ModConfiguredFeatures.BLACK_MINERAL_ORE_COPPER_LARGE);
        final Holder<ConfiguredFeature<?, ?>> BLACK_MINERAL_ORE_COPPER_SMALL = configuredFeatureGetter.getOrThrow(ModConfiguredFeatures.BLACK_MINERAL_ORE_COPPER_SMALL);
        final Holder<ConfiguredFeature<?, ?>> BUDDING_AMETHYST = configuredFeatureGetter.getOrThrow(ModConfiguredFeatures.BUDDING_AMETHYST);

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
        registerFeature(context, ModPlacedFeatures.PURPLE_MINERAL_BLOB, PURPLE_MINERAL_BLOB, coloredMineraBlobModifiers());
        registerFeature(context, ModPlacedFeatures.BLUE_MINERAL_BLOB, BLUE_MINERAL_BLOB, coloredMineraBlobModifiers());
        registerFeature(context, ModPlacedFeatures.LIGHT_BLUE_MINERAL_BLOB, LIGHT_BLUE_MINERAL_BLOB, coloredMineraBlobModifiers());
        registerFeature(context, ModPlacedFeatures.GREEN_MINERAL_BLOB, GREEN_MINERAL_BLOB, coloredMineraBlobModifiers());
        registerFeature(context, ModPlacedFeatures.YELLOW_MINERAL_BLOB, YELLOW_MINERAL_BLOB, coloredMineraBlobModifiers());
        registerFeature(context, ModPlacedFeatures.ORANGE_MINERAL_BLOB, ORANGE_MINERAL_BLOB, coloredMineraBlobModifiers());
        registerFeature(context, ModPlacedFeatures.RED_MINERAL_BLOB, RED_MINERAL_BLOB, coloredMineraBlobModifiers());

        registerFeature(context, ModPlacedFeatures.BLACK_MINERAL_ORE_COAL, BLACK_MINERAL_ORE_COAL, commonOrePlacement(20, HeightRangePlacement.triangle(VerticalAnchor.absolute(0), VerticalAnchor.absolute(192))));
        registerFeature(context, ModPlacedFeatures.BLACK_MINERAL_ORE_COAL_BURIED, BLACK_MINERAL_ORE_COAL_BURIED, commonOrePlacement(20, HeightRangePlacement.triangle(VerticalAnchor.absolute(0), VerticalAnchor.absolute(192))));
        registerFeature(context, ModPlacedFeatures.BLACK_MINERAL_ORE_IRON, BLACK_MINERAL_ORE_IRON, commonOrePlacement(10, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(72))));
        registerFeature(context, ModPlacedFeatures.BLACK_MINERAL_ORE_IRON_SMALL, BLACK_MINERAL_ORE_IRON_SMALL, commonOrePlacement(10, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(72))));
        registerFeature(context, ModPlacedFeatures.BLACK_MINERAL_ORE_GOLD, BLACK_MINERAL_ORE_GOLD, orePlacement(CountPlacement.of(UniformInt.of(0, 1)), HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(-48))));
        registerFeature(context, ModPlacedFeatures.BLACK_MINERAL_ORE_GOLD_BURIED, BLACK_MINERAL_ORE_GOLD_BURIED, orePlacement(CountPlacement.of(UniformInt.of(0, 1)), HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(-48))));
        registerFeature(context, ModPlacedFeatures.BLACK_MINERAL_ORE_REDSTONE, BLACK_MINERAL_ORE_REDSTONE, commonOrePlacement(8, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-32), VerticalAnchor.aboveBottom(32))));
        registerFeature(context, ModPlacedFeatures.BLACK_MINERAL_ORE_DIAMOND_SMALL, BLACK_MINERAL_ORE_DIAMOND_SMALL, commonOrePlacement(7, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));
        registerFeature(context, ModPlacedFeatures.BLACK_MINERAL_ORE_DIAMOND_LARGE, BLACK_MINERAL_ORE_DIAMOND_LARGE, commonOrePlacement(9, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));
        registerFeature(context, ModPlacedFeatures.BLACK_MINERAL_ORE_DIAMOND_BURIED, BLACK_MINERAL_ORE_DIAMOND_BURIED, commonOrePlacement(4, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));
        registerFeature(context, ModPlacedFeatures.BLACK_MINERAL_ORE_LAPIS, BLACK_MINERAL_ORE_LAPIS, commonOrePlacement(2, HeightRangePlacement.triangle(VerticalAnchor.absolute(-32), VerticalAnchor.absolute(32))));
        registerFeature(context, ModPlacedFeatures.BLACK_MINERAL_ORE_LAPIS_BURIED, BLACK_MINERAL_ORE_LAPIS_BURIED, commonOrePlacement(4, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(64))));
        registerFeature(context, ModPlacedFeatures.BLACK_MINERAL_ORE_EMERALD, BLACK_MINERAL_ORE_EMERALD, commonOrePlacement(100, HeightRangePlacement.triangle(VerticalAnchor.absolute(-16), VerticalAnchor.absolute(480))));
        registerFeature(context, ModPlacedFeatures.BLACK_MINERAL_ORE_COPPER_SMALL, BLACK_MINERAL_ORE_COPPER_SMALL, commonOrePlacement(16, HeightRangePlacement.triangle(VerticalAnchor.absolute(-16), VerticalAnchor.absolute(112))));
        registerFeature(context, ModPlacedFeatures.BLACK_MINERAL_ORE_COPPER_LARGE, BLACK_MINERAL_ORE_COPPER_LARGE, commonOrePlacement(16, HeightRangePlacement.triangle(VerticalAnchor.absolute(-16), VerticalAnchor.absolute(112))));
        registerFeature(context, ModPlacedFeatures.BUDDING_AMETHYST, BUDDING_AMETHYST, commonOrePlacement(32, HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(480))));
    }

    private static List<PlacementModifier> coloredMineraBlobModifiers(){
        return List.of(
                CountPlacement.of(30),
                InSquarePlacement.spread(),
                PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
                EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_OR_WATER_PREDICATE, 12),
                RandomOffsetPlacement.vertical(ConstantInt.of(1)),
                BiomeFilter.biome());
    }

    private static List<PlacementModifier> orePlacement(PlacementModifier p_195347_, PlacementModifier p_195348_) {
        return List.of(p_195347_, InSquarePlacement.spread(), p_195348_, BiomeFilter.biome());
    }

    private static List<PlacementModifier> commonOrePlacement(int pCount, PlacementModifier pHeightRange) {
        return orePlacement(CountPlacement.of(pCount), pHeightRange);
    }

    private static List<PlacementModifier> rareOrePlacement(int pChance, PlacementModifier pHeightRange) {
        return orePlacement(RarityFilter.onAverageOnceEvery(pChance), pHeightRange);
    }

    private static void registerFeature(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> placedFeatureKey, Holder<ConfiguredFeature<?, ?>> configuredFeatureHolder, List<PlacementModifier> placementModifiers) {
        context.register(placedFeatureKey, new PlacedFeature(configuredFeatureHolder, placementModifiers));
    }
}
