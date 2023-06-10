package net.AbraXator.chakral.blocks.entity;

import net.AbraXator.chakral.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class ChakralSignBlockEntity extends SignBlockEntity {
    public ChakralSignBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(pPos, pBlockState);
    }

    @Override
    public BlockEntityType<?> getType() {
        return ModBlockEntities.CHAKRAL_SIGN.get();
    }
}
