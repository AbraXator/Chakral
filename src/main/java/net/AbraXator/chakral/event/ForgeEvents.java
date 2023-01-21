package net.AbraXator.chakral.event;

import net.AbraXator.chakral.Chakral;
import net.AbraXator.chakral.blocks.ModBlocks;
import net.AbraXator.chakral.blocks.custom.ShardRefinerBlock;
import net.AbraXator.chakral.chakra.*;
import net.AbraXator.chakral.capability.NecklaceCapProvider;
import net.AbraXator.chakral.items.ModItems;
import net.AbraXator.chakral.utils.ModTags;
import net.AbraXator.chakral.utils.PlayerUtil;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.EnderManAngerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

import java.util.Collection;
import java.util.Random;

@Mod.EventBusSubscriber(modid = Chakral.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ForgeEvents {
    public static boolean EQUIPPED = false;
    @SubscribeEvent
    public static void EnderManAngerReset(EnderManAngerEvent event){
        ChakrasEquip.sugiliteEnderMan(event.getPlayer(), event);
    }

    public static void inventoryScreenEvent(ScreenEvent.Init event){
        Screen screen = event.getScreen();
        if(screen instanceof InventoryScreen inventoryScreen){
            //inventoryScreen.;
        }
    }

    @SubscribeEvent
    public static void refinerInteract(PlayerInteractEvent.RightClickBlock event){
        Level level = event.getEntity().level;
        BlockPos pos = event.getPos();
        if(event.getHand().equals(InteractionHand.MAIN_HAND)
                && level.getBlockState(pos).getBlock() instanceof ShardRefinerBlock block
                && !event.getEntity().getItemInHand(event.getHand()).is(ModTags.Items.REFINER_KITS)
                && event.getEntity().isShiftKeyDown()){
            Item kit = switch (block.getTier(level.getBlockState(pos))){
                case FAINT -> ItemStack.EMPTY.getItem();
                case WEAKENED -> ModItems.WEAK_REFINER_KIT.get();
                case POWERFUL -> ModItems.POWERFUL_REFINER_KIT.get();
                case ENLIGHTENED -> ModItems.ENGLIGHTENED_REFINER_KIT.get();
            };
            if(!kit.getDefaultInstance().is(ItemStack.EMPTY.getItem())){
                PlayerUtil.addItemToInventory(event.getEntity(), kit.getDefaultInstance());
            }
            level.setBlock(pos, level.getBlockState(pos).setValue(ShardRefinerBlock.TIER, ChakraStrength.FAINT), 3);
        }
    }



    //@SubscribeEvent
    //public static void BlockBreak(BlockEvent.BreakEvent event){
    //    BlockPos pos = event.getPos();
    //    LevelAccessor level = event.getLevel();
    //    BlockState state = level.getBlockState(pos);
    //    Player player = event.getPlayer();
    //    Random random = new Random();
    //    if(random.nextDouble() < 0.30D) {
    //        if (state.is(Blocks.AMETHYST_CLUSTER)) {
    //            player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 100, 2));
    //        }
    //        if (state.is(ModBlocks.ORANGE_CRYSTAL.get())) {
    //            player.setSecondsOnFire(5);
    //        }
    //    }
    //    if(state.is(ModBlocks.YELLOW_CRYSTAL.get())) {
    //        BlockPos blockPos = new BlockPos(player.getX(), player.getY() + 1, player.getZ());
    //        int i = level.getBrightness(LightLayer.SKY, blockPos) - level.getSkyDarken();
    //        if(i >= 15){
    //            player.hurt(DamageSource.LIGHTNING_BOLT, 5);
    //        }
    //    }
    //}

    @SubscribeEvent
    public static void TickEvent(TickEvent.PlayerTickEvent event){
        Player player = event.player;
        Level level = event.player.getLevel();
        BlockPos pos = player.getOnPos();
        Random random = new Random();
        BlockState state = level.getBlockState(pos);
        if(state.is(ModTags.Blocks.CRYSTALS) && random.nextDouble() < 0.03D){
            if(state.is(ModBlocks.ORANGE_CRYSTAL.get())){
                player.setSecondsOnFire(5);
            }
        }

        player.getCapability(NecklaceCapProvider.NECKLACE_CAP).ifPresent(necklaceCap -> {
            if(!level.isClientSide) {
                //System.out.println(necklaceCap.getNecklace());
            }
        });
    }

    @SubscribeEvent
    public static void chakraTick(TickEvent.PlayerTickEvent event) {
        event.player.getCapability(NecklaceCapProvider.NECKLACE_CAP).ifPresent(necklaceCap -> {
            //System.out.println(necklaceCap.getNecklace());
        });
        Collection<RegistryObject<Chakra>> collection = ChakraRegistries.CHAKRA.getEntries();
        collection.stream().toList().get(1);
        ChakraRegistries.CHAKRA.getEntries().forEach(s -> {
            Chakra chakra = s.get();
            if (chakra.isEnabled(chakra)) {
                chakra.tick(event.player, event.player.level);
            }
        });
        if (!event.player.level.isClientSide) {
            if (event.phase == TickEvent.Phase.END) {

            }
        }
    }
}
