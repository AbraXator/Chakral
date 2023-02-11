package net.AbraXator.chakral.client;

import net.AbraXator.chakral.networking.packet.ChakraHeartsS2CPacket;
import net.minecraft.world.entity.player.Player;

public class ChakraHeartData {
    private static float health;

    public static void setHealth(float health){
        ChakraHeartData.health = health;
    }

    public static float getHealth(){
        return health;
    }
}
