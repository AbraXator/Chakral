package net.AbraXator.chakral.blocks;

import net.AbraXator.chakral.blocks.entity.StemShroomBlockEntity;
import net.AbraXator.chakral.init.ModBlockEntities;
import net.AbraXator.chakral.init.ModEntities;
import net.AbraXator.chakral.entity.stemspore.StemSporeEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.DaylightDetectorBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

public class StemshroomBlock extends BaseEntityBlock {
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final DirectionProperty FACING = BlockStateProperties.FACING;
    public static final VoxelShape SHAPE_UP = Stream.of(
            Block.box(4, 12, 4, 12, 13, 12),
            Block.box(6, 0, 6, 10, 12, 10),
            Block.box(2, 7.1, 2, 14, 12.1, 14)
            ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public StemshroomBlock(Properties p_49795_) {
        super(p_49795_);
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
         return SHAPE_UP;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE_UP;
    }

    @Override
    public VoxelShape getOcclusionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return SHAPE_UP;
    }


    /*@Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if(pLevel.getBrightness(LightLayer.BLOCK, pPos) >= 10){
            double x = pPos.getX() + ((pRandom.nextDouble() - pRandom.nextDouble()) * 20);
            double y = pPos.getY() + 5 + ((pRandom.nextDouble() - pRandom.nextDouble()) * 10);
            double z = pPos.getZ() + ((pRandom.nextDouble() - pRandom.nextDouble()) * 20);
            StemSporeEntity entity = new StemSporeEntity(ModEntities.STEM_SPORE.get(), pLevel);
            entity.setPos(x, y, z);
            pLevel.addFreshEntity(entity);
        }
    }*/

    public void tick(Level level, BlockPos pos, BlockState state, StemShroomBlockEntity blockEntity){
        RandomSource randomSource = level.getRandom();
        if(level.hasNearbyAlivePlayer(pos.getX(), pos.getY(), pos.getZ(), 10)){
            for(int i = 0; i <= randomSource.nextInt(5); i++){
                StemSporeEntity entity = new StemSporeEntity(ModEntities.STEM_SPORE.get(), level);
                Vec3 pos1;
                do {
                    pos1 = generatePos(randomSource, pos);
                }while (level.getBlockState(new BlockPos(((int) pos1.x()), ((int) pos1.y()), ((int) pos1.z()))).is(Blocks.AIR));
                entity.setPos(pos1);
                level.addFreshEntity(entity);
            }
        }
    }

    private Vec3 generatePos(RandomSource randomSource, BlockPos blockPos){
        double u = randomSource.nextDouble();
        double v = randomSource.nextDouble();
        float theta = ((float) (u * 2 * Mth.PI));
        float phi = ((float) Math.acos(2 * v - 1));
        double r = Math.cbrt(randomSource.nextDouble());
        double sinTheta = Mth.sin(theta);
        double cosTheta = Mth.cos(theta);
        double sinPhi = Mth.sin(phi);
        double cosPhi = Mth.cos(phi);
        double x = (r * sinPhi * cosTheta) * 5 + blockPos.getX();
        double y = (r * sinPhi * sinTheta) * 5 + blockPos.getY();
        double z = (r * cosPhi) * 5 + blockPos.getZ();
        return new Vec3(x, y, z);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new StemShroomBlockEntity(pPos, pState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return !pLevel.isClientSide ? createTickerHelper(pBlockEntityType, ModBlockEntities.STEMSHROOM_BLOCK_ENTITY.get(), this::tick) : null;
    }
}