package net.AbraXator.chakral.init;

import net.AbraXator.chakral.Chakral;
import net.AbraXator.chakral.chakra.ChakraStrength;
import net.AbraXator.chakral.chakra.ChakraType;
import net.AbraXator.chakral.items.*;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.RecordItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Chakral.MOD_ID);

    public static final RegistryObject<Item> AMETHYST_QUARTZ = ITEMS.register("amethyst_quartz", () -> new ChakraItem(new Item.Properties(), ChakraType.CROWN, ChakraStrength.FAINT, ModChakras.AMETHYST_QUARTZ));
    public static final RegistryObject<Item> SUGILITE = ITEMS.register("sugilite", () -> new ChakraItem(new Item.Properties(), ChakraType.CROWN, ChakraStrength.WEAKENED, ModChakras.SUGILITE));
    public static final RegistryObject<Item> MOON_STONE = ITEMS.register("moon_stone", () -> new ChakraItem(new Item.Properties(), ChakraType.CROWN, ChakraStrength.POWERFUL, ModChakras.MOON_STONE));
    public static final RegistryObject<Item> LABBRADORITE = ITEMS.register("labbradorite", () -> new ChakraItem(new Item.Properties(), ChakraType.CROWN, ChakraStrength.ENLIGHTENED, ModChakras.LABBRADORITE));
    public static final RegistryObject<Item> HAG_STONE = ITEMS.register("hag_stone", () -> new ChakraItem(new Item.Properties(), ChakraType.THIRD_EYE, ChakraStrength.FAINT, ModChakras.HAG_STONE));
    public static final RegistryObject<Item> DUMORTIERITE = ITEMS.register("dumortierite", () -> new ChakraItem(new Item.Properties(), ChakraType.THIRD_EYE, ChakraStrength.WEAKENED, ModChakras.DUMORTIERITE));
    public static final RegistryObject<Item> LEPIDOLITE = ITEMS.register("lepidolite", () -> new ChakraItem(new Item.Properties(), ChakraType.THIRD_EYE, ChakraStrength.POWERFUL, ModChakras.LEPIDOLITE));
    public static final RegistryObject<Item> AZURITE = ITEMS.register("azurite", () -> new ChakraItem(new Item.Properties(), ChakraType.THIRD_EYE, ChakraStrength.ENLIGHTENED, ModChakras.AZURITE));
    public static final RegistryObject<Item> BLUE_LACE_AGATE = ITEMS.register("blue_lace_agate", () -> new ChakraItem(new Item.Properties(), ChakraType.THROAT, ChakraStrength.FAINT, ModChakras.BLUE_LACE_AGATE));
    public static final RegistryObject<Item> KYANITE = ITEMS.register("kyanite", () -> new ChakraItem(new Item.Properties(), ChakraType.THROAT, ChakraStrength.WEAKENED, ModChakras.KYANITE));
    public static final RegistryObject<Item> BLUE_HOWLITE = ITEMS.register("blue_howlite", () -> new ChakraItem(new Item.Properties(), ChakraType.THROAT, ChakraStrength.POWERFUL, ModChakras.BLUE_HOWLITE));
    public static final RegistryObject<Item> AQUAMARINE = ITEMS.register("aquamarine", () -> new ChakraItem(new Item.Properties(), ChakraType.THROAT, ChakraStrength.ENLIGHTENED, ModChakras.AQUAMARINE));
    public static final RegistryObject<Item> AMAZONITE = ITEMS.register("amazonite", () -> new ChakraItem(new Item.Properties(), ChakraType.HEART, ChakraStrength.FAINT, ModChakras.AMAZONITE));
    public static final RegistryObject<Item> RHODONITE = ITEMS.register("rhodonite", () -> new ChakraItem(new Item.Properties(), ChakraType.HEART, ChakraStrength.WEAKENED, ModChakras.RHODONITE));
    public static final RegistryObject<Item> MALACHITE = ITEMS.register("malachite", () -> new ChakraItem(new Item.Properties(), ChakraType.HEART, ChakraStrength.POWERFUL, ModChakras.MALACHITE));
    public static final RegistryObject<Item> GREEN_OPAL = ITEMS.register("green_opal", () -> new ChakraItem(new Item.Properties(), ChakraType.HEART, ChakraStrength.ENLIGHTENED, ModChakras.GREEN_OPAL));
    public static final RegistryObject<Item> HELIOLITE = ITEMS.register("heliolite", () -> new ChakraItem(new Item.Properties(), ChakraType.SOLAR, ChakraStrength.FAINT, ModChakras.HELIOLITE));
    public static final RegistryObject<Item> CITRINE = ITEMS.register("citrine", () -> new ChakraItem(new Item.Properties(), ChakraType.SOLAR, ChakraStrength.WEAKENED, ModChakras.CITRINE));
    public static final RegistryObject<Item> AMBER = ITEMS.register("amber", () -> new ChakraItem(new Item.Properties(), ChakraType.SOLAR, ChakraStrength.POWERFUL, ModChakras.AMBER));
    public static final RegistryObject<Item> FIRE_OPAL = ITEMS.register("fire_opal", () -> new ChakraItem(new Item.Properties(), ChakraType.SOLAR, ChakraStrength.ENLIGHTENED, ModChakras.FIRE_OPAL));
    public static final RegistryObject<Item> STILLBITE = ITEMS.register("stillbite", () -> new ChakraItem(new Item.Properties(), ChakraType.SACRAL, ChakraStrength.FAINT, ModChakras.STILLBITE));
    public static final RegistryObject<Item> CARNELIAN = ITEMS.register("carnelian", () -> new ChakraItem(new Item.Properties(), ChakraType.SACRAL, ChakraStrength.WEAKENED, ModChakras.CARNELIAN));
    public static final RegistryObject<Item> GARNET_SPESSARTINE = ITEMS.register("garnet_spessartine", () -> new ChakraItem(new Item.Properties(), ChakraType.SACRAL, ChakraStrength.POWERFUL, ModChakras.GARNET_SPESSARTINE));
    public static final RegistryObject<Item> GOLDEN_TIGERS_EYE = ITEMS.register("golden_tigers_eye", () -> new ChakraItem(new Item.Properties(), ChakraType.SACRAL, ChakraStrength.ENLIGHTENED, ModChakras.GOLDEN_TIGERS_EYE));
    public static final RegistryObject<Item> BLACK_ONYX = ITEMS.register("black_onyx", () -> new ChakraItem(new Item.Properties(), ChakraType.ROOT, ChakraStrength.FAINT, ModChakras.BLACK_ONYX));
    public static final RegistryObject<Item> MAHOGANY = ITEMS.register("mahogany", () -> new ChakraItem(new Item.Properties(), ChakraType.ROOT, ChakraStrength.WEAKENED, ModChakras.MAHOGANY));
    public static final RegistryObject<Item> HELIOTROPE = ITEMS.register("heliotrope", () -> new ChakraItem(new Item.Properties(), ChakraType.ROOT, ChakraStrength.POWERFUL, ModChakras.HELIOTROPE));
    public static final RegistryObject<Item> TOURMALINE = ITEMS.register("tourmaline", () -> new ChakraItem(new Item.Properties(), ChakraType.ROOT, ChakraStrength.ENLIGHTENED, ModChakras.TOURMALINE));

    public static final RegistryObject<Item> BLUE_SHARD = ITEMS.register("blue_shard", () ->
            new Shard(new Item.Properties(), ModBlocks.BLUE_CRYSTAL.get()));
    public static final RegistryObject<Item> LIGHT_BLUE_SHARD = ITEMS.register("light_blue_shard", () ->
            new Shard(new Item.Properties(), ModBlocks.LIGHT_BLUE_CRYSTAL.get()));
    public static final RegistryObject<Item> GREEN_SHARD = ITEMS.register("green_shard", () ->
            new Shard(new Item.Properties(), ModBlocks.GREEN_CRYSTAL.get()));
    public static final RegistryObject<Item> YELLOW_SHARD = ITEMS.register("yellow_shard", () ->
            new Shard(new Item.Properties(), ModBlocks.YELLOW_CRYSTAL.get()));
    public static final RegistryObject<Item> ORANGE_SHARD = ITEMS.register("orange_shard", () ->
            new Shard(new Item.Properties(), ModBlocks.ORANGE_CRYSTAL.get()));
    public static final RegistryObject<Item> RED_SHARD = ITEMS.register("red_shard", () ->
            new Shard(new Item.Properties(), ModBlocks.RED_CRYSTAL.get()));

    public static final RegistryObject<Item> SHARD_DUST = ITEMS.register("shard_dust", () ->
            new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_STEMSHROOM_STEM = ITEMS.register("raw_stemshroom_stem", () ->
            new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(1).saturationMod(0.3F).build())));
    public static final RegistryObject<Item> COOKED_STEMSHROOM_STEM = ITEMS.register("cooked_stemshroom_stem", () ->
            new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(5).saturationMod(0.6F).build())));

    public static final RegistryObject<Item> GOLDEN_NECKLACE = ITEMS.register("golden_necklace", () ->
            new NecklaceItem(new Item.Properties().stacksTo(1), 1));
    public static final RegistryObject<Item> DIAMOND_NECKLACE = ITEMS.register("diamond_necklace", () ->
            new NecklaceItem(new Item.Properties().stacksTo(1), 2));
    public static final RegistryObject<Item> NETHERITE_NECKLACE = ITEMS.register("netherite_necklace", () ->
            new NecklaceItem(new Item.Properties().stacksTo(1), 3));
    public static final RegistryObject<Item> RAINBOW_NECKLACE = ITEMS.register("rainbow_necklace", () ->
            new NecklaceItem(new Item.Properties().stacksTo(1),funguje👍 4));

    public static final RegistryObject<Item> WEAK_REFINER_KIT = ITEMS.register("weak_refiner_kit", () ->
            new RefinerKit(new Item.Properties(), ChakraStrength.WEAKENED));
    public static final RegistryObject<Item> POWERFUL_REFINER_KIT = ITEMS.register("powerful_refiner_kit", () ->
            new RefinerKit(new Item.Properties(), ChakraStrength.POWERFUL));
    public static final RegistryObject<Item> ENGLIGHTENED_REFINER_KIT = ITEMS.register("enlightened_refiner_kit", () ->
            new RefinerKit(new Item.Properties(), ChakraStrength.ENLIGHTENED));

    public static final RegistryObject<Item> MUSIC_DISC = ITEMS.register("music_disc", () ->
            new RecordItem(10, ModSoundEvents.SOUND1, new Item.Properties(), 3060));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
