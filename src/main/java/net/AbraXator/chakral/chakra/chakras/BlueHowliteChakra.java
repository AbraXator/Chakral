package net.AbraXator.chakral.chakra.chakras;

import net.AbraXator.chakral.chakra.Chakra;
import net.AbraXator.chakral.chakra.ChakraStrength;
import net.AbraXator.chakral.chakra.ChakraType;
import net.minecraft.network.chat.Style;

import java.util.List;

public class BlueHowliteChakra extends Chakra {
    public BlueHowliteChakra(ChakraType type, ChakraStrength chakraStrength) {
        super(type, chakraStrength);
    }

    @Override
    public List<Style> getColors() {
        return chakraColor("00bfb0", "009da5", "098e9d", "1378a0", "096995", "10416c", "0a3a64");
    }
}
