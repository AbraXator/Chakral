package net.AbraXator.chakral.chakra.chakras;

import net.AbraXator.chakral.chakra.Chakra;
import net.AbraXator.chakral.chakra.ChakraStrength;
import net.AbraXator.chakral.chakra.ChakraType;
import net.minecraft.network.chat.Style;

import java.util.List;

public class HeliotropeChakra extends Chakra {
    public HeliotropeChakra(ChakraType type, ChakraStrength chakraStrength) {
        super(type, chakraStrength);
    }

    @Override
    public List<Style> getColors() {
        return chakraColor("4aa470", "2e765d", "8c1f46", "176151", "004b4b", "07515e", "611f46", "07445e", "17384f");
    }
}
