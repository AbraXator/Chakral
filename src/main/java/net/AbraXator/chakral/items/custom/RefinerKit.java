package net.AbraXator.chakral.items.custom;

import net.AbraXator.chakral.blocks.ModBlocks;
import net.AbraXator.chakral.blocks.custom.ShardRefinerBlock;
import net.AbraXator.chakral.chakra.ChakraStrenght;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import javax.swing.plaf.basic.BasicComboBoxUI;

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
        ItemStack stack = pContext.getItemInHand();
        if(block instanceof ShardRefinerBlock shardRefinerBlock && player.isShiftKeyDown()){
            if(shardRefinerBlock.getTier(state) != chakraStrenght) {
                shardRefinerBlock.upgrade(chakraStrenght, level, pos, state);
                stack.shrink(1);
            }
        }
        return super.useOn(pContext);
    }
}
