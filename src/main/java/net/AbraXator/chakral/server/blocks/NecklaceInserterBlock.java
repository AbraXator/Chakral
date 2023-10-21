package net.AbraXator.chakral.server.blocks;

import net.AbraXator.chakral.client.gui.necklace.inserter.NecklaceInserterMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

public class NecklaceInserterBlock extends Block {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final VoxelShape SHAPE_COMMON = Block.box(0, 0, 0, 16, 10, 16);
    public static final VoxelShape SHAPE_NORTH = Stream.of(
            Block.box(1, 16, 9, 15, 19, 12),
            Block.box(0, 0, 0, 16, 10, 16),
            Block.box(0, 10, 14, 16, 24, 16),
            Block.box(1, 10, 2, 15, 12, 14),
            Block.box(1, 12, 4, 15, 14, 14),
            Block.box(1, 14, 6, 15, 16, 14),
            Block.box(1, 16, 12, 15, 21, 14),
            Block.box(0, 9, 0, 16, 11, 2),
            Block.box(0, 11, 2, 16, 13, 4),
            Block.box(0, 13, 4, 16, 15, 6),
            Block.box(0, 15, 6, 16, 17, 8),
            Block.box(0, 17, 8, 16, 19, 10),
            Block.box(0, 19, 10, 16, 21, 12),
            Block.box(0, 21, 12, 16, 23, 14)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SHAPE_SOUTH = Stream.of(
            Block.box(1, 16, 4, 15, 19, 7),
            Block.box(0, 0, 0, 16, 10, 16),
            Block.box(0, 10, 0, 16, 24, 2),
            Block.box(1, 10, 2, 15, 12, 14),
            Block.box(1, 12, 2, 15, 14, 12),
            Block.box(1, 14, 2, 15, 16, 10),
            Block.box(1, 16, 2, 15, 21, 4),
            Block.box(0, 9, 14, 16, 11, 16),
            Block.box(0, 11, 12, 16, 13, 14),
            Block.box(0, 13, 10, 16, 15, 12),
            Block.box(0, 15, 8, 16, 17, 10),
            Block.box(0, 17, 6, 16, 19, 8),
            Block.box(0, 19, 4, 16, 21, 6),
            Block.box(0, 21, 2, 16, 23, 4)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SHAPE_WEST = Stream.of(
            Block.box(9, 16, 1, 12, 19, 15),
            Block.box(0, 0, 0, 16, 10, 16),
            Block.box(14, 10, 0, 16, 24, 16),
            Block.box(2, 10, 1, 14, 12, 15),
            Block.box(4, 12, 1, 14, 14, 15),
            Block.box(6, 14, 1, 14, 16, 15),
            Block.box(12, 16, 1, 14, 21, 15),
            Block.box(0, 9, 0, 2, 11, 16),
            Block.box(2, 11, 0, 4, 13, 16),
            Block.box(4, 13, 0, 6, 15, 16),
            Block.box(6, 15, 0, 8, 17, 16),
            Block.box(8, 17, 0, 10, 19, 16),
            Block.box(10, 19, 0, 12, 21, 16),
            Block.box(12, 21, 0, 14, 23, 16)
            ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SHAPE_EAST = Stream.of(
            Block.box(4, 16, 1, 7, 19, 15),
            Block.box(0, 0, 0, 16, 10, 16),
            Block.box(0, 10, 0, 2, 24, 16),
            Block.box(2, 10, 1, 14, 12, 15),
            Block.box(2, 12, 1, 12, 14, 15),
            Block.box(2, 14, 1, 10, 16, 15),
            Block.box(2, 16, 1, 4, 21, 15),
            Block.box(14, 9, 0, 16, 11, 16),
            Block.box(12, 11, 0, 14, 13, 16),
            Block.box(10, 13, 0, 12, 15, 16),
            Block.box(8, 15, 0, 10, 17, 16),
            Block.box(6, 17, 0, 8, 19, 16),
            Block.box(4, 19, 0, 6, 21, 16),
            Block.box(2, 21, 0, 4, 23, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public NecklaceInserterBlock(Properties p_49795_) {
        super(p_49795_);
    }

    public VoxelShape createVoxelShape(BlockState state){
        return switch (state.getValue(FACING)) {
            case EAST -> SHAPE_EAST;
            case NORTH -> SHAPE_NORTH;
            case SOUTH -> SHAPE_SOUTH;
            case WEST -> SHAPE_WEST;
            default -> SHAPE_COMMON;
        };
    }

    public VoxelShape getOcclusionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return createVoxelShape(pState);
    }

    public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return createVoxelShape(pState);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return createVoxelShape(pState);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, LevelAccessor level, BlockPos pos, Rotation direction) {
        return state.setValue(FACING, direction.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING);
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (pLevel.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            pPlayer.openMenu(new SimpleMenuProvider((pContainerId, pInventory, pPlayer1) -> new NecklaceInserterMenu(pContainerId, pInventory, ContainerLevelAccess.create(pLevel, pPos)), Component.literal("")));
            return InteractionResult.CONSUME;
        }
    }

    @javax.annotation.Nullable
    public MenuProvider getMenuProvider(BlockState pState, Level pLevel, BlockPos pPos) {
        return new SimpleMenuProvider((p_57074_, p_57075_, p_57076_) -> new NecklaceInserterMenu(p_57074_, p_57075_, ContainerLevelAccess.create(pLevel, pPos)), Component.literal(""));
    }
}
