package net.AbraXator.chakral.blocks.entity;

import net.AbraXator.chakral.Chakral;
import net.AbraXator.chakral.entity.stemspore.MenacingStemshroomSporeEntity;
import net.AbraXator.chakral.init.ModBlockEntities;
import net.AbraXator.chakral.init.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gameevent.GameEventListener;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class StemShroomBlockEntity extends BaseLightrayBlockEntity {
    public StemShroomBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.STEMSHROOM_BLOCK_ENTITY.get(), pWorldPosition, pBlockState);
    }

    @Override
    public void onSignalReceive(ServerLevel serverLevel, GameEventListener listener, BlockPos pos, GameEvent gameEvent, @Nullable Entity owner, float distance) {
        RandomSource randomSource = serverLevel.getRandom();
        for(int i = 0; i <= randomSource.nextInt(5); i++){
            MenacingStemshroomSporeEntity entity = new MenacingStemshroomSporeEntity(ModEntities.MENACING_STEMSHROOM_SPORE.get(), level);
            Vec3 pos1;
            do {
                pos1 = generatePos(randomSource, pos.getCenter());
            }while (!(level.getBlockState(new BlockPos(((int) pos1.x()), ((int) pos1.y()), ((int) pos1.z()))).is(Blocks.AIR)));
            entity.setPos(pos1);
            level.addFreshEntity(entity);
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
        Chakral.LOGGER.info("x: {}; y: {}; z: {}; u: {}; v: {}", x, y, z, u, v);
        return new Vec3(x, y, z);
    }

    @Override
    public void entityTick(Level level, BlockPos blockpos, BlockState state, BaseLightrayBlockEntity entity) {

    }
}
