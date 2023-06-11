package net.AbraXator.chakral.world.feature;

import net.AbraXator.chakral.Chakral;
import net.AbraXator.chakral.init.ModBlocks;
import net.AbraXator.chakral.init.ModFeatures;
import net.AbraXator.chakral.world.feature.configuration.BigReplaceBlobConfiguration;
import net.AbraXator.chakral.world.feature.configuration.MineralCrystalConfiguration;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.DarkOakTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraftforge.registries.RegistryObject;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> WILTED_TREE = Chakral.createKey(Registries.CONFIGURED_FEATURE, "tree/wilted_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLACK_MINERAL_BLOB = Chakral.createKey(Registries.CONFIGURED_FEATURE, "black_mineral_blob");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PURPLE_MINERAL_BLOB = Chakral.createKey(Registries.CONFIGURED_FEATURE, "purple_mineral_blob");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLUE_MINERAL_BLOB = Chakral.createKey(Registries.CONFIGURED_FEATURE, "blue_mineral_blob");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LIGHT_BLUE_MINERAL_BLOB = Chakral.createKey(Registries.CONFIGURED_FEATURE, "light_blue_mineral_blob");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GREEN_MINERAL_BLOB = Chakral.createKey(Registries.CONFIGURED_FEATURE, "green_mineral_blob");
    public static final ResourceKey<ConfiguredFeature<?, ?>> YELLOW_MINERAL_BLOB = Chakral.createKey(Registries.CONFIGURED_FEATURE, "yellow_mineral_blob");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORANGE_MINERAL_BLOB = Chakral.createKey(Registries.CONFIGURED_FEATURE, "orange_mineral_blob");
    public static final ResourceKey<ConfiguredFeature<?, ?>> RED_MINERAL_BLOB = Chakral.createKey(Registries.CONFIGURED_FEATURE, "red_mineral_blob");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MINERAL_CRYSTAL = Chakral.createKey(Registries.CONFIGURED_FEATURE, "mineral_crystal");

    public static final ResourceKey<ConfiguredFeature<?, ?>> BLACK_MINERAL_ORE_COAL = Chakral.createKey(Registries.CONFIGURED_FEATURE, "black_mineral_ore_coal");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLACK_MINERAL_ORE_COAL_BURIED = Chakral.createKey(Registries.CONFIGURED_FEATURE, "black_mineral_ore_coal_buried");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLACK_MINERAL_ORE_IRON = Chakral.createKey(Registries.CONFIGURED_FEATURE, "black_mineral_ore_iron");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLACK_MINERAL_ORE_IRON_SMALL = Chakral.createKey(Registries.CONFIGURED_FEATURE, "black_mineral_ore_iron_small");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLACK_MINERAL_ORE_GOLD = Chakral.createKey(Registries.CONFIGURED_FEATURE, "black_mineral_ore_gold");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLACK_MINERAL_ORE_GOLD_BURIED = Chakral.createKey(Registries.CONFIGURED_FEATURE, "black_mineral_ore_gold_buried");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLACK_MINERAL_ORE_REDSTONE = Chakral.createKey(Registries.CONFIGURED_FEATURE, "black_mineral_ore_redstone");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLACK_MINERAL_ORE_DIAMOND_SMALL = Chakral.createKey(Registries.CONFIGURED_FEATURE, "black_mineral_ore_diamond_small");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLACK_MINERAL_ORE_DIAMOND_LARGE = Chakral.createKey(Registries.CONFIGURED_FEATURE, "black_mineral_ore_diamond_large");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLACK_MINERAL_ORE_DIAMOND_BURIED = Chakral.createKey(Registries.CONFIGURED_FEATURE, "black_mineral_ore_buried");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLACK_MINERAL_ORE_LAPIS = Chakral.createKey(Registries.CONFIGURED_FEATURE, "black_mineral_ore_lapis");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLACK_MINERAL_ORE_LAPIS_BURIED = Chakral.createKey(Registries.CONFIGURED_FEATURE, "black_mineral_ore_lapis_buried");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLACK_MINERAL_ORE_EMERALD = Chakral.createKey(Registries.CONFIGURED_FEATURE, "black_mineral_ore_emerald");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLACK_MINERAL_ORE_COPPER_SMALL = Chakral.createKey(Registries.CONFIGURED_FEATURE, "black_mineral_ore_copper_small");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLACK_MINERAL_ORE_COPPER_LARGE = Chakral.createKey(Registries.CONFIGURED_FEATURE, "black_mineral_ore_copper_large");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BUDDING_AMETHYST = Chakral.createKey(Registries.CONFIGURED_FEATURE, "budding_amethyst");

    public static final ResourceKey<ConfiguredFeature<?, ?>> CACTOID_PATCH = Chakral.createKey(Registries.CONFIGURED_FEATURE, "budding_amethyst");

    private static final RuleTest BLACK_MINERAL_TEST = new BlockMatchTest(ModBlocks.BLACK_MINERAL.get());
    private static final BlockState COAL = ModBlocks.BLACK_MINERAL_COAL_ORE.get().defaultBlockState();
    private static final BlockState IRON = ModBlocks.BLACK_MINERAL_IRON_ORE.get().defaultBlockState();
    private static final BlockState GOLD = ModBlocks.BLACK_MINERAL_GOLD_ORE.get().defaultBlockState();
    private static final BlockState REDSTONE = ModBlocks.BLACK_MINERAL_REDSTONE_ORE.get().defaultBlockState();
    private static final BlockState DIAMOND = ModBlocks.BLACK_MINERAL_DIAMOND_ORE.get().defaultBlockState();
    private static final BlockState LAPIS = ModBlocks.BLACK_MINERAL_LAPIS_ORE.get().defaultBlockState();
    private static final BlockState EMERALD = ModBlocks.BLACK_MINERAL_EMERALD_ORE.get().defaultBlockState();
    private static final BlockState COPPER = ModBlocks.BLACK_MINERAL_EMERALD_ORE.get().defaultBlockState();

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context){
        HolderGetter<ConfiguredFeature<?, ?>> features = context.lookup(Registries.CONFIGURED_FEATURE);
        registerFeature(context, WILTED_TREE, Feature.TREE, treeConfiguration().build());
        registerFeature(context, MINERAL_CRYSTAL, ModFeatures.MINERAL_CRYSTAL.get(), new MineralCrystalConfiguration(ModBlocks.RED_MINERAL.get().defaultBlockState(), ModBlocks.BUDDING_RED_MINERAL.get().defaultBlockState(), UniformInt.of(3, 7), UniformInt.of(6, 14)));
        registerFeature(context, BLACK_MINERAL_BLOB, ModFeatures.BIG_REPLACE_BLOB.get(), new BigReplaceBlobConfiguration(Blocks.DEEPSLATE.defaultBlockState(), ModBlocks.BLACK_MINERAL.get().defaultBlockState(), UniformInt.of(32, 32)));
        registerFeature(context, PURPLE_MINERAL_BLOB, ModFeatures.BIG_REPLACE_BLOB.get(), coloredMineralBlobConfig(Blocks.AMETHYST_BLOCK, Blocks.BUDDING_AMETHYST));
        registerFeature(context, BLUE_MINERAL_BLOB, ModFeatures.BIG_REPLACE_BLOB.get(), coloredMineralBlobConfig(ModBlocks.BLUE_MINERAL, ModBlocks.BUDDING_BLUE_MINERAL));
        registerFeature(context, LIGHT_BLUE_MINERAL_BLOB, ModFeatures.BIG_REPLACE_BLOB.get(), coloredMineralBlobConfig(ModBlocks.LIGHT_BLUE_MINERAL, ModBlocks.BUDDING_LIGHT_BLUE_MINERAL));
        registerFeature(context, GREEN_MINERAL_BLOB, ModFeatures.BIG_REPLACE_BLOB.get(), coloredMineralBlobConfig(ModBlocks.GREEN_MINERAL, ModBlocks.BUDDING_GREEN_MINERAL));
        registerFeature(context, YELLOW_MINERAL_BLOB, ModFeatures.BIG_REPLACE_BLOB.get(), coloredMineralBlobConfig(ModBlocks.YELLOW_MINERAL, ModBlocks.BUDDING_YELLOW_MINERAL));
        registerFeature(context, ORANGE_MINERAL_BLOB, ModFeatures.BIG_REPLACE_BLOB.get(), coloredMineralBlobConfig(ModBlocks.ORANGE_MINERAL, ModBlocks.BUDDING_ORANGE_MINERAL));
        registerFeature(context, RED_MINERAL_BLOB, ModFeatures.BIG_REPLACE_BLOB.get(), coloredMineralBlobConfig(ModBlocks.RED_MINERAL, ModBlocks.BUDDING_RED_MINERAL));
        registerFeature(context, BLACK_MINERAL_ORE_COAL, Feature.ORE, blackMineralOreConfig(COAL, 17));
        registerFeature(context, BLACK_MINERAL_ORE_COAL_BURIED, Feature.ORE, blackMineralOreConfig(COAL, 17, 0.5F));
        registerFeature(context, BLACK_MINERAL_ORE_IRON, Feature.ORE, blackMineralOreConfig(IRON, 9));
        registerFeature(context, BLACK_MINERAL_ORE_IRON_SMALL, Feature.ORE, blackMineralOreConfig(IRON, 4));
        registerFeature(context, BLACK_MINERAL_ORE_GOLD, Feature.ORE, blackMineralOreConfig(IRON, 9));
        registerFeature(context, BLACK_MINERAL_ORE_GOLD_BURIED, Feature.ORE, blackMineralOreConfig(GOLD, 9, 0.5F));
        registerFeature(context, BLACK_MINERAL_ORE_REDSTONE, Feature.ORE, blackMineralOreConfig(REDSTONE, 8));
        registerFeature(context, BLACK_MINERAL_ORE_DIAMOND_SMALL, Feature.ORE, blackMineralOreConfig(DIAMOND, 4, 0.5F));
        registerFeature(context, BLACK_MINERAL_ORE_DIAMOND_LARGE, Feature.ORE, blackMineralOreConfig(DIAMOND, 12, 0.7F));
        registerFeature(context, BLACK_MINERAL_ORE_DIAMOND_BURIED, Feature.ORE, blackMineralOreConfig(DIAMOND, 8, 1.0F));
        registerFeature(context, BLACK_MINERAL_ORE_LAPIS, Feature.ORE, blackMineralOreConfig(LAPIS, 7));
        registerFeature(context, BLACK_MINERAL_ORE_LAPIS_BURIED, Feature.ORE, blackMineralOreConfig(LAPIS, 7, 1.0F));
        registerFeature(context, BLACK_MINERAL_ORE_EMERALD, Feature.ORE, blackMineralOreConfig(EMERALD, 3));
        registerFeature(context, BLACK_MINERAL_ORE_COPPER_SMALL, Feature.ORE, blackMineralOreConfig(COPPER, 10));
        registerFeature(context, BLACK_MINERAL_ORE_COPPER_LARGE, Feature.ORE, blackMineralOreConfig(COPPER, 20));
        registerFeature(context, BUDDING_AMETHYST, Feature.ORE, new OreConfiguration(new BlockMatchTest(Blocks.AMETHYST_BLOCK), Blocks.BUDDING_AMETHYST.defaultBlockState(), 5));
    }

    private static OreConfiguration blackMineralOreConfig(BlockState state, int size){
        return blackMineralOreConfig(state, size, 0);
    }

    private static OreConfiguration blackMineralOreConfig(BlockState state, int size, float discardOnAirExposureChance){
        return new OreConfiguration(BLACK_MINERAL_TEST, state, size, discardOnAirExposureChance);
    }

    private static BigReplaceBlobConfiguration coloredMineralBlobConfig(RegistryObject<Block> mineral, RegistryObject<Block> budding){
        return coloredMineralBlobConfig(mineral.get(), budding.get());
    }

    private static BigReplaceBlobConfiguration coloredMineralBlobConfig(Block mineral, Block budding){
        return new BigReplaceBlobConfiguration(ModBlocks.BLACK_MINERAL.get().defaultBlockState(), mineral.defaultBlockState(), budding.defaultBlockState(), UniformInt.of(28, 32));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void registerFeature(BootstapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> configuredFeatureKey, F feature, FC configuration) {
        context.register(configuredFeatureKey, new ConfiguredFeature<>(feature, configuration));
    }

    private static TreeConfiguration.TreeConfigurationBuilder treeConfiguration(){
        return createStraightBlobTree(ModBlocks.WILTED_LOG.get(), ModBlocks.WILTED_LEAVES.get(), 4, 2, 0, 2).ignoreVines();
    }

    private static TreeConfiguration.TreeConfigurationBuilder createStraightBlobTree(Block p_195147_, Block p_195148_, int pBaseHeight, int pHeightRandA, int pHeightRandB, int p_195152_) {
        return new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(p_195147_), new DarkOakTrunkPlacer(pBaseHeight, pHeightRandA, pHeightRandB), BlockStateProvider.simple(p_195148_), new BlobFoliagePlacer(ConstantInt.of(p_195152_), ConstantInt.of(0), 3), new TwoLayersFeatureSize(1, 0, 1));
    }
}
