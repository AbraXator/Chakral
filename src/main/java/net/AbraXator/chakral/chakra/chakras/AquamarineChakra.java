package net.AbraXator.chakral.chakra.chakras;

import net.AbraXator.chakral.chakra.Chakra;
import net.AbraXator.chakral.chakra.ChakraStrength;
import net.AbraXator.chakral.chakra.ChakraType;
import net.minecraft.network.chat.Style;

import java.util.List;

public class AquamarineChakra extends Chakra {
    public AquamarineChakra(ChakraType type, ChakraStrength chakraStrength) {
        super(type, chakraStrength);
    }

    @Override
    public List<Style> getColors() {
        return chakraColor("1779BD", "299BE4", "33B3E7", "47D0ED", "40EADF", "82F3E5", "ABFFE5");
    }
}
