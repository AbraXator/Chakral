package net.AbraXator.chakral.chakra;

import net.AbraXator.chakral.chakra.chakras.*;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.registries.RegistryObject;

import java.util.UUID;

public class Chakras {
    public static void registerEntries(){
        Chakras chakras = null;
    }

    //CROWN
    public static final RegistryObject<Chakra> AMETHYST_QUARTZ_CHAKRA =    ChakraRegistry.CHAKRA.register("amethyst_quartz_chakra", AmethystQuartzChakra::new);
    public static final RegistryObject<Chakra> SUGILITE_CHAKRA =           ChakraRegistry.CHAKRA.register("sugilite_chakra", DefaultChakra::new);
    //THIRD EYE
    public static final RegistryObject<Chakra> HAG_STONE_CHAKRA =          ChakraRegistry.CHAKRA.register("hag_stone_chakra", () -> new HagStoneChakra(UUID.randomUUID())); //TOOL RANGE
    public static final RegistryObject<Chakra> DUMORTIERITE_CHAKRA =       ChakraRegistry.CHAKRA.register("dumortierite_chakra", DumortieriteChakra::new);                  //UKÁŽE ORE PO FALL DAMAGE
    //THROAT
    public static final RegistryObject<Chakra> BLUE_LACE_AGATE_CHAKRA =    ChakraRegistry.CHAKRA.register("blue_lace_agate_chakra", DefaultChakra::new);
    public static final RegistryObject<Chakra> KYANITE_CHAKRA =            ChakraRegistry.CHAKRA.register("kyanite_chakra", DefaultChakra::new);
    //HEART
    public static final RegistryObject<Chakra> AMAZONITE_CHAKRA =          ChakraRegistry.CHAKRA.register("amazonite_chakra", AmazoniteChakra::new);                  //BONUS HP
    public static final RegistryObject<Chakra> RHODONITE_CHAKRA =          ChakraRegistry.CHAKRA.register("rhodonite_chakra", DefaultChakra::new);
    //SOLAR
    public static final RegistryObject<Chakra> HELIOLITE_CHAKRA =          ChakraRegistry.CHAKRA.register("heliolite_chakra", HelioliteChakra::new);                  //3x3 SEEDS
    public static final RegistryObject<Chakra> CITRINE_CHAKRA =            ChakraRegistry.CHAKRA.register("citrine_chakra", DefaultChakra::new);
    //SACRAL
    public static final RegistryObject<Chakra> STILLBITE_CHAKRA =          ChakraRegistry.CHAKRA.register("stillbite_chakra", DefaultChakra::new);
    public static final RegistryObject<Chakra> CARNELIAN_CHAKRA =          ChakraRegistry.CHAKRA.register("carnelian_chakra", DefaultChakra::new);
    //ROOT
    public static final RegistryObject<Chakra> BLACK_ONYX_CHAKRA =         ChakraRegistry.CHAKRA.register("black_onyx_chakra", BlackOnyx::new);
    public static final RegistryObject<Chakra> MAHOGANY_CHAKRA =           ChakraRegistry.CHAKRA.register("mahogany_chakra", DefaultChakra::new);

    private static void playerUseOnBlock(PlayerInteractEvent.RightClickBlock event){
        Player player = event.getEntity();
        if(!player.getLevel().isClientSide()) {
            ChakraRegistry.CHAKRA.getEntries().forEach(s -> {
                Chakra chakra = s.get();
                if (chakra.isEnabled()) {
                    chakra.onRightClickBlock(player, player.level, event);
                }
            });
        }
    }

    //public static void register(IEventBus eventBus){
    //    CHAKRAS.register(eventBus);
    //};

    public static void registerEvents(){
        MinecraftForge.EVENT_BUS.addListener(Chakras::playerUseOnBlock);
    }
}
