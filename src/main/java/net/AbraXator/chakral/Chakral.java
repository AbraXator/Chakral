package net.AbraXator.chakral;

import com.mojang.logging.LogUtils;
import net.AbraXator.chakral.client.gui.chakralnexus.ChakralNexusScreen;
import net.AbraXator.chakral.client.gui.enricher.MineralEnricherScreen;
import net.AbraXator.chakral.client.gui.necklace.NecklaceInserterScreen;
import net.AbraXator.chakral.client.gui.necklace.NecklaceSlotterScreen;
import net.AbraXator.chakral.client.gui.refiner.ShardRefinerScreen;
import net.AbraXator.chakral.config.ChakralClientConfig;
import net.AbraXator.chakral.init.*;
import net.AbraXator.chakral.networking.ModMessages;
import net.AbraXator.chakral.recipes.ModRecipes;
import net.AbraXator.chakral.utils.ChakralLocation;
import net.AbraXator.chakral.utils.ModItemProperties;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod("chakral")
public class Chakral {
    public static final String MOD_ID = "chakral";
    public static final Logger LOGGER = LogUtils.getLogger();

    public Chakral() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.           register(eventBus);
        ModBlocks.          register(eventBus);
        ModBlockEntities.   register(eventBus);
        ModMenuTypes.       register(eventBus);
        ModRecipes.         register(eventBus);
        ModEntities.        register(eventBus);
        ModParticles.       register(eventBus);
        ModChakras.         register(eventBus);
        ModSoundEvents.     register(eventBus);
        ModBiomes.          register(eventBus);
        ModFeatures.        register(eventBus);
        ModGameEvents.      register(eventBus);
        ModCreativeTabs.    register(eventBus);

        eventBus.addListener(this::setup);
        eventBus.addListener(this::clientSetup);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ChakralClientConfig.CLIENT_SPEC);
        MinecraftForge.EVENT_BUS.register(this);
    }

    public void clientSetup(final FMLClientSetupEvent event){
        MenuScreens.register(ModMenuTypes.NECKLACE_SLOTTER_MENU.get(), NecklaceSlotterScreen::new);
        MenuScreens.register(ModMenuTypes.NECKLACE_INSERTER_MENU.get(), NecklaceInserterScreen::new);
        MenuScreens.register(ModMenuTypes.SHARD_REFINER_MENU.get(), ShardRefinerScreen::new);
        MenuScreens.register(ModMenuTypes.MINERAL_ENRICHER_MENU.get(), MineralEnricherScreen::new);
        MenuScreens.register(ModMenuTypes.CHAKRAL_NEXUS_MENU.get(), ChakralNexusScreen::new);
        ModItemProperties.addCustomProperties();
    }

    private void setup(final FMLCommonSetupEvent event) {
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getName());
        event.enqueueWork(() -> {
            ModMessages.register();
            VanillaCompat.register();
            ModBiomes.setupBiomes();
        });
    }

    public static <T> ResourceKey<T> createKey(ResourceKey<Registry<T>> registry, String key){
        return ResourceKey.create(registry, new ChakralLocation(key));
    }
}