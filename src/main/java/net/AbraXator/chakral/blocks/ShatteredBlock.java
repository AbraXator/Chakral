package net.AbraXator.chakral.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ShatteredBlock extends Block {
    public ShatteredBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void fallOn(Level pLevel, BlockState pState, BlockPos pPos, Entity pEntity, float pFallDistance) {

    }

    @Override
    public void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) {
        if (pLevel instanceof ServerLevel level && pEntity.fallDistance >= 2.5F) {
            level.destroyBlock(pPos, false);
            BlockParticleOption blockParticleOption = new BlockParticleOption(ParticleTypes.BLOCK, pState);
            Vec3 vec3 = pPos.getCenter();
            level.playSound(null, pPos, SoundEvents.DEEPSLATE_BREAK, SoundSource.BLOCKS, 1.0F, 0.1F);
            level.sendParticles(blockParticleOption, vec3.x(), vec3.y(), vec3.z(), 25, 0, 0, 0, 0);
        }
    }

    @Override
    public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        if(pContext instanceof EntityCollisionContext context){
            Entity entity = context.getEntity();
            if (entity != null && entity.fallDistance > 2.5F) {
                return Shapes.empty();
            }
        }
        return super.getCollisionShape(pState, pLevel, pPos, pContext);
    }
}
