package net.AbraXator.chakral.chakra.chakras;

import net.AbraXator.chakral.chakra.Chakra;
import net.AbraXator.chakral.chakra.ChakraStrength;
import net.AbraXator.chakral.chakra.ChakraType;
import net.AbraXator.chakral.chakra.Chakras;
import net.AbraXator.chakral.items.ModItems;
import net.minecraft.world.item.Item;

public class SugiliteChakra extends Chakra {
    public SugiliteChakra() {
        super(ModItems.SUGILITE.get(), ChakraType.CROWN, ChakraStrength.WEAKENED);
    }
}
