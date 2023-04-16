package net.AbraXator.chakral.items;

import net.AbraXator.chakral.blocks.ShardRefinerBlock;
import net.AbraXator.chakral.chakra.ChakraStrength;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class RefinerKit extends Item {
    public ChakraStrength chakraStrength;
    public RefinerKit(Properties pProperties, ChakraStrength chakraStrength) {
        super(pProperties);
        this.chakraStrength = chakraStrength;
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();
        BlockPos pos = pContext.getClickedPos();
        BlockState state = level.getBlockState(pos);
        Block block = state.getBlock();
        Player player = pContext.getPlayer();
        ItemStack stack = pContext.getItemInHand();
        if(block instanceof ShardRefinerBlock shardRefinerBlock && player.isShiftKeyDown() && shardRefinerBlock.getTier(level.getBlockState(pos)).equals(ChakraStrength.FAINT)){
            if(shardRefinerBlock.getTier(state) != chakraStrength) {
                shardRefinerBlock.upgrade(chakraStrength, level, pos, state);
                stack.shrink(1);
            }
        }
        return super.useOn(pContext);
    }
}
