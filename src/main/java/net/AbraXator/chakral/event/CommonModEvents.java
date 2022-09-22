package net.AbraXator.chakral.event;

import net.AbraXator.chakral.Chakral;
import net.AbraXator.chakral.entity.CrystalFish;
import net.AbraXator.chakral.entity.ModEntity;
import net.AbraXator.chakral.entity.MineralSnail;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Chakral.MOD_ID)
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
    //            event.addCapability(new ResourceLocation(Chakral.MOD_ID, "properties"), new PlayerChakraProvider());
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
