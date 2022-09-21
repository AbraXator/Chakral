package net.AbraXator.chakramod.networking.packet;

import net.AbraXator.chakramod.items.ModItems;
import net.AbraXator.chakramod.items.custom.GoldenNecklace;
import net.AbraXator.chakramod.utils.InventoryUtil;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;
import java.util.logging.Level;

public class ChakraC2SPacket {
    public ChakraC2SPacket(){

    }

    public ChakraC2SPacket(FriendlyByteBuf byteBuf){

    }

    public void toBytes(FriendlyByteBuf buf){

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() ->{
            ServerPlayer player = context.getSender();
            ServerLevel level = player.getLevel();
            ItemStack necklace = player.getInventory().getItem(1)

        });
    }
}
