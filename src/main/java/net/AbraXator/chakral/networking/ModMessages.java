package net.AbraXator.chakral.networking;

import net.AbraXator.chakral.Chakral;
import net.AbraXator.chakral.networking.packet.ItemStackSyncS2CPacket;
import net.AbraXator.chakral.networking.packet.NecklaceC2SPacket;
import net.AbraXator.chakral.networking.packet.StoneFunctionC2SPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModMessages {
    private static SimpleChannel INSTANCE;

    private static int packetId = 0;
    private static int id(){
        return packetId++;
    }

    public static void register(){
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(Chakral.MOD_ID, "messages"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = net;

        net.messageBuilder(StoneFunctionC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(StoneFunctionC2SPacket::new)
                .encoder(StoneFunctionC2SPacket::toBytes)
                .consumerMainThread(StoneFunctionC2SPacket::handle)
                .add();

        net.messageBuilder(NecklaceC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(NecklaceC2SPacket::new)
                .encoder(NecklaceC2SPacket::toBytes)
                .consumerMainThread(NecklaceC2SPacket::handle)
                .add();

        net.messageBuilder(ItemStackSyncS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(ItemStackSyncS2CPacket::new)
                .encoder(ItemStackSyncS2CPacket::toBytes)
                .consumerMainThread(ItemStackSyncS2CPacket::handle)
                .add();
    }

    public static <MSG> void sendToServer(MSG msg){
        INSTANCE.sendToServer(msg);
    }

    public static <MSG> void sendToPlayer(MSG msg, ServerPlayer player){
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), msg);
    }

    public static <MSG> void sendToClients(MSG msg){
        INSTANCE.send(PacketDistributor.ALL.noArg(), msg);
    }
}

