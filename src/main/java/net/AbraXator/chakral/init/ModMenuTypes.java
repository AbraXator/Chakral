package net.AbraXator.chakral.init;

import net.AbraXator.chakral.Chakral;
import net.AbraXator.chakral.client.gui.chakralnexus.ChakralNexusMenu;
import net.AbraXator.chakral.client.gui.enricher.MineralEnricherMenu;
import net.AbraXator.chakral.client.gui.refiner.ShardRefinerMenu;
import net.AbraXator.chakral.client.gui.necklace.NecklaceInserterMenu;
import net.AbraXator.chakral.client.gui.necklace.NecklaceSlotterMenu;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, Chakral.MOD_ID);

    public static final RegistryObject<MenuType<NecklaceSlotterMenu>> NECKLACE_SLOTTER_MENU =
            registerMenuType(NecklaceSlotterMenu::new, "necklace_slotter_menu");

    public static final RegistryObject<MenuType<NecklaceInserterMenu>> NECKLACE_INSERTER_MENU =
            registerMenuType(NecklaceInserterMenu::new, "necklace_inserter_menu");

    public static final RegistryObject<MenuType<ShardRefinerMenu>> SHARD_REFINER_MENU =
            registerMenuType(ShardRefinerMenu::new, "shard_refiner_menu");

    public static final RegistryObject<MenuType<MineralEnricherMenu>> MINERAL_ENRICHER_MENU =
            registerMenuType(MineralEnricherMenu::new, "mineral_builder_menu");

    public static final RegistryObject<MenuType<ChakralNexusMenu>> CHAKRAL_NEXUS_MENU =
            registerMenuType(ChakralNexusMenu::new, "chakral_nexus_menu");


    private static <T extends AbstractContainerMenu>RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> factory, String name){
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }

    public static void register(IEventBus eventBus){
        MENUS.register(eventBus);
    }
}