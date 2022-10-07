package net.AbraXator.chakral.items.custom;

import net.AbraXator.chakral.blocks.ModBlocks;
import net.AbraXator.chakral.blocks.custom.ShardRefinerBlock;
import net.AbraXator.chakral.chakra.ChakraStrenght;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class RefinerKit extends Item {
    public ChakraStrenght chakraStrenght;
    public RefinerKit(Properties pProperties, ChakraStrenght chakraStrenght) {
        super(pProperties);
        this.chakraStrenght = chakraStrenght;
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();
        BlockPos pos = pContext.getClickedPos();
        BlockState state = level.getBlockState(pos);
        Block block = state.getBlock();
        Player player = pContext.getPlayer();
        if(block instanceof ShardRefinerBlock shardRefinerBlock && player.isShiftKeyDown()){
            shardRefinerBlock.upgrade(chakraStrenght, level, pos, state);
        }
        return super.useOn(pContext);
    }
}
