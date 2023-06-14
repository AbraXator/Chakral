package net.AbraXator.chakral.client.particle;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SimpleAnimatedParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.util.function.Consumer;

public class LightRayParticle extends SimpleAnimatedParticle {
    private final Vec3 target;
    private float rot;
    private float rotO;
    private float pitch;
    private float pitchO;

    protected LightRayParticle(ClientLevel pLevel, double pX, double pY, double pZ, SpriteSet pSprites, Vec3 pos, int lifetime) {
        super(pLevel, pX, pY, pZ, pSprites, 0);
        this.quadSize = 0.3F;
        this.target = pos;
        this.lifetime = lifetime;
        this.setSpriteFromAge(pSprites);
        this.hasPhysics = false;
        double d0 = pX - pos.x();
        double d1 = pY - pos.y();
        double d2 = pZ - pos.z();
        this.rotO = this.rot = (float) Mth.atan2(d0, d2);
        this.pitchO = this.pitch = (float) Mth.atan2(d1, Mth.sqrt(((float) (d0 * d0 + d2 * d2))));
    }

    @Override
    public void render(VertexConsumer pBuffer, Camera pRenderInfo, float pPartialTicks) {
        float f = Mth.sin((float) (age + pPartialTicks - (float) Mth.PI * 2F) * 0.05F) * 2.0F;
        float f1 = Mth.lerp(pPartialTicks, this.rotO, this.rot);
        float f2 = Mth.lerp(pPartialTicks, this.pitchO, this.pitch) + ((float) Mth.PI / 2F);
        this.renderSignal(pBuffer, pRenderInfo, pPartialTicks, quaternionf ->
                quaternionf.rotateY(f1).rotateX(-f2).rotateY(f));
        this.renderSignal(pBuffer, pRenderInfo, pPartialTicks, quaternionf ->
                quaternionf.rotateY(-(float)Mth.PI + f1).rotateX(f2).rotateY(f));
    }

    private void renderSignal(VertexConsumer pBuffer, Camera pRenderInfo, float pPartialTicks, Consumer<Quaternionf> pQuaternionConsumer) {
        Vec3 vec3 = pRenderInfo.getPosition();
        float f = (float)(Mth.lerp((double)pPartialTicks, this.xo, this.x) - vec3.x());
        float f1 = (float)(Mth.lerp((double)pPartialTicks, this.yo, this.y) - vec3.y());
        float f2 = (float)(Mth.lerp((double)pPartialTicks, this.zo, this.z) - vec3.z());
        Vector3f vector3f = (new Vector3f(0.5F, 0.5F, 0.5F)).normalize();
        Quaternionf quaternionf = (new Quaternionf()).setAngleAxis(0.0F, vector3f.x(), vector3f.y(), vector3f.z());
        pQuaternionConsumer.accept(quaternionf);
        Vector3f[] avector3f = new Vector3f[]{new Vector3f(-1.0F, -1.0F, 0.0F), new Vector3f(-1.0F, 1.0F, 0.0F), new Vector3f(1.0F, 1.0F, 0.0F), new Vector3f(1.0F, -1.0F, 0.0F)};
        float f3 = this.getQuadSize(pPartialTicks);

        for(int i = 0; i < 4; ++i) {
            Vector3f vector3f1 = avector3f[i];
            vector3f1.rotate(quaternionf);
            vector3f1.mul(f3);
            vector3f1.add(f, f1, f2);
        }

        float f6 = this.getU0();
        float f7 = this.getU1();
        float f4 = this.getV0();
        float f5 = this.getV1();
        int j = this.getLightColor(pPartialTicks);
        pBuffer.vertex((double)avector3f[0].x(), (double)avector3f[0].y(), (double)avector3f[0].z()).uv(f7, f5).color(this.rCol, this.gCol, this.bCol, this.alpha).uv2(j).endVertex();
        pBuffer.vertex((double)avector3f[1].x(), (double)avector3f[1].y(), (double)avector3f[1].z()).uv(f7, f4).color(this.rCol, this.gCol, this.bCol, this.alpha).uv2(j).endVertex();
        pBuffer.vertex((double)avector3f[2].x(), (double)avector3f[2].y(), (double)avector3f[2].z()).uv(f6, f4).color(this.rCol, this.gCol, this.bCol, this.alpha).uv2(j).endVertex();
        pBuffer.vertex((double)avector3f[3].x(), (double)avector3f[3].y(), (double)avector3f[3].z()).uv(f6, f5).color(this.rCol, this.gCol, this.bCol, this.alpha).uv2(j).endVertex();
    }

    @Override
    public void tick() {
        this.xo = x;
        this.yo = y;
        this.zo = z;
        if (this.age++ >= this.lifetime) {
            this.remove();
        } else {
            int lifetimeRemaining = this.lifetime - this.age;
            double lifetimeRemainingFraction = 1.0D / lifetimeRemaining;
            this.x = Mth.lerp(lifetimeRemainingFraction, this.x, target.x);
            this.y = Mth.lerp(lifetimeRemainingFraction, this.y, target.y);
            this.z = Mth.lerp(lifetimeRemainingFraction, this.z, target.z);
            this.setPos(this.x, this.y, this.z);
            double XposMinDest = this.x - target.x();
            double YposMinDest = this.y - target.y();
            double ZposMinDest = this.z - target.z();
            this.rotO = this.rot;
            this.rot = (float) Mth.atan2(XposMinDest, ZposMinDest);
            this.pitchO = this.pitch;
            this.pitch = (float) Mth.atan2(YposMinDest, Mth.sqrt((float) (XposMinDest * XposMinDest + ZposMinDest * ZposMinDest)));
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
            return new LightRayParticle(pLevel, pX, pY, pZ, sprites, pType.destination, pType.arrivalInTicks);
        }
    }
}
