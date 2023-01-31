package net.AbraXator.chakral.blocks.custom;

import net.AbraXator.chakral.blocks.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraft.world.level.block.SpreadingSnowyDirtBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.List;
import java.util.Optional;

public class MineralRichGrassBlock extends SpreadingSnowyDirtBlock implements BonemealableBlock {
    public MineralRichGrassBlock(BlockBehaviour.Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader p_256229_, BlockPos p_256432_, BlockState p_255677_, boolean p_256630_) {
        return p_256229_.getBlockState(p_256432_.above()).isAir();
    }

    @Override
    public boolean isBonemealSuccess(Level pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState) {
        BlockPos blockpos = pPos.above();
        BlockState blockstate = ModBlocks.MINERAL_RICH_GRASS.get().defaultBlockState();
        Optional<Holder.Reference<PlacedFeature>> optional = pLevel.registryAccess().registryOrThrow(Registries.PLACED_FEATURE).getHolder(VegetationPlacements.GRASS_BONEMEAL);

        label49:
        for(int i = 0; i < 128; ++i) {
            BlockPos blockpos1 = blockpos;

            for(int j = 0; j < i / 16; ++j) {
                blockpos1 = blockpos1.offset(pRandom.nextInt(3) - 1, (pRandom.nextInt(3) - 1) * pRandom.nextInt(3) / 2, pRandom.nextInt(3) - 1);
                if (!pLevel.getBlockState(blockpos1.below()).is(this) || pLevel.getBlockState(blockpos1).isCollisionShapeFullBlock(pLevel, blockpos1)) {
                    continue label49;
                }
            }
            pLevel.setBlock(blockpos1, ModBlocks.MINERAL_RICH_PERENNIAL.get().defaultBlockState(), 3);
        }
    }
}