package net.AbraXator.chakral.data;

import net.AbraXator.chakral.Chakral;
import net.AbraXator.chakral.init.ModBiomes;
import net.AbraXator.chakral.init.ModDamageTypes;
import net.AbraXator.chakral.world.feature.ModConfiguredFeatures;
import net.AbraXator.chakral.world.noise.ModNoises;
import net.AbraXator.chakral.world.placements.ModPlacedFeatures;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class RegistryDataGenerator extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, ModPlacedFeatures::bootstrap)
            .add(Registries.BIOME, ModBiomes::bootstrap)
            .add(Registries.NOISE, ModNoises::bootstrap)
            .add(Registries.DAMAGE_TYPE, ModDamageTypes::bootstrap);

    public RegistryDataGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of("minecraft", Chakral.MOD_ID));
    }

    private static HolderLookup.Provider append(HolderLookup.Provider original, RegistrySetBuilder builder) {
        return builder.buildPatch(RegistryAccess.fromRegistryOfRegistries(BuiltInRegistries.REGISTRY), original);
    }
}
