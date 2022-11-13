package net.AbraXator.chakral.chakra;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.List;

public class ChakraUtil {
    public static int moonStoneCooldown = 0;
    public static int moonStoneMaxCooldown;
    public static final List<MobEffect> AMETHYST_QUARTZ_EFFECTS = List.of(MobEffects.DIG_SLOWDOWN, MobEffects.CONFUSION, MobEffects.BLINDNESS, MobEffects.HUNGER, MobEffects.WEAKNESS, MobEffects.UNLUCK);
    public static final List<MobEffect> SUGILITE_EFFECTS = List.of(MobEffects.DIG_SLOWDOWN, MobEffects.MOVEMENT_SLOWDOWN,MobEffects.CONFUSION, MobEffects.BLINDNESS, MobEffects.HUNGER, MobEffects.WEAKNESS, MobEffects.UNLUCK, MobEffects.HARM, MobEffects.POISON, MobEffects.WITHER, MobEffects.DARKNESS);
    public static final Item CITRINE_FOOD(ItemStack stack){
        if(stack.is(Items.PORKCHOP)) return Items.COOKED_PORKCHOP;
        if(stack.is(Items.BEEF)) return Items.COOKED_BEEF;
        if(stack.is(Items.MUTTON)) return Items.COOKED_MUTTON;
        if(stack.is(Items.RABBIT)) return Items.COOKED_RABBIT;
        if(stack.is(Items.CHICKEN)) return Items.COOKED_CHICKEN;
        if(stack.is(Items.SALMON)) return Items.COOKED_SALMON;
        if(stack.is(Items.COD)) return Items.COOKED_COD;
        if(stack.is(Items.KELP)) return Items.DRIED_KELP;
        if(stack.is(Items.POTATO)) return Items.BAKED_POTATO;
        if(stack.is(Items.CHORUS_FRUIT)) return Items.POPPED_CHORUS_FRUIT;
        return ItemStack.EMPTY.getItem();
    }
}

