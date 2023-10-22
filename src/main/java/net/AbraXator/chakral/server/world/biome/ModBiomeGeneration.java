package net.AbraXator.chakral.server.world.biome;

import com.github.alexthe666.citadel.server.event.EventReplaceBiome;
import com.google.common.reflect.TypeToken;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.AbraXator.chakral.Chakral;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.commons.io.FileUtils;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class ModBiomeGeneration {
    public static final Gson GSON = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).setPrettyPrinting().create();
    private static final BiomeGenerationNoiseCondition MINERAL_CAVERNS = new BiomeGenerationNoiseCondition.Builder()
            .distanceFromSpawn(150)
            .modRarityOffset(0)
            .continentalness(0.1F, 1F)
            .depth(0.2F, 1F)
            .build();
    private static final BiomeGenerationNoiseCondition AMETHYST_CAVERNS = new BiomeGenerationNoiseCondition.Builder()
            .distanceFromSpawn(150)
            .modRarityOffset(0)
            .continentalness(0.1F, 1F)
            .depth(0.2F, 1F)
            .build();
    private static Map<ResourceKey<Biome>, BiomeGenerationNoiseCondition> biomes = new HashMap<>();


    public static void reloadConfig() {
        biomes.put(ModBiomes.MINRAL_CAVERNS, getConfigData("mineral_caverns", MINERAL_CAVERNS));
        biomes.put(ModBiomes.AMETHYST_CAVERNS, getConfigData("amethyst_caverns", AMETHYST_CAVERNS));
    }

    @Nullable
    public static ResourceKey<Biome> getBiomeForEvent(EventReplaceBiome event) {
        for(Map.Entry<ResourceKey<Biome>, BiomeGenerationNoiseCondition> condition : biomes.entrySet()) {
            if(condition.getValue().test(event)) {
                return condition.getKey();
            }
        }
        return null;
    }

    public static int getBiomeCount() {
        return biomes.size();
    }

    private static <T> T getOrCreateConfigFile(File file, String name, T defaults, Type type) {
        File configFile = new File(file, name + ".json");
        if(!configFile.exists()) {
            try {
                FileUtils.write(configFile, GSON.toJson(defaults));
            } catch (IOException e) {
                Chakral.LOGGER.error("Biome Generation Config: Couldn't write " + configFile, e);
            }
        }
        try {
            return GSON.fromJson(FileUtils.readFileToString(configFile), type);
        } catch (IOException e) {
            Chakral.LOGGER.error("Biome Generation Config: Couldn't load " + configFile, e);
        }

        return defaults;
    }

    private static File getConfigDirectory() {
        Path configPath = FMLPaths.CONFIGDIR.get();
        Path jsonPath = Paths.get(configPath.toAbsolutePath().toString(), "chakral_biome_generation");
        return jsonPath.toFile();
    }

    private static BiomeGenerationNoiseCondition getConfigData(String name, BiomeGenerationNoiseCondition condition) {
        BiomeGenerationNoiseCondition configData = getOrCreateConfigFile(getConfigDirectory(), name, condition, new TypeToken<BiomeGenerationNoiseCondition>() {}.getType());
        return configData;
    }
}
