package net.AbraXator.chakral.blocks;

import net.AbraXator.chakral.blocks.entity.ChakralSignBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;

public class ChakralSignBlock extends StandingSignBlock {
    public ChakralSignBlock(Properties pProperties, WoodType pType) {
        super(pProperties, pType);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new ChakralSignBlockEntity(pPos, pState);
    }
}