package net.AbraXator.chakral.blocks.custom;

import mezz.jei.api.helpers.IStackHelper;
import net.AbraXator.chakral.blocks.entity.ModBlockEntities;
import net.AbraXator.chakral.blocks.entity.custom.MineralEnricherBlockEntity;
import net.AbraXator.chakral.blocks.entity.custom.ShardRefinerBlockEntity;
import net.AbraXator.chakral.chakra.ChakraStrenght;
import net.AbraXator.chakral.utils.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
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
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

public class MineralEnricherBlock extends BaseEntityBlock {
    public static final VoxelShape SHAPE = Block.box(0, 0, 0, 16, 16, 16);
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

    //@Nullable
    //@Override
    //public BlockState getStateForPlacement(BlockPlaceContext pContext) {
    //    return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite()).setValue(TIER, ChakraStrenght.FAINT);
    //}

    //@Override
    //public BlockState rotate(BlockState state, LevelAccessor level, BlockPos pos, Rotation direction) {
    //    return state.setValue(FACING, direction.rotate(state.getValue(FACING)));
    //}

    //@Override
    //public BlockState mirror(BlockState pState, Mirror pMirror) {
    //    return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
    //}

    //@Override
    //protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
    //    pBuilder.add(FACING, TIER);
    //}

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
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
        }
        return InteractionResult.SUCCESS;
    }


    //@Override
    //public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
    //    if(!pLevel.isClientSide) {
    //        BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
    //        if(blockEntity instanceof MineralEnricherBlockEntity mineralEnricher){
    //            ItemStack item = pPlayer.getUseItem();
    //            ItemStackHandler itemHandler = mineralEnricher.itemHandler;
    //            SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
    //            for(int i = 0; i < itemHandler.getSlots(); i++){
    //                inventory.setItem(i, itemHandler.getStackInSlot(i));
    //            }
    //            ItemStack mineral = inventory.getItem(0);
    //            ItemStack dust = inventory.getItem(1);
    //            boolean isMineralValid = !mineral.isEmpty() && mineral.getCount() < 64;
    //            boolean isDustPresent = !dust.isEmpty();

    //            if(item.is(ModTags.Items.MINERALS)){
    //                if()
    //            }
    //        }
    //    }
    //}

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
