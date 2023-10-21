package net.AbraXator.chakral.server.blocks;

import net.AbraXator.chakral.server.init.ModDamageTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import java.util.function.Supplier;

public class RosetteCactoidBlock extends ChakralFlowerBlock{
    public RosetteCactoidBlock(Supplier<MobEffect> effectSupplier, int pEffectDuration, Properties pProperties) {
        super(effectSupplier, pEffectDuration, pProperties);
    }

    public void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) {
        if (pEntity instanceof LivingEntity) {
            pEntity.makeStuckInBlock(pState, new Vec3(0.9F, 0.85D, 0.9F));
            if (!pLevel.isClientSide && (pEntity.xOld != pEntity.getX() || pEntity.zOld != pEntity.getZ())) {
                double d0 = Math.abs(pEntity.getX() - pEntity.xOld);
                double d1 = Math.abs(pEntity.getZ() - pEntity.zOld);
                if (d0 >= (double)0.003F || d1 >= (double)0.003F) {
                    pEntity.hurt(ModDamageTypes.getDamageSource(pLevel, ModDamageTypes.ROSETTE_CACTOID), 1.0F);
                }
            }

        }
    }
}
