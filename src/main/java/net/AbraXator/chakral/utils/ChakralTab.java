package net.AbraXator.chakral.utils;

import net.AbraXator.chakral.Chakral;
import net.AbraXator.chakral.init.ModBlocks;
import net.AbraXator.chakral.init.ModItems;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Chakral.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ChakralTab {
    private static final String PATH = "textures/gui/container/creative_inventory/";
    public static CreativeModeTab TAB;

    @SubscribeEvent
    public static void register(CreativeModeTabEvent.Register event){
        TAB = event.registerCreativeModeTab(
                new ResourceLocation(Chakral.MOD_ID, "chakral_tab"), builder -> builder
                        .icon(() -> new ItemStack(ModItems.GOLDEN_NECKLACE.get()))
                        .displayItems((parameters, output) -> ModItems.ITEMS.getEntries().forEach(o -> {
                            output.accept(o.get());
                            /*if(o.get().equals(ModItems.TOURMALINE.get())){
                                output.accept(Items.AMETHYST_SHARD);
                            }*/
                            if(o.get().equals(ModBlocks.SMALL_TRUE_WHITE_BUD.get().asItem())){
                                output.accept(Blocks.AMETHYST_BLOCK.asItem());
                                output.accept(Blocks.BUDDING_AMETHYST.asItem());
                            }
                        }))
                        .title(Component.translatable("chakral.chakral_tab"))
                        .hideTitle()
                        .withBackgroundLocation(new ResourceLocation(Chakral.MOD_ID,  PATH + "tab_chakral.png"))
                        .withSearchBar(88)
                        .withTabsImage(new ResourceLocation(Chakral.MOD_ID, PATH + "chakral_tabs.png"))
        );
    }
}
