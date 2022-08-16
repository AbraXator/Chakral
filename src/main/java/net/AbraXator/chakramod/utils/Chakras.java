package net.AbraXator.chakramod.utils;

import net.AbraXator.chakramod.items.ModItems;
import net.AbraXator.chakramod.particles.ModParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.ServerStatsCounter;
import net.minecraft.stats.Stats;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;

import java.util.*;


public class Chakras {
    public static boolean PRESSED = false;

    public static void amazonite(Entity entity){
        ServerStatsCounter statsCounter = ((ServerPlayer) entity).getStats();
        statsCounter.setValue(((ServerPlayer) entity), Stats.CUSTOM.get(Stats.TIME_SINCE_REST), 1);
    }

    public static void amber(Player player, ItemStack stack, Level level){
        boolean enabled = false;
        if(!level.isClientSide){
            String deathLoc = player.getLastDeathLocation().get().pos().toString();
            if(player.getRemovalReason() == Entity.RemovalReason.KILLED){
                enabled = true;
                if(enabled){
                    player.sendSystemMessage(Component.literal(player.getName().getString() + " died at " + deathLoc));
                    enabled = false;
                }
            }
        }
    }

    public static void aquamarine(Level level, Player player){
        BlockPos pos = new BlockPos(player.position());
        int duration = 0;
        if(level.getBiome(pos).get().equals(Biomes.BEACH)
                || level.getBiome(pos).get().equals(Biomes.STONY_SHORE)
                || level.getBiome(pos).get().equals(Biomes.SNOWY_BEACH)
                || level.getBiome(pos).get().equals(Biomes.OCEAN)
                || level.getBiome(pos).get().equals(Biomes.COLD_OCEAN)
                || level.getBiome(pos).get().equals(Biomes.FROZEN_OCEAN)
                || level.getBiome(pos).get().equals(Biomes.LUKEWARM_OCEAN)
                || level.getBiome(pos).get().equals(Biomes.WARM_OCEAN)
                || level.getBiome(pos).get().equals(Biomes.DEEP_COLD_OCEAN)
                || level.getBiome(pos).get().equals(Biomes.DEEP_LUKEWARM_OCEAN)
                || level.getBiome(pos).get().equals(Biomes.DEEP_OCEAN)){
            duration = 1;
            player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, duration, 5));
        }
    }

    public static void rhodonite(Player entity){
        entity.removeEffect(MobEffects.POISON);
    }

    public static void carnelian(Player player, Level level){
        if(PRESSED){
            for(int i = 0; i < 2 * Math.PI * 5; i++){
                level.addParticle(ParticleTypes.FLAME, i, player.getY(), i/5, 1, 1, 1);
                if(i < 2 * Math.PI * 5){
                    PRESSED = false;
                }
            }
        }
    }
}
