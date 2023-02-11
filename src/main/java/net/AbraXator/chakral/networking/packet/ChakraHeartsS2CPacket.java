package net.AbraXator.chakral.networking.packet;

import net.AbraXator.chakral.blocks.entity.custom.MineralEnricherBlockEntity;
import net.AbraXator.chakral.client.ChakraHeartData;
import net.AbraXator.chakral.client.ModOverlay;
import net.AbraXator.chakral.screen.enricher.MineralEnricherMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ChakraHeartsS2CPacket {
    private float health;

    public ChakraHeartsS2CPacket(float health) {
        this.health = health;
    }

    public ChakraHeartsS2CPacket(FriendlyByteBuf buf){
        this.health = buf.readFloat();
    }

    public void toBytes(FriendlyByteBuf buf){
        buf.writeFloat(health);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ChakraHeartData.setHealth(health);
        });
        return true;
    }
}
