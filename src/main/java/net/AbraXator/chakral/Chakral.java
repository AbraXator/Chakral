package net.AbraXator.chakral;

import com.mojang.logging.LogUtils;
import net.AbraXator.chakral.blocks.ModBlocks;
import net.AbraXator.chakral.blocks.entity.ModBlockEntities;
import net.AbraXator.chakral.chakra.ChakraRegistries;
import net.AbraXator.chakral.chakra.Chakras;
import net.AbraXator.chakral.entity.ModEntities;
import net.AbraXator.chakral.items.ModItems;
import net.AbraXator.chakral.networking.ModMessages;
import net.AbraXator.chakral.recipes.ModRecipes;
import net.AbraXator.chakral.screen.*;
import net.AbraXator.chakral.screen.necklace.NecklaceScreen;
import net.AbraXator.chakral.utils.ModItemProperties;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("chakral")
public class Chakral {
    public static final String MOD_ID = "chakral";
    public static final Logger LOGGER = LogUtils.getLogger();

    public Chakral() {
        // Register the setup method for modloading
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.           register(eventBus);
        ModBlocks.          register(eventBus);
        ModBlockEntities.   register(eventBus);
        ModMenuTypes.       register(eventBus);
        ModRecipes.         register(eventBus);
        ModEntities.        register(eventBus);
        //ModFeatures.        register(eventBus);
        //ModConfigFeatures.  register(eventBus);
        //ModPlacedFeature.   register(eventBus);
        register(eventBus);


        eventBus.addListener(this::setup);
        eventBus.addListener(this::clientSetup);
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

    }

    public void register(IEventBus eventBus){
        ChakraRegistries.CHAKRA.register(eventBus);
        Chakras.registerEntries();
        Chakras.registerEvents();
    }

    private void clientSetup(final FMLClientSetupEvent event){
        MenuScreens.register(ModMenuTypes.NECKLACE_SLOTTER_MENU.get(), NecklaceSlotterScreen::new);
        MenuScreens.register(ModMenuTypes.NECKLACE_INSERTER_MENU.get(), NecklaceInserterScreen::new);
        MenuScreens.register(ModMenuTypes.SHARD_REFINER_MENU.get(), ShardRefinerScreen::new);
        MenuScreens.register(ModMenuTypes.MINERAL_ENRICHER_MENU.get(), MineralEnricherScreen::new);
        MenuScreens.register(ModMenuTypes.NECKLACE_MENU.get(), NecklaceScreen::new);
        ModItemProperties.addCustomProperties();
    }

    private void setup(final FMLCommonSetupEvent event) {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getName().toString());
        event.enqueueWork(ModMessages::register);
    }
}