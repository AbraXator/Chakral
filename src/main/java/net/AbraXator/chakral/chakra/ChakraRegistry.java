package net.AbraXator.chakral.chakra;

import net.AbraXator.chakral.Chakral;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;

import java.util.function.Supplier;

public class ChakraRegistry {
    public static final ResourceKey<Registry<Chakra>> CHAKRA_REGISTRY = ResourceKey.createRegistryKey(new ResourceLocation(Chakral.MOD_ID, "chakra"));
    //public static final Supplier<IForgeRegistry<Chakra>> CHAKRA_REGISTRY = CHAKRA.makeRegistry(() -> new RegistryBuilder<Chakra>().disableSaving().setMaxID(Integer.MAX_VALUE - 1).setName(CHAKRA_REGISTRY.location()));
}
