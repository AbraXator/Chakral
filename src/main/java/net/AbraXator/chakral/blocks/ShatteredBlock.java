package net.AbraXator.chakral.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class ShatteredBlock extends Block {
    public ShatteredBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void fallOn(Level pLevel, BlockState pState, BlockPos pPos, Entity pEntity, float pFallDistance) {
        if(pEntity instanceof ServerPlayer player && pLevel instanceof ServerLevel level) {
            pLevel.setBlock(pPos, Blocks.AIR.defaultBlockState(), 3);
            BlockParticleOption blockParticleOption = new BlockParticleOption(ParticleTypes.BLOCK, pState);
            Vec3 vec3 = pPos.getCenter();
            level.sendParticles(blockParticleOption, vec3.x(), vec3.y(), vec3.z(), 25, 0, 0, 0, 0);
            super.fallOn(pLevel, pState, pPos, pEntity, pFallDistance);
        }
    }
}
