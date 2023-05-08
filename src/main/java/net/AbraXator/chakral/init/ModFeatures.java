package net.AbraXator.chakral.init;

import net.AbraXator.chakral.Chakral;
import net.AbraXator.chakral.world.feature.configuration.BigReplaceBlobConfiguration;
import net.AbraXator.chakral.world.feature.configuration.MineralCrystalConfiguration;
import net.AbraXator.chakral.world.placements.feature.BigReplaceBlobFeature;
import net.AbraXator.chakral.world.placements.feature.MineralCrystalFeature;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.NoOpFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(
            ForgeRegistries.FEATURES, Chakral.MOD_ID);

    public static final RegistryObject<Feature<BigReplaceBlobConfiguration>> BIG_REPLACE_BLOB = FEATURES.register("big_replace_blob", () -> new BigReplaceBlobFeature(BigReplaceBlobConfiguration.CODEC));
    public static final RegistryObject<Feature<MineralCrystalConfiguration>> MINERAL_CRYSTAL = FEATURES.register("mineral_crystal", () -> new MineralCrystalFeature(MineralCrystalConfiguration.CODEC));

    public static void register(IEventBus eventBus) {
        FEATURES.register(eventBus);
    }
}
