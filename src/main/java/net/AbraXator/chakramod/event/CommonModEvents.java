package net.AbraXator.chakramod.event;

import net.AbraXator.chakramod.ChakraMod;
import net.AbraXator.chakramod.entity.CrystalFish;
import net.AbraXator.chakramod.entity.ModEntity;
import net.AbraXator.chakramod.entity.MineralSnail;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = ChakraMod.MOD_ID)
public class CommonModEvents {

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event){
        event.put(ModEntity.MINERAL_SNAIL.get(), MineralSnail.createAttributes().build());
        event.put(ModEntity.CRYSTAL_FISH.get(), CrystalFish.createAttributes().build());
    }

    //@SubscribeEvent
    //public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
    //    if(event.getObject() instanceof Player) {
    //        if(!event.getObject().getCapability(PlayerChakraProvider.PLAYER_CHAKRA).isPresent()) {
    //            event.addCapability(new ResourceLocation(ChakraMod.MOD_ID, "properties"), new PlayerChakraProvider());
    //        }
    //    }
    //}
//
    //@SubscribeEvent
    //public static void onPlayerCloned(PlayerEvent.Clone event) {
    //    if(event.isWasDeath()) {
    //        event.getOriginal().getCapability(PlayerChakraProvider.PLAYER_CHAKRA).ifPresent(oldStore -> {
    //            event.getOriginal().getCapability(PlayerChakraProvider.PLAYER_CHAKRA).ifPresent(newStore -> {
    //                newStore.copyFrom(oldStore);
    //            });
    //        });
    //    }
    //}

    //@SubscribeEvent
    //public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        //event.register(PlayerChakra.class);
    //}

}
