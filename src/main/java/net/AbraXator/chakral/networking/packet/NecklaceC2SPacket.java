package net.AbraXator.chakral.networking.packet;

import net.AbraXator.chakral.Chakral;
import net.AbraXator.chakral.chakra.Chakra;
import net.AbraXator.chakral.chakra.ChakraHelper;
import net.AbraXator.chakral.chakra.capability.NecklaceCap;
import net.AbraXator.chakral.chakra.capability.NecklaceCapProvider;
import net.AbraXator.chakral.event.ForgeEvents;
import net.AbraXator.chakral.items.ModItems;
import net.AbraXator.chakral.utils.ModTags;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class NecklaceC2SPacket {
    public NecklaceC2SPacket(){

    }

    public NecklaceC2SPacket(FriendlyByteBuf byteBuf){

    }

    public void toBytes(FriendlyByteBuf buf){

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            Player player = context.getSender();
            ItemStack stack = player.getItemInHand(InteractionHand.MAIN_HAND);
            String name = "none";
            if (stack.is(ModItems.GOLDEN_NECKLACE.get())) {
                name = "golden_necklace";
            }
            if (stack.is(ModItems.DIAMOND_NECKLACE.get())) {
                name = "diamond_necklace";
            }
            String finalName = name;
            player.getCapability(NecklaceCapProvider.NECKLACE).ifPresent(necklaceCap -> {
                ItemStack necklace = necklaceCap.getNecklace();
                if(necklaceCap.getNecklace().is(ItemStack.EMPTY.getItem())){
                    if(stack.is(ModTags.Items.NECKLACES)){
                        necklaceCap.setNecklace(stack);
                        ChakraHelper.ChakraVisitor visitor = chakra -> {
                            chakra.onEquip(player, player.level);
                        };
                        player.setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);

                    }
                } else {
                    if(player.getInventory().getFreeSlot() == -1){
                        player.drop(necklace, false);
                    }else {
                        player.addItem(necklace);
                    }
                    necklaceCap.setNecklace(ItemStack.EMPTY);
                    ForgeEvents.EQUIPPED = false;
                }
            });
        });

        return true;
    }

    @FunctionalInterface
    interface ChakraVisitor{
        void accept(Chakra chakra);
    }
}
