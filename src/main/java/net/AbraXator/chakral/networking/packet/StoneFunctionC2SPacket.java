package net.AbraXator.chakral.networking.packet;

import net.AbraXator.chakral.chakra.Chakra;
import net.AbraXator.chakral.chakra.ChakraRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
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
        Player player = context.getSender();
        ChakraRegistries.CHAKRA.getEntries().forEach(s -> {
            Chakra chakra = s.get();
            chakra.onFunctionKeyPress(player, player.level);
            if(chakra.isEnabled()) {
                if (chakra.isUpgraded(chakra.getType())) {
                    chakra.onFunctionKeyPressUpgraded(player, player.level);
                } else {
                    chakra.onFunctionKeyPress(player, player.level);
                }
            }
        });
        return true;
    }
}
