package net.AbraXator.chakral.networking.packet;

import net.AbraXator.chakral.items.ModItems;
import net.AbraXator.chakral.utils.InventoryUtil;
import net.AbraXator.chakral.utils.ModTags;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

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
            ItemStack necklace = player.getInventory().getItem(InventoryUtil.getFirstInventoryIndex(player, ModItems.GOLDEN_NECKLACE.get()));
            if(necklace.hasTag()) {
                String nbtData = necklace.getTag().getString("chakral.stones");
                nbtData = nbtData.replace("[", "").replace("]", "").replace(" ", "_").toLowerCase();

            }
        });
        return true;
    }
}
