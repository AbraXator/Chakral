package net.AbraXator.chakral.event;

import net.AbraXator.chakral.Chakral;
import net.AbraXator.chakral.capability.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Chakral.MOD_ID)
public class CommonModEvents {
    @SubscribeEvent
    public static void attachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if(event.getObject() instanceof Player) {
            if(!event.getObject().getCapability(NecklaceCapProvider.NECKLACE_CAP).isPresent()) {
                event.addCapability(new ResourceLocation(Chakral.MOD_ID, "necklace"), new NecklaceCapProvider());
                event.addCapability(new ResourceLocation(Chakral.MOD_ID, "chakra_mastery"), new ChakraMasteryCapProvider());
                event.addCapability(new ResourceLocation(Chakral.MOD_ID, "chakra_health"), new AdditionalHealthCapProvider());
                //event.addCapability(new ResourceLocation(Chakral.MOD_ID, "properties"), new PlayerGemsCapProvider());
            }
        }
    }

    //TODO: Fix not being able to equip necklace after death
    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if(event.isWasDeath()) {
            Player player = event.getOriginal();
            event.getOriginal().getCapability(NecklaceCapProvider.NECKLACE_CAP).ifPresent(oldStore -> {
                oldStore.getStones().forEach(itemStack -> {
                   player.drop(itemStack.copy(), false);
                });
                event.getEntity().getCapability(NecklaceCapProvider.NECKLACE_CAP).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                });
            });
            event.getOriginal().getCapability(ChakraMasteryCapProvider.CHAKRA_MASTERY_CAP).ifPresent(oldStore -> {
                event.getEntity().getCapability(ChakraMasteryCapProvider.CHAKRA_MASTERY_CAP).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                });
            });
            event.getOriginal().getCapability(AdditionalHealthCapProvider.ADD_HEALTH_CAP).ifPresent(oldStore -> {
                event.getEntity().getCapability(AdditionalHealthCapProvider.ADD_HEALTH_CAP).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                    newStore.setHealth(newStore.getMaxHealth());
                });
            });
        }
    }

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event){
        event.register(NecklaceCap.class);
        event.register(ChakraMasteryCap.class);
        //event.register(PlayerGemsCap.class);
    }
}
