package net.AbraXator.chakral.worldgen.features;

import net.AbraXator.chakral.Chakral;
import net.AbraXator.chakral.blocks.ModBlocks;
import net.AbraXator.chakral.worldgen.BigCrystalConfiguration;
import net.AbraXator.chakral.worldgen.ModFeatures;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModConfigFeatures {
    /*public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURE =
            DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, Chakral.MOD_ID);

    public static final RegistryObject<ConfiguredFeature<?, ?>> CRYSTAL =
            CONFIGURED_FEATURE.register("crystal", () ->
                    new ConfiguredFeature<>(ModFeatures.BIG_CRYSTAL_FEATURE.get(), new BigCrystalConfiguration(
                            BlockStateProvider.simple(ModBlocks.GREEN_MINERAL.get()),
                            BlockStateProvider.simple(ModBlocks.GREEN_CRYSTAL.get())
                    )));

    public static void register(IEventBus eventBus){
        CONFIGURED_FEATURE.register(eventBus);
    }*/
}
