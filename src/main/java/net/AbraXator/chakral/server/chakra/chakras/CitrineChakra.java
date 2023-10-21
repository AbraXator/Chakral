package net.AbraXator.chakral.server.chakra.chakras;

import net.AbraXator.chakral.server.chakra.Chakra;
import net.AbraXator.chakral.server.chakra.ChakraStrength;
import net.AbraXator.chakral.server.chakra.ChakraType;
import net.minecraft.network.chat.Style;

import java.util.List;

public class CitrineChakra extends Chakra {
    public CitrineChakra(ChakraType type, ChakraStrength chakraStrength) {
        super(type, chakraStrength);
    }

    @Override
    public List<Style> getColors() {
        return chakraColor("f1ff97", "fcf680", "f6d86c", "f6c145", "e8851a", "ea7211", "d9610c");
    }
}
