package net.AbraXator.chakral.worldgen;

import net.AbraXator.chakral.Chakral;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModFeatures {
    public static final DeferredRegister<Feature<?>> REGISTER =
            DeferredRegister.create(ForgeRegistries.FEATURES, Chakral.MOD_ID);

    public static final RegistryObject<BigCrystalFeature> BIG_CRYSTAL_FEATURE = registerFeature("big_crystal", () ->
            new BigCrystalFeature(BigCrystalConfiguration.CODEC));

    private static <T extends Feature> RegistryObject<T> registerFeature(String name, Supplier<T> feature){
        RegistryObject<T>  toReturn = REGISTER.register(name, feature);
        return toReturn;
    }

    public static void register(IEventBus eventBus){
        REGISTER.register(eventBus);
    }
}
