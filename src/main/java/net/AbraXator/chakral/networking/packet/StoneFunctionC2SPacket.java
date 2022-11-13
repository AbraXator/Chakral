package net.AbraXator.chakral.networking.packet;

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
        context.enqueueWork(() ->{
            Player player = context.getSender();
            player.getCapability(NecklaceCapProvider.NECKLACE).ifPresent(necklaceCap -> {
                ItemStack necklace = necklaceCap.getNecklace();
                if(necklace.hasTag()){
                    CompoundTag tag = necklace.getTag();
                    Item stone1 = tag.get("Stone1") != null || ItemStack.of(tag.getCompound("Stone1")).is(Items.AIR)
                            ? ItemStack.of(tag.getCompound("Stone1")).getItem() : Items.AIR;
                    Item stone2 = tag.get("Stone2") != null || ItemStack.of(tag.getCompound("Stone2")).is(Items.AIR)
                            ? ItemStack.of(tag.getCompound("Stone2")).getItem() : Items.AIR;
                    Item stone3 = tag.get("Stone3") != null || ItemStack.of(tag.getCompound("Stone3")).is(Items.AIR)
                            ? ItemStack.of(tag.getCompound("Stone3")).getItem() : Items.AIR;
                    Item stone4 = tag.get("Stone4") != null || ItemStack.of(tag.getCompound("Stone4")).is(Items.AIR)
                            ? ItemStack.of(tag.getCompound("Stone4")).getItem() : Items.AIR;
                    List<Item> gems = List.of(stone1, stone2, stone3, stone4);
                    if(gems.contains(ModItems.CITRINE.get().getDefaultInstance())){
                        ChakrasEquip.citrine(player);
                    }
                    boolean contains = gems.contains(ModItems.MOON_STONE.get());
                    if(contains){
                        ChakrasEquip.moonStone(player);
                        System.out.println(ChakraUtil.moonStoneCooldown);
                    }
                }
            });
        });
        return true;
    }
}
