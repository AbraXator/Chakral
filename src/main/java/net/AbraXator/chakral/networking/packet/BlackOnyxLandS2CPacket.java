package net.AbraXator.chakral.networking.packet;

import net.AbraXator.chakral.client.ChakraHeartData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.ParticleStatus;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class BlackOnyxLandS2CPacket {
    private Vec3 pos;

    public BlackOnyxLandS2CPacket(Vec3 pos) {
        this.pos = pos;
    }

    public BlackOnyxLandS2CPacket(FriendlyByteBuf buf){
        float j = buf.readFloat();
        float k = buf.readFloat();
        float l = buf.readFloat();
        this.pos = new Vec3(j, k, l);
    }

    public void toBytes(FriendlyByteBuf buf){
        buf.writeFloat((float) pos.x());
        buf.writeFloat((float) pos.y());
        buf.writeFloat((float) pos.z());
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        List<Vec2> s = new ArrayList<>();
        context.enqueueWork(() -> {
            for(int j = -4; j <= 4; j++) {
                for (int i = 0; i < Mth.TWO_PI * 4; i++) {
                    float x = ((float) pos.x()) + Mth.cos(i) * j;
                    float z = ((float) pos.z()) + Mth.sin(i) * j;
                    Minecraft.getInstance().level.addAlwaysVisibleParticle(ParticleTypes.LARGE_SMOKE, x, pos.y + 0.5, z, 0.0D, 0.0D, 0.0D);
                }
            }
        });
        return true;
    }
}
