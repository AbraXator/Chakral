package net.AbraXator.chakral.event;

import net.AbraXator.chakral.Chakral;
import net.AbraXator.chakral.blocks.ModBlocks;
import net.AbraXator.chakral.chakra.ChakraUtil;
import net.AbraXator.chakral.chakra.ChakrasEquip;
import net.AbraXator.chakral.chakra.capability.NecklaceCapProvider;
import net.AbraXator.chakral.items.ModItems;
import net.AbraXator.chakral.utils.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.EnderManAngerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.Random;

@Mod.EventBusSubscriber(modid = Chakral.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ForgeEvents {
    public static boolean EQUIPPED = false;
    @SubscribeEvent
    public static void EnderManAngerReset(EnderManAngerEvent event){
        ChakrasEquip.sugiliteEnderMan(event.getPlayer(), event);
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
    }

    @SubscribeEvent
    public static void gemEffects(TickEvent.PlayerTickEvent event){
        Player player = event.player;
        Level level = player.level;
        player.getCapability(NecklaceCapProvider.NECKLACE).ifPresent(necklaceCap -> {
            if(necklaceCap.getNecklace() != null) {
                ItemStack necklace = necklaceCap.getNecklace();
                if (necklace.hasTag()) {
                    CompoundTag tag = necklace.getTag();
                    Item stone1 = tag.get("Stone1") != null || ItemStack.of(tag.getCompound("Stone1")).is(Items.AIR)
                            ? ItemStack.of(tag.getCompound("Stone1")).getItem() : Items.AIR;
                    Item stone2 = tag.get("Stone2") != null || ItemStack.of(tag.getCompound("Stone2")).is(Items.AIR)
                            ? ItemStack.of(tag.getCompound("Stone2")).getItem() : Items.AIR;
                    Item stone3 = tag.get("Stone3") != null || ItemStack.of(tag.getCompound("Stone3")).is(Items.AIR)
                            ? ItemStack.of(tag.getCompound("Stone3")).getItem() : Items.AIR;
                    Item stone4 = tag.get("Stone4") != null || ItemStack.of(tag.getCompound("Stone4")).is(Items.AIR)
                            ? ItemStack.of(tag.getCompound("Stone4")).getItem() : Items.AIR;
                    List<Item> gems = List.of(stone1, stone2, stone3, stone4);
                    if(gems.contains(ModItems.AMETHYST_QUARTZ.get())){
                        ChakrasEquip.amethystQuartz(player);
                    }
                    if(gems.contains(ModItems.SUGILITE.get())){

                    }
                    if (gems.contains(ModItems.MOON_STONE.get()) && ChakraUtil.moonStoneCooldown != ChakraUtil.moonStoneMaxCooldown) {
                        ChakraUtil.moonStoneCooldown++;
                    }
                    ChakrasEquip.azurite(player, level);
                    ChakrasEquip.greenOpal(player);
                }
            }
        });
    }
}
