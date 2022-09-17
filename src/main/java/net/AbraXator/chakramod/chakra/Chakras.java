package net.AbraXator.chakramod.chakra;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Position;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.common.Tags;

import java.lang.reflect.Array;
import java.util.List;


public class Chakras {

    //------------------FAINT-------------
    public static void hag_stone(Player player) {
        if (player.getY() < 50) {
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 2, 1));
        }
    }

    public static void amazonite(Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.HEALTH_BOOST, 2, 4));
    }

    public static void blue_lace_agate(Player player) {
        if (!player.getEyeInFluidType().isAir()) {
            int j = player.getAirSupply();
            player.setAirSupply(j * 2);
        }
    }

    public static void NOTblue_lace_agate(Player player) {
        if (!player.getEyeInFluidType().isAir()) {
            int j = player.getAirSupply();
            player.setAirSupply(j / 2);
        }
    }

    public static void dumortierite(Player player) {
        if (player.getUseItem().is(Tags.Items.TOOLS_PICKAXES)) {
            player.getAttribute(Attributes.ATTACK_SPEED).addTransientModifier(new AttributeModifier("attack_speed_boost", 0.15D, AttributeModifier.Operation.ADDITION));
        }
    }

    public static void rhodonite(Player player) {
        double maxHealth = player.getMaxHealth();
        double health10 = maxHealth / 100 * 10;
        if (player.getHealth() >= health10) {
            player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 2, 1));
        }
    }

    public static void malachite(Player player) {

    }

    public static void azurite(Player player, Level level) {

    }
}
