package net.AbraXator.chakral.server.blocks;

import net.AbraXator.chakral.server.blocks.entity.StemShroomBlockEntity;
import net.AbraXator.chakral.server.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

public class StemshroomBlock extends BaseLightrayBlock {
    public static final VoxelShape SHAPE_UP = Stream.of(
            Block.box(4, 12, 4, 12, 13, 12),
            Block.box(6, 0, 6, 10, 12, 10),
            Block.box(2, 7.1, 2, 14, 12.1, 14)
            ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public StemshroomBlock(Properties p_49795_) {
        super(p_49795_, ModBlockEntities.STEMSHROOM_BLOCK_ENTITY::get);
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

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new StemShroomBlockEntity(pPos, pState);
    }
}