package net.AbraXator.chakral.chakra.chakras;

import net.AbraXator.chakral.chakra.Chakra;
import net.AbraXator.chakral.chakra.ChakraStrength;
import net.AbraXator.chakral.chakra.ChakraType;
import net.AbraXator.chakral.utils.ChakralLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public class DefaultChakra extends Chakra {
    public DefaultChakra() {
        super(new ChakralLocation("default"), ChakraType.CROWN, ChakraStrength.FAINT);
    }
}
