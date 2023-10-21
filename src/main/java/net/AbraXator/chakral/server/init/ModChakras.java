package net.AbraXator.chakral.server.init;

import net.AbraXator.chakral.server.chakra.Chakra;
import net.AbraXator.chakral.server.chakra.ChakraStrength;
import net.AbraXator.chakral.server.chakra.ChakraType;
import net.AbraXator.chakral.server.chakra.chakras.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;

import static net.AbraXator.chakral.server.chakra.ChakraRegistry.CHAKRAS;

public class ModChakras {
    public static void registerEntries(){
        ModChakras chakras = null;
    }

    public static final RegistryObject<Chakra> AMETHYST_QUARTZ = CHAKRAS.register("amethyst_quartz", () -> new AmethystQuartzChakra(ChakraType.CROWN, ChakraStrength.FAINT));
    public static final RegistryObject<Chakra> SUGILITE = CHAKRAS.register("sugilite", () -> new SugiliteChakra(ChakraType.CROWN, ChakraStrength.WEAKENED));
    public static final RegistryObject<Chakra> MOON_STONE = CHAKRAS.register("moon_stone", () -> new MoonstoneChakra(ChakraType.CROWN, ChakraStrength.POWERFUL));
    public static final RegistryObject<Chakra> LABBRADORITE = CHAKRAS.register("labbradorite", () -> new LabbradoriteChakra(ChakraType.CROWN, ChakraStrength.ENLIGHTENED));
    public static final RegistryObject<Chakra> HAG_STONE = CHAKRAS.register("hag_stone", () -> new HagStoneChakra(ChakraType.THIRD_EYE, ChakraStrength.FAINT));
    public static final RegistryObject<Chakra> DUMORTIERITE = CHAKRAS.register("dumortierite", () -> new DumortieriteChakra(ChakraType.THIRD_EYE, ChakraStrength.WEAKENED));
    public static final RegistryObject<Chakra> LEPIDOLITE = CHAKRAS.register("lepidolite", () -> new LepidoliteChakra(ChakraType.THIRD_EYE, ChakraStrength.POWERFUL));
    public static final RegistryObject<Chakra> AZURITE = CHAKRAS.register("azurite", () -> new AzuriteChakra(ChakraType.THIRD_EYE, ChakraStrength.ENLIGHTENED));
    public static final RegistryObject<Chakra> BLUE_LACE_AGATE = CHAKRAS.register("blue_lace_agate", () -> new BlueLaceAgateChakra(ChakraType.THROAT, ChakraStrength.FAINT));
    public static final RegistryObject<Chakra> KYANITE = CHAKRAS.register("kyanite", () -> new KyaniteChakra(ChakraType.THROAT, ChakraStrength.WEAKENED));
    public static final RegistryObject<Chakra> BLUE_HOWLITE = CHAKRAS.register("blue_howlite", () -> new BlueHowliteChakra(ChakraType.THROAT, ChakraStrength.POWERFUL));
    public static final RegistryObject<Chakra> AQUAMARINE = CHAKRAS.register("aquamarine", () -> new AquamarineChakra(ChakraType.THROAT, ChakraStrength.ENLIGHTENED));
    public static final RegistryObject<Chakra> AMAZONITE = CHAKRAS.register("amazonite", () -> new AmazoniteChakra(ChakraType.HEART, ChakraStrength.FAINT));
    public static final RegistryObject<Chakra> RHODONITE = CHAKRAS.register("rhodonite", () -> new RhodoniteChakra(ChakraType.HEART, ChakraStrength.WEAKENED));
    public static final RegistryObject<Chakra> MALACHITE = CHAKRAS.register("malachite", () -> new MalachiteChakra(ChakraType.HEART, ChakraStrength.POWERFUL));
    public static final RegistryObject<Chakra> GREEN_OPAL = CHAKRAS.register("green_opal", () -> new GreenOpalChakra(ChakraType.HEART, ChakraStrength.ENLIGHTENED));
    public static final RegistryObject<Chakra> HELIOLITE = CHAKRAS.register("heliolite", () -> new HelioliteChakra(ChakraType.SOLAR, ChakraStrength.FAINT));
    public static final RegistryObject<Chakra> CITRINE = CHAKRAS.register("citrine", () -> new CitrineChakra(ChakraType.SOLAR, ChakraStrength.WEAKENED));
    public static final RegistryObject<Chakra> AMBER = CHAKRAS.register("amber", () -> new AmberChakra(ChakraType.SOLAR, ChakraStrength.POWERFUL));
    public static final RegistryObject<Chakra> FIRE_OPAL = CHAKRAS.register("fire_opal", () -> new FireOpalChakra(ChakraType.SOLAR, ChakraStrength.ENLIGHTENED));
    public static final RegistryObject<Chakra> STILLBITE = CHAKRAS.register("stillbite", () -> new StillbiteChakra(ChakraType.SACRAL, ChakraStrength.FAINT));
    public static final RegistryObject<Chakra> CARNELIAN = CHAKRAS.register("carnelian", () -> new CarnelianChakra(ChakraType.SACRAL, ChakraStrength.WEAKENED));
    public static final RegistryObject<Chakra> GARNET_SPESSARTINE = CHAKRAS.register("garnet_spessartine", () -> new GarnetSpessartineChakra(ChakraType.SACRAL, ChakraStrength.POWERFUL));
    public static final RegistryObject<Chakra> GOLDEN_TIGERS_EYE = CHAKRAS.register("golden_tigers_eye", () -> new GoldenTigersEyeChakra(ChakraType.SACRAL, ChakraStrength.ENLIGHTENED));
    public static final RegistryObject<Chakra> BLACK_ONYX = CHAKRAS.register("black_onyx", () -> new BlackOnyxChakra(ChakraType.ROOT, ChakraStrength.FAINT));
    public static final RegistryObject<Chakra> MAHOGANY = CHAKRAS.register("mahogany", () -> new MahoganyChakra(ChakraType.ROOT, ChakraStrength.WEAKENED));
    public static final RegistryObject<Chakra> HELIOTROPE = CHAKRAS.register("heliotrope", () -> new HeliotropeChakra(ChakraType.ROOT, ChakraStrength.POWERFUL));
    public static final RegistryObject<Chakra> TOURMALINE = CHAKRAS.register("tourmaline", () -> new TourmalineChakra(ChakraType.ROOT, ChakraStrength.ENLIGHTENED));

    public static void register(IEventBus eventBus) {
        ModChakras chakras = null;
        CHAKRAS.register(eventBus);
    }
}
