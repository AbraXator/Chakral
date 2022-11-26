package net.AbraXator.chakral.blocks.entity.custom;

import net.AbraXator.chakral.blocks.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ConduitBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class GleamshroomBlockEntity extends BlockEntity {
    public GleamshroomBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.GLEAMSHROOM_BLOCK_ENTITY.get(), pWorldPosition, pBlockState);
    }

    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, GleamshroomBlockEntity pBlockEntity){
        if(pLevel.isClientSide){
            return;
        }

    }
}
