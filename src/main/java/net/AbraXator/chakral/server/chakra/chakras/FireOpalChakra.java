package net.AbraXator.chakral.server.chakra.chakras;

import net.AbraXator.chakral.server.chakra.Chakra;
import net.AbraXator.chakral.server.chakra.ChakraStrength;
import net.AbraXator.chakral.server.chakra.ChakraType;
import net.minecraft.network.chat.Style;

import java.util.List;

public class FireOpalChakra extends Chakra {
    public FireOpalChakra(ChakraType type, ChakraStrength chakraStrength) {
        super(type, chakraStrength);
    }

    @Override
    public List<Style> getColors() {
        return chakraColor("de4400", "e7600a", "eaba07", "8dc008", "00a1c3", "005793", "003c93");
    }
}
