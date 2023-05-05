package net.AbraXator.chakral.world.biome;

import com.mojang.datafixers.util.Pair;
import net.AbraXator.chakral.init.ModBiomes;
import net.AbraXator.chakral.utils.ChakralLocation;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.Region;
import terrablender.api.RegionType;

import java.util.function.Consumer;

public class ModOverworldRegion extends Region {
    public static final ResourceLocation LOCATION = new ChakralLocation("overworld");

    public ModOverworldRegion() {
        super(LOCATION, RegionType.OVERWORLD, 1);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        this.addModifiedVanillaOverworldBiomes(mapper, builder -> {
            builder.replaceBiome(Biomes.DEEP_DARK, ModBiomes.MINRAL_CAVERNS);
        });
    }
}