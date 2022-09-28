package net.AbraXator.chakral.networking.packet;

import net.AbraXator.chakral.chakra.capability.NecklaceCap;
import net.AbraXator.chakral.chakra.capability.NecklaceCapProvider;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
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
            Player player = context.getSender();
            ItemStack stack = player.getItemInHand(InteractionHand.MAIN_HAND);
            player.getCapability(NecklaceCapProvider.NECKLACE).ifPresent(necklaceCap -> {
                necklaceCap.setNecklace(stack.getDisplayName().getString());
                player.sendSystemMessage(Component.literal("Your Necklace is: " + stack.getDisplayName()));
            });
        });

        return true;
    }
}
