package net.AbraXator.chakral.chakra;

import cpw.mods.modlauncher.api.IEnvironment;
import net.AbraXator.chakral.Chakral;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;

import java.security.Key;
import java.util.function.Supplier;

public class ChakraRegistries {
    public static final ResourceKey<Registry<Chakra>> RESOURCE_KEY = ResourceKey.createRegistryKey(new ResourceLocation(Chakral.MOD_ID, "chakra"));
    public static final DeferredRegister<Chakra> CHAKRA = DeferredRegister.create(RESOURCE_KEY, Chakral.MOD_ID);
    public static final Supplier<IForgeRegistry<Chakra>> CHAKRA_REGISTRY = CHAKRA.makeRegistry(() -> new RegistryBuilder<Chakra>().disableSaving().setMaxID(Integer.MAX_VALUE - 1).setName(RESOURCE_KEY.location()));
}
