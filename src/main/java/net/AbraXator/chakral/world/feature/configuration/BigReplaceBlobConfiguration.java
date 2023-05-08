package net.AbraXator.chakral.world.feature.configuration;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public class BigReplaceBlobConfiguration implements FeatureConfiguration {
    public static final Codec<BigReplaceBlobConfiguration> CODEC = RecordCodecBuilder.create(objectInstance -> {
        return objectInstance.group(
                BlockState.CODEC.fieldOf("target").forGetter((getter) -> getter.targetState),
                BlockState.CODEC.fieldOf("state").forGetter((p_161098_) -> p_161098_.replaceState),
                IntProvider.codec(16, 32).fieldOf("radius").forGetter((p_161095_) -> p_161095_.radius))
                .apply(objectInstance, BigReplaceBlobConfiguration::new);
    });

    public final BlockState targetState;
    public final BlockState replaceState;
    private final IntProvider radius;

    public BigReplaceBlobConfiguration(BlockState p_161091_, BlockState p_161092_, IntProvider p_161093_) {
        this.targetState = p_161091_;
        this.replaceState = p_161092_;
        this.radius = p_161093_;
    }

    public IntProvider radius() {
        return this.radius;
    }
}
