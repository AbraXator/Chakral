package net.AbraXator.chakral.worldgen;

import com.mojang.serialization.Codec;
import net.AbraXator.chakral.blocks.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

public class BigCrystalFeature extends Feature<BigCrystalConfiguration> {
    public BigCrystalFeature(Codec<BigCrystalConfiguration> pCodec) {
        super(pCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<BigCrystalConfiguration> pContext) {
        BlockPos blockPos = pContext.origin();
        WorldGenLevel worldGenLevel;
        for (worldGenLevel = pContext.level(); worldGenLevel.isEmptyBlock(blockPos) && blockPos.getY() > worldGenLevel.getMinBuildHeight() + 2; blockPos = blockPos.below()){
        }
        this.setBlock(worldGenLevel, blockPos, pContext.config().baseBlock.getState(pContext.random(), blockPos));
        return true;
    }

    public boolean place2(FeaturePlaceContext<BigCrystalConfiguration> pContext) {
        BlockPos blockPos = pContext.origin();
        RandomSource randomSource = pContext.random();
        WorldGenLevel worldGenLevel;
        for (worldGenLevel = pContext.level(); worldGenLevel.isEmptyBlock(blockPos) && blockPos.getY() > worldGenLevel.getMinBuildHeight() + 2; blockPos = blockPos.below()){
        }

        if(!worldGenLevel.getBlockState(blockPos).is(ModBlocks.BLACK_MINERAL.get())){
            return false;
        }else {
            blockPos = blockPos.above(randomSource.nextInt(4));
            int i = randomSource.nextInt(4);
            int j = randomSource.nextInt(4) + 7;
            if (j > 1 && randomSource.nextInt(60) == 0) {
                blockPos = blockPos.above(10 + randomSource.nextInt(30));
            }

            for (int k = 0; k < i; ++i) {
                float f = (1.0F - (float)k / (float)i) * (float)j;
                int l = Mth.ceil(f);
                for (int i1 = -l; i1 <= l; ++i1){
                    float f1 = (float)Mth.abs(i1) - 0.25F;
                    for (int j1 = -l; j1 <= l; ++j1){
                        float f2 = (float)Mth.abs(j1) - 0.25F;
                        if((i1 == 0 && j1 == 0 || !(f1 * f1 + f2 * f2 > f * f))&& (i1 != -l && i1 != l && j1 != -l && j1 != l || !(randomSource.nextFloat() > 0.75F))) {
                            BlockState blockState = worldGenLevel.getBlockState(blockPos.offset(i1, k, j1));
                            if(blockState.isAir() || blockState.is(ModBlocks.BLACK_MINERAL.get())){
                                this.setBlock(worldGenLevel, blockPos.offset(i1, k, j1), pContext.config().baseBlock.getState(randomSource, blockPos));
                            }
                            if(k != 0 && l > 1){
                                blockState = worldGenLevel.getBlockState(blockPos.offset(i1, -k, j1));
                                if(blockState.isAir() || blockState.is(ModBlocks.BLACK_MINERAL.get())){
                                    this.setBlock(worldGenLevel, blockPos.offset(i1, -k, j1), pContext.config().baseBlock.getState(randomSource, blockPos));
                                }
                            }
                        }
                    }
                }
            }
            int k1 = j - 1;
            if(k1 < 0){
                k1 = 0;
            }else if (k1 > 1){
                k1 = 1;
            }
            for(int l1 = -k1; l1 <= k1; ++l1){
                for(int i2 = -k1; i2 <= k1; ++i2){
                    BlockPos blockPos1 = blockPos.offset(l1, -1, i2);
                    int j2 = 50;
                    if(Math.abs(l1) == 1 && Math.abs(i2) == 1){
                        j2 = randomSource.nextInt(5);
                    }

                }
            }
            return true;
        }
    }
}
