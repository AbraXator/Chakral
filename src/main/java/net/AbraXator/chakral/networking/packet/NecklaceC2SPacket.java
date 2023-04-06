package net.AbraXator.chakral.networking.packet;

import net.AbraXator.chakral.capability.AdditionalHealthCapProvider;
import net.AbraXator.chakral.capability.NecklaceCapProvider;
import net.AbraXator.chakral.chakra.Chakra;
import net.AbraXator.chakral.chakra.ChakraRegistries;
import net.AbraXator.chakral.client.gui.chakralnexus.ChakralNexusMenu;
import net.AbraXator.chakral.networking.ModMessages;
import net.AbraXator.chakral.utils.ModTags;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.NetworkEvent;

import java.util.Collection;
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
            context.getSender().openMenu(new SimpleMenuProvider((pContainerId, pPlayerInventory, pPlayer) -> new ChakralNexusMenu(pContainerId, pPlayerInventory), Component.translatable("")));
            /*Player player = context.getSender();
            ItemStack stack = player.getItemInHand(InteractionHand.MAIN_HAND);
            player.getCapability(NecklaceCapProvider.NECKLACE_CAP).ifPresent(necklaceCap -> {
                ItemStack necklace = necklaceCap.getNecklace() != null ? necklaceCap.getNecklace() : ItemStack.EMPTY;
                    if(necklaceCap.getNecklace().is(ItemStack.EMPTY.getItem()) && stack.is(ModTags.Items.NECKLACES)){
                    necklaceCap.setNecklace(stack);
                    player.setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
                    ChakraRegistries.CHAKRA.getEntries().forEach(s -> {
                        Chakra chakra = s.get();
                        chakra.setNecklace(stack);
                        if(chakra.isEnabled()) {
                            if (chakra.isUpgraded()) {
                                chakra.onEquipUpgraded(player, player.level);
                            } else {
                                chakra.onEquip(player, player.level);
                            }
                        }
                    });
                } else {
                    ChakraRegistries.CHAKRA.getEntries().forEach(s -> {
                        Chakra chakra = s.get();
                        chakra.setNecklace(necklaceCap.getNecklace());
                        if(chakra.isEnabled()) {
                            if(chakra.isUpgraded()) {
                                chakra.onUnequipUpgraded(player, player.level);
                            } else {
                                chakra.onUnequip(player, player.level);
                            }
                        }
                    });
                    if(player.getInventory().getFreeSlot() == -1){
                        player.drop(necklace, false);
                    }else {
                        player.addItem(necklace);
                    }
                    necklaceCap.setNecklace(ItemStack.EMPTY);
                }
            });*/
        });
        return true;
    }
}
