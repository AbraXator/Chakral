package net.AbraXator.chakral.server.chakra.chakras;

import net.AbraXator.chakral.server.chakra.Chakra;
import net.AbraXator.chakral.server.chakra.ChakraStrength;
import net.AbraXator.chakral.server.chakra.ChakraType;
import net.minecraft.network.chat.Style;

import java.util.List;

public class LabbradoriteChakra extends Chakra {
    public LabbradoriteChakra(ChakraType type, ChakraStrength chakraStrength) {
        super(type, chakraStrength);
    }

    @Override
    public List<Style> getColors() {
        return chakraColor("76ffe4", "26e9ed", "21ace4", "2182e4", "2657ed", "351fed", "4000cf");
    }
}
