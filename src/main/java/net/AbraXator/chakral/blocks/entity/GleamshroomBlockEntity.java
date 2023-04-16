package net.AbraXator.chakral.blocks.entity;

import net.AbraXator.chakral.init.ModBlocks;
import net.AbraXator.chakral.blocks.GleamshroomBlock;
import net.AbraXator.chakral.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class GleamshroomBlockEntity extends BlockEntity {
    public GleamshroomBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.GLEAMSHROOM_BLOCK_ENTITY.get(), pWorldPosition, pBlockState);
    }

    public static final int maxCooldown = 999999999;
    public static int cooldown = 0;

    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, GleamshroomBlockEntity pBlockEntity){
        if(pLevel.isClientSide){
            return;
        }
        int lightLevel = pLevel.getBrightness(LightLayer.BLOCK, pPos);
        if(lightLevel > 2 && cooldown == 0){
            pLevel.setBlock(pPos, ModBlocks.GLEAMSHROOM.get().defaultBlockState().setValue(GleamshroomBlock.LIT, true), 3);
            cooldown = maxCooldown;
        }
        while (cooldown >= 0){
            cooldown--;
        }
    }
}