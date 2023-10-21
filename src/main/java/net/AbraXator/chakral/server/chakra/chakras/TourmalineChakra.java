package net.AbraXator.chakral.server.chakra.chakras;

import net.AbraXator.chakral.server.chakra.Chakra;
import net.AbraXator.chakral.server.chakra.ChakraStrength;
import net.AbraXator.chakral.server.chakra.ChakraType;
import net.minecraft.network.chat.Style;

import java.util.List;

public class TourmalineChakra extends Chakra {
    public TourmalineChakra(ChakraType type, ChakraStrength chakraStrength) {
        super(type, chakraStrength);
    }

    @Override
    public List<Style> getColors() {
        return chakraColor("ff437e", "ff6584", "ff8ca5", "f0a8a0", "f0d8a0", "ebf687", "bfed4c", "7ad221", "47c317");
    }
}
