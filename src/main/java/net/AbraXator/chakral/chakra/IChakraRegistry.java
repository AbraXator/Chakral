package net.AbraXator.chakral.chakra;

import net.minecraft.resources.ResourceLocation;

import java.util.List;

public interface IChakraRegistry {
    void register(Chakra chakra);

    List<Chakra> getChakras();

    Chakra getChakraById(ResourceLocation id);
}
