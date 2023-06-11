package net.AbraXator.chakral.init;

import net.AbraXator.chakral.utils.ChakralLocation;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageEffects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public class ModDamageTypes {
    public static final ResourceKey<DamageType> ROSETTE_CACTOID = create("rosette_cactoid");

    private static ResourceKey<DamageType> create(String name){
        return ResourceKey.create(Registries.DAMAGE_TYPE, new ChakralLocation(name));
    }

    public static DamageSource getDamageSource(Level level, ResourceKey<DamageType> type){
        return new DamageSource(level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(type), null, null);
    }

    public static DamageSource getDamageSource(Level level, ResourceKey<DamageType> type, @Nullable Entity attacker, @Nullable Entity indirectAttacker){
        return new DamageSource(level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(type), attacker, indirectAttacker);
    }

    public static void bootstrap(BootstapContext<DamageType> context){
        context.register(ROSETTE_CACTOID, new DamageType("chakral.rosette_cactoid", 0.1F, DamageEffects.POKING));
    }
}
