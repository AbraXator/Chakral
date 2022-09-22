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

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if(!pContext.getLevel().getBlockState(pContext.getClickedPos()).is(ModBlocks.SHARD_REFINER.get())){
            return InteractionResult.PASS;
        }else {
            int i;
            switch (chakraStrenght){
                case WEAKENED -> i = 1;
                case POWERFUL -> i = 2;
                case ENLIGHTENED -> i = 3;
                default -> i = 0;
            }

            return ShardRefinerBlock.tryUpgrade(pContext.getPlayer(), pContext.getLevel(), pContext.getClickedPos(), pContext.getLevel().getBlockState(pContext.getClickedPos()), i)
                    ? InteractionResult.sidedSuccess(pContext.getLevel().isClientSide) : InteractionResult.PASS;
        }
    }
}
