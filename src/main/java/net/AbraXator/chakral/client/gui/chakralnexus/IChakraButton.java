package net.AbraXator.chakral.client.gui.chakralnexus;

import net.AbraXator.chakral.server.chakra.Chakra;

public interface IChakraButton {
    Chakra getRepresentedChakra();

    void setChakra(Chakra chakra);
}
