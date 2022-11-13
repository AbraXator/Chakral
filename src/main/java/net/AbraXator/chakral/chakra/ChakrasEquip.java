package net.AbraXator.chakral.chakra;

import net.AbraXator.chakral.chakra.capability.NecklaceCapProvider;
import net.AbraXator.chakral.items.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.entity.living.EnderManAngerEvent;
import net.minecraftforge.registries.ForgeRegistries;
import org.lwjgl.opengl.GL;

import java.util.ArrayList;
import java.util.List;


public class ChakrasEquip {
    //-------------CROWN---------------
    public static void amethystQuartz(Player player){
        List<MobEffectInstance> effectsList = player.getActiveEffects().stream().toList();
        for (int i = 0; i < effectsList.size(); i++) {
            MobEffectInstance instance = effectsList.get(i);
            if(ChakraUtil.AMETHYST_QUARTZ_EFFECTS.contains(instance)){
                double j = instance.getDuration();
                double k = instance.getAmplifier();
                instance = new MobEffectInstance(instance.getEffect(), ((int) Math.round(j / 0.10)), ((int) Math.round(k / 0.30)));
                player.addEffect(instance);
            }
        }

    }

    public static void sugiliteEffects(Player player){
        List<MobEffectInstance> effectsList = player.getActiveEffects().stream().toList();
        for (int i = 0; i < effectsList.size(); i++) {
            MobEffectInstance instance = effectsList.get(i);
            if(ChakraUtil.SUGILITE_EFFECTS.contains(instance)){
                double j = instance.getDuration();
                double k = instance.getAmplifier();
                instance = new MobEffectInstance(instance.getEffect(), ((int) Math.round(j / 0.10)), ((int) Math.round(k / 0.30)));
                player.addEffect(instance);
            }
        }
    }

    public static void sugiliteEnderMan(Player player, EnderManAngerEvent event){
        player.getCapability(NecklaceCapProvider.NECKLACE).ifPresent(necklaceCap -> {
            if(necklaceCap.getNecklace() != null) {
                if (!necklaceCap.getNecklace().isEmpty()) {
                    if (necklaceCap.getNecklace().hasTag()) {
                        if (necklaceCap.getNecklace().getTag().get("Stone2") != null) {
                            ItemStack gemWeakened = ItemStack.of(necklaceCap.getNecklace().getTag().getCompound("Stone2"));
                            if (gemWeakened.is(ModItems.SUGILITE.get())) {
                                event.setCanceled(true);
                            }
                        }
                    }
                }
            }
        });
    }

