package net.AbraXator.chakral.items.custom;

import net.AbraXator.chakral.blocks.ModBlocks;
import net.AbraXator.chakral.blocks.custom.ShardRefinerBlock;
import net.AbraXator.chakral.chakra.ChakraStrenght;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;

public class RefinerKit extends Item {
    public static ChakraStrenght chakraStrenght;
    public RefinerKit(Properties pProperties, ChakraStrenght chakraStrenght) {
        super(pProperties);
        RefinerKit.chakraStrenght = chakraStrenght;
    }

    public static ChakraStrenght getStrenght(){
        return chakraStrenght;
    }

    //@Override
    //public InteractionResult useOn(UseOnContext pContext) {
    //    if(pContext.getLevel().getBlockState(pContext.getClickedPos()).is(ModBlocks.SHARD_REFINER.get())){
    //        return ShardRefinerBlock.upgrade(chakraStrenght);
    //    }else {
    //        return InteractionResult.PASS;
    //    }
    //}
}
