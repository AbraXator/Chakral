package net.AbraXator.chakramod.items;

import net.AbraXator.chakramod.ChakraMod;
import net.AbraXator.chakramod.chakra.ChakraStrenght;
import net.AbraXator.chakramod.chakra.ChakraType;
import net.AbraXator.chakramod.items.custom.Gem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Gems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ChakraMod.MOD_ID);



    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
