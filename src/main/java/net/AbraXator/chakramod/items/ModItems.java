package net.AbraXator.chakramod.items;

import net.AbraXator.chakramod.ChakraMod;
import net.AbraXator.chakramod.entity.ModEntity;
import net.AbraXator.chakramod.items.custom.DiamondNecklace;
import net.AbraXator.chakramod.items.custom.Gem;
import net.AbraXator.chakramod.items.custom.GoldenNecklace;
import net.AbraXator.chakramod.chakra.ChakraStrenght;
import net.AbraXator.chakramod.chakra.ChakraType;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ChakraMod.MOD_ID);

    public static final RegistryObject<Item> AMETHYST_QUARTZ = ITEMS.register("amethyst_quartz", () ->
            new Gem(new Item.Properties().tab(ChakraMod.Tab.CHAKRA_TAB), ChakraType.CROWN, "amethyst_quartz", ChakraStrenght.FAINT));
    public static final RegistryObject<Item> SUGILITE = ITEMS.register("sugilite", () ->
            new Gem(new Item.Properties().tab(ChakraMod.Tab.CHAKRA_TAB), ChakraType.CROWN, "sugilite", ChakraStrenght.WEAKENED));
    public static final RegistryObject<Item> MOON_STONE = ITEMS.register("moon_stone", () ->
            new Gem(new Item.Properties().tab(ChakraMod.Tab.CHAKRA_TAB), ChakraType.CROWN, "moon_stone", ChakraStrenght.POWERFUL));
    public static final RegistryObject<Item> LABBRADORITE = ITEMS.register("labbradorite", () ->
            new Gem(new Item.Properties().tab(ChakraMod.Tab.CHAKRA_TAB), ChakraType.CROWN, "labbradorite", ChakraStrenght.ENLIGHTENED));
    public static final RegistryObject<Item> HAG_STONE = ITEMS.register("hag_stone", () ->
            new Gem(new Item.Properties().tab(ChakraMod.Tab.CHAKRA_TAB), ChakraType.THIRD_EYE, "hag_stone", ChakraStrenght.FAINT));
    public static final RegistryObject<Item> DUMORTIERITE = ITEMS.register("dumortierite", () ->
            new Gem(new Item.Properties().tab(ChakraMod.Tab.CHAKRA_TAB), ChakraType.THIRD_EYE, "dumortierite", ChakraStrenght.WEAKENED));
    public static final RegistryObject<Item> LEPIDOLITE = ITEMS.register("lepidolite", () ->
            new Gem(new Item.Properties().tab(ChakraMod.Tab.CHAKRA_TAB), ChakraType.THIRD_EYE, "lepidolite", ChakraStrenght.POWERFUL));
    public static final RegistryObject<Item> AZURITE = ITEMS.register("azurite", () ->
            new Gem(new Item.Properties().tab(ChakraMod.Tab.CHAKRA_TAB), ChakraType.THIRD_EYE, "azurite", ChakraStrenght.ENLIGHTENED));
    public static final RegistryObject<Item> BLUE_LACE_AGATE = ITEMS.register("blue_lace_agate", () ->
            new Gem(new Item.Properties().tab(ChakraMod.Tab.CHAKRA_TAB), ChakraType.THROAT, "blue_lace_agate", ChakraStrenght.FAINT));
    public static final RegistryObject<Item> KYANITE = ITEMS.register("kyanite", () ->
            new Gem(new Item.Properties().tab(ChakraMod.Tab.CHAKRA_TAB), ChakraType.THROAT, "kyanite", ChakraStrenght.WEAKENED));
    public static final RegistryObject<Item> BLUE_HOWLITE = ITEMS.register("blue_howlite", () ->
            new Gem(new Item.Properties().tab(ChakraMod.Tab.CHAKRA_TAB), ChakraType.THROAT, "blue_howlite", ChakraStrenght.POWERFUL));
    public static final RegistryObject<Item> AQUAMARINE = ITEMS.register("aquamarine", () ->
            new Gem(new Item.Properties().tab(ChakraMod.Tab.CHAKRA_TAB), ChakraType.THROAT, "aquamarine", ChakraStrenght.ENLIGHTENED));
    public static final RegistryObject<Item> AMAZONITE = ITEMS.register("amazonite", () ->
            new Gem(new Item.Properties().tab(ChakraMod.Tab.CHAKRA_TAB), ChakraType.HEART, "amazonite", ChakraStrenght.FAINT));
    public static final RegistryObject<Item> RHODONITE = ITEMS.register("rhodonite", () ->
            new Gem(new Item.Properties().tab(ChakraMod.Tab.CHAKRA_TAB), ChakraType.HEART, "rhodonite", ChakraStrenght.WEAKENED));
    public static final RegistryObject<Item> MALACHITE = ITEMS.register("malachite", () ->
            new Gem(new Item.Properties().tab(ChakraMod.Tab.CHAKRA_TAB), ChakraType.HEART, "malachite", ChakraStrenght.POWERFUL));
    public static final RegistryObject<Item> GREEN_OPAL = ITEMS.register("green_opal", () ->
            new Gem(new Item.Properties().tab(ChakraMod.Tab.CHAKRA_TAB), ChakraType.HEART, "green_opal", ChakraStrenght.ENLIGHTENED));
    public static final RegistryObject<Item> HELIOLITE = ITEMS.register("heliolite", () ->
            new Gem(new Item.Properties().tab(ChakraMod.Tab.CHAKRA_TAB), ChakraType.SOLAR, "heliolite", ChakraStrenght.FAINT));
    public static final RegistryObject<Item> CITRINE = ITEMS.register("citrine", () ->
            new Gem(new Item.Properties().tab(ChakraMod.Tab.CHAKRA_TAB), ChakraType.SOLAR, "citrine", ChakraStrenght.WEAKENED));
    public static final RegistryObject<Item> AMBER = ITEMS.register("amber", () ->
            new Gem(new Item.Properties().tab(ChakraMod.Tab.CHAKRA_TAB), ChakraType.SOLAR, "amber", ChakraStrenght.POWERFUL));
    public static final RegistryObject<Item> FIRE_OPAL = ITEMS.register("fire_opal", () ->
            new Gem(new Item.Properties().tab(ChakraMod.Tab.CHAKRA_TAB), ChakraType.SOLAR, "fire_opal", ChakraStrenght.ENLIGHTENED));
    public static final RegistryObject<Item> STILLBITE = ITEMS.register("stillbite", () ->
            new Gem(new Item.Properties().tab(ChakraMod.Tab.CHAKRA_TAB), ChakraType.SACRAL, "stillbite", ChakraStrenght.FAINT));
    public static final RegistryObject<Item> CARNELIAN = ITEMS.register("carnelian", () ->
            new Gem(new Item.Properties().tab(ChakraMod.Tab.CHAKRA_TAB), ChakraType.SACRAL, "carnelian", ChakraStrenght.WEAKENED));
    public static final RegistryObject<Item> GARNET_SPESSARTINE = ITEMS.register("garnet_spessartine", () ->
            new Gem(new Item.Properties().tab(ChakraMod.Tab.CHAKRA_TAB), ChakraType.SACRAL, "garnet_spessartine", ChakraStrenght.POWERFUL));
    public static final RegistryObject<Item> GOLDEN_TIGERS_EYE = ITEMS.register("golden_tigers_eye", () ->
            new Gem(new Item.Properties().tab(ChakraMod.Tab.CHAKRA_TAB), ChakraType.SACRAL, "golden_tigers_eye", ChakraStrenght.ENLIGHTENED));
    public static final RegistryObject<Item> BLACK_ONYX = ITEMS.register("black_onyx", () ->
            new Gem(new Item.Properties().tab(ChakraMod.Tab.CHAKRA_TAB), ChakraType.ROOT, "black_onyx", ChakraStrenght.FAINT));
    public static final RegistryObject<Item> MAHOGANY = ITEMS.register("mahogany", () ->
            new Gem(new Item.Properties().tab(ChakraMod.Tab.CHAKRA_TAB), ChakraType.ROOT, "mahogany", ChakraStrenght.WEAKENED));
    public static final RegistryObject<Item> HELIOTROPE = ITEMS.register("heliotrope", () ->
            new Gem(new Item.Properties().tab(ChakraMod.Tab.CHAKRA_TAB), ChakraType.ROOT, "heliotrope", ChakraStrenght.POWERFUL));
    public static final RegistryObject<Item> TOURMALINE = ITEMS.register("tourmaline", () ->
            new Gem(new Item.Properties().tab(ChakraMod.Tab.CHAKRA_TAB), ChakraType.ROOT, "tourmaline", ChakraStrenght.ENLIGHTENED));

    public static final RegistryObject<Item> BLUE_SHARD = ITEMS.register("blue_shard", () ->
            new Item(new Item.Properties().tab(ChakraMod.Tab.CHAKRA_TAB)));
    public static final RegistryObject<Item> LIGHT_BLUE_SHARD = ITEMS.register("light_blue_shard", () ->
            new Item(new Item.Properties().tab(ChakraMod.Tab.CHAKRA_TAB)));
    public static final RegistryObject<Item> GREEN_SHARD = ITEMS.register("green_shard", () ->
            new Item(new Item.Properties().tab(ChakraMod.Tab.CHAKRA_TAB)));
    public static final RegistryObject<Item> YELLOW_SHARD = ITEMS.register("yellow_shard", () ->
            new Item(new Item.Properties().tab(ChakraMod.Tab.CHAKRA_TAB)));
    public static final RegistryObject<Item> ORANGE_SHARD = ITEMS.register("orange_shard", () ->
            new Item(new Item.Properties().tab(ChakraMod.Tab.CHAKRA_TAB)));
    public static final RegistryObject<Item> RED_SHARD = ITEMS.register("red_shard", () ->
            new Item(new Item.Properties().tab(ChakraMod.Tab.CHAKRA_TAB)));

    public static final RegistryObject<Item> SHARD_DUST = ITEMS.register("shard_dust", () ->
            new Item(new Item.Properties().tab(ChakraMod.Tab.CHAKRA_TAB)));

    public static final RegistryObject<Item> GOLDEN_NECKLACE = ITEMS.register("golden_necklace", () ->
            new GoldenNecklace(new Item.Properties().tab(ChakraMod.Tab.CHAKRA_TAB).stacksTo(1)));
    public static final RegistryObject<Item> DIAMOND_NECKLACE = ITEMS.register("diamond_necklace", () ->
            new DiamondNecklace(new Item.Properties().tab(ChakraMod.Tab.CHAKRA_TAB)));
    public static final RegistryObject<Item> NETHERITE_NECKLACE = ITEMS.register("netherite_necklace", () ->
            new Item(new Item.Properties().tab(ChakraMod.Tab.CHAKRA_TAB)));
    public static final RegistryObject<Item> RAINBOW_NECKLACE = ITEMS.register("rainbow_necklace", () ->
            new Item(new Item.Properties().tab(ChakraMod.Tab.CHAKRA_TAB)));

    public static final RegistryObject<MobBucketItem> CRYSTAL_FISH_BUCKET = ITEMS.register("crystal_fish_bucket", () ->
           new MobBucketItem(ModEntity.CRYSTAL_FISH, () -> Fluids.WATER, () -> SoundEvents.BUCKET_EMPTY_FISH,
                   new Item.Properties().tab(ChakraMod.Tab.CHAKRA_TAB).stacksTo(1)));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
