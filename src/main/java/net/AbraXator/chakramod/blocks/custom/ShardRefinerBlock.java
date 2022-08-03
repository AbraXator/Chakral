package net.AbraXator.chakramod.blocks.custom;

import net.AbraXator.chakramod.blocks.entity.custom.ShardRefinerBlockEntity;
import net.AbraXator.chakramod.screen.ShardRefinerMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class ShardRefinerBlock extends BaseEntityBlock {
    public ShardRefinerBlock(Properties properties) {
        super(properties);
    }


    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new ShardRefinerBlockEntity(pPos, pState);
    }

   //@Override
   //public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
   //    return pPlayer.openMenu(ShardRefinerMenu);
   //}
}
