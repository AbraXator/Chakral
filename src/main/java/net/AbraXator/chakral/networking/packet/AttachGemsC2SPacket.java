package net.AbraXator.chakral.networking.packet;

import net.AbraXator.chakral.chakra.capability.NecklaceCapProvider;
import net.AbraXator.chakral.chakra.capability.PlayerGemsCapProvider;
import net.AbraXator.chakral.items.ModItems;
import net.AbraXator.chakral.utils.InventoryUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class AttachGemsC2SPacket {
    public AttachGemsC2SPacket(){

    }

    public AttachGemsC2SPacket(FriendlyByteBuf byteBuf){

    }

    public void toBytes(FriendlyByteBuf buf){

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() ->{
            Player player = context.getSender();
            player.getCapability(PlayerGemsCapProvider.GEMS_CAP).ifPresent(playerGemsCap -> {
                player.getCapability(NecklaceCapProvider.NECKLACE).ifPresent(necklaceCap -> {
                    ItemStack stack = necklaceCap.getNecklace();
                    CompoundTag tag = stack.getTag();
                    ListTag listTag = new ListTag();
                    listTag.add(tag);
//                    playerGemsCap.setGems(listTag.);
                });
            });
        });
        return true;
    }
}
