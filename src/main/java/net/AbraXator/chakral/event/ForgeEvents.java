package net.AbraXator.chakral.event;

import com.mojang.math.Vector3f;
import net.AbraXator.chakral.Chakral;
import net.AbraXator.chakral.blocks.ModBlocks;
import net.AbraXator.chakral.chakra.ChakraType;
import net.AbraXator.chakral.utils.ModTags;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.entity.living.EnderManAngerEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

@Mod.EventBusSubscriber(modid = Chakral.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ForgeEvents {

    @SubscribeEvent
    public static void onItemTooltip(ItemTooltipEvent itemTooltipEvent) {
        ChakraType chakraType = null;
        //Component chakraComponent;
        if(itemTooltipEvent.getItemStack().is(Items.AMETHYST_SHARD)){
            chakraType = ChakraType.CROWN;
            //chakraComponent = Component.literal(chakraType.ChakraToString(chakraType)).withStyle(chakraType.getColorFromChakra(chakraType));
            itemTooltipEvent.getToolTip().add(Component.literal("Gems: " + chakraType).withStyle(chakraType.getColorFromChakra(chakraType)));
            itemTooltipEvent.getToolTip().add(Component.literal("<Hold shift for more info>").withStyle(ChatFormatting.GOLD));
        }
        if(itemTooltipEvent.getItemStack().is(Items.LAPIS_LAZULI)){
            chakraType = ChakraType.THIRD_EYE;
            //chakraComponent = Component.literal(chakraType.ChakraToString(chakraType)).withStyle(chakraType.getColorFromChakra(chakraType));
            itemTooltipEvent.getToolTip().add(Component.literal("Gems: " + chakraType).withStyle(chakraType.getColorFromChakra(chakraType)));
            itemTooltipEvent.getToolTip().add(Component.literal("<Hold shift for more info>").withStyle(ChatFormatting.GOLD));
        }
        if(itemTooltipEvent.getItemStack().is(Items.QUARTZ)){
            chakraType = ChakraType.CROWN;
            //chakraComponent = Component.literal(chakraType.ChakraToString(chakraType)).withStyle(chakraType.getColorFromChakra(chakraType));
            itemTooltipEvent.getToolTip().add(Component.literal("Gems: " + chakraType).withStyle(chakraType.getColorFromChakra(chakraType)));
            itemTooltipEvent.getToolTip().add(Component.literal("<Hold shift for more info>").withStyle(ChatFormatting.GOLD));
        }
    }

    @SubscribeEvent
    public static void EnderManAngerReset(EnderManAngerEvent event){
        event.setCanceled(true);
    }

    @SubscribeEvent
    public static void BlockBreak(BlockEvent.BreakEvent event){
        BlockPos pos = event.getPos();
        LevelAccessor level = event.getLevel();
        BlockState state = level.getBlockState(pos);
        Player player = event.getPlayer();
        Random random = new Random();
        if(random.nextDouble() < 0.30D) {
            if (state.is(Blocks.AMETHYST_CLUSTER)) {
                player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 100, 2));
            }
            if (state.is(ModBlocks.ORANGE_CRYSTAL.get())) {
                player.setSecondsOnFire(5);
            }
        }
        if(state.is(ModBlocks.YELLOW_CRYSTAL.get())) {
            BlockPos blockPos = new BlockPos(player.getX(), player.getY() + 1, player.getZ());
            int i = level.getBrightness(LightLayer.SKY, blockPos) - level.getSkyDarken();
            if(i >= 15){
                player.hurt(DamageSource.LIGHTNING_BOLT, 5);
            }
        }
    }


}
