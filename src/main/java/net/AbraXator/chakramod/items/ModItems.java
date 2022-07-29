package net.AbraXator.chakramod.items;

import net.AbraXator.chakramod.ChakraMod;
import net.AbraXator.chakramod.entity.ModEntity;
import net.AbraXator.chakramod.items.custom.GoldenNecklace;
import net.AbraXator.chakramod.items.custom.TestItem;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ChakraMod.MOD_ID);

    public static final RegistryObject<Item> AMAZONITE = ITEMS.register("amazonite", () ->
            new Item(new Item.Properties().tab(ChakraMod.Tab.CHAKRA_TAB)));
    public static final RegistryObject<Item> AMBER = ITEMS.register("amber", () ->
            new Item(new Item.Properties().tab(ChakraMod.Tab.CHAKRA_TAB)));
    public static final RegistryObject<Item> AQUAMARINE = ITEMS.register("aquamarine", () ->
            new Item(new Item.Properties().tab(ChakraMod.Tab.CHAKRA_TAB)));
    public static final RegistryObject<Item> AZURITE = ITEMS.register("azurite", () ->
            new Item(new Item.Properties().tab(ChakraMod.Tab.CHAKRA_TAB)));
    public static final RegistryObject<Item> BLACK_OBSIDIAN = ITEMS.register("black_obsidian", () ->
            new Item(new Item.Properties().tab(ChakraMod.Tab.CHAKRA_TAB)));
    public static final RegistryObject<Item> BLUE_HOWLITE = ITEMS.register("blue_howlite", () ->
            new Item(new Item.Properties().tab(ChakraMod.Tab.CHAKRA_TAB)));
    public static final RegistryObject<Item> BLUE_LACE_AGATE = ITEMS.register("blue_lace_agate", () ->
            new Item(new Item.Properties().tab(ChakraMod.Tab.CHAKRA_TAB)));
    public static final RegistryObject<Item> CARNELIAN = ITEMS.register("carnelian", () ->
            new Item(new Item.Properties().tab(ChakraMod.Tab.CHAKRA_TAB)));
    public static final RegistryObject<Item> CITRINE = ITEMS.register("citrine", () ->
            new Item(new Item.Properties().tab(ChakraMod.Tab.CHAKRA_TAB)));
    public static final RegistryObject<Item> DUMORTIERITE = ITEMS.register("dumortierite", () ->
            new Item(new Item.Properties().tab(ChakraMod.Tab.CHAKRA_TAB)));
    public static final RegistryObject<Item> FIRE_OPAL = ITEMS.register("fire_opal", () ->
            new Item(new Item.Properties().tab(ChakraMod.Tab.CHAKRA_TAB)));
    public static final RegistryObject<Item> GARNET_SPESSARTINE = ITEMS.register("garnet_spessartine", () ->
            new Item(new Item.Properties().tab(ChakraMod.Tab.CHAKRA_TAB)));
    public static final RegistryObject<Item> GOLDEN_TIGERS_EYE = ITEMS.register("golden_tiger_eye", () ->
            new Item(new Item.Properties().tab(ChakraMod.Tab.CHAKRA_TAB)));
    public static final RegistryObject<Item> GREEN_OPAL = ITEMS.register("green_opal", () ->
            new Item(new Item.Properties().tab(ChakraMod.Tab.CHAKRA_TAB)));
    public static final RegistryObject<Item> HELIOLITE = ITEMS.register("heliolite", () ->
            new Item(new Item.Properties().tab(ChakraMod.Tab.CHAKRA_TAB)));
    public static final RegistryObject<Item> HELIOTROPE = ITEMS.register("heliotrope", () ->
            new Item(new Item.Properties().tab(ChakraMod.Tab.CHAKRA_TAB)));
    public static final RegistryObject<Item> KYANITE = ITEMS.register("kyanite", () ->
            new Item(new Item.Properties().tab(ChakraMod.Tab.CHAKRA_TAB)));
    public static final RegistryObject<Item> LABBRADORITE = ITEMS.register("labbradorite", () ->
            new Item(new Item.Properties().tab(ChakraMod.Tab.CHAKRA_TAB)));
    public static final RegistryObject<Item> MAHOGANY = ITEMS.register("mahogany", () ->
            new Item(new Item.Properties().tab(ChakraMod.Tab.CHAKRA_TAB)));
    public static final RegistryObject<Item> MALACHITE = ITEMS.register("malachite", () ->
            new Item(new Item.Properties().tab(ChakraMod.Tab.CHAKRA_TAB)));
    public static final RegistryObject<Item> MOON_STONE = ITEMS.register("moon_stone", () ->
            new Item(new Item.Properties().tab(ChakraMod.Tab.CHAKRA_TAB)));
    public static final RegistryObject<Item> RHODONITE = ITEMS.register("rhodonite", () ->
            new Item(new Item.Properties().tab(ChakraMod.Tab.CHAKRA_TAB)));
    public static final RegistryObject<Item> SAPPHIRE = ITEMS.register("sapphire", () ->
            new Item(new Item.Properties().tab(ChakraMod.Tab.CHAKRA_TAB)));
    public static final RegistryObject<Item> STILLBITE = ITEMS.register("stillbite", () ->
            new Item(new Item.Properties().tab(ChakraMod.Tab.CHAKRA_TAB)));
    public static final RegistryObject<Item> TIGER_EYE = ITEMS.register("tiger_eye", () ->
            new Item(new Item.Properties().tab(ChakraMod.Tab.CHAKRA_TAB)));
    public static final RegistryObject<Item> TOURMALINE = ITEMS.register("tourmaline", () ->
            new Item(new Item.Properties().tab(ChakraMod.Tab.CHAKRA_TAB)));

    public static final RegistryObject<Item> GOLDEN_NECKLACE = ITEMS.register("golden_necklace", () ->
            new GoldenNecklace(new Item.Properties().tab(ChakraMod.Tab.CHAKRA_TAB).stacksTo(1)));
    public static final RegistryObject<Item> DIAMOND_NECKLACE = ITEMS.register("diamond_necklace", () ->
            new Item(new Item.Properties().tab(ChakraMod.Tab.CHAKRA_TAB)));
    public static final RegistryObject<Item> NETHERITE_NECKLACE = ITEMS.register("netherite_necklace", () ->
            new Item(new Item.Properties().tab(ChakraMod.Tab.CHAKRA_TAB)));
    public static final RegistryObject<Item> RAINBOW_NECKLACE = ITEMS.register("rainbow_necklace", () ->
            new Item(new Item.Properties().tab(ChakraMod.Tab.CHAKRA_TAB)));
    public static final RegistryObject<Item> TEST_ITEM = ITEMS.register("test_item", () ->
            new TestItem(new Item.Properties().tab(ChakraMod.Tab.CHAKRA_TAB)));

    public static final RegistryObject<MobBucketItem> CRYSTAL_FISH_BUCKET = ITEMS.register("crystal_fish_bucket", () ->
           new MobBucketItem(ModEntity.CRYSTAL_FISH, () -> Fluids.WATER, () -> SoundEvents.BUCKET_EMPTY_FISH,
                   new Item.Properties().tab(ChakraMod.Tab.CHAKRA_TAB).stacksTo(1)));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
