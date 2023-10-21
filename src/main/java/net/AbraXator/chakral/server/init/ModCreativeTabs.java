package net.AbraXator.chakral.server.init;

import net.AbraXator.chakral.Chakral;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = Chakral.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(
            BuiltInRegistries.CREATIVE_MODE_TAB.key(), Chakral.MOD_ID);

    private static final String PATH = "textures/gui/container/creative_inventory/";
    public static CreativeModeTab TAB;

    public static final RegistryObject<CreativeModeTab> CHAKRAL_TAB = TABS.register("chakral_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("chakral.chakral_tab"))
            .icon(() -> ModItems.GOLDEN_NECKLACE.get().getDefaultInstance())
            .displayItems((parameters, output) -> ModItems.ITEMS.getEntries().forEach(itemRegistryObject -> {
                output.accept(itemRegistryObject.get());
                if(itemRegistryObject.get().equals(ModBlocks.SMALL_TRUE_WHITE_BUD.get().asItem())){
                    output.accept(Blocks.AMETHYST_BLOCK.asItem());
                    output.accept(Blocks.BUDDING_AMETHYST.asItem());
                }
            }))
            .hideTitle()
            .withBackgroundLocation(new ResourceLocation(Chakral.MOD_ID,  PATH + "tab_chakral.png"))
            .withTabsImage(new ResourceLocation(Chakral.MOD_ID, PATH + "chakral_tabs.png"))
            .withSearchBar(88)
            .build());

    public static void register(IEventBus eventBus){
        TABS.register(eventBus);
    }
}
