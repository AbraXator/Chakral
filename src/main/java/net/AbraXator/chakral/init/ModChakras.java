package net.AbraXator.chakral.init;

import net.AbraXator.chakral.Chakral;
import net.AbraXator.chakral.chakra.Chakra;
import net.AbraXator.chakral.chakra.ChakraRegistry;
import net.AbraXator.chakral.chakra.chakras.*;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.UUID;

public class ModChakras {
    public static void registerEntries(){
        ModChakras chakras = null;
    }

    public static final DeferredRegister<Chakra> CHAKRAS = DeferredRegister.create(
            ChakraRegistry.CHAKRA_REGISTRY, Chakral.MOD_ID);


    //CROWN
    public static final RegistryObject<Chakra> AMETHYST_QUARTZ_CHAKRA =    CHAKRAS.register();
    public static final RegistryObject<Chakra> SUGILITE_CHAKRA =           CHAKRAS.register("sugilite_chakra", DefaultChakra::new);
    //THIRD EYE
    public static final RegistryObject<Chakra> HAG_STONE_CHAKRA =          CHAKRAS.register("hag_stone_chakra", () -> new HagStoneChakra(UUID.randomUUID())); //TOOL RANGE
    public static final RegistryObject<Chakra> DUMORTIERITE_CHAKRA =       CHAKRAS.register("dumortierite_chakra", DumortieriteChakra::new);                  //UKÁŽE ORE PO FALL DAMAGE
    //THROAT
    public static final RegistryObject<Chakra> BLUE_LACE_AGATE_CHAKRA =    CHAKRAS.register("blue_lace_agate_chakra", DefaultChakra::new);
    public static final RegistryObject<Chakra> KYANITE_CHAKRA =            CHAKRAS.register("kyanite_chakra", DefaultChakra::new);
    //HEART
    public static final RegistryObject<Chakra> AMAZONITE_CHAKRA =          CHAKRAS.register("amazonite_chakra", AmazoniteChakra::new);                  //BONUS HP
    public static final RegistryObject<Chakra> RHODONITE_CHAKRA =          CHAKRAS.register("rhodonite_chakra", DefaultChakra::new);
    //SOLAR
    public static final RegistryObject<Chakra> HELIOLITE_CHAKRA =          CHAKRAS.register("heliolite_chakra", HelioliteChakra::new);                  //3x3 SEEDS
    public static final RegistryObject<Chakra> CITRINE_CHAKRA =            CHAKRAS.register("citrine_chakra", DefaultChakra::new);
    //SACRAL
    public static final RegistryObject<Chakra> STILLBITE_CHAKRA =          CHAKRAS.register("stillbite_chakra", DefaultChakra::new);
    public static final RegistryObject<Chakra> CARNELIAN_CHAKRA =          CHAKRAS.register("carnelian_chakra", DefaultChakra::new);
    //ROOT
    public static final RegistryObject<Chakra> BLACK_ONYX_CHAKRA =         CHAKRAS.register("black_onyx_chakra", BlackOnyxChakra::new);
    public static final RegistryObject<Chakra> MAHOGANY_CHAKRA =           CHAKRAS.register("mahogany_chakra", DefaultChakra::new);

    private void register(Chakra chakra){
        CHAKRAS.register(chakra.getId().getPath(), );
    }

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
        MinecraftForge.EVENT_BUS.addListener(ModChakras::playerUseOnBlock);
    }
}
