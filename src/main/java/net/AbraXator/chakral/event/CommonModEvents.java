package net.AbraXator.chakral.event;

import net.AbraXator.chakral.Chakral;
import net.AbraXator.chakral.chakra.capability.NecklaceCap;
import net.AbraXator.chakral.chakra.capability.NecklaceCapProvider;
import net.AbraXator.chakral.entity.CrystalFish;
import net.AbraXator.chakral.entity.ModEntity;
import net.AbraXator.chakral.entity.MineralSnail;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.jar.Attributes;

@Mod.EventBusSubscriber(modid = Chakral.MOD_ID)
public class CommonModEvents {

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event){
        event.put(ModEntity.MINERAL_SNAIL.get(), MineralSnail.setAttributes());
        event.put(ModEntity.CRYSTAL_FISH.get(), CrystalFish.createAttributes().build());
    }

    @SubscribeEvent
    public static void attachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if(event.getObject() instanceof Player) {
            if(!event.getObject().getCapability(NecklaceCapProvider.NECKLACE).isPresent()) {
                event.addCapability(new ResourceLocation(Chakral.MOD_ID, "properties"), new NecklaceCapProvider());
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if(event.isWasDeath()) {
            event.getOriginal().getCapability(NecklaceCapProvider.NECKLACE).ifPresent(oldStore -> {
                event.getOriginal().getCapability(NecklaceCapProvider.NECKLACE).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                });
            });
        }
    }

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event){
        event.register(NecklaceCap.class);
    }
}
