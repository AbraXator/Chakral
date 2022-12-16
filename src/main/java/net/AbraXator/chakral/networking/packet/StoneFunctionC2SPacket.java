package net.AbraXator.chakral.networking.packet;

import net.AbraXator.chakral.chakra.Chakra;
import net.AbraXator.chakral.chakra.ChakraRegistries;
import net.AbraXator.chakral.chakra.ChakraUtil;
import net.AbraXator.chakral.chakra.ChakrasEquip;
import net.AbraXator.chakral.chakra.capability.NecklaceCapProvider;
import net.AbraXator.chakral.items.ModItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.network.NetworkEvent;

import java.util.List;
import java.util.function.Supplier;

public class StoneFunctionC2SPacket {
    public StoneFunctionC2SPacket(){

    }

    public StoneFunctionC2SPacket(FriendlyByteBuf buf){

    }

    public void toBytes(FriendlyByteBuf buf){

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        Player player = context.getSender();
        ChakraRegistries.CHAKRA.getEntries().forEach(s -> {
            Chakra chakra = s.get();
            chakra.onFunctionKeyPress(player, player.level);
            boolean b = chakra.stones().contains(chakra.getType().getTier4(chakra.getType()));
            if(chakra.isEnabled(chakra)) {
                if (b) {
                    chakra.onFunctionKeyPressUpgraded(player, player.level);
                } else {
                    chakra.onFunctionKeyPress(player, player.level);
                }
            }
        });
        return true;
    }
}
