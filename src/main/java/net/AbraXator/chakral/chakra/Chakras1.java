package net.AbraXator.chakral.chakra;

import net.AbraXator.chakral.chakra.chakras.AmazoniteChakra;
import net.AbraXator.chakral.items.ModItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.*;

import java.util.function.Supplier;

import static net.AbraXator.chakral.Chakral.MOD_ID;

public class Chakras1 {
    public static Supplier<IForgeRegistry<Chakra>> CHAKRA_REGISTRY;
    public static final DeferredRegister<Chakra> CHAKRAS = DeferredRegister.create(new ResourceLocation(MOD_ID, "chakras"), MOD_ID);

    //public static final RegistryObject<Chakra> AMAZONITE_CHAKRA = CHAKRAS.register("amazonite", () -> new AmazoniteChakra(ModItems.AMAZONITE.get(), ChakraType.HEART, "HJF98I"));

    public static void register(IEventBus eventBus){
        eventBus.addListener(Chakras1::newRegistry);
        CHAKRAS.register(eventBus);
    }

    private static void newRegistry(NewRegistryEvent event) {
        CHAKRA_REGISTRY = event.create(new RegistryBuilder<Chakra>().setMaxID(Integer.MAX_VALUE - 1).setName(new ResourceLocation(MOD_ID, "chakras")));
    }
}
