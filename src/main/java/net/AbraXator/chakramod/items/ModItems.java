package net.AbraXator.chakramod.items;

import net.AbraXator.chakramod.ChakraMod;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ChakraMod.MOD_ID);

    public static final RegistryObject<Item> AMAZONITE = ITEMS.register("amazonite", () ->
            new Item(new Item.Properties().tab(Tab.CHAKRA_TAB)));
    public static final RegistryObject<Item> AQUAMARINE = ITEMS.register("aquamarine", () ->
            new Item(new Item.Properties().tab(Tab.CHAKRA_TAB)));
    public static final RegistryObject<Item> BLUE_HOWLITE = ITEMS.register("blue_howlite", () ->
            new Item(new Item.Properties().tab(Tab.CHAKRA_TAB)));
    public static final RegistryObject<Item> BLUE_LACE_AGATE = ITEMS.register("blue_lace_agate", () ->
            new Item(new Item.Properties().tab(Tab.CHAKRA_TAB)));
    public static final RegistryObject<Item> GREEN_OPAL = ITEMS.register("green_opal", () ->
            new Item(new Item.Properties().tab(Tab.CHAKRA_TAB)));
    public static final RegistryObject<Item> HELIOTROPE = ITEMS.register("heliotrope", () ->
            new Item(new Item.Properties().tab(Tab.CHAKRA_TAB)));
    public static final RegistryObject<Item> KARNEOLE = ITEMS.register("karneole", () ->
            new Item(new Item.Properties().tab(Tab.CHAKRA_TAB)));
    public static final RegistryObject<Item> KYANITE = ITEMS.register("kyanite", () ->
            new Item(new Item.Properties().tab(Tab.CHAKRA_TAB)));
    public static final RegistryObject<Item> MAHOGANY = ITEMS.register("mahogany", () ->
            new Item(new Item.Properties().tab(Tab.CHAKRA_TAB)));
    public static final RegistryObject<Item> MALACHITE = ITEMS.register("malachite", () ->
            new Item(new Item.Properties().tab(Tab.CHAKRA_TAB)));
    public static final RegistryObject<Item> RHODONITE = ITEMS.register("rhodonite", () ->
            new Item(new Item.Properties().tab(Tab.CHAKRA_TAB)));
    public static final RegistryObject<Item> TOURMALINE = ITEMS.register("tourmaline", () ->
            new Item(new Item.Properties().tab(Tab.CHAKRA_TAB)));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
