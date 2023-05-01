package net.AbraXator.chakral.networking.packet;

import net.AbraXator.chakral.init.ModParticles;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class HagStoneS2CPacket {
    private Vec3 playerPos;
    private Vec3 blockPos;

    public HagStoneS2CPacket(Vec3 playerPos, Vec3 blockPos) {
        this.playerPos = playerPos;
        this.blockPos = blockPos;
    }

    public HagStoneS2CPacket(FriendlyByteBuf buf){
        float a = buf.readFloat();
        float b = buf.readFloat();
        float c = buf.readFloat();
        this.playerPos = new Vec3(a, b, c);
        float j = buf.readFloat();
        float k = buf.readFloat();
        float l = buf.readFloat();
        this.blockPos = new Vec3(j, k, l);
    }

    public void toBytes(FriendlyByteBuf buf){
        buf.writeFloat((float) playerPos.x());
        buf.writeFloat((float) playerPos.y());
        buf.writeFloat((float) playerPos.z());
        buf.writeFloat((float) blockPos.x());
        buf.writeFloat((float) blockPos.y());
        buf.writeFloat((float) blockPos.z());
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            float powX = (float) (playerPos.x - blockPos.x);
            float powY = (float) (playerPos.y - blockPos.y);
            float powZ = (float) (playerPos.z - blockPos.z);
            Vec3 delta = new Vec3(powX, powY, powZ).multiply(0.5, 0.5, 0.5);
            Level level = Minecraft.getInstance().level;
            drawSphere(level);
                level.addParticle(ModParticles.HAGSTONE_FRAGMNETIUM.get(), blockPos.x(), blockPos.y(), blockPos.z(), delta.x, delta.y, delta.z);
        });
        return true;
    }

    private void drawSphere(Level level){
        double radius = 0.5D;

        for(double X = -radius; X < radius; X += 0.3) {
            for (double Y = -radius; Y < radius; Y += 0.3) {
                for (double Z = -radius; Z < radius; Z += 0.3) {
                    if (Math.sqrt((X * X) + (Y * Y) + (Z * Z)) <= radius) {
                        float powX = (float) (blockPos.x - X);
                        float powY = (float) (blockPos.y - Y);
                        float powZ = (float) (blockPos.z - Z);
                        Vec3 vec3 = new Vec3(powX, powY, powZ);
                        level.addParticle(ModParticles.HAGSTONE_FRAGMNETIUM.get(), X + blockPos.x, Y + blockPos.y, Z + blockPos.z, vec3.x, vec3.y, vec3.z);
                    }
                }
            }
        }
    }
}
