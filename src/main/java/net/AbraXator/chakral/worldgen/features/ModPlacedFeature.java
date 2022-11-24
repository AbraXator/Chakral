package net.AbraXator.chakral.worldgen.features;

import net.AbraXator.chakral.Chakral;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class ModPlacedFeature {
    public static final DeferredRegister<PlacedFeature> PLACED_FEATURE =
        DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, Chakral.MOD_ID);

    public static final RegistryObject<PlacedFeature> CRYSTAL_PLACED = PLACED_FEATURE.register("crystal",
            () -> new PlacedFeature(ModConfigureFeatures.CRYSTAL.getHolder().get(), commonOrePlacement(7, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-200), VerticalAnchor.aboveBottom(200)))));

    public static List<PlacementModifier> orePlacement(PlacementModifier p_195347_, PlacementModifier p_195348_) {
        return List.of(p_195347_, InSquarePlacement.spread(), p_195348_, BiomeFilter.biome());
    }

    public static List<PlacementModifier> commonOrePlacement(int p_195344_, PlacementModifier p_195345_) {
        return orePlacement(CountPlacement.of(p_195344_), p_195345_);
    }

    public static void register(IEventBus eventBus){
        PLACED_FEATURE.register(eventBus);
    }
}
