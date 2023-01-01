package net.AbraXator.chakral.screen;

import net.AbraXator.chakral.Chakral;
import net.AbraXator.chakral.blocks.custom.NecklaceInserterBlock;
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

    private static <T extends AbstractContainerMenu>RegistryObject<MenuType<T>>
    registerMenuType(IContainerFactory<T> factory, String name){
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }

    public static void register(IEventBus eventBus){
        MENUS.register(eventBus);
    }
}