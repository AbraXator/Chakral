package net.AbraXator.chakral.lightray;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.AbraXator.chakral.blocks.BaseLightrayBlock;
import net.AbraXator.chakral.blocks.entity.BaseLightrayBlockEntity;
import net.AbraXator.chakral.client.particle.TravelingParticle;
import net.AbraXator.chakral.init.ModGameEvents;
import net.AbraXator.chakral.init.ModParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.SculkSensorPhase;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gameevent.GameEventListener;
import net.minecraft.world.level.gameevent.PositionSource;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.Optional;

;

public class LightrayListener implements GameEventListener {
    protected final PositionSource listenerSource;
    protected final int listenerRange;
    protected final LightrayConfig config;
    public LightrayData currentLightray;
    private final LightraySelector lightraySelector;
    public int travelTimeInTicks;

    public static Codec<LightrayListener> codec(LightrayConfig config) {
        return RecordCodecBuilder.create(lightrayListenerInstance -> lightrayListenerInstance.group(
          PositionSource.CODEC.fieldOf("source").forGetter(lightrayListener -> lightrayListener.listenerSource),
          ExtraCodecs.NON_NEGATIVE_INT.fieldOf("range").forGetter(lightrayListener -> lightrayListener.listenerRange),
          LightrayData.CODEC.optionalFieldOf("ray").forGetter(lightrayListener -> Optional.ofNullable(lightrayListener.currentLightray)),
          LightraySelector.CODEC.fieldOf("selector").forGetter(lightrayListener -> lightrayListener.lightraySelector),
          ExtraCodecs.NON_NEGATIVE_INT.fieldOf("event_delay").orElse(0).forGetter(lightrayListener -> lightrayListener.travelTimeInTicks)
        ).apply(lightrayListenerInstance, (positionSource, integer, data, lightraySelector1, integer2) ->
                new LightrayListener(positionSource, integer, config, data.orElse(null), lightraySelector1, integer)));
    }

    public LightrayListener(PositionSource listenerSource, int listenerRange, LightrayConfig config, @Nullable LightrayData data, LightraySelector lightraySelector, int travelTimeInTicks) {
        this.listenerSource = listenerSource;
        this.listenerRange = listenerRange;
        this.config = config;
        this.currentLightray = data;
        this.lightraySelector = lightraySelector;
        this.travelTimeInTicks = travelTimeInTicks;
    }

    public LightrayListener(PositionSource source, int listenerRange, LightrayConfig config){
        this(source, listenerRange, config, null, new LightraySelector(), 0);
    }

    @Override
    public PositionSource getListenerSource() {
        return this.listenerSource;
    }

    @Override
    public int getListenerRadius() {
        return this.listenerRange;
    }

    public <T extends BlockEntity> void tick(Level level, BlockPos pos, BlockState state, T blockEntity){
        this.config.entityTick(level, pos, state, ((BaseLightrayBlockEntity) blockEntity));
        if(level instanceof ServerLevel serverLevel){
            if (this.currentLightray == null){
                this.lightraySelector.chosenRay(serverLevel.getGameTime()).ifPresent(lightrayData -> {
                    this.currentLightray = lightrayData;
                    Vec3 vec3 = lightrayData.pos();
                    this.travelTimeInTicks = Mth.floor(this.currentLightray.distance());
                    serverLevel.sendParticles(new TravelingParticle(ModParticles.LIGHT_RAY.get(), this.listenerSource, this.travelTimeInTicks), vec3.x(), vec3.y(), vec3.z(), 1, 0.0D, 0.0D, 0.0D, 0.0D);
                    this.config.onSignalSchedule();
                    this.lightraySelector.startOver();
                });
            }

            if(this.currentLightray != null){
                --this.travelTimeInTicks;
                if(this.travelTimeInTicks <= 0){
                    this.travelTimeInTicks = 0;
                    this.config.onSignalReceive(serverLevel, this, BlockPos.containing(this.currentLightray.pos()), this.currentLightray.event(), this.currentLightray.getOwner(serverLevel).orElse(null), this.currentLightray.distance());
                    level.setBlock(pos, state.setValue(BaseLightrayBlock.PHASE, SculkSensorPhase.ACTIVE), 3);
                    level.gameEvent(ModGameEvents.LIGHT_RAY_HIT.get(), pos, GameEvent.Context.of(state));
                    level.scheduleTick(pos, state.getBlock(), 40);
                    this.currentLightray = null;
                }
            }
        }
    }

    @Override
    public boolean handleGameEvent(ServerLevel level, GameEvent gameEvent, GameEvent.Context context, Vec3 pos) {
        if (this.currentLightray != null) return false;
        else if(gameEvent.getName().equals("block_place") || gameEvent.getName().equals("light_ray_hit")) {
            Optional<Vec3> optionalPos = this.listenerSource.getPosition(level);
            if(optionalPos.isEmpty() || !this.config.shouldListen(level, this, BlockPos.containing(pos), gameEvent, context)) {
                return false;
            }else {
                BlockPos blockPos = BlockPos.containing(pos.x, pos.y, pos.z);
                BlockState state = level.getBlockState(blockPos);
                if(state.getBlock().getLightEmission(state, level, blockPos) > 0){
                    this.lightraySelector.addRay(new LightrayData(pos, state, context.sourceEntity(), ((float) pos.distanceTo(optionalPos.get())), gameEvent), level.getGameTime());
                }
                return true;
            }
        }else return false;
    }

    public interface LightrayConfig {
        boolean shouldListen(ServerLevel serverLevel, GameEventListener listener, BlockPos pos,  GameEvent gameEvent, GameEvent.Context context);

        void onSignalReceive(ServerLevel serverLevel, GameEventListener listener, BlockPos pos, GameEvent gameEvent, @Nullable Entity owner, float distance);

        void entityTick(Level level, BlockPos blockpos, BlockState state, BaseLightrayBlockEntity entity);

        void onSignalSchedule();
    }
}