package net.AbraXator.chakramod.blocks.custom;

import net.AbraXator.chakramod.blocks.entity.ModBlockEntities;
import net.AbraXator.chakramod.blocks.entity.custom.ShardRefinerBlockEntity;
import net.AbraXator.chakramod.chakra.ChakraStrenght;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

public class ShardRefinerBlock extends BaseEntityBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final EnumProperty TIER = EnumProperty.create("tier", ChakraStrenght.class);
    public static final BooleanProperty CHARGED = BlockStateProperties.ENABLED;
    protected static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);

    public ShardRefinerBlock(BlockBehaviour.Properties p_49224_) {
        super(p_49224_);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(TIER, ChakraStrenght.FAINT));
    }

    public VoxelShape getOcclusionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return SHAPE;
    }

    public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return switch (pState.getValue(FACING)) {
            case EAST -> SHAPE;
            case NORTH ->SHAPE;
            case SOUTH ->SHAPE;
            case WEST -> SHAPE;
            default ->   SHAPE;
        };
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite()).setValue(TIER, ChakraStrenght.FAINT);
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
        pBuilder.add(FACING, CHARGED, TIER);
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if(pState.getBlock() != pNewState.getBlock()){
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
            if(blockEntity instanceof ShardRefinerBlockEntity){
                ((ShardRefinerBlockEntity) blockEntity).drops();
            }
        }
        super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos,
                                 Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (!pLevel.isClientSide()) {
            BlockEntity entity = pLevel.getBlockEntity(pPos);
            if(entity instanceof ShardRefinerBlockEntity) {
                NetworkHooks.openScreen(((ServerPlayer)pPlayer), (ShardRefinerBlockEntity)entity, pPos);
            } else {
                throw new IllegalStateException("Our Container provider is missing!");
            }

            if(pPlayer.isShiftKeyDown()){

            }
        }
        return InteractionResult.sidedSuccess(pLevel.isClientSide());
    }

    public static boolean tryUpgrade(Player player, Level pLevel, BlockPos pPos, BlockState state,int tier) {
        if (!pLevel.isClientSide) {
            upgrade(player, pLevel, pPos, state,tier);
            return true;
        } else {
            return false;
        }
    }

    public static void upgrade(Player player, Level pLevel, BlockPos pPos, BlockState state,int tier){
        BlockEntity blockentity = pLevel.getBlockEntity(pPos);
        switch (tier){
            case 1 -> state = state.setValue(TIER, ChakraStrenght.WEAKENED);
            case 0 -> state = state.setValue(TIER, ChakraStrenght.POWERFUL);
            case 2 -> state = state.setValue(TIER, ChakraStrenght.ENLIGHTENED);
            default -> state = state.setValue(TIER, ChakraStrenght.FAINT);
        }
        if(blockentity instanceof ShardRefinerBlockEntity shardRefinerBlock){
            shardRefinerBlock.upgradeTier(tier);
        }
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new ShardRefinerBlockEntity(pPos, pState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return (BlockEntityTicker<T>) createTickerHelper(pBlockEntityType, ModBlockEntities.SHARD_REFINER_BLOCK_ENTITY.get(),
                ShardRefinerBlockEntity::tick);
    }
}
