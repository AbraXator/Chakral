package net.AbraXator.chakral;

import com.mojang.logging.LogUtils;
import net.AbraXator.chakral.blocks.ModBlocks;
import net.AbraXator.chakral.blocks.entity.ModBlockEntities;
import net.AbraXator.chakral.entity.ModEntity;
import net.AbraXator.chakral.items.ModItems;
import net.AbraXator.chakral.networking.ModMessages;
import net.AbraXator.chakral.recipes.ModRecipes;
import net.AbraXator.chakral.screen.*;
import net.AbraXator.chakral.utils.ModItemProperties;
import net.AbraXator.chakral.worldgen.ModFeatures;
import net.AbraXator.chakral.worldgen.features.ModConfigureFeatures;
import net.AbraXator.chakral.worldgen.features.ModPlacedFeature;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegistryObject;
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
        ModEntity.          register(eventBus);
        ModBlockEntities.   register(eventBus);
        ModMenuTypes.       register(eventBus);
        ModRecipes.         register(eventBus);
        ModFeatures.        register(eventBus);
        ModConfigureFeatures.register(eventBus);
        ModPlacedFeature.   register(eventBus);


        eventBus.addListener(this::setup);
        eventBus.addListener(this::clientSetup);
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

    }

    private void clientSetup(final FMLClientSetupEvent event){
        MenuScreens.register(ModMenuTypes.NECKLACE_SLOTTER_MENU.get(), NecklaceSlotterScreen::new);
        MenuScreens.register(ModMenuTypes.NECKLACE_INSERTER_MENU.get(), NecklaceInserterScreen::new);
        MenuScreens.register(ModMenuTypes.SHARD_REFINER_MENU.get(), ShardRefinerScreen::new);
        MenuScreens.register(ModMenuTypes.MINERAL_ENRICHER_MENU.get(), MineralEnricherScreen::new);
        ModItemProperties.addCustomProperties();
    }

    private void setup(final FMLCommonSetupEvent event) {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getName().toString());
        event.enqueueWork(ModMessages::register);
    }

    public class Tab {
        public static final CreativeModeTab CHAKRA_TAB = new CreativeModeTab("chakral") {
            @Override
            public ItemStack makeIcon() {
                return new ItemStack(ModItems.GOLDEN_NECKLACE.get());
            }

            @Override
            public void fillItemList(NonNullList<ItemStack> pItems) {
                for(RegistryObject<Item> item : ModItems.ITEMS.getEntries()){
                    pItems.add(item.get().getDefaultInstance());
                    if(item.get().equals(ModItems.TOURMALINE.get())){
                        pItems.add(Items.AMETHYST_SHARD.getDefaultInstance());
                    }
                    if(item.get().equals(ModBlocks.TRUE_WHITE_CRYSTAL.get().asItem())){
                        pItems.add(Blocks.AMETHYST_BLOCK.asItem().getDefaultInstance());
                        pItems.add(Blocks.BUDDING_AMETHYST.asItem().getDefaultInstance());
                    }
                }
            }
        };
    }
}