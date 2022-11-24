package net.AbraXator.chakral.blocks.custom;

import net.AbraXator.chakral.blocks.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class ModBuddingBlock extends BuddingAmethystBlock {
    public static final int GROWTH_CHANCE = 5;
    private static final Direction[] DIRECTIONS = Direction.values();
    private final List<Block> crystal;

    public ModBuddingBlock(Properties properties, List<Block> crystal) {
        super(properties);
        this.crystal = crystal;
    }

    public PushReaction getPistonPushReaction(BlockState pState) {
        return PushReaction.DESTROY;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        if(randomSource.nextInt(5) == 0){
            Direction direction = DIRECTIONS[randomSource.nextInt(DIRECTIONS.length)];
            BlockPos growPos = blockPos.relative(direction);
            BlockState growState = serverLevel.getBlockState(growPos);
            Block block = null;
            if(canClusterGrowAtState(growState)){
                block = crystal.get(randomSource.nextInt(crystal.size()));
            }
            if (block != null) {
                BlockState budState = block.defaultBlockState().setValue(Crystal.FACING, direction).setValue(Crystal.WATERLOGGED, Boolean.valueOf(growState.getFluidState().getType() == Fluids.WATER));
                serverLevel.setBlockAndUpdate(growPos, budState);
            }
        }
    }
}
