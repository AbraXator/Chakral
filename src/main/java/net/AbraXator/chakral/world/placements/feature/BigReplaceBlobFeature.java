package net.AbraXator.chakral.world.placements.feature;

import com.mojang.serialization.Codec;
import net.AbraXator.chakral.world.feature.configuration.BigReplaceBlobConfiguration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

import javax.annotation.Nullable;

public class BigReplaceBlobFeature extends Feature<BigReplaceBlobConfiguration> {
    public BigReplaceBlobFeature(Codec<BigReplaceBlobConfiguration> pCodec) {
        super(pCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<BigReplaceBlobConfiguration> pContext) {
        BigReplaceBlobConfiguration replacesphereconfiguration = pContext.config();
        WorldGenLevel worldgenlevel = pContext.level();
        RandomSource randomsource = pContext.random();
        Block block = replacesphereconfiguration.targetState.getBlock();
        BlockPos blockpos = findTarget(worldgenlevel, pContext.origin().mutable().clamp(Direction.Axis.Y, worldgenlevel.getMinBuildHeight() + 1, worldgenlevel.getMaxBuildHeight() - 1), block);
        if (blockpos == null) {
            return false;
        } else {
            int i = replacesphereconfiguration.radius().sample(randomsource);
            int j = replacesphereconfiguration.radius().sample(randomsource);
            int k = replacesphereconfiguration.radius().sample(randomsource);
            int l = Math.max(i, Math.max(j, k));
            boolean flag = false;

            for(BlockPos blockpos1 : BlockPos.withinManhattan(blockpos, i, j, k)) {
                if (blockpos1.distManhattan(blockpos) > l) {
                    break;
                }

                BlockState blockstate = worldgenlevel.getBlockState(blockpos1);
                if (blockstate.is(block)) {
                    this.setBlock(worldgenlevel, blockpos1, randomsource.nextDouble() <= 0.05 ? replacesphereconfiguration.secondaryState : replacesphereconfiguration.primaryState);
                    flag = true;
                }
            }

            return flag;
        }
    }

    @Nullable
    private static BlockPos findTarget(LevelAccessor pLevel, BlockPos.MutableBlockPos pTopPos, Block pBlock) {
        while(pTopPos.getY() > pLevel.getMinBuildHeight() + 1) {
            BlockState blockstate = pLevel.getBlockState(pTopPos);
            if (blockstate.is(pBlock)) {
                return pTopPos;
            }

            pTopPos.move(Direction.DOWN);
        }

        return null;
    }
}
