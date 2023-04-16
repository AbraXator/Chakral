package net.AbraXator.chakral.blocks.entity;

import net.AbraXator.chakral.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class StemShroomBlockEntity extends BlockEntity {
    public StemShroomBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.STEMSHROOM_BLOCK_ENTITY.get(), pWorldPosition, pBlockState);
    }
}
