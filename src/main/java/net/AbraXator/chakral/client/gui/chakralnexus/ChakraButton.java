package net.AbraXator.chakral.client.gui.chakralnexus;

import net.AbraXator.chakral.chakra.Chakra;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.resources.ResourceLocation;

public class ChakraButton extends ImageButton implements IChakraButton {
    private final ChakralNexusScreen chakralNexusScreen;
    private Chakra chakra;

    public ChakraButton(ChakralNexusScreen chakralNexusScreen, int pX, int pY, int pWidth, int pHeight, int pXTexStart, int pYTexStart, ResourceLocation pResourceLocation) {
        super(pX, pY, pWidth, pHeight, pXTexStart, pYTexStart, pResourceLocation, pButton -> {
            //chakra.openInfoSidePanel();
        });

        this.chakralNexusScreen = chakralNexusScreen;
    }

    @Override
    public void setChakra(Chakra chakra) {
        this.chakra = chakra;
    }

    @Override
    public Chakra getRepresentedChakra() {
        return chakra;
    }
}
