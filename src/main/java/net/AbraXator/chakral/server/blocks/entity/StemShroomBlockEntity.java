package net.AbraXator.chakral.server.blocks.entity;

import net.AbraXator.chakral.server.entity.stemspore.MenacingStemshroomSporeEntity;
import net.AbraXator.chakral.server.init.ModBlockEntities;
import net.AbraXator.chakral.server.init.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gameevent.GameEventListener;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class StemShroomBlockEntity extends BaseLightrayBlockEntity{
    public StemShroomBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.STEMSHROOM_BLOCK_ENTITY.get(), pWorldPosition, pBlockState);
    }

    @Override
    public void onSignalReceive(ServerLevel serverLevel, GameEventListener listener, BlockPos pos, GameEvent gameEvent, @Nullable Entity owner, float distance) {
        RandomSource randomSource = serverLevel.getRandom();
        for(int i = 0; i <= (randomSource.nextInt(7)) - 3; i++){
            MenacingStemshroomSporeEntity entity = new MenacingStemshroomSporeEntity(ModEntities.MENACING_STEMSHROOM_SPORE.get(), serverLevel);
            Vec3 vec3;
            Vec3 vec31 = this.getBlockPos().getCenter();
            do {
                vec3 = generatePos(randomSource, this.getBlockPos().getCenter());
            }while(!(serverLevel.getBlockState(BlockPos.containing(vec3)).is(Blocks.AIR)));
            do {
                vec31.offsetRandom(randomSource, 0.5F);
            }while(!(serverLevel.getBlockState(BlockPos.containing(vec31)).is(Blocks.AIR)));
            float powX = (float) (vec31.x() - vec3.x());
            float powY = (float) (vec31.y() - vec3.y());
            float powZ = (float) (vec31.z() - vec3.z());
            Vec3 deltaMov = new Vec3(powX, powY, powZ);
            entity.setPos(vec31);
            entity.addDeltaMovement(deltaMov);
            serverLevel.addFreshEntity(entity);
        }
    }

    private Vec3 generatePos(RandomSource randomSource, Vec3 blockPos){
        double u = randomSource.nextDouble();
        double v = randomSource.nextDouble();
        float theta = ((float) (u * 2 * Mth.PI));
        float phi = ((float) Math.acos(2 * v - 1));
        double r = Math.cbrt(randomSource.nextDouble());
        double sinTheta = Mth.sin(theta);
        double cosTheta = Mth.cos(theta);
        double sinPhi = Mth.sin(phi);
        double cosPhi = Mth.cos(phi);
        double x = (r * sinPhi * cosTheta) * 5 + blockPos.x();
        double y = (r * sinPhi * sinTheta) * 5 + blockPos.y();
        double z = (r * cosPhi) * 5 + blockPos.z();
        return new Vec3(x, y, z);
    }

    @Override
    public void entityTick(Level level, BlockPos blockpos, BlockState state, BaseLightrayBlockEntity entity) {

    }
}
