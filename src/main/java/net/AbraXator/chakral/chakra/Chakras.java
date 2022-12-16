package net.AbraXator.chakral.chakra;

import net.AbraXator.chakral.Chakral;
import net.AbraXator.chakral.chakra.chakras.AmazoniteChakra;
import net.AbraXator.chakral.chakra.chakras.GarnetSpessartineChakra;
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
import java.util.function.Supplier;

public class Chakras {

    //public static final ResourceKey<Registry<Chakra>> CHAKRA = ResourceKey.createRegistryKey(new ResourceLocation(Chakral.MOD_ID, "chakra"));
    //public static final DeferredRegister<Chakra> CHAKRAS =
    //        DeferredRegister.create(CHAKRA, Chakral.MOD_ID);

    public static void registerEntries(){
        Chakras chakras = null;
    }

    public static final RegistryObject<Chakra> AMAZONITE = ChakraRegistries.CHAKRA.register("amazonite", () -> new AmazoniteChakra(ModItems.AMAZONITE.get(), ChakraType.HEART));
    public static final RegistryObject<Chakra> GARNET_SPESSARTINE = ChakraRegistries.CHAKRA.register("garnet_spessartine", () -> new GarnetSpessartineChakra(ModItems.GARNET_SPESSARTINE.get(), ChakraType.SACRAL));


    private static void tick(TickEvent.PlayerTickEvent event){
        if(event.phase == TickEvent.Phase.END){
            ChakraRegistries.CHAKRA.getEntries().forEach(s -> {
                Chakra chakra = s.get();
                Item item = chakra.getItem();
                List<Item> list = chakra.stones();
                boolean b = list != null && item != null && list.contains(item);
                if(b){
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
