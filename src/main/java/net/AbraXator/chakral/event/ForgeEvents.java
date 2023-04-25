package net.AbraXator.chakral.event;

import net.AbraXator.chakral.Chakral;
import net.AbraXator.chakral.init.ModBlocks;
import net.AbraXator.chakral.blocks.ShardRefinerBlock;
import net.AbraXator.chakral.chakra.*;
import net.AbraXator.chakral.capability.NecklaceCapProvider;
import net.AbraXator.chakral.chakra.chakras.BlackOnyxChakra;
import net.AbraXator.chakral.client.gui.chakralnexus.ChakralNexusMenu;
import net.AbraXator.chakral.init.ModItems;
import net.AbraXator.chakral.init.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerContainerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

@Mod.EventBusSubscriber(modid = Chakral.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ForgeEvents {
    public static boolean EQUIPPED = false;

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
                addItemToInventory(event.getEntity(), kit.getDefaultInstance());
            }
            level.setBlock(pos, level.getBlockState(pos).setValue(ShardRefinerBlock.TIER, ChakraStrength.FAINT), 3);
        }
    }

    @SubscribeEvent
    public static void tick(TickEvent.PlayerTickEvent event){
        ChakraUtil.getChakrasFromPlayer(event.player).forEach(chakra -> chakra.tick(event.player, event.player.getLevel()));
    }

    @SubscribeEvent
    public static void BlockBreak(BlockEvent.BreakEvent event){
        ChakraUtil.getChakrasFromPlayer(event.getPlayer()).forEach(chakra -> chakra.onDestroyBlock(event));
    }

    public static void addItemToInventory(Player player, ItemStack stack){
        if(player.getInventory().getFreeSlot() == -1){
            player.drop(stack, false);
        }else {
            player.addItem(stack);
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
    public static void PlayerDamageEvent(LivingDamageEvent event){
        if(event.getEntity().getType().equals(EntityType.PLAYER)){
            ChakraUtil.getChakrasFromPlayer(((Player) event.getEntity())).forEach(chakra -> chakra.onDamage(event));
        }
    }

    @SubscribeEvent
    public void onAttackEntity(AttackEntityEvent event) {
        ChakraUtil.getChakrasFromPlayer(event.getEntity()).forEach(chakra -> {
            if(chakra instanceof BlackOnyxChakra blackOnyx){
                event.setCanceled(true);
                event.getTarget();
                blackOnyx.onAttackEntity(event.getEntity(), event.getEntity().getLevel(), event.getTarget());
            }
        });
    }

    @SubscribeEvent
    public static void TickEvent(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        Level level = event.player.getLevel();
        BlockPos pos = player.getOnPos();
        Random random = new Random();
        BlockState state = level.getBlockState(pos);
        if (state.is(ModTags.Blocks.CRYSTALS) && random.nextDouble() < 0.03D) {
            if (state.is(ModBlocks.ORANGE_CRYSTAL.get())) {
                player.setSecondsOnFire(5);
            }
        }

        player.getCapability(NecklaceCapProvider.NECKLACE_CAP).ifPresent(necklaceCap -> {
            if (!level.isClientSide) {
                //System.out.println(necklaceCap.getNecklace());
            }
        });
    }

    @SubscribeEvent
    public void onPlayerInteractLeftClickBlock(PlayerInteractEvent.LeftClickEmpty event) {
        ChakraUtil.getChakrasFromPlayer(event.getEntity()).forEach(chakra -> chakra.leftClick(event));
    }

    @SubscribeEvent
    public void onPlayerInteract(PlayerInteractEvent event) {
        ChakraUtil.getChakrasFromPlayer(event.getEntity()).forEach(chakra -> chakra.interact(event));
    }

    @SubscribeEvent
    public static void onPlayerContainer(PlayerContainerEvent.Open event) {
        if(event.getContainer() instanceof ChakralNexusMenu chakralNexusMenu){
            event.getEntity().getCapability(NecklaceCapProvider.NECKLACE_CAP).ifPresent(necklaceCap -> {
                chakralNexusMenu.setItemInSlot(0, necklaceCap.getNecklace());
            });
        }
    }
}
