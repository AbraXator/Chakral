package net.AbraXator.chakral.world.feature.configuration;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public class MineralCrystalConfiguration implements FeatureConfiguration {
    public static final Codec<MineralCrystalConfiguration> CODEC = RecordCodecBuilder.create(objectInstance -> {
        return objectInstance.group(
                BlockState.CODEC.fieldOf("mineral").forGetter(getter -> getter.mineral),
                BlockState.CODEC.fieldOf("budding").forGetter(getter -> getter.budding),
                IntProvider.codec(3, 7).fieldOf("height").forGetter(getter -> getter.height),
                IntProvider.codec(3, 6).fieldOf("radius").forGetter(getter -> getter.radius))
                .apply(objectInstance, MineralCrystalConfiguration::new);
    });

    public final BlockState mineral;
    public final BlockState budding;
    private final IntProvider height;
    private final IntProvider radius;

    public MineralCrystalConfiguration(BlockState mineral, BlockState budding, IntProvider height, IntProvider radius) {
        this.mineral = mineral;
        this.budding = budding;
        this.height = height;
        this.radius = radius;
    }

    public IntProvider getHeight() {
        return this.height;
    }

    public IntProvider getRadius() {
        return this.height;
    }
}
