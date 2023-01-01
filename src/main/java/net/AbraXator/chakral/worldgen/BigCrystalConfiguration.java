package net.AbraXator.chakral.worldgen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.GeodeBlockSettings;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class BigCrystalConfiguration implements FeatureConfiguration {
    public final BlockStateProvider baseBlock;
    public static final Codec<BigCrystalConfiguration> CODEC = RecordCodecBuilder.create((instance) -> {
        return instance.group(BlockStateProvider.CODEC.fieldOf("base_block").forGetter((o) -> {
            return o.baseBlock;
        })).apply(instance, BigCrystalConfiguration::new);
    });

    public BigCrystalConfiguration(BlockStateProvider baseBlock){
        this.baseBlock = baseBlock;
    }
}
