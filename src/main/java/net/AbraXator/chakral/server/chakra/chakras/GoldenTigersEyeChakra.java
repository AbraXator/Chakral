package net.AbraXator.chakral.server.chakra.chakras;

import net.AbraXator.chakral.server.chakra.Chakra;
import net.AbraXator.chakral.server.chakra.ChakraStrength;
import net.AbraXator.chakral.server.chakra.ChakraType;
import net.minecraft.network.chat.Style;

import java.util.List;

public class GoldenTigersEyeChakra extends Chakra {
    public GoldenTigersEyeChakra(ChakraType type, ChakraStrength chakraStrength) {
        super(type, chakraStrength);
    }

    @Override
    public List<Style> getColors() {
        return chakraColor("dc5813", "e1711d", "f0971c", "97603b", "643f27", "eaac1e", "eaac1e", "f0971c", "97603b", "643f27", "5e3114", "f0971c", "e1711d", "d65009");
    }
}
