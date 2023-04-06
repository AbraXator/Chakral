package net.AbraXator.chakral.chakra;

import net.AbraXator.chakral.capability.NecklaceCapProvider;
import net.AbraXator.chakral.chakra.chakras.*;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import java.util.UUID;

public class Chakras {
    public static void registerEntries(){
        Chakras chakras = null;
    }

    //CROWN
    public static final Chakra AMETHYST_QUARTZ_CHAKRA = new AmethystQuartzChakra();
    public static final Chakra SUGILITE_CHAKRA = new SugiliteChakra();
    //THIRD EYE
    public static final Chakra HAG_STONE_CHAKRA = new HagStone(UUID.randomUUID());
    public static final Chakra DUMORTIERITE_CHAKRA = new DumortieriteChakra();            //UKÁŽE ORE PO FALL DAMAGE
    //THROAT
    public static final Chakra BLUE_LACE_AGATE_CHAKRA = new DefaultChakra();
    public static final Chakra KYANITE_CHAKRA = new DefaultChakra();
    //HEART
    public static final Chakra AMAZONITE_CHAKRA = new AmazoniteChakra();               //BONUS HP
    public static final Chakra RHODONITE_CHAKRA = new Rhodo
    //SOLAR
    public static final Chakra HELIOLITE_CHAKRA =          ChakraRegistries.CHAKRA.register("heliolite_chakra", HelioliteChakra::new);                  //3x3 SEEDS
    public static final Chakra CITRINE_CHAKRA =            ChakraRegistries.CHAKRA.register("citrine_chakra", DefaultChakra::new);
    //SACRAL
    public static final Chakra STILLBITE_CHAKRA =          ChakraRegistries.CHAKRA.register("stillbite_chakra", DefaultChakra::new);
    public static final Chakra CARNELIAN_CHAKRA =          ChakraRegistries.CHAKRA.register("carnelian_chakra", DefaultChakra::new);
    //ROOT
    public static final Chakra BLACK_ONYX_CHAKRA =         ChakraRegistries.CHAKRA.register("black_onyx_chakra", DefaultChakra::new);
    public static final Chakra MAHOGANY_CHAKRA =           ChakraRegistries.CHAKRA.register("mahogany_chakra", DefaultChakra::new);



    private static void tick(TickEvent.PlayerTickEvent event){
        if(event.phase == TickEvent.Phase.END && event.side.isServer()){
            ServerPlayer player = ((ServerPlayer) event.player);
            ChakraRegistries.CHAKRA.getEntries().forEach(s -> {
                Chakra chakra = s.get();
                event.player.getCapability(NecklaceCapProvider.NECKLACE_CAP).ifPresent(necklaceCap -> {
                    //chakra.necklace = necklaceCap.getNecklace();
                });
                if(chakra.isEnabled()){
                    chakra.tick(event.player, event.player.level);
                }
            });
        }
    }

    private static void playerUseOnBlock(PlayerInteractEvent.RightClickBlock event){
        Player player = event.getEntity();
        if(!player.getLevel().isClientSide()) {
            ChakraRegistries.CHAKRA.getEntries().forEach(s -> {
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
        MinecraftForge.EVENT_BUS.addListener(Chakras::tick);
        MinecraftForge.EVENT_BUS.addListener(Chakras::playerUseOnBlock);
    }
}
