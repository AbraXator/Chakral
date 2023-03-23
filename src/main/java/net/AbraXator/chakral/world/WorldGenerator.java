package net.AbraXator.chakral.world;

import net.AbraXator.chakral.Chakral;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class WorldGenerator extends DatapackBuiltinEntriesProvider {

    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap);

    public WorldGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of("minecraft", Chakral.MOD_ID));
    }

    public static void addProvider(boolean isServer, DataGenerator dataGenerators, PackOutput output, CompletableFuture<HolderLookup.Provider> provider, ExistingFileHelper helper){
        dataGenerators.addProvider(isServer, new WorldGenerator(output, provider));
    }

    private static HolderLookup.Provider append(HolderLookup.Provider original, RegistrySetBuilder builder){
        return builder.buildPatch(RegistryAccess.fromRegistryOfRegistries(BuiltInRegistries.REGISTRY), original);
    }
}
