package net.AbraXator.chakral.server.world.feature.configuration;

import com.mojang.serialization.Codec;
import net.AbraXator.chakral.server.init.ModBlocks;
import net.AbraXator.chakral.server.init.ModTags;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.material.Fluids;

public class WiltedTreeFeature extends Feature<NoneFeatureConfiguration> {
    public WiltedTreeFeature(Codec<NoneFeatureConfiguration> pCodec) {
        super(pCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        BlockPos pos = context.origin();
        if(placeTree(context)) {
            RandomSource randomSource = context.random();
            WorldGenLevel level = context.level();
            BlockPos.MutableBlockPos leafPos = new BlockPos.MutableBlockPos();

            for(int i = 0; i < 5 + randomSource.nextInt(10); i++) {
                leafPos.set(pos);
                leafPos.move(randomSource.nextInt(12) - 6, randomSource.nextInt(12), randomSource.nextInt(12) - 6);
                while(level.isEmptyBlock(leafPos) && leafPos.getY() > level.getMinBuildHeight()) {
                    leafPos.move(0, -1, 0);
                }
                if(level.getBlockState(leafPos).isCollisionShapeFullBlock(level, leafPos)) {
                    leafPos.move(0, 1, 0);
                    if(level.getBlockState(leafPos).canBeReplaced()) {
                        level.setBlock(leafPos, ModBlocks.WILTED_LEAVES.get().defaultBlockState().setValue(LeavesBlock.WATERLOGGED, level.getFluidState(leafPos).is(Fluids.WATER)), 4);
                    }
                }
            }
            return true;
        }
        return false;
    }

    public boolean placeTree(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        RandomSource randomsource = context.random();
        WorldGenLevel level = context.level();
        BlockPos treeGround = context.origin();
        int centerAboveGround = randomsource.nextInt(5);
        int height = centerAboveGround + 4 + randomsource.nextInt(5);
        if(!checkCanTreePlace(level, treeGround, height)) {
            return false;
        }

        BlockPos centerPos = treeGround.above(centerAboveGround);
        if(centerAboveGround > 0) {
            int leafCount = 0;
            for(Direction direction : Direction.Plane.HORIZONTAL) {
                if(leafCount <= 3 + randomsource.nextInt(1)) {
                    generateLeaf(level, centerPos.relative(direction), 0.25, randomsource, direction, centerAboveGround + 1 + randomsource.nextInt(6));
                    leafCount++;
                }
            }
        }

        BlockPos.MutableBlockPos trunkPos = new BlockPos.MutableBlockPos();
        int i = 0;
        trunkPos.set(centerPos);
        trunkPos.move(0, -1, 0);
        int tallPart = height - centerAboveGround;
        while(i < tallPart) {
            i++;
            trunkPos.move(0, 1, 0);
            if(randomsource.nextInt(5) == 0) {
                level.setBlock(trunkPos, ModBlocks.WILTED_WOOD.get().defaultBlockState(), 3);
                trunkPos.move(Direction.Plane.HORIZONTAL.getRandomDirection(randomsource));
                level.setBlock(trunkPos, ModBlocks.WILTED_WOOD.get().defaultBlockState(), 3);
            } else {
                level.setBlock(trunkPos, i == tallPart ? ModBlocks.WILTED_WOOD.get().defaultBlockState() : ModBlocks.WILTED_LOG.get().defaultBlockState(), 3);
            }
            decorateLog(level, trunkPos, randomsource, true);
        }

        BlockPos canopy = trunkPos.immutable();
        BlockPos.MutableBlockPos canopyLogPos = new BlockPos.MutableBlockPos();
        for(Direction direction : Direction.Plane.HORIZONTAL) {
            canopyLogPos.set(canopy);
            int canopyLength = 1 + randomsource.nextInt(1);
            for(int j = 0; j <= canopyLength; j++) {
                boolean upFlag = false;
                canopyLogPos.move(direction);
                if(randomsource.nextInt(3) != 0) {
                    upFlag = true;
                    level.setBlock(canopyLogPos, ModBlocks.WILTED_WOOD.get().defaultBlockState().setValue(RotatedPillarBlock.AXIS, direction.getAxis()), 3);
                    canopyLogPos.move(0, 1, 0);
                }
                if(j == canopyLength && level.getBlockState(canopyLogPos).canBeReplaced()) {
                    level.setBlock(canopyLogPos, ModBlocks.WILTED_LEAVES.get().defaultBlockState().setValue(LeavesBlock.WATERLOGGED, level.getFluidState(canopyLogPos).is(Fluids.WATER)), 3);
                } else {
                    Block wood = j == canopyLength - 1 || upFlag ? ModBlocks.WILTED_WOOD.get() : ModBlocks.WILTED_LOG.get();
                    level.setBlock(canopyLogPos, wood.defaultBlockState().setValue(RotatedPillarBlock.AXIS, direction.getAxis()), 3);
                    decorateLog(level, trunkPos, randomsource, true);
                }
            }
        }
        return true;
    }

    private void generateLeaf(WorldGenLevel level, BlockPos from, double bendChance, RandomSource randomsource, Direction direction, int length) {
        BlockPos.MutableBlockPos at = new BlockPos.MutableBlockPos();
        at.set(from);
        int i = 0;
        while (i < length) {
            if(level.getBlockState(at).is(BlockTags.WITHER_IMMUNE)) {
                return;
            }
            if(randomsource.nextFloat() < bendChance) {
                if(!level.getBlockState(at).is(ModBlocks.WILTED_WOOD.get())) {
                    level.setBlock(at, ModBlocks.WILTED_WOOD.get().defaultBlockState().setValue(RotatedPillarBlock.AXIS, direction.getAxis()), 2);
                }
                at.move(0, -1, 0);
                at.move(direction);
                level.setBlock(at, ModBlocks.WILTED_WOOD.get().defaultBlockState(), 3);
            } else {
                at.move(0, -1, 0);
                level.setBlock(at, i == 0 ? ModBlocks.WILTED_WOOD.get().defaultBlockState() : ModBlocks.WILTED_LOG.get().defaultBlockState(), 3);
            }
            decorateLog(level, at, randomsource, false);
            i++;
        }
        BlockPos leafPos = at.immutable().below();
        if(level.getBlockState(leafPos).canBeReplaced()) {
            level.setBlock(leafPos, ModBlocks.WILTED_LEAVES.get().defaultBlockState().setValue(LeavesBlock.WATERLOGGED, level.getFluidState(leafPos).is(Fluids.WATER)), 3);
        }
    }

    private void decorateLog(WorldGenLevel level, BlockPos.MutableBlockPos from, RandomSource randomsource, boolean logBranches) {
        if(randomsource.nextFloat() < 0.80F) {
            Direction ranDir = Util.getRandom(Direction.values(), randomsource);
            BlockPos branchPos = from.immutable().relative(ranDir);
            if(level.getBlockState(branchPos).canBeReplaced()) {
                if(logBranches && randomsource.nextFloat() < 0.65F) {
                    int bigBranchLength = 1 + randomsource.nextInt(1);
                    for(int i = 0; i < bigBranchLength; i++) {
                        Block wood = i == bigBranchLength - 1 ? ModBlocks.WILTED_WOOD.get() : ModBlocks.WILTED_LOG.get();
                        level.setBlock(branchPos, wood.defaultBlockState().setValue(RotatedPillarBlock.AXIS, ranDir.getAxis()), 3);
                        branchPos = branchPos.relative(ranDir);
                    }
                }
                level.setBlock(branchPos, ModBlocks.WILTED_LEAVES.get().defaultBlockState().setValue(LeavesBlock.WATERLOGGED, level.getFluidState(branchPos).is(Fluids.WATER)), 3);
            }
        }
    }

    private boolean checkCanTreePlace(WorldGenLevel level, BlockPos treeGround, int height) {
        BlockState below = level.getBlockState(treeGround.below());
        if(!below.is(ModTags.Blocks.MINERAL_RICH_TURFS)) {
            return false;
        }
        for (int i = 0; i < height; i++) {
            if(!canReplace(level.getBlockState(treeGround.above(i)))) {
                return false;
            }
        }
        BlockPos treeTop = treeGround.above(height).immutable();
        for(BlockPos checkLeaf : BlockPos.betweenClosed(treeTop.offset(-2, -1, -2), treeTop.offset(2, 1, 2))) {
            if(!canReplace(level.getBlockState(checkLeaf))) {
                return false;
            }
        }
        return true;
    }

    private boolean canReplace(BlockState state) {
        return (state.isAir() || state.canBeReplaced() || state.is(ModBlocks.WILTED_LEAVES.get()) || state.is(BlockTags.DIRT) || state.is(Blocks.PACKED_MUD)) || state.is(ModTags.Blocks.MINERAL_RICH_TURFS) && !state.is(BlockTags.WITHER_IMMUNE);
    }
}
