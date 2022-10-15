package net.AbraXator.chakral.blocks.custom;

import mezz.jei.api.helpers.IStackHelper;
import net.AbraXator.chakral.blocks.entity.ModBlockEntities;
import net.AbraXator.chakral.blocks.entity.custom.MineralEnricherBlockEntity;
import net.AbraXator.chakral.blocks.entity.custom.ShardRefinerBlockEntity;
import net.AbraXator.chakral.utils.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

public class MineralEnricherBlock extends BaseEntityBlock {
    public MineralEnricherBlock(Properties p_49224_) {
        super(p_49224_);
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
