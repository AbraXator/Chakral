package net.AbraXator.chakral.server.lightray;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.UUIDUtil;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.UUID;

public record LightrayData(Vec3 pos, BlockState originalState, @Nullable UUID owner, float distance, GameEvent event) {
    public static final Codec<LightrayData> CODEC = RecordCodecBuilder.create(lightrayDataInstance ->
            lightrayDataInstance.group (
                Vec3.CODEC.fieldOf("pos").forGetter(o -> o.pos),
                BlockState.CODEC.fieldOf("originalState").forGetter(o -> o.originalState),
                UUIDUtil.CODEC.fieldOf("owner").forGetter(o -> o.owner),
                Codec.floatRange(0.0F, Float.MAX_VALUE).fieldOf("distance").forGetter(o -> o.distance),
                BuiltInRegistries.GAME_EVENT.byNameCodec().fieldOf("game_event").forGetter(o -> o.event)
            ).apply(lightrayDataInstance, LightrayData::new)
    );

    public LightrayData(Vec3 pos, BlockState originalState, @Nullable Entity entity, float distance, GameEvent event) {
        this(pos, originalState, entity != null ? entity.getUUID() : null, distance, event);
    }

    public Optional<Entity> getOwner(ServerLevel level){
        if(owner() == null) return Optional.empty();
        else return Optional.of(owner()).map(level::getEntity);
    }
}
