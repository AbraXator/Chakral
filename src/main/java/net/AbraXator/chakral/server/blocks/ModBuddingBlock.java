package net.AbraXator.chakral.server.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.PushReaction;

import java.util.List;
import java.util.function.Supplier;

public class ModBuddingBlock extends BuddingAmethystBlock {
    public static final int GROWTH_CHANCE = 5;
    private static final Direction[] DIRECTIONS = Direction.values();
    private final List<Supplier<Block>> crystal;

    public ModBuddingBlock(Properties properties, Supplier<Block> crystal){
        this(properties.pushReaction(PushReaction.DESTROY), List.of(crystal));
    }

    public ModBuddingBlock(Properties properties, List<Supplier<Block>> crystals) {
        super(properties);
        this.crystal = crystals;
    }

    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (pRandom.nextInt(5) == 0) {
            Direction direction = DIRECTIONS[pRandom.nextInt(DIRECTIONS.length)];
            BlockPos blockpos = pPos.relative(direction);
            BlockState blockstate = pLevel.getBlockState(blockpos);
            Block block = null;
            if (canClusterGrowAtState(blockstate)) {
                block = crystal.get(0).get();
            } else if (blockstate.is(crystal.get(0).get()) && blockstate.getValue(AmethystClusterBlock.FACING) == direction) {
                block = crystal.get(1).get();
            } else if (blockstate.is(crystal.get(1).get()) && blockstate.getValue(AmethystClusterBlock.FACING) == direction) {
                block = crystal.get(2).get();
            }

            if (block != null) {
                BlockState blockstate1 = block.defaultBlockState().setValue(AmethystClusterBlock.FACING, direction).setValue(AmethystClusterBlock.WATERLOGGED, Boolean.valueOf(blockstate.getFluidState().getType() == Fluids.WATER));
                pLevel.setBlockAndUpdate(blockpos, blockstate1);
            }
        }
    }
}
