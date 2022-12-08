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
    static final DeferredRegister<Chakra> DEFERRED_CHAKRAS = DeferredRegister
            .create(Keys.CHAKRAS, Keys.CHAKRAS.location().getNamespace());

    public static final Supplier<IForgeRegistry<Chakra>> CHAKRAS = DEFERRED_CHAKRAS
            .makeRegistry(ChakraRegistries::getChakraResourceBuilder);

    private static RegistryBuilder<Chakra> getChakraResourceBuilder() {
        return makeRegistry(Keys.CHAKRAS);
    }

    private static <T> RegistryBuilder<T> makeRegistry(ResourceKey<? extends Registry<T>> key) {
        return new RegistryBuilder<T>().setName(key.location()).setMaxID(Integer.MAX_VALUE - 1);
    }

    public static class Keys {
        public static final ResourceKey<Registry<Chakra>> CHAKRAS = ResourceKey
                .createRegistryKey(new ResourceLocation(Chakral.MOD_ID, "chakras"));
    }
}
