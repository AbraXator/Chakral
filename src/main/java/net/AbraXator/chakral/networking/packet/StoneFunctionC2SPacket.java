package net.AbraXator.chakral.networking.packet;

import net.AbraXator.chakral.chakra.ChakraUtil;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class StoneFunctionC2SPacket {
    public StoneFunctionC2SPacket(){

    }

    public StoneFunctionC2SPacket(FriendlyByteBuf buf){

    }

    public void toBytes(FriendlyByteBuf buf){

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        supplier.get().enqueueWork(() -> {
            Player player = context.getSender();
            Level level = player.getLevel();
            ChakraUtil.getChakrasFromPlayer(player).forEach(chakra -> chakra.onFunctionKeyPress(player, level));
        });

        return true;
    }
}
