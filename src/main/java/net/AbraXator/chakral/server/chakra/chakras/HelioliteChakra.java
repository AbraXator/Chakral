package net.AbraXator.chakral.server.chakra.chakras;

import net.AbraXator.chakral.server.chakra.Chakra;
import net.AbraXator.chakral.server.chakra.ChakraStrength;
import net.AbraXator.chakral.server.chakra.ChakraType;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Style;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import java.util.ArrayList;
import java.util.List;

public class HelioliteChakra extends Chakra {
    public HelioliteChakra(ChakraType type, ChakraStrength chakraStrength) {
        super(type, chakraStrength);
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

    @Override
    public List<Style> getColors() {
        return chakraColor("ffc55c", "fcb042", "ff702c", "ff852c", "ff5b2c");
    }
}
