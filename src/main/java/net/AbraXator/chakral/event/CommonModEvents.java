package net.AbraXator.chakral.event;

import net.AbraXator.chakral.Chakral;
import net.AbraXator.chakral.capability.NecklaceCap;
import net.AbraXator.chakral.capability.NecklaceCapProvider;
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
                event.addCapability(new ResourceLocation(Chakral.MOD_ID, "properties"), new NecklaceCapProvider());
                //event.addCapability(new ResourceLocation(Chakral.MOD_ID, "properties"), new PlayerGemsCapProvider());
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if(event.isWasDeath()) {
            event.getOriginal().getCapability(NecklaceCapProvider.NECKLACE_CAP).ifPresent(oldStore -> {
                event.getOriginal().getCapability(NecklaceCapProvider.NECKLACE_CAP).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                });
            });
           //event.getOriginal().getCapability(PlayerGemsCapProvider.GEMS_CAP).ifPresent(oldStore -> {
           //    event.getOriginal().getCapability(PlayerGemsCapProvider.GEMS_CAP).ifPresent(newStore -> {
           //        newStore.copyFrom(oldStore);
           //    });
           //});
        }
    }

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event){
        event.register(NecklaceCap.class);
        //event.register(PlayerGemsCap.class);
    }
}
