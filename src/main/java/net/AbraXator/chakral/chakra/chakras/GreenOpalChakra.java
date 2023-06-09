package net.AbraXator.chakral.chakra.chakras;

import net.AbraXator.chakral.chakra.Chakra;
import net.AbraXator.chakral.chakra.ChakraStrength;
import net.AbraXator.chakral.chakra.ChakraType;
import net.minecraft.network.chat.Style;

import java.util.List;

public class GreenOpalChakra extends Chakra {
    public GreenOpalChakra(ChakraType type, ChakraStrength chakraStrength) {
        super(type, chakraStrength);
    }

    @Override
    public List<Style> getColors() {
        return chakraColor("c9cc33", "95b211", "6fa510", "4e8319", "2e790e", "b7c282", "bcc886", "9db67c");
    }
}
