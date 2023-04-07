package net.AbraXator.chakral.chakra.chakras;

import com.google.common.collect.ImmutableList;
import net.AbraXator.chakral.chakra.Chakra;
import net.AbraXator.chakral.chakra.ChakraStrength;
import net.AbraXator.chakral.chakra.ChakraType;
import net.AbraXator.chakral.items.ModItems;
import net.AbraXator.chakral.utils.ChakralLocation;
import net.AbraXator.chakral.utils.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HelioliteChakra extends Chakra {
    public HelioliteChakra() {
        super(new ChakralLocation("heliolite"), ChakraType.SOLAR, ChakraStrength.WEAKENED);
    }

    @Override
    public void onRightClickBlock(Player player, Level level, PlayerInteractEvent.RightClickBlock event) {
        BlockPos pos = event.getPos();
        if(level.getBlockState(pos).getBlock() instanceof FarmBlock farmBlock){
            BlockPos seedPos = pos.south(1);
            seedPos = seedPos.above(2);
            ItemStack seeds = player.getItemInHand(InteractionHand.MAIN_HAND);
            if(seeds.is(Tags.Items.SEEDS) && seeds.getItem() instanceof ItemNameBlockItem itemNameBlockItem){
                level.setBlock(pos, itemNameBlockItem.getBlock().defaultBlockState().setValue(CropBlock.AGE, 1), 3);
            }
        }
    }

    public void placeSeeds(Player player, Level level, BlockPos pos, ItemStack seeds){
        Iterable<BlockPos> blockPos = createBlockPos(level, pos);
        for(BlockPos seedPos : blockPos){
            if(level.getBlockState(pos).getBlock() instanceof FarmBlock farmBlock){
                seedPos = seedPos.above();
                if(seeds.is(Tags.Items.SEEDS) && seeds.getItem() instanceof ItemNameBlockItem itemNameBlockItem){
                    level.setBlock(pos, itemNameBlockItem.getBlock().defaultBlockState().setValue(CropBlock.AGE, 1), 3);
                }
            }
        }
    }

    public List<BlockPos> createBlockPos(Level level, BlockPos pos) {
        List<BlockPos> list = new ArrayList<>();
        for(int i = pos.getX(); i <= pos.getX() + 3; i++){
            for(int j = pos.getZ(); j <= pos.getZ() + 3; i++){
                BlockPos farmLandPos = new BlockPos(i, pos.getY(), j);
                if(level.getBlockState(farmLandPos).is(Blocks.FARMLAND)){
                    list.add(farmLandPos);
                }
            }
        }
        return null;
    }
}
