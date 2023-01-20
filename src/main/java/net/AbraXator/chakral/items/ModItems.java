package net.AbraXator.chakral.items;

import net.AbraXator.chakral.Chakral;
import net.AbraXator.chakral.chakra.ChakraStrength;
import net.AbraXator.chakral.chakra.Chakras;
import net.AbraXator.chakral.items.custom.*;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Chakral.MOD_ID);

    public static final RegistryObject<Item> AMETHYST_QUARTZ = ITEMS.register("amethyst_quartz", () -> new Gem(new Item.Properties()));
    public static final RegistryObject<Item> SUGILITE = ITEMS.register("sugilite", () -> new Gem(new Item.Properties()));
    public static final RegistryObject<Item> MOON_STONE = ITEMS.register("moon_stone", () -> new Gem(new Item.Properties()));
    public static final RegistryObject<Item> LABBRADORITE = ITEMS.register("labbradorite", () -> new Gem(new Item.Properties()));
    public static final RegistryObject<Item> HAG_STONE = ITEMS.register("hag_stone", () -> new Gem(new Item.Properties()));
    public static final RegistryObject<Item> DUMORTIERITE = ITEMS.register("dumortierite", () -> new Gem(new Item.Properties()));
    public static final RegistryObject<Item> LEPIDOLITE = ITEMS.register("lepidolite", () -> new Gem(new Item.Properties()));
    public static final RegistryObject<Item> AZURITE = ITEMS.register("azurite", () -> new Gem(new Item.Properties()));
    public static final RegistryObject<Item> BLUE_LACE_AGATE = ITEMS.register("blue_lace_agate", () -> new Gem(new Item.Properties()));
    public static final RegistryObject<Item> KYANITE = ITEMS.register("kyanite", () -> new Gem(new Item.Properties()));
    public static final RegistryObject<Item> BLUE_HOWLITE = ITEMS.register("blue_howlite", () -> new Gem(new Item.Properties()));
    public static final RegistryObject<Item> AQUAMARINE = ITEMS.register("aquamarine", () -> new Gem(new Item.Properties()));
    public static final RegistryObject<Item> AMAZONITE = ITEMS.register("amazonite", () -> new Gem(new Item.Properties()));
    public static final RegistryObject<Item> RHODONITE = ITEMS.register("rhodonite", () -> new Gem(new Item.Properties()));
    public static final RegistryObject<Item> MALACHITE = ITEMS.register("malachite", () -> new Gem(new Item.Properties()));
    public static final RegistryObject<Item> GREEN_OPAL = ITEMS.register("green_opal", () -> new Gem(new Item.Properties()));
    public static final RegistryObject<Item> HELIOLITE = ITEMS.register("heliolite", () -> new Gem(new Item.Properties()));
    public static final RegistryObject<Item> CITRINE = ITEMS.register("citrine", () -> new Gem(new Item.Properties()));
    public static final RegistryObject<Item> AMBER = ITEMS.register("amber", () -> new Gem(new Item.Properties()));
    public static final RegistryObject<Item> FIRE_OPAL = ITEMS.register("fire_opal", () -> new Gem(new Item.Properties()));
    public static final RegistryObject<Item> STILLBITE = ITEMS.register("stillbite", () -> new Gem(new Item.Properties()));
    public static final RegistryObject<Item> CARNELIAN = ITEMS.register("carnelian", () -> new Gem(new Item.Properties()));
    public static final RegistryObject<Item> GARNET_SPESSARTINE = ITEMS.register("garnet_spessartine", () -> new Gem(new Item.Properties()));
    public static final RegistryObject<Item> GOLDEN_TIGERS_EYE = ITEMS.register("golden_tigers_eye", () -> new Gem(new Item.Properties()));
    public static final RegistryObject<Item> BLACK_ONYX = ITEMS.register("black_onyx", () -> new Gem(new Item.Properties()));
    public static final RegistryObject<Item> MAHOGANY = ITEMS.register("mahogany", () -> new Gem(new Item.Properties()));
    public static final RegistryObject<Item> HELIOTROPE = ITEMS.register("heliotrope", () -> new Gem(new Item.Properties()));
    public static final RegistryObject<Item> TOURMALINE = ITEMS.register("tourmaline", () -> new Gem(new Item.Properties()));

    public static final RegistryObject<Item> BLUE_SHARD = ITEMS.register("blue_shard", () ->
            new Item(new Item.Properties()));
    public static final RegistryObject<Item> LIGHT_BLUE_SHARD = ITEMS.register("light_blue_shard", () ->
            new Item(new Item.Properties()));
    public static final RegistryObject<Item> GREEN_SHARD = ITEMS.register("green_shard", () ->
            new Item(new Item.Properties()));
    public static final RegistryObject<Item> YELLOW_SHARD = ITEMS.register("yellow_shard", () ->
            new Item(new Item.Properties()));
    public static final RegistryObject<Item> ORANGE_SHARD = ITEMS.register("orange_shard", () ->
            new Item(new Item.Properties()));
    public static final RegistryObject<Item> RED_SHARD = ITEMS.register("red_shard", () ->
            new Item(new Item.Properties()));

    public static final RegistryObject<Item> SHARD_DUST = ITEMS.register("shard_dust", () ->
            new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_STEMSHROOM_STEM = ITEMS.register("raw_stemshroom_stem", () ->
            new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(1).saturationMod(0.3F).build())));
    public static final RegistryObject<Item> COOKED_STEMSHROOM_STEM = ITEMS.register("cooked_stemshroom_stem", () ->
            new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(5).saturationMod(0.6F).build())));

    public static final RegistryObject<Item> GOLDEN_NECKLACE = ITEMS.register("golden_necklace", () ->
            new GoldenNecklace(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> DIAMOND_NECKLACE = ITEMS.register("diamond_necklace", () ->
            new DiamondNecklace(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> NETHERITE_NECKLACE = ITEMS.register("netherite_necklace", () ->
            new NetheriteNecklace(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> RAINBOW_NECKLACE = ITEMS.register("rainbow_necklace", () ->
            new RainbowNecklace(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> WEAK_REFINER_KIT = ITEMS.register("weak_refiner_kit", () ->
            new RefinerKit(new Item.Properties(), ChakraStrength.WEAKENED));
    public static final RegistryObject<Item> POWERFUL_REFINER_KIT = ITEMS.register("powerful_refiner_kit", () ->
            new RefinerKit(new Item.Properties(), ChakraStrength.POWERFUL));
    public static final RegistryObject<Item> ENGLIGHTENED_REFINER_KIT = ITEMS.register("enlightened_refiner_kit", () ->
            new RefinerKit(new Item.Properties(), ChakraStrength.ENLIGHTENED));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
