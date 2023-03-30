package net.AbraXator.chakral.networking.packet;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class OrePosS2CPacket {
    private final BlockPos pos;

    public OrePosS2CPacket(BlockState state, BlockPos pos) {
        this.pos = pos;
    }
    public OrePosS2CPacket(FriendlyByteBuf buf){
        this.pos = buf.readBlockPos();
    }
    public void toBytes(FriendlyByteBuf buf){
        buf.writeBlockPos(pos);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() ->{

        });
        return true;
    }
}
