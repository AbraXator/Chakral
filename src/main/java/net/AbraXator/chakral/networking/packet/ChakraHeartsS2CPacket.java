package net.AbraXator.chakral.networking.packet;

import net.AbraXator.chakral.client.ChakraHeartData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ChakraHeartsS2CPacket {
    private float health;

    public ChakraHeartsS2CPacket(float health) {
        this.health = health;
    }

    public ChakraHeartsS2CPacket(FriendlyByteBuf buf){
        this.health = buf.readFloat();
    }

    public void toBytes(FriendlyByteBuf buf){
        buf.writeFloat(health);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ChakraHeartData.setHealth(health);
        });
        return true;
    }
}
