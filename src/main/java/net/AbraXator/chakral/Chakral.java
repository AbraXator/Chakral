package net.AbraXator.chakral;

import com.mojang.logging.LogUtils;
import net.AbraXator.chakral.blocks.ModBlocks;
import net.AbraXator.chakral.blocks.entity.ModBlockEntities;
import net.AbraXator.chakral.entity.ModEntity;
import net.AbraXator.chakral.items.ModItems;
import net.AbraXator.chakral.networking.ModMessages;
import net.AbraXator.chakral.recipes.ModRecipes;
import net.AbraXator.chakral.screen.ModMenuTypes;
import net.AbraXator.chakral.screen.ShardRefinerScreen;
import net.AbraXator.chakral.screen.NecklaceSlotterScreen;
import net.AbraXator.chakral.utils.ModItemProperties;
import net.AbraXator.chakral.world.ModConfiguredFeatures;
import net.AbraXator.chakral.world.ModPlacedFeature;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
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
    private static final Logger LOGGER = LogUtils.getLogger();

    public Chakral() {
        // Register the setup method for modloading
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.           register(eventBus);
        ModBlocks.          register(eventBus);
        ModEntity.          register(eventBus);
        ModBlockEntities.   register(eventBus);
        ModMenuTypes.       register(eventBus);
        ModRecipes.         register(eventBus);
        ModConfiguredFeatures.register(eventBus);
        ModPlacedFeature.   register(eventBus);


        eventBus.addListener(this::setup);
        eventBus.addListener(this::clientSetup);
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

    }

    private void commonSetup(final FMLClientSetupEvent event){
        event.enqueueWork(() ->{
           ModMessages.register();
        });
    }

    private void clientSetup(final FMLClientSetupEvent event){
        MenuScreens.register(ModMenuTypes.STONE_BENCH_MENU.get(), NecklaceSlotterScreen::new);
        MenuScreens.register(ModMenuTypes.SHARD_REFINER_MENU.get(), ShardRefinerScreen::new);
        ModItemProperties.addCustomProperties();
    }

    private void setup(final FMLCommonSetupEvent event) {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getName().toString());
    }

    public class Tab {
        public static final CreativeModeTab CHAKRA_TAB = new CreativeModeTab("Chakral") {
            @Override
            public ItemStack makeIcon() {
                return new ItemStack(ModItems.MALACHITE.get());
            }

            @Override
            public void fillItemList(NonNullList<ItemStack> pItems) {
                for(int i = 0; i < ModItems.ITEMS.getEntries().stream().toList().size(); i++){
                    pItems.add(ModItems.ITEMS.getEntries().stream().toList().get(i).get().getDefaultInstance());
                    //pItems.add(Gems.ITEMS.getEntries().stream().toList().get(i).get().getDefaultInstance());
                }
            }
        };
    }
}