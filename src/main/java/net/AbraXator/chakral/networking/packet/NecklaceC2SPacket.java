package net.AbraXator.chakral.networking.packet;

import net.AbraXator.chakral.client.gui.chakralnexus.ChakralNexusMenu;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class NecklaceC2SPacket {
    public NecklaceC2SPacket(){

    }

    public NecklaceC2SPacket(FriendlyByteBuf byteBuf){

    }

    public void toBytes(FriendlyByteBuf buf){

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            context.getSender().openMenu(new SimpleMenuProvider((pContainerId, pPlayerInventory, pPlayer) -> new ChakralNexusMenu(pContainerId, pPlayerInventory), Component.translatable("")));
        });
        return true;
    }
}
