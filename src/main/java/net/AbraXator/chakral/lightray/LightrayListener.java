package net.AbraXator.chakral.lightray;

import net.AbraXator.chakral.client.particle.LightRayParticleOption;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gameevent.GameEventListener;
import net.minecraft.world.level.gameevent.PositionSource;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.Optional;

public class LightrayListener implements GameEventListener {
    protected final PositionSource listenerSource;
    protected final int listenerRange;
    public LightrayData currentLightray;
    public int travelTimeInTicks;
    private final LightraySelector lightraySelector;

    public LightrayListener(PositionSource listenerSource, LightrayData currentLightray, int listenerRange, LightraySelector lightraySelector) {
        this.listenerSource = listenerSource;
        this.currentLightray = currentLightray;
        this.listenerRange = listenerRange;
        this.lightraySelector = lightraySelector;
    }

    @Override
    public PositionSource getListenerSource() {
        return this.listenerSource;
    }

    @Override
    public int getListenerRadius() {
        return this.listenerRange;
    }

    public void tick(Level level){
        if(level instanceof ServerLevel serverLevel){
            if (this.currentLightray == null){
                this.lightraySelector.chosenRay(serverLevel.getGameTime()).ifPresent(lightrayData -> {
                    this.currentLightray = lightrayData;
                    Vec3 vec3 = lightrayData.pos();
                    this.travelTimeInTicks = Mth.floor(this.currentLightray.distance());
                    serverLevel.sendParticles(new LightRayParticleOption(this.listenerSource, this.travelTimeInTicks), vec3.x(), vec3.y(), vec3.z(), 1, 0.0D, 0.0D, 0.0D, 0.0D);
                    this.lightraySelector.startOver();
                });
            }

            if(this.currentLightray != null){
                --this.travelTimeInTicks;
                if(this.travelTimeInTicks <= 0){
                    this.travelTimeInTicks = 0;
                    this.currentLightray = null;
                }
            }
        }
    }

    @Override
    public boolean handleGameEvent(ServerLevel level, GameEvent gameEvent, GameEvent.Context context, Vec3 pos) {
        if (this.currentLightray != null || !gameEvent.getName().equals("block_place")) return false;
        else {
            Optional<Vec3> optionalPos = this.listenerSource.getPosition(level);
            if(optionalPos.isEmpty()) {
                return false;
            }else {
                AABB aabb = new AABB(pos, pos);
                level.getBlockStates(aabb).forEach(blockState -> {

                });
                this.currentLightray = new LightrayData(pos, level.getBlockState(BlockPos.containing(pos.x, pos.y, pos.z)), context.sourceEntity().getUUID(), ((float) pos.distanceTo(optionalPos.get())));
                return true;
            }
        }
    }
}
