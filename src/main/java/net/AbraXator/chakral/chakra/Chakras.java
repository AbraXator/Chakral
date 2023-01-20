package net.AbraXator.chakral.chakra;

import net.AbraXator.chakral.capability.NecklaceCapProvider;
import net.AbraXator.chakral.chakra.chakras.*;
import net.AbraXator.chakral.items.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.extensions.IForgeItem;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.registries.*;

import java.util.UUID;

public class Chakras {
    public static void registerEntries(){
        Chakras chakras = null;
    }

    //CROWN
    public static final RegistryObject<Chakra> AMETHYST_QUARTZ_CHAKRA =    ChakraRegistries.CHAKRA.register("amethyst_quartz_chakra", AmethystQuartzChakra::new);
    public static final RegistryObject<Chakra> SUGILITE_CHAKRA =           ChakraRegistries.CHAKRA.register("sugilite_chakra", DefaultChakra::new);
    //THIRD EYE
    public static final RegistryObject<Chakra> HAG_STONE_CHAKRA =          ChakraRegistries.CHAKRA.register("hag_stone_chakra", () -> new HagStone(UUID.randomUUID()));
    public static final RegistryObject<Chakra> DUMORTIERITE_CHAKRA =       ChakraRegistries.CHAKRA.register("dumortierite_chakra", Dumortierite::new);
    //THROAT
    public static final RegistryObject<Chakra> BLUE_LACE_AGATE_CHAKRA =    ChakraRegistries.CHAKRA.register("blue_lace_agate_chakra", DefaultChakra::new);
    public static final RegistryObject<Chakra> KYANITE_CHAKRA =            ChakraRegistries.CHAKRA.register("kyanite_chakra", DefaultChakra::new);
    //HEART
    public static final RegistryObject<Chakra> AMAZONITE_CHAKRA =          ChakraRegistries.CHAKRA.register("amazonite_chakra", AmazoniteChakra::new);
    public static final RegistryObject<Chakra> RHODONITE_CHAKRA =          ChakraRegistries.CHAKRA.register("rhodonite_chakra", DefaultChakra::new);
    //SOLAR
    public static final RegistryObject<Chakra> HELIOLITE_CHAKRA =          ChakraRegistries.CHAKRA.register("heliolite_chakra", HelioliteChakra::new);
    public static final RegistryObject<Chakra> CITRINE_CHAKRA =            ChakraRegistries.CHAKRA.register("citrine_chakra", DefaultChakra::new);
    //SACRAL
    public static final RegistryObject<Chakra> STILLBITE_CHAKRA =          ChakraRegistries.CHAKRA.register("stillbite_chakra", DefaultChakra::new);
    public static final RegistryObject<Chakra> CARNELIAN_CHAKRA =          ChakraRegistries.CHAKRA.register("carnelian_chakra", DefaultChakra::new);
    //ROOT
    public static final RegistryObject<Chakra> BLACK_ONYX_CHAKRA =         ChakraRegistries.CHAKRA.register("black_onyx_chakra", DefaultChakra::new);
    public static final RegistryObject<Chakra> MAHOGANY_CHAKRA =           ChakraRegistries.CHAKRA.register("mahogany_chakra", DefaultChakra::new);


    private static void tick(TickEvent.PlayerTickEvent event){
        if(event.phase == TickEvent.Phase.END){
            ChakraRegistries.CHAKRA.getEntries().forEach(s -> {
                Chakra chakra = s.get();
                event.player.getCapability(NecklaceCapProvider.NECKLACE_CAP).ifPresent(necklaceCap -> {
                    chakra.necklace = necklaceCap.getNecklace();
                });
                if(chakra.isEnabled(chakra)){
                    chakra.tick(event.player, event.player.level);
                }
            });
        }
    }

    private static void playerUseOnBlock(PlayerInteractEvent.RightClickBlock event){
        ChakraRegistries.CHAKRA.getEntries().forEach(s -> {
            Player player = event.getEntity();
            Chakra chakra = s.get();
            player.getCapability(NecklaceCapProvider.NECKLACE_CAP).ifPresent(necklaceCap -> {
                chakra.necklace = necklaceCap.getNecklace();
            });
            if(chakra.isEnabled(chakra) && chakra instanceof HelioliteChakra helioliteChakra){
                helioliteChakra.placeSeeds(player, player.level, event.getPos(), event.getItemStack());
            }
        });
    }

    //public static void register(IEventBus eventBus){
    //    CHAKRAS.register(eventBus);
    //};

    public static void registerEvents(){
        MinecraftForge.EVENT_BUS.addListener(Chakras::tick);
        MinecraftForge.EVENT_BUS.addListener(Chakras::playerUseOnBlock);
    }
}
