package net.AbraXator.chakral.networking.packet;

import net.AbraXator.chakral.blocks.entity.custom.MineralEnricherBlockEntity;
import net.AbraXator.chakral.client.gui.chakralnexus.ChakralNexusMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.network.NetworkEvent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

public class NexusSyncS2CPacket {
    private final ItemStack itemStack;

    public NexusSyncS2CPacket(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public NexusSyncS2CPacket(FriendlyByteBuf buf){
        itemStack = buf.readItem();
    }

    public void toBytes(FriendlyByteBuf buf){
        buf.writeItem(itemStack);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            Player player = context.getSender();
            if(player.containerMenu instanceof ChakralNexusMenu chakralNexusMenu){
                chakralNexusMenu.setItemInSlot(0, itemStack);
            }
        });

        return true;
    }
}
