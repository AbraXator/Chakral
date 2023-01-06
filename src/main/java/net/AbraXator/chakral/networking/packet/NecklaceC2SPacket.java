package net.AbraXator.chakral.networking.packet;

import net.AbraXator.chakral.chakra.Chakra;
import net.AbraXator.chakral.chakra.ChakraRegistries;
import net.AbraXator.chakral.capability.NecklaceCapProvider;
import net.AbraXator.chakral.items.ModItems;
import net.AbraXator.chakral.utils.ModTags;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkHooks;

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
            ServerPlayer serverPlayer = context.getSender();
            ItemStack stack = player.getItemInHand(InteractionHand.MAIN_HAND);
            String name = "none";
            if (stack.is(ModItems.GOLDEN_NECKLACE.get())) {
                name = "golden_necklace";
            }
            if (stack.is(ModItems.DIAMOND_NECKLACE.get())) {
                name = "diamond_necklace";
            }
            String finalName = name;
            Minecraft minecraft = Minecraft.getInstance();
            NetworkHooks.openScreen(serverPlayer, new ModMenuProvider());
            player.getCapability(NecklaceCapProvider.NECKLACE_CAP).ifPresent(necklaceCap -> {
                ItemStack necklace = necklaceCap.getNecklace();
                ChakraRegistries.CHAKRA.getEntries().forEach(s -> s.get().necklace = necklace);
                if(necklaceCap.getNecklace().is(ItemStack.EMPTY.getItem()) && stack.is(ModTags.Items.NECKLACES)){
                    necklaceCap.setNecklace(stack);
                    player.setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
                    ChakraRegistries.CHAKRA.getEntries().forEach(s -> {
                        Chakra chakra = s.get();
                        chakra.necklace = stack;
                        boolean b = chakra.stones().contains(chakra.getType().getTier4(chakra.getType()));
                        if(chakra.isEnabled(chakra)) {
                            if (b) {
                                chakra.onEquipUpgraded(player, player.level);
                            } else {
                                chakra.onEquip(player, player.level);
                            }
                        }
                    });
                } else {
                    if(player.getInventory().getFreeSlot() == -1){
                        player.drop(necklace, false);
                    }else {
                        player.addItem(necklace);
                    }
                    necklaceCap.setNecklace(ItemStack.EMPTY);
                    ChakraRegistries.CHAKRA.getEntries().forEach(s -> {
                        Chakra chakra = s.get();
                        chakra.necklace = ItemStack.EMPTY;
                        boolean b = chakra.stones().contains(chakra.getType().getTier4(chakra.getType()));
                        if(chakra.isEnabled(chakra)) {
                            if (b) {
                                chakra.onUnequipUpgraded(player, player.level);
                            } else {
                                chakra.onUnequip(player, player.level);
                            }
                        }
                    });
                }
            });
        });
        return true;
    }
}
