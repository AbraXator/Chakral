package net.AbraXator.chakral.server.networking;

import net.AbraXator.chakral.Chakral;
import net.AbraXator.chakral.server.networking.packet.*;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModPacketHandler {
    private static final String VERSION = "1";

    public static final SimpleChannel CHANNEL = NetworkRegistry.ChannelBuilder
            .named(Chakral.loc("channel"))
            .networkProtocolVersion(() -> VERSION)
            .clientAcceptedVersions(VERSION::equals)
            .serverAcceptedVersions(VERSION::equals)
            .simpleChannel();

    public static void register(){
        int id = 0;

        CHANNEL.registerMessage(id++, StoneFunctionC2SPacket.class, StoneFunctionC2SPacket::toBytes, StoneFunctionC2SPacket::new, StoneFunctionC2SPacket::handle);
        CHANNEL.registerMessage(id++, NecklaceC2SPacket.class, NecklaceC2SPacket::toBytes, NecklaceC2SPacket::new, NecklaceC2SPacket::handle);
        CHANNEL.registerMessage(id++, EnricherSyncS2CPacket.class, EnricherSyncS2CPacket::toBytes, EnricherSyncS2CPacket::new, EnricherSyncS2CPacket::handle);
        CHANNEL.registerMessage(id++, FluidSyncS2CPacket.class, FluidSyncS2CPacket::toBytes, FluidSyncS2CPacket::new, FluidSyncS2CPacket::handle);
        CHANNEL.registerMessage(id++, ChakraHeartsS2CPacket.class, ChakraHeartsS2CPacket::toBytes, ChakraHeartsS2CPacket::new, ChakraHeartsS2CPacket::handle);
        CHANNEL.registerMessage(id++, NexusSyncS2CPacket.class, NexusSyncS2CPacket::toBytes, NexusSyncS2CPacket::new, NexusSyncS2CPacket::handle);
        CHANNEL.registerMessage(id++, BlackOnyxLandS2CPacket.class, BlackOnyxLandS2CPacket::toBytes, BlackOnyxLandS2CPacket::new, BlackOnyxLandS2CPacket::handle);
        CHANNEL.registerMessage(id++, ChakraItemNameRendererC2SPacket.class, ChakraItemNameRendererC2SPacket::toBytes, ChakraItemNameRendererC2SPacket::new, ChakraItemNameRendererC2SPacket::handle);
    }

    public static <MSG> void sendToServer(MSG msg){
        CHANNEL.sendToServer(msg);
    }

    public static <MSG> void sendToPlayer(MSG msg, ServerPlayer player){
        CHANNEL.send(PacketDistributor.PLAYER.with(() -> player), msg);
    }

    public static <MSG> void sendToClients(MSG msg){
        CHANNEL.send(PacketDistributor.ALL.noArg(), msg);
    }
}

