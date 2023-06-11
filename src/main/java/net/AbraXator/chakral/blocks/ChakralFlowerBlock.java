package net.AbraXator.chakral.blocks;

import net.AbraXator.chakral.init.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Supplier;

public class ChakralFlowerBlock extends FlowerBlock {
    public ChakralFlowerBlock(Supplier<MobEffect> effectSupplier, int pEffectDuration, Properties pProperties) {
        super(effectSupplier, pEffectDuration, pProperties);
    }

    @Override
    protected boolean mayPlaceOn(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return pState.is(ModTags.Blocks.MINERAL_RICH_TURFS);
    }
}
