package net.AbraXator.chakramod.blocks.custom;

import net.AbraXator.chakramod.blocks.entity.custom.ShardRefinerBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class ShardRefinerBlock extends BaseEntityBlock {
    public ShardRefinerBlock(Properties properties) {
        super(properties);
    }


    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new ShardRefinerBlockEntity(pPos, pState);
    }
}
