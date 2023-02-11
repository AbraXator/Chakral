package net.AbraXator.chakral.blocks.custom;

import net.AbraXator.chakral.entity.ModEntities;
import net.AbraXator.chakral.entity.stemspore.StemSporeEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

public class StemshroomBlock extends Block {
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


    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if(pLevel.getBrightness(LightLayer.BLOCK, pPos) >= 10){
            double x = pPos.getX() + ((pRandom.nextDouble() - pRandom.nextDouble()) * 20);
            double y = pPos.getY() + 5 + ((pRandom.nextDouble() - pRandom.nextDouble()) * 10);
            double z = pPos.getZ() + ((pRandom.nextDouble() - pRandom.nextDouble()) * 20);
            StemSporeEntity entity = new StemSporeEntity(ModEntities.STEM_SPORE.get(), pLevel);
            entity.setPos(x, y, z);
            pLevel.addFreshEntity(entity);
        }
    }
}