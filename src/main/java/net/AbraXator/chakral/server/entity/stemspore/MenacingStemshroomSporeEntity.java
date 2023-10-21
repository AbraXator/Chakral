package net.AbraXator.chakral.server.entity.stemspore;

import net.AbraXator.chakral.server.init.ModParticles;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;

public class MenacingStemshroomSporeEntity extends Projectile {
    public MenacingStemshroomSporeEntity(EntityType<? extends MenacingStemshroomSporeEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public void tick() {
        Player player = level().getNearestPlayer(position().x(), position().y(), position().z(), 6D, false);
        this.setPos(this.getX(), this.getY() + Mth.sin(level().getGameTime()) * 0.5, this.getZ());
        if (player == null) {

        } else {
            /*this.xPower = player.getX() - this.getX();
            this.yPower = player.getY(0.5D) - (0.5D + this.getY(0.5D));
            this.zPower = player.getZ() - this.getZ();

            if(getDeltaMovement().equals(Vec3.ZERO)){
                this.xPower = 0;
                this.yPower = 0;
                this.zPower = 0;
            }*/
        }
    }

    protected ParticleOptions getTrailParticle() {
        return ModParticles.STEMSHROOM_SPORE.get();
    }

    @Override
    protected void defineSynchedData() {}
}
