package net.AbraXator.chakral.server.world.tree;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Function3;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.List;
import java.util.function.BiConsumer;

public class WiltedTrunkPlacer extends TrunkPlacer {
    public static final Codec<WiltedTrunkPlacer> CODEC = RecordCodecBuilder.create((p_70261_) -> {
        return trunkPlacerParts(p_70261_).apply(p_70261_, WiltedTrunkPlacer::new);
    });

    public static <T extends TrunkPlacer> TrunkPlacerType<T> makeType(Function3<Integer, Integer, Integer, T> function3){
        return new TrunkPlacerType<>(RecordCodecBuilder.create(tInstance -> trunkPlacerParts(tInstance).apply(tInstance, function3)));
    }

    public WiltedTrunkPlacer(int pBaseHeight, int pHeightRandA, int pHeightRandB) {
        super(pBaseHeight, pHeightRandA, pHeightRandB);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return makeType(WiltedTrunkPlacer::new);
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader pLevel, BiConsumer<BlockPos, BlockState> pBlockSetter, RandomSource pRandom, int pFreeTreeHeight, BlockPos pPos, TreeConfiguration pConfig) {
        Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(pRandom);
        for(int i = 0; i <= pFreeTreeHeight - 3; i++) {
            placeLog(pLevel, pBlockSetter, pRandom, pPos.above(i), pConfig);
        }

        int x = pPos.getX();
        int z = pPos.getZ();
        int j = pRandom.nextInt(2);
        for(int i = 0; i <= j; i++){
            x += direction.getStepX();
            z += direction.getStepZ();
            placeLog(pLevel, pBlockSetter, pRandom, new BlockPos(x, pPos.getY() + 3, z), pConfig);
        }

        for(int i = 0; i <= pFreeTreeHeight - 3; i++) {
            if(i != pPos.getY()){
                placeLog(pLevel, pBlockSetter, pRandom, pPos.below(i), pConfig);
            }
        }

        return ImmutableList.of(new FoliagePlacer.FoliageAttachment(new BlockPos(x, pPos.below(3).getY(), z), 0, false));
    }
}
