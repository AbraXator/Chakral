package net.AbraXator.chakral.blocks.custom;

import net.AbraXator.chakral.blocks.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class MineralRichDirtBlock extends Block {
    public BlockState blockState;
    public MineralRichDirtBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if(stateAroundBlock(3, pLevel).getBlock() instanceof MineralRichDirtBlock dirtBlock){
            dirtBlock.blockState = ModBlocks.GLEAMSHROOM.get().defaultBlockState();
        }
    }

    private BlockState stateAroundBlock(int space, Level level){
        for (int i = 0; i < space; i++) {
            for (int j = 0; j < space; j++) {
                for (int k = 0; k < space; k++) {
                    BlockPos blockPos = new BlockPos(i, j, k);
                    BlockState state = level.getBlockState(blockPos);
                    return state;
                }
            }
        }
        return null;
    }
}