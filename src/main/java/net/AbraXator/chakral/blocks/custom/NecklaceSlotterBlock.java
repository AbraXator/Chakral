package net.AbraXator.chakral.blocks.custom;

import net.AbraXator.chakral.blocks.entity.ModBlockEntities;
import net.AbraXator.chakral.blocks.entity.custom.NecklaceSlotterBlockEntity;
import net.AbraXator.chakral.screen.NecklaceSlotterMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.stats.Stats;
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
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
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

public class NecklaceSlotterBlock extends Block {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final VoxelShape SHAPE_NORTH = Stream.of(
            Block.box(5, 1, 5, 11, 2, 11),
            Block.box(6, 2, 6, 10, 12, 10),
            Block.box(6, 12, 7, 10, 13, 10),
            Block.box(6, 13, 9, 10, 14, 10),
            Block.box(4, 0, 4, 12, 1, 12),
            Block.box(1, 10, 1.5, 15, 12, 3.5),
            Block.box(1, 10.75, 3.5, 15, 12.75, 5.5),
            Block.box(1, 11.5, 5.5, 15, 13.5, 7.5),
            Block.box(1, 12.4, 7.5, 15, 14.4, 9.5),
            Block.box(1, 13.25, 9.5, 15, 15.25, 11.5),
            Block.box(1, 14, 11.5, 15, 16, 13.5),
            Block.box(1, 14.75, 13.5, 15, 16.75, 15.5)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SHAPE_EAST = Stream.of(
            Block.box(5, 1, 5, 11, 2, 11),
            Block.box(6, 2, 6, 10, 12, 10),
            Block.box(6, 12, 6, 9, 13, 10),
            Block.box(6, 13, 6, 7, 14, 10),
            Block.box(4, 0, 4, 12, 1, 12),
            Block.box(12.5, 10, 1, 14.5, 12, 15),
            Block.box(10.5, 10.75, 1, 12.5, 12.75, 15),
            Block.box(8.5, 11.5, 1, 10.5, 13.5, 15),
            Block.box(6.5, 12.4, 1, 8.5, 14.4, 15),
            Block.box(4.5, 13.25, 1, 6.5, 15.25, 15),
            Block.box(2.5, 14, 1, 4.5, 16, 15),
            Block.box(0.5, 14.75, 1, 2.5, 16.75, 15)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SHAPE_SOUTH = Stream.of(
            Block.box(5, 1, 5, 11, 2, 11),
            Block.box(6, 2, 6, 10, 12, 10),
            Block.box(6, 12, 6, 10, 13, 9),
            Block.box(6, 13, 6, 10, 14, 7),
            Block.box(4, 0, 4, 12, 1, 12),
            Block.box(1, 10, 12.5, 15, 12, 14.5),
            Block.box(1, 10.75, 10.5, 15, 12.75, 12.5),
            Block.box(1, 11.5, 8.5, 15, 13.5, 10.5),
            Block.box(1, 12.4, 6.5, 15, 14.4, 8.5),
            Block.box(1, 13.25, 4.5, 15, 15.25, 6.5),
            Block.box(1, 14, 2.5, 15, 16, 4.5),
            Block.box(1, 14.75, 0.5, 15, 16.75, 2.5)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SHAPE_WEST = Stream.of(
            Block.box(5, 1, 5, 11, 2, 11),
            Block.box(6, 2, 6, 10, 12, 10),
            Block.box(7, 12, 6, 10, 13, 10),
            Block.box(9, 13, 6, 10, 14, 10),
            Block.box(4, 0, 4, 12, 1, 12),
            Block.box(1.5, 10, 1, 3.5, 12, 15),
            Block.box(3.5, 10.75, 1, 5.5, 12.75, 15),
            Block.box(5.5, 11.5, 1, 7.5, 13.5, 15),
            Block.box(7.5, 12.4, 1, 9.5, 14.4, 15),
            Block.box(9.5, 13.25, 1, 11.5, 15.25, 15),
            Block.box(11.5, 14, 1, 13.5, 16, 15),
            Block.box(13.5, 14.75, 1, 15.5, 16.75, 15)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SHAPE_COMMON = Stream.of(
            Block.box(5, 1, 5, 11, 2, 11),
            Block.box(6, 2, 6, 10, 12, 10),
            Block.box(6, 12, 6, 9, 13, 10),
            Block.box(6, 13, 6, 7, 14, 10),
            Block.box(4, 0, 4, 12, 1, 12)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public NecklaceSlotterBlock(Properties properties) {
        super(properties);
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

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if(pState.getBlock() != pNewState.getBlock()){
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
            if(blockEntity instanceof NecklaceSlotterBlockEntity){
                ((NecklaceSlotterBlockEntity) blockEntity).drops();
            }
        }
        super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
    }

   //@Override
   //public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos,
   //                             Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
   //    if(!pLevel.isClientSide()){
   //        BlockEntity entity = pLevel.getBlockEntity(pPos);
   //        if (entity instanceof NecklaceSlotterBlockEntity){
   //            NetworkHooks.openScreen(((ServerPlayer)pPlayer), (NecklaceSlotterBlockEntity)entity, pPos);
   //        }else {
   //            throw new IllegalStateException("Our container provider is missing!");
   //        }
   //    }
   //    return InteractionResult.sidedSuccess(pLevel.isClientSide());
   //}


    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (pLevel.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            pPlayer.openMenu(pState.getMenuProvider(pLevel, pPos));
            return InteractionResult.CONSUME;
        }
    }

    @javax.annotation.Nullable
    public MenuProvider getMenuProvider(BlockState pState, Level pLevel, BlockPos pPos) {
        return new SimpleMenuProvider((p_57074_, p_57075_, p_57076_) -> {
            return new NecklaceSlotterMenu(p_57074_, p_57075_, ContainerLevelAccess.create(pLevel, pPos));
        }, Component.literal(""));
    }
}