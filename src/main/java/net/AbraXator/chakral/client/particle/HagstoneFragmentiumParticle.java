package net.AbraXator.chakral.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SimpleAnimatedParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class HagstoneFragmentiumParticle extends SimpleAnimatedParticle {
    private final Vec3 target;

    protected HagstoneFragmentiumParticle(ClientLevel pLevel, double pX, double pY, double pZ, SpriteSet pSprites, Vec3 target, int lifetime) {
        super(pLevel, pX, pY, pZ, pSprites, -0.1F);
        this.lifetime = lifetime;
        this.target = target;
        this.setSpriteFromAge(pSprites);
        this.hasPhysics = false;
    }

    @Override
    public void tick() {
        this.xo = x;
        this.yo = y;
        this.zo = z;
        if(this.age++ >= this.lifetime){
            this.remove();
        }else {
            int lifetimeRemaining = this.lifetime - this.age;
            double lifetimeRemainingFraction = 1.0D / lifetimeRemaining;
            this.x = Mth.lerp(lifetimeRemainingFraction, this.x, target.x);
            this.y = Mth.lerp(lifetimeRemainingFraction, this.y, target.y);
            this.z = Mth.lerp(lifetimeRemainingFraction, this.z, target.z);
            this.setPos(this.x, this.y, this.z);
        }
    }

    public static class Provider implements ParticleProvider<TravelingParticle> {
        private final SpriteSet sprites;

        public Provider(SpriteSet sprites) {
            this.sprites = sprites;
        }

        @Nullable
        @Override
        public Particle createParticle(TravelingParticle pType, ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed) {
            return new HagstoneFragmentiumParticle(pLevel, pX, pY, pZ, sprites, pType.destination.getPosition(pLevel).get(), pType.arrivalInTicks);
        }
    }
}
