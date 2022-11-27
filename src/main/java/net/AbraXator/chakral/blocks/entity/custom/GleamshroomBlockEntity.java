package net.AbraXator.chakral.blocks.entity.custom;

import net.AbraXator.chakral.blocks.ModBlocks;
import net.AbraXator.chakral.blocks.custom.GleamshroomBlock;
import net.AbraXator.chakral.blocks.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ConduitBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class GleamshroomBlockEntity extends BlockEntity {
    public GleamshroomBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.GLEAMSHROOM_BLOCK_ENTITY.get(), pWorldPosition, pBlockState);
    }

    public static final int maxCooldown = 100;
    public static int cooldown = 0;

    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, GleamshroomBlockEntity pBlockEntity){
        if(pLevel.isClientSide){
            return;
        }
        int lightLevel = pLevel.getBrightness(LightLayer.BLOCK, pPos);
        if(lightLevel > 2 && cooldown == 0){
            pLevel.setBlock(pPos, ModBlocks.GLEAMSHROOM.get().defaultBlockState().setValue(GleamshroomBlock.LIT, true), 1);
            cooldown = maxCooldown;
        }
        while (cooldown >= 0){
            cooldown--;
        }
    }
}
