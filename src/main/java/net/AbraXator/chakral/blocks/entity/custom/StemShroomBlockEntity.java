package net.AbraXator.chakral.blocks.entity.custom;

import net.AbraXator.chakral.blocks.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.TickingBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class StemShroomBlockEntity extends BlockEntity {
    public StemShroomBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.STEMSHROOM_BLOCK_ENTITY.get(), pWorldPosition, pBlockState);
    }
}
