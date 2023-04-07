package net.AbraXator.chakral.networking;

import net.AbraXator.chakral.Chakral;
import net.AbraXator.chakral.networking.packet.*;
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
        net.messageBuilder(EnricherSyncS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(EnricherSyncS2CPacket::new)
                .encoder(EnricherSyncS2CPacket::toBytes)
                .consumerMainThread(EnricherSyncS2CPacket::handle)
                .add();
        net.messageBuilder(FluidSyncS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(FluidSyncS2CPacket::new)
                .encoder(FluidSyncS2CPacket::toBytes)
                .consumerMainThread(FluidSyncS2CPacket::handle)
                .add();
        net.messageBuilder(ChakraHeartsS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(ChakraHeartsS2CPacket::new)
                .encoder(ChakraHeartsS2CPacket::toBytes)
                .consumerMainThread(ChakraHeartsS2CPacket::handle)
                .add();
        net.messageBuilder(NexusSyncS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(NexusSyncS2CPacket::new)
                .encoder(NexusSyncS2CPacket::toBytes)
                .consumerMainThread(NexusSyncS2CPacket::handle)
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

