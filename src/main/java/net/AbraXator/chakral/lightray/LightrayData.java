package net.AbraXator.chakral.lightray;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.UUIDUtil;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import java.util.Optional;
import java.util.UUID;

public record LightrayData(Vec3 pos, BlockState originalState, UUID owner, float distance) {
    public static final Codec<LightrayData> CODEC = RecordCodecBuilder.create(lightrayDataInstance ->
            lightrayDataInstance.group (
                Vec3.CODEC.fieldOf("pos").forGetter(o -> o.pos),
                BlockState.CODEC.fieldOf("originalState").forGetter(o -> o.originalState),
                UUIDUtil.CODEC.fieldOf("owner").forGetter(o -> o.owner),
                Codec.floatRange(0.0F, Float.MAX_VALUE).fieldOf("distance").forGetter(o -> o.distance)
            ).apply(lightrayDataInstance, LightrayData::new)
    );

    public Optional<Entity> getOwner(ServerLevel level){
        return Optional.of(owner()).map(level::getEntity);
    }
}
