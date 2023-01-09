package net.AbraXator.chakral.chakra;

import net.AbraXator.chakral.Chakral;
import net.AbraXator.chakral.capability.NecklaceCapProvider;
import net.AbraXator.chakral.chakra.chakras.*;
import net.AbraXator.chakral.items.ModItems;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.*;

import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;

public class Chakras {

    //public static final ResourceKey<Registry<Chakra>> CHAKRA = ResourceKey.createRegistryKey(new ResourceLocation(Chakral.MOD_ID, "chakra"));
    //public static final DeferredRegister<Chakra> CHAKRAS =
    //        DeferredRegister.create(CHAKRA, Chakral.MOD_ID);

    public static void registerEntries(){
        Chakras chakras = null;
    }

    public static final RegistryObject<Chakra> AMAZONITE =          ChakraRegistries.CHAKRA.register("amazonite", () -> new AmazoniteChakra(ModItems.AMAZONITE.get(), ChakraType.HEART));
    public static final RegistryObject<Chakra> HAG_STONE =          ChakraRegistries.CHAKRA.register("hag_stone", () -> new HagStone(ModItems.HAG_STONE.get(), ChakraType.HEART, UUID.randomUUID()));
    public static final RegistryObject<Chakra> AMETHYST_QUARTZ =    ChakraRegistries.CHAKRA.register("amethyst_quartz", () -> new AmethystQuartzChakra(ModItems.AMETHYST_QUARTZ.get(), ChakraType.HEART));
    public static final RegistryObject<Chakra> GARNET_SPESSARTINE = ChakraRegistries.CHAKRA.register("garnet_spessartine", () -> new GarnetSpessartineChakra(ModItems.GARNET_SPESSARTINE.get(), ChakraType.SACRAL));
    public static final RegistryObject<Chakra> MOON_STONE =         ChakraRegistries.CHAKRA.register("moon_stone", () -> new MoonstoneChakra(ModItems.MOON_STONE.get(), ChakraType.CROWN));


    private static void tick(TickEvent.PlayerTickEvent event){
        if(event.phase == TickEvent.Phase.END){
            ChakraRegistries.CHAKRA.getEntries().forEach(s -> {
                Chakra chakra = s.get();
                event.player.getCapability(NecklaceCapProvider.NECKLACE_CAP).ifPresent(necklaceCap -> {
                    chakra.necklace = necklaceCap.getNecklace();
                });
                if(chakra.isEnabled(chakra)){
                    chakra.tick(event.player, event.player.level);
                }
            });
        }
    }

    //public static void register(IEventBus eventBus){
    //    CHAKRAS.register(eventBus);
    //}

    public static void registerEvents(){
        MinecraftForge.EVENT_BUS.addListener(Chakras::tick);
    }
}
