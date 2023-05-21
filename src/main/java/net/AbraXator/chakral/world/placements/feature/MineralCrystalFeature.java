package net.AbraXator.chakral.world.placements.feature;

import com.mojang.serialization.Codec;
import net.AbraXator.chakral.world.feature.configuration.MineralCrystalConfiguration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.FloatProvider;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformFloat;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Column;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.Optional;

public class MineralCrystalFeature extends Feature<MineralCrystalConfiguration> {
    public MineralCrystalFeature(Codec<MineralCrystalConfiguration> pCodec) {
        super(pCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<MineralCrystalConfiguration> pContext) {
        MineralCrystalConfiguration config = pContext.config();
        WorldGenLevel worldGenLevel = pContext.level();
        RandomSource randomSource = pContext.random();
        Block mineral = config.mineral.getBlock();
        Block budding = config.budding.getBlock();
        IntProvider height = config.getHeight();
        IntProvider radius = config.getRadius();
        BlockPos blockPos = pContext.origin();
        if(!isEmptyOrWater(worldGenLevel, blockPos)) return false;
        else {
            Optional<Column> optionalColumn = Column.scan(
                    worldGenLevel,
                    blockPos,
                    30,
                    blockState -> blockState.is(Blocks.AIR),
                    blockState -> !blockState.isAir());
            if (optionalColumn.isPresent() && optionalColumn.get() instanceof Column.Range columnRange) {
                if(columnRange.height() < height.getMinValue()) return false;
                else {
                    int radiusToCaveColumn = (int)((float)columnRange.height() * 0.33);
                    int getRadius = Mth.clamp(radiusToCaveColumn, radius.getMinValue(), radius.getMaxValue());
                    int randomisedRadius = Mth.randomBetweenInclusive(randomSource, radius.getMinValue(), getRadius);
                    MineralCrystal mineralCrystal = new MineralCrystal(blockPos.atY(columnRange.floor() + 1), randomisedRadius, UniformFloat.of(0.0F, 0.3F).sample(randomSource), UniformFloat.of(0.4F, 2.0F).sample(randomSource), height.sample(randomSource), mineral, budding);
                    WindOffsetter windOffsetter = new WindOffsetter(blockPos.getY(), randomSource, UniformFloat.of(0.0F, 0.3F));
                    boolean flag = mineralCrystal.moveBackUntilBaseIsInsideStoneAndShrinkRadiusIfNecessary(worldGenLevel, windOffsetter);
                    if(flag) {
                        mineralCrystal.placeBlocks(worldGenLevel, randomSource, windOffsetter);
                    }
                    return false;
                }
            }else {
                return false;
            }
        }
    }

    boolean isEmptyOrWater(LevelAccessor pLevel, BlockPos pPos) {
        return true;
    }

    static final class MineralCrystal {
        private BlockPos root;
        private int radius;
        private final double bluntness;
        private final double scale;
        private final double height;
        private final Block mineral;
        private final Block budding;

        public MineralCrystal(BlockPos root, int radius, double bluntness, double scale, double height, Block mineral, Block budding) {
            this.root = root;
            this.radius = radius;
            this.bluntness = bluntness;
            this.scale = scale;
            this.height = height;
            this.mineral = mineral;
            this.budding = budding;
        }

        boolean moveBackUntilBaseIsInsideStoneAndShrinkRadiusIfNecessary(WorldGenLevel worldGenLevel, WindOffsetter windOffsetter){
            while(this.radius > 1){
                BlockPos.MutableBlockPos mutableBlockPos = this.root.mutable();
                int i = (int) Math.min(10, height);

                for(int j = 0; j < i; j++){
                    if(worldGenLevel.getBlockState(mutableBlockPos).is(Blocks.LAVA)) {
                        return false;
                    }

                    if(isCircleMostlyEmbeddedInStone(worldGenLevel, windOffsetter.offset(mutableBlockPos), this.radius)){
                        this.root = mutableBlockPos;
                        return true;
                    }

                    mutableBlockPos.move(Direction.UP);
                }

                this.radius /= 2;
            }

            return false;
        }

        private static boolean isCircleMostlyEmbeddedInStone(WorldGenLevel pLevel, BlockPos pPos, int pRadius) {
            if (isEmptyOrWaterOrLava(pLevel, pPos)) {
                return false;
            } else {
                float f = 6.0F;
                float f1 = 6.0F / (float)pRadius;

                for(float f2 = 0.0F; f2 < ((float)Math.PI * 2F); f2 += f1) {
                    int i = (int)(Mth.cos(f2) * (float)pRadius);
                    int j = (int)(Mth.sin(f2) * (float)pRadius);
                    if (isEmptyOrWaterOrLava(pLevel, pPos.offset(i, 0, j))) {
                        return false;
                    }
                }

                return true;
            }
        }

        private int getHeightAtRadius(float pRadius) {
            return (int)getDripstoneHeight(pRadius, this.radius, this.scale, this.bluntness);
        }

        private static double getDripstoneHeight(double pRadius, double pMaxRadius, double pScale, double pMinRadius) {
            if (pRadius < pMinRadius) {
                pRadius = pMinRadius;
            }

            double d0 = 0.384D;
            double d1 = pRadius / pMaxRadius * 0.384D;
            double d2 = 0.75D * Math.pow(d1, 1.3333333333333333D);
            double d3 = Math.pow(d1, 0.6666666666666666D);
            double d4 = 0.3333333333333333D * Math.log(d1);
            double d5 = pScale * (d2 - d3 - d4);
            d5 = Math.max(d5, 0.0D);
            return d5 / 0.384D * pMaxRadius;
        }

        public void placeBlocks(WorldGenLevel worldGenLevel, RandomSource randomSource, WindOffsetter windOffsetter){
            for(int i = -this.radius; i <= this.radius; i++){
                for(int j = -this.radius; j <= this.radius; ++j) {
                    float radSqrt = Mth.sqrt(i * i + j * j);
                    if(!(radSqrt > this.radius)){
                        int heightAtRadius = getHeightAtRadius(radSqrt);
                        if(heightAtRadius > 0) {
                            if(randomSource.nextFloat() < 0.2D){
                                heightAtRadius = (int)((float)(heightAtRadius * Mth.randomBetween(randomSource, 0.8F, 1)));
                            }

                            BlockPos.MutableBlockPos mutableBlockPos = this.root.offset(i, 0, j).mutable();
                            boolean flag = false;
                            int worldGenLevelHeight = worldGenLevel.getHeight(Heightmap.Types.WORLD_SURFACE_WG, mutableBlockPos.getX(), mutableBlockPos.getZ());

                            for(int i1 = 0; i1 < heightAtRadius && mutableBlockPos.getY() < worldGenLevelHeight; i1++){
                                BlockPos pos = windOffsetter.offset(mutableBlockPos);
                                if(isEmptyOrWaterOrLava(worldGenLevel, pos)){
                                    flag = true;
                                    Block block = randomSource.nextFloat() < 0.2D ? this.budding : this.mineral;
                                    worldGenLevel.setBlock(pos, block.defaultBlockState(), 2);
                                } else if(flag && worldGenLevel.getBlockState(pos).is(BlockTags.BASE_STONE_OVERWORLD)){
                                    break;
                                }

                                mutableBlockPos.move(Direction.UP);
                            }
                        }
                    }
                }
            }
        }

        private static boolean isEmptyOrWaterOrLava(LevelAccessor pLevel, BlockPos pPos) {
            return pLevel.isStateAtPosition(pPos, blockState -> !blockState.isAir());
        }
    }

    static final class WindOffsetter {
        private final int originY;
        @Nullable
        private final Vec3 windSpeed;

        WindOffsetter(int pOriginY, RandomSource pRandom, FloatProvider pMagnitude) {
            this.originY = pOriginY;
            float f = pMagnitude.sample(pRandom);
            float f1 = Mth.randomBetween(pRandom, 0.0F, (float)Math.PI);
            this.windSpeed = new Vec3(Mth.cos(f1) * f, 0.0D, Mth.sin(f1) * f);
        }

        private WindOffsetter() {
            this.originY = 0;
            this.windSpeed = null;
        }

        static MineralCrystalFeature.WindOffsetter noWind() {
            return new MineralCrystalFeature.WindOffsetter();
        }

        BlockPos offset(BlockPos pPos) {
            if (this.windSpeed == null) {
                return pPos;
            } else {
                int i = this.originY - pPos.getY();
                Vec3 vec3 = this.windSpeed.scale(i);
                return pPos.offset(Mth.floor(vec3.x), 0, Mth.floor(vec3.z));
            }
        }
    }
}
