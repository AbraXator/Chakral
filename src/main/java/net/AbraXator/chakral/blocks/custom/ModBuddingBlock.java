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

public class ModBuddingBlock extends AmethystBlock {
    public static final int GROWTH_CHANCE = 5;
    private static final Direction[] DIRECTIONS = Direction.values();
    private final RegistryObject<Block> crystal;

    public ModBuddingBlock(Properties properties, RegistryObject<Block> crystal) {
        super(properties);
        this.crystal = crystal;
    }

    public PushReaction getPistonPushReaction(BlockState pState) {
        return PushReaction.DESTROY;
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource randomSource) {
        if(randomSource.nextInt(5) == 0){
            Direction direction = DIRECTIONS[randomSource.nextInt(DIRECTIONS.length)];
            BlockPos blockpos = pos.relative(direction);
            BlockState blockstate = level.getBlockState(blockpos);
            Block block = null;
            if(canGrow(blockstate)){
                block = ModBlocks.GLOWSTONE_CRYSTAL.get();
            }

            if(block != null){
                BlockState budState = block.defaultBlockState().setValue(Crystal.FACING, direction).setValue(Crystal.WATERLOGGED, blockstate.getFluidState().getType() == Fluids.WATER);
            }
        }
    }

    public static boolean canGrow(BlockState pState) {
        return pState.isAir() || pState.is(Blocks.WATER) && pState.getFluidState().getAmount() == 8;
    }
}
