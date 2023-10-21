package net.AbraXator.chakral.server.init;

import net.AbraXator.chakral.Chakral;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;

public class ModCarvers {
    public static final ResourceKey<ConfiguredWorldCarver<?>> MINERAL_CARVER = Chakral.createKey(Registries.CONFIGURED_CARVER, "mineral_carver");

    public static void boostrap(BootstapContext<ConfiguredWorldCarver<?>> context){
    }
}
