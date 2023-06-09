package net.AbraXator.chakral.chakra.chakras;

import net.AbraXator.chakral.chakra.Chakra;
import net.AbraXator.chakral.chakra.ChakraStrength;
import net.AbraXator.chakral.chakra.ChakraType;
import net.minecraft.network.chat.Style;

import java.util.List;

public class StillbiteChakra extends Chakra {
    public StillbiteChakra(ChakraType type, ChakraStrength chakraStrength) {
        super(type, chakraStrength);
    }

    @Override
    public List<Style> getColors() {
        return chakraColor("ffda90", "edbf78", "ed9343", "ed8132", "c35a28", "b24319");
    }
}
