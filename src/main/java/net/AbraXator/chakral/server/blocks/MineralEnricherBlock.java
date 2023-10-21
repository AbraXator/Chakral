package net.AbraXator.chakral.server.blocks;

import net.AbraXator.chakral.server.blocks.entity.MineralEnricherBlockEntity;
import net.AbraXator.chakral.server.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
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
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

public class MineralEnricherBlock extends BaseEntityBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final IntegerProperty DUST = IntegerProperty.create("dust", 0, 4);
    public static final BooleanProperty WATER = BooleanProperty.create("water");
    public static final VoxelShape SHAPE = Stream.of(
            Block.box(12, 13, 3, 13, 15, 13),
            Block.box(5, 6, 0.1, 11, 8, 3.1),
            Block.box(0, 0, 0, 0.1, 15, 16),
            Block.box(0, 0, 0, 16, 15, 0.1),
            Block.box(15.9, 0, 0, 16, 15, 16),
            Block.box(0, 0, 15.9, 16, 15, 16),
            Block.box(3, 0, 0, 13, 0.1, 3),
            Block.box(3, 0, 13, 13, 0.1, 16),
            Block.box(0, 0, 0, 3, 0.1, 16),
            Block.box(13, 0, 0, 16, 0.1, 16),
            Block.box(0, 15, 0, 4, 16, 16),
            Block.box(4, 15, 0, 12, 16, 4),
            Block.box(4, 15, 12, 12, 16, 16),
            Block.box(12, 15, 0, 16, 16, 16),
            Block.box(4, 12, 4, 12, 13, 12),
            Block.box(7, 10, 7, 9, 12, 9),
            Block.box(4, 13, 3, 12, 15, 4),
            Block.box(4, 13, 12, 12, 15, 13),
            Block.box(3, 13, 3, 4, 15, 13)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public MineralEnricherBlock(Properties p_49224_) {
        super(p_49224_);
    }

    public VoxelShape getOcclusionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return SHAPE;
    }

    public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite()).setValue(DUST, 0).setValue(WATER, false);
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
        pBuilder.add(FACING, DUST, WATER);
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    public static void setCharge(int charge, Level level, BlockPos pos, BlockState state){
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos,
                                 Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (!pLevel.isClientSide()) {
            BlockEntity entity = pLevel.getBlockEntity(pPos);
            if (entity instanceof MineralEnricherBlockEntity) {
                NetworkHooks.openScreen(((ServerPlayer) pPlayer), (MineralEnricherBlockEntity) entity, pPos);
            } else {
                throw new IllegalStateException("Our Container provider is missing!");
            }
            if(pPlayer.isShiftKeyDown()){
                pPlayer.sendSystemMessage(Component.literal(pState.toString()));
            }
        }
        return InteractionResult.SUCCESS;
    }


    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new MineralEnricherBlockEntity(pPos, pState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return createTickerHelper(pBlockEntityType, ModBlockEntities.MINERAL_ENRICHER_BLOCK_ENTITY.get(),
                MineralEnricherBlockEntity::tick);
    }
}