    public static void moonStone(Player player){
        ChakraUtil.moonStoneMaxCooldown = 260;
        if(ChakraUtil.moonStoneCooldown == ChakraUtil.moonStoneMaxCooldown) {
            player.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 60, 2));
            ChakraUtil.moonStoneCooldown = 0;
        }
    }

    public static void labbradorite(Player player){
        //player.addEffect(new MobEffectInstance(MobEffects.LEVITATION))
    }

    //------------------THIRD EYE-------------
    public static void hagStone(Player player) {
        if (player.getY() < 50) {
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 2, 1));
        }
    }

    public static void dumortierite(Player player){
        boolean hasPickaxe = player.getItemInHand(InteractionHand.MAIN_HAND).is(Tags.Items.TOOLS_PICKAXES);
        if(hasPickaxe){
            player.getAttribute(Attributes.ATTACK_SPEED).addTransientModifier(new AttributeModifier("attack_speed_boost", 0.15D, AttributeModifier.Operation.ADDITION));
        }else {
            player.getAttribute(Attributes.ATTACK_SPEED).addTransientModifier(new AttributeModifier("attack_speed_boost", -0.15D, AttributeModifier.Operation.ADDITION));
        }
    }

    public static void lepidolite(){}

    public static void azurite(Player player, Level level){
        for (int i = -2; i < 2; i++) {
            for (int j = -2; j < 2; j++) {
                for (int k = -2; k < 2; k++) {
                    Vec3 vec3 = new Vec3(player.getX() + i, player.getY() + j, player.getZ() + k);
                    BlockPos pos = new BlockPos(vec3);
                    BlockState state = level.getBlockState(pos);
                    //System.out.println(vec3);
                    if(ForgeRegistries.BLOCKS.tags().getTag(Tags.Blocks.ORES).contains(state.getBlock())
                            && level.getEntities(EntityType.SHULKER.create(level), new AABB(pos)).contains(EntityType.SHULKER.create(level))){
                        Shulker shulker = EntityType.SHULKER.create(level);
                        shulker.addEffect(new MobEffectInstance(MobEffects.GLOWING, 99999));
                        shulker.addEffect(new MobEffectInstance(MobEffects.WITHER, 10, 2));
                        shulker.moveTo(vec3);
                        level.addFreshEntity(shulker);
                    }
                }
            }
        }
    }

    //------------THROAT----------------
    public static void blue_lace_agate(Player player) {
        if (player.getEyeInFluidType().isAir()) {
            int j = player.getAirSupply();
            player.setAirSupply(j * 2);
        }
    }

    public static void kyanite(Player player){
        if(player.isInWater()){
            player.getAttribute(ForgeMod.SWIM_SPEED.get()).addTransientModifier(new AttributeModifier("swim_speed", 0.15D, AttributeModifier.Operation.ADDITION));
        }else {
            player.getAttribute(ForgeMod.SWIM_SPEED.get()).addTransientModifier(new AttributeModifier("swim_speed", -0.15D, AttributeModifier.Operation.ADDITION));
        }
        //TODO: add boat speed modifier
    }

    //---------------HEART----------------
    public static void amazonite(Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.HEALTH_BOOST, 2, 4));
    }

    public static void rhodonite(Player player){
        float j = player.getHealth();
        float k = player.getMaxHealth();
        float l = k - j;
        if(l <= k / 0.30){
            player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 2, 1));
        }
    }

    //---------------SOLAR-----------------

    public static void heliolite(Player player, Level level){
        BlockPos blockPos = new BlockPos(player.getX(), player.getY() + 1, player.getZ());
        int j = level.getBrightness(LightLayer.SKY, blockPos) - level.getSkyDarken();
        if(j >= 15){
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 2, 0));
        }
        player.getLookAngle();
    }

    public static void citrine(Player player){
        Level level = player.level;
        BlockPos blockPos = new BlockPos(player.getX(), player.getY() + 1, player.getZ());
        int j = level.getBrightness(LightLayer.SKY, blockPos) - level.getSkyDarken();
        if(j == 15){
            ItemStack stack = player.getItemInHand(InteractionHand.MAIN_HAND);
            if(ChakraUtil.CITRINE_FOOD(stack) != ItemStack.EMPTY.getItem()){
                if(player.getInventory().getFreeSlot() == -1){
                    player.drop(ChakraUtil.CITRINE_FOOD(stack).getDefaultInstance(), false);
                }else {
                    player.addItem(ChakraUtil.CITRINE_FOOD(stack).getDefaultInstance());
                }
            }
        }
    }

    //-------------ROOT--------------
    public static void blackOnyx(Player player){
        player.getAttribute(Attributes.ATTACK_DAMAGE).addTransientModifier(new AttributeModifier("attack_damage", 0.20D, AttributeModifier.Operation.ADDITION));
    }

    public static void mahogany(Player player){
        //TODO: In all attribute modifiers, make it better
        boolean hasWeapon = player.getItemInHand(InteractionHand.MAIN_HAND).is(Tags.Items.TOOLS_SWORDS);
        AttributeInstance instance = player.getAttribute(Attributes.ATTACK_SPEED);
        double j = instance.getValue();
        if(hasWeapon){
            player.getAttribute(Attributes.ATTACK_SPEED).addTransientModifier(new AttributeModifier("attack_speed_boost", -0.2D, AttributeModifier.Operation.ADDITION));
        }
        //player.getAttributeBaseValue()
    }

    public static void NOTblue_lace_agate(Player player) {
        if (!player.getEyeInFluidType().isAir()) {
            int j = player.getAirSupply();
            player.setAirSupply(j / 2);
        }
    }

    public static void malachite(Player player) {

    }
}
