package net.AbraXator.chakral.blocks.entity;

import net.AbraXator.chakral.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SculkSensorBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gameevent.GameEventListener;

public class GleamshroomBlockEntity extends BaseLightrayBlockEntity{
    public GleamshroomBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.GLEAM_SHROOM_BLOCK_ENTITY.get(), pPos, pBlockState);
    }

    @Override
    public void onSignalReceive(ServerLevel serverLevel, GameEventListener listener, BlockPos pos, GameEvent gameEvent, Entity owner, float distance) {

    }

    @Override
    public void entityTick(Level level, BlockPos blockpos, BlockState state, BaseLightrayBlockEntity entity) {

    }
}