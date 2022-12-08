package net.AbraXator.chakral.chakra;

import net.AbraXator.chakral.Chakral;
import net.AbraXator.chakral.chakra.chakras.AmazoniteChakra;
import net.AbraXator.chakral.items.ModItems;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryBuilder;
import net.minecraftforge.registries.RegistryObject;
import org.checkerframework.checker.units.qual.C;

import java.util.Comparator;
import java.util.function.Supplier;

public class Chakras {
    public static final DeferredRegister<Chakra> CHAKRAS = DeferredRegister
            .create(ChakraRegistries.Keys.CHAKRAS, Chakral.MOD_ID);

    public static final RegistryObject<Chakra> AMAZONITE = register("amazonite", () -> new AmazoniteChakra(ModItems.AMAZONITE.get(), ChakraType.HEART ,"fbuwauzfvb"));

    public static <T extends Chakra> RegistryObject<T> register(String name, Supplier<T> supplier){
        RegistryObject<T> reg = CHAKRAS.register(name, supplier);
        return reg;
    }

    public static void register(IEventBus eventBus){
        CHAKRAS.register(eventBus);
    }
}
