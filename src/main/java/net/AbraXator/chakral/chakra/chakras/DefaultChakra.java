package net.AbraXator.chakral.chakra.chakras;

import net.AbraXator.chakral.chakra.Chakra;
import net.AbraXator.chakral.chakra.ChakraStrength;
import net.AbraXator.chakral.chakra.ChakraType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public class DefaultChakra extends Chakra {
    public DefaultChakra() {
        super(Items.AIR, ChakraType.CROWN, ChakraStrength.FAINT);
    }
}
