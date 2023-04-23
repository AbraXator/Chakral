package net.AbraXator.chakral.chakra.chakras;

import com.mojang.blaze3d.vertex.PoseStack;
import net.AbraXator.chakral.chakra.Chakra;
import net.AbraXator.chakral.chakra.ChakraStrength;
import net.AbraXator.chakral.chakra.ChakraType;
import net.AbraXator.chakral.client.gui.chakralnexus.ChakralNexusScreen;
import net.minecraft.network.chat.Style;

import java.util.List;

public class GarnetSpessartineChakra extends Chakra {
    public GarnetSpessartineChakra(ChakraType type, ChakraStrength chakraStrength) {
        super(type, chakraStrength);
    }

    @Override
    public void openInfoSidePanel(ChakralNexusScreen screen, PoseStack poseStack, int x, int y) {

    }

    @Override
    public List<Style> getColors() {
        return chakraColor("ffa250", "f58a3e", "f26d2a", "d65025", "bf3513", "a21c08");
    }
}
