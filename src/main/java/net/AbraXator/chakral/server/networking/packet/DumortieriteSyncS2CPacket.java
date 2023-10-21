package net.AbraXator.chakral.server.networking.packet;

import net.AbraXator.chakral.server.chakra.ChakraUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class DumortieriteSyncS2CPacket {
    public List<BlockPos> pos;

    public DumortieriteSyncS2CPacket(List<BlockPos> blockPos) {
        this.pos = blockPos;
    }

    public DumortieriteSyncS2CPacket(FriendlyByteBuf buf){
        this.pos = buf.readCollection(ArrayList::new, FriendlyByteBuf::readBlockPos);
    }

    public void toBytes(FriendlyByteBuf buf){
        buf.writeCollection(pos, FriendlyByteBuf::writeBlockPos);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ChakraUtil.getChakrasFromPlayer(Minecraft.getInstance().player).forEach(chakra -> {
            });
        });
        return true;
    }
}
