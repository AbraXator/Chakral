package net.AbraXator.chakral.server.blocks;

import net.AbraXator.chakral.server.blocks.entity.GleamshroomBlockEntity;
import net.AbraXator.chakral.server.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class GleamshroomBlock extends BaseLightrayBlock {
    public GleamshroomBlock(Properties p_49224_) {
        super(p_49224_, ModBlockEntities.GLEAM_SHROOM_BLOCK_ENTITY::get);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new GleamshroomBlockEntity(pPos, pState);
    }
}
