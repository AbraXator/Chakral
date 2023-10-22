package net.AbraXator.chakral.server.world.biome;

import net.AbraXator.chakral.server.world.placements.ModPlacedFeatures;
import net.minecraft.core.HolderGetter;
import net.minecraft.data.worldgen.Carvers;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.Musics;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import javax.annotation.Nullable;

public class ModOverworldBiomes {
    protected static int calculateSkyColor(float p_194844_) {
        float $$1 = p_194844_ / 3.0F;
        $$1 = Mth.clamp($$1, -1.0F, 1.0F);
        return Mth.hsvToRgb(0.62222224F - $$1 * 0.05F, 0.5F + $$1 * 0.1F, 1.0F);
    }

    private static Biome biome(boolean p_265746_, float p_265800_, float p_265276_, MobSpawnSettings.Builder p_265425_, BiomeGenerationSettings.Builder p_265371_, @Nullable Music p_265636_) {
        return biome(p_265746_, p_265800_, p_265276_, 4159204, 329011, (Integer)null, (Integer)null, p_265425_, p_265371_, p_265636_);
    }

    private static Biome biome(boolean p_273483_, float p_272621_, float p_273588_, int p_273605_, int p_272756_, @Nullable Integer p_272889_, @Nullable Integer p_272657_, MobSpawnSettings.Builder p_273300_, BiomeGenerationSettings.Builder p_272700_, @Nullable Music p_272996_) {
        BiomeSpecialEffects.Builder biomespecialeffects$builder = (new BiomeSpecialEffects.Builder()).waterColor(p_273605_).waterFogColor(p_272756_).fogColor(12638463).skyColor(calculateSkyColor(p_272621_)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).backgroundMusic(p_272996_);
        if (p_272889_ != null) {
            biomespecialeffects$builder.grassColorOverride(p_272889_);
        }

        if (p_272657_ != null) {
            biomespecialeffects$builder.foliageColorOverride(p_272657_);
        }

        return (new Biome.BiomeBuilder()).hasPrecipitation(p_273483_).temperature(p_272621_).downfall(p_273588_).specialEffects(biomespecialeffects$builder.build()).mobSpawnSettings(p_273300_.build()).generationSettings(p_272700_.build()).build();
    }


    private static void addFeature(BiomeGenerationSettings.Builder biomeBuilder, GenerationStep.Decoration step, ResourceKey<PlacedFeature> featureResourceKey) {
        biomeBuilder.addFeature(step, featureResourceKey);
    }

    private static void addCaveCarvers(BiomeGenerationSettings.Builder biomeBuilder){
        biomeBuilder.addCarver(GenerationStep.Carving.AIR, Carvers.CAVE);
        biomeBuilder.addCarver(GenerationStep.Carving.AIR, Carvers.CAVE_EXTRA_UNDERGROUND);
        biomeBuilder.addCarver(GenerationStep.Carving.AIR, Carvers.CANYON);
    }

    private static void addModOres(BiomeGenerationSettings.Builder builder){
        addFeature(builder, GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.BLACK_MINERAL_ORE_COAL);
        addFeature(builder, GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.BLACK_MINERAL_ORE_COAL_BURIED);
        addFeature(builder, GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.BLACK_MINERAL_ORE_COPPER_SMALL);
        addFeature(builder, GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.BLACK_MINERAL_ORE_COPPER_LARGE);
        addFeature(builder, GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.BLACK_MINERAL_ORE_IRON);
        addFeature(builder, GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.BLACK_MINERAL_ORE_IRON_SMALL);
        addFeature(builder, GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.BLACK_MINERAL_ORE_GOLD);
        addFeature(builder, GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.BLACK_MINERAL_ORE_GOLD_BURIED);
        addFeature(builder, GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.BLACK_MINERAL_ORE_REDSTONE);
        addFeature(builder, GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.BLACK_MINERAL_ORE_LAPIS);
        addFeature(builder, GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.BLACK_MINERAL_ORE_LAPIS_BURIED);
        addFeature(builder, GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.BLACK_MINERAL_ORE_DIAMOND_SMALL);
        addFeature(builder, GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.BLACK_MINERAL_ORE_DIAMOND_LARGE);
        addFeature(builder, GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.BLACK_MINERAL_ORE_DIAMOND_BURIED);
        addFeature(builder, GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.BLACK_MINERAL_ORE_EMERALD);
    }

    public static Biome mineralCaverns(HolderGetter<PlacedFeature> placed, HolderGetter<ConfiguredWorldCarver<?>> carver){
        MobSpawnSettings.Builder mobspawnsettings$builder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placed, carver);
        addCaveCarvers(biomeBuilder);
        //BiomeDefaultFeatures.addDefaultCrystalFormations(biomeBuilder);
        //addFeature(biomeBuilder, GenerationStep.Decoration.UNDERGROUND_DECORATION, ModPlacedFeatures.MINERAL_CRYSTAL);
        //addFeature(biomeBuilder, GenerationStep.Decoration.RAW_GENERATION, ModPlacedFeatures.BLACK_MINERAL_BLOB);
        //addFeature(biomeBuilder, GenerationStep.Decoration.UNDERGROUND_DECORATION, ModPlacedFeatures.PURPLE_MINERAL_BLOB);
        //addFeature(biomeBuilder, GenerationStep.Decoration.UNDERGROUND_DECORATION, ModPlacedFeatures.BLUE_MINERAL_BLOB);
        //addFeature(biomeBuilder, GenerationStep.Decoration.UNDERGROUND_DECORATION, ModPlacedFeatures.LIGHT_BLUE_MINERAL_BLOB);
        //addFeature(biomeBuilder, GenerationStep.Decoration.UNDERGROUND_DECORATION, ModPlacedFeatures.GREEN_MINERAL_BLOB);
        //addFeature(biomeBuilder, GenerationStep.Decoration.UNDERGROUND_DECORATION, ModPlacedFeatures.YELLOW_MINERAL_BLOB);
        //addFeature(biomeBuilder, GenerationStep.Decoration.UNDERGROUND_DECORATION, ModPlacedFeatures.ORANGE_MINERAL_BLOB);
        //addFeature(biomeBuilder, GenerationStep.Decoration.UNDERGROUND_DECORATION, ModPlacedFeatures.RED_MINERAL_BLOB);
        addModOres(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.UNDERGROUND_DECORATION, ModPlacedFeatures.WILTED_TREE);
        Music music = Musics.createGameMusic(SoundEvents.MUSIC_GAME);
        return biome(true, 0.8F, 0.4F, mobspawnsettings$builder, biomeBuilder, music);
    }

    public static Biome amethystCaverns(HolderGetter<PlacedFeature> placed, HolderGetter<ConfiguredWorldCarver<?>> carver){
        MobSpawnSettings.Builder mobspawnsettings$builder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placed, carver);
        addCaveCarvers(biomeBuilder);
        addModOres(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.UNDERGROUND_DECORATION, ModPlacedFeatures.WILTED_TREE);
        addFeature(biomeBuilder, GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.BUDDING_AMETHYST);
        Music music = Musics.createGameMusic(SoundEvents.MUSIC_GAME);
        return biome(true, 0.8F, 0.4F, mobspawnsettings$builder, biomeBuilder, music);
    }
}
