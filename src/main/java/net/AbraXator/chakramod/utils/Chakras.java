package net.AbraXator.chakramod.utils;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.ServerStatsCounter;
import net.minecraft.stats.Stats;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;


public class Chakras {
    public static void amazonite(Entity entity){
        ServerStatsCounter statsCounter = ((ServerPlayer) entity).getStats();
        statsCounter.setValue(((ServerPlayer) entity), Stats.CUSTOM.get(Stats.TIME_SINCE_REST), 1);
    }

    public static void rhodonite(Player entity){
        entity.removeEffect(MobEffects.POISON);
    }


}
