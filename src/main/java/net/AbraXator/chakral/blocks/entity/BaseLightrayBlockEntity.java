package net.AbraXator.chakral.blocks.entity;

import com.mojang.serialization.Dynamic;
import net.AbraXator.chakral.Chakral;
import net.AbraXator.chakral.lightray.LightrayListener;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.SculkSensorBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.BlockPositionSource;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gameevent.GameEventListener;

public abstract class BaseLightrayBlockEntity extends BlockEntity implements LightrayListener.LightrayConfig {
    private LightrayListener listener;

    public BaseLightrayBlockEntity(BlockEntityType<?> type, BlockPos pPos, BlockState pBlockState) {
        super(type, pPos, pBlockState);
        this.listener = new LightrayListener(new BlockPositionSource(this.getBlockPos()), 12, this);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        if(pTag.contains("listener", 10)) {
            LightrayListener.codec(this).parse(new Dynamic<>(NbtOps.INSTANCE, pTag.getCompound("listener"))).resultOrPartial(Chakral.LOGGER::error).ifPresent(lightrayListener -> {
                this.listener = lightrayListener;
            });
        }
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        LightrayListener.codec(this).encodeStart(NbtOps.INSTANCE, this.listener).resultOrPartial(Chakral.LOGGER::error).ifPresent(tag -> {
            pTag.put("listener", tag);
        });
    }

    public LightrayListener getListener() {
        return this.listener;
    }

    @Override
    public void onSignalSchedule() {
        this.setChanged();
    }

    @Override
    public boolean shouldListen(ServerLevel serverLevel, GameEventListener listener, BlockPos pos, GameEvent gameEvent, GameEvent.Context context) {
        return !pos.equals(this.getBlockPos()) && SculkSensorBlock.canActivate(this.getBlockState());
    }
}
