package net.AbraXator.chakral.entity.stemspore;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class MenacingStemshroomSporeEntity extends Projectile {
    private float fallingSpeed = 0.001F;

    public MenacingStemshroomSporeEntity(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.noPhysics = true;
    }

    @Override
    public void tick() {
        /*super.tick();
        fallingSpeed -= 0.01;
        HitResult hitResult = ProjectileUtil.getHitResult(this, this::canHitEntity);
        if(hitResult.getType() != HitResult.Type.MISS && !net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, hitResult)){
            this.onHit(hitResult);
        }
        Vec3 vec3 = this.getDeltaMovement();
        double d0 = this.getX() + vec3.x;
        double d1 = this.getY() + vec3.y;
        double d2 = this.getZ() + vec3.z;
        this.updateRotation();
        if(this.level.getBlockStates(this.getBoundingBox()).noneMatch(BlockBehaviour.BlockStateBase::isAir)){
            this.discard();
        }else {
            float finalSpeed = ((float) (-0.1 + fallingSpeed));
            this.setDeltaMovement(0.0, -0.1 + fallingSpeed, 0.0D);
        }
        this.setPos(d0, d1, d2);*/
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        if(pResult.getEntity() instanceof LivingEntity){
            explode();
            this.discard();
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        super.onHitBlock(pResult);
        explode();
        this.discard();
    }

    private void explode(){
        level.explode(this, this.getX(), this.getY(), this.getZ(), 1F, Level.ExplosionInteraction.MOB);
    }

    @Override
    protected void onHit(HitResult pResult) {
        super.onHit(pResult);
        this.discard();
    }

    @Override
    protected void defineSynchedData() {}
}
