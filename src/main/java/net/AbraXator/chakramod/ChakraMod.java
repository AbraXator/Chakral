package net.AbraXator.chakramod;

import com.mojang.logging.LogUtils;
import net.AbraXator.chakramod.blocks.ModBlocks;
import net.AbraXator.chakramod.blocks.entity.ModBlockEntities;
import net.AbraXator.chakramod.entity.ModEntity;
import net.AbraXator.chakramod.items.ModItems;
import net.AbraXator.chakramod.items.custom.Gem;
import net.AbraXator.chakramod.screen.ModMenuTypes;
import net.AbraXator.chakramod.screen.StoneBenchScreen;
import net.AbraXator.chakramod.utils.ModItemProperties;
import net.AbraXator.chakramod.world.feature.ModPlaceFeatures;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("chakramod")
public class ChakraMod {
    public static final String MOD_ID = "chakramod";
    private static final Logger LOGGER = LogUtils.getLogger();

    public ChakraMod() {
        // Register the setup method for modloading
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.           register(eventBus);
        ModBlocks.          register(eventBus);
        ModEntity.          register(eventBus);
        ModBlockEntities.   register(eventBus);
        ModMenuTypes.       register(eventBus);
        ModPlaceFeatures.   register(eventBus);

        eventBus.addListener(this::setup);
        eventBus.addListener(this::clientSetup);
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

    }

    private void clientSetup(final FMLClientSetupEvent event){
        MenuScreens.register(ModMenuTypes.STONE_BENCH_MENU.get(), StoneBenchScreen::new);
        ModItemProperties.addCustomProperties();
    }

    private void setup(final FMLCommonSetupEvent event) {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getName().toString());
    }

    public class Tab {
        public static final CreativeModeTab CHAKRA_TAB = new CreativeModeTab("chakramod") {
            @Override
            public ItemStack makeIcon() {
                return new ItemStack(ModItems.MALACHITE.get());
            }

            @Override
            public void fillItemList(NonNullList<ItemStack> pItems) {
                for(int i = 0; i < ModItems.ITEMS.getEntries().stream().toList().size(); i++){
                    pItems.add(ModItems.ITEMS.getEntries().stream().toList().get(i).get().getDefaultInstance());
                }
            }
        };
    }
}