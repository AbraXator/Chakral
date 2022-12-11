package net.AbraXator.chakral.chakra;

import net.AbraXator.chakral.Chakral;
import net.AbraXator.chakral.chakra.chakras.AmazoniteChakra;
import net.AbraXator.chakral.chakra.chakras.GarnetSpessartineChakra;
import net.AbraXator.chakral.items.ModItems;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class Chakras {
    public static void registerEntries(){
        Chakras chakras = null;
    }

    //public static final RegistryObject<Chakra> AMAZONITE = ChakraRegistries.CHAKRA.register("amazonite", () -> new AmazoniteChakra(ModItems.AMAZONITE.get(), ChakraType.HEART));
    public static final RegistryObject<Chakra> GARNET_SPESSARTINE = ChakraRegistries.CHAKRA.register("garnet_spessartine", () -> new GarnetSpessartineChakra(ModItems.GARNET_SPESSARTINE.get(), ChakraType.SACRAL));


    private static void tick(TickEvent.PlayerTickEvent event){
        if(event.phase == TickEvent.Phase.END){
            ChakraRegistries.CHAKRA.getEntries().forEach(s -> {
                s.get().tick(event.player, event.player.level);
            });
        }
    }

    public static void registerEvents(){
        MinecraftForge.EVENT_BUS.addListener(Chakras::tick);
    }
}
