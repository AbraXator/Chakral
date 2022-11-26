package net.AbraXator.chakral.blocks.custom;

import net.AbraXator.chakral.screen.NecklaceInserterMenu;
import net.AbraXator.chakral.screen.NecklaceSlotterMenu;
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
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class NecklaceInserterBlock extends Block {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final VoxelShape SHAPE_BASE = Block.box(0, 0, 0, 16, 10, 16);
    public static final VoxelShape SHAPE_NORTH = Shapes.or(SHAPE_BASE, Block.box(0, 11, 0, 16, 12, 16), Block.box(1, 13, 1, 16, 14, 16), Block.box(2, 15, 2, 16, 16, 16), Block.box(0, 17, 0, 16, 18, 16), Block.box(0, 19, 0, 16, 20, 16), Block.box(0, 21, 0, 16, 22, 16), Block.box(0, 23, 0, 16, 24, 16));
    public static final VoxelShape SHAPE_SOUTH = SHAPE_BASE;
    public static final VoxelShape SHAPE_WEST = SHAPE_BASE;
    public static final VoxelShape SHAPE_EAST = SHAPE_BASE;
    public NecklaceInserterBlock(Properties p_49795_) {
        super(p_49795_);
    }

    @Override
    public VoxelShape getOcclusionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return SHAPE_BASE;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE_BASE;
    }

    /*@Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return switch (pState.getValue(FACING)){
            case EAST -> SHAPE_EAST;
            case WEST -> SHAPE_WEST;
            case NORTH -> SHAPE_NORTH;
            case SOUTH -> SHAPE_SOUTH;
            default -> SHAPE_BASE;
        };
        return SHAPE_BASE;
    }*/

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
        return new SimpleMenuProvider((p_57074_, p_57075_, p_57076_) -> {
            return new NecklaceInserterMenu(p_57074_, p_57075_, ContainerLevelAccess.create(pLevel, pPos));
        }, Component.literal(""));
    }
}
