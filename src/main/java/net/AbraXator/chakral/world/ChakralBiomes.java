package net.AbraXator.chakral.world;

import net.AbraXator.chakral.Chakral;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.registries.ForgeRegistries;

public class ChakralBiomes {
    public static final ResourceKey<Biome> MINERAL_CAVERNS = register("mineral_caverns");

    private static ResourceKey<Biome> register(String name){
        return ResourceKey.create(ForgeRegistries.BIOMES.getRegistryKey(), new ResourceLocation(Chakral.MOD_ID, name));
    }
}
