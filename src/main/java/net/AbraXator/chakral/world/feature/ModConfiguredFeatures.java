package net.AbraXator.chakral.world.feature;

import net.AbraXator.chakral.Chakral;
import net.AbraXator.chakral.init.ModBlocks;
import net.AbraXator.chakral.utils.ChakralLocation;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.ReplaceSphereConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.DarkOakTrunkPlacer;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> WILTED_TREE = Chakral.createKey(Registries.CONFIGURED_FEATURE, "tree/wilted_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLACK_MINERAL_BLOB = Chakral.createKey(Registries.CONFIGURED_FEATURE, "black_mineral_blob");

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context){
        HolderGetter<ConfiguredFeature<?, ?>> features = context.lookup(Registries.CONFIGURED_FEATURE);
        registerFeature(context, WILTED_TREE, Feature.TREE, treeConfiguration().build());
        registerFeature(context, BLACK_MINERAL_BLOB, Feature.REPLACE_BLOBS, new ReplaceSphereConfiguration(Blocks.DEEPSLATE.defaultBlockState(), ModBlocks.BLACK_MINERAL.get().defaultBlockState(), UniformInt.of(5, 9)));
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
