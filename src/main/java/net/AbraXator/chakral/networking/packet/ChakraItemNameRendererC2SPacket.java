package net.AbraXator.chakral.networking.packet;

import net.AbraXator.chakral.client.gui.chakralnexus.ChakralNexusMenu;
import net.AbraXator.chakral.items.ChakraItem;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ChakraItemNameRendererC2SPacket {
    public ChakraItemNameRendererC2SPacket(){

    }

    public ChakraItemNameRendererC2SPacket(FriendlyByteBuf byteBuf){

    }

    public void toBytes(FriendlyByteBuf buf){

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            Player player = context.getSender();
            ItemStack itemStack = player.getMainHandItem();
            if(itemStack.getItem() instanceof ChakraItem chakraItem){
                chakraItem.changeItemName(itemStack);
            }
        });
        return true;
    }
}
