package net.AbraXator.chakral.entity.stemspore;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class MenacingStemshroomSporeEntity extends Projectile {
    public MenacingStemshroomSporeEntity(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    public void tick() {
        Player player = level.getNearestPlayer(position().x(), position().y(), position().z(), 6D, false);
        if(player != null) {
            float distance = ((float) position().distanceTo(player.getPosition(0)));
            double distanceFraction = 1.0D / distance;
            float x = (float) Mth.lerp(distanceFraction, this.getX(), player.getX());
            float y = (float) Mth.lerp(distanceFraction, this.getY(), player.getY());
            float z = (float) Mth.lerp(distanceFraction, this.getZ(), player.getZ());
            this.setPos(x, y, z);


            HitResult hitresult = ProjectileUtil.getHitResult(this, this::canHitEntity);
            if (hitresult.getType() != HitResult.Type.MISS && !net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, hitresult)) {
                this.onHit(hitresult);
                this.discard();
            }
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        if(pResult.getEntity() instanceof LivingEntity){
            explode();
            this.discard();
        }
    }

    private void explode(){
        level.explode(this, this.getX(), this.getY(), this.getZ(), 1F, Level.ExplosionInteraction.MOB);
    }

    @Override
    protected void onHit(HitResult pResult) {
        super.onHit(pResult);
    }

    @Override
    protected void defineSynchedData() {}
}
