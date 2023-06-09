package net.AbraXator.chakral.chakra.chakras;

import net.AbraXator.chakral.chakra.Chakra;
import net.AbraXator.chakral.chakra.ChakraStrength;
import net.AbraXator.chakral.chakra.ChakraType;
import net.minecraft.network.chat.Style;

import java.util.List;

public class AmberChakra extends Chakra {
    public AmberChakra(ChakraType type, ChakraStrength chakraStrength) {
        super(type, chakraStrength);
    }


    @Override
    public List<Style> getColors() {
        return chakraColor("FBFC33", "FFC91A", "FFA41A", "F07500");
    }
}
