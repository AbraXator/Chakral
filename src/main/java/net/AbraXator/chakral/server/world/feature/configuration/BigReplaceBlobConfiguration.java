package net.AbraXator.chakral.server.world.feature.configuration;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public class BigReplaceBlobConfiguration implements FeatureConfiguration {
    public static final Codec<BigReplaceBlobConfiguration> CODEC = RecordCodecBuilder.create(objectInstance -> {
        return objectInstance.group(
                BlockState.CODEC.fieldOf("target").forGetter((getter) -> getter.targetState),
                BlockState.CODEC.fieldOf("primary_state").forGetter((p_161098_) -> p_161098_.primaryState),
                BlockState.CODEC.fieldOf("secondary_state").forGetter((p_161098_) -> p_161098_.secondaryState),
                IntProvider.codec(8, 32).fieldOf("radius").forGetter((p_161095_) -> p_161095_.radius))
                .apply(objectInstance, BigReplaceBlobConfiguration::new);
    });

    public final BlockState targetState;
    public final BlockState primaryState;
    public final BlockState secondaryState;
    private final IntProvider radius;

    public BigReplaceBlobConfiguration(BlockState targetState, BlockState primaryState, IntProvider radius) {
        this(targetState, primaryState, primaryState, radius);
    }

    public BigReplaceBlobConfiguration(BlockState targetState, BlockState primaryState, BlockState secondaryState, IntProvider radius) {
        this.targetState = targetState;
        this.primaryState = primaryState;
        this.secondaryState = secondaryState;
        this.radius = radius;
    }

    public IntProvider radius() {
        return this.radius;
    }
}
