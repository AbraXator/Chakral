package net.AbraXator.chakral.chakra.chakras;

import com.mojang.blaze3d.vertex.PoseStack;
import net.AbraXator.chakral.chakra.Chakra;
import net.AbraXator.chakral.chakra.ChakraStrength;
import net.AbraXator.chakral.chakra.ChakraType;
import net.AbraXator.chakral.client.gui.chakralnexus.ChakralNexusScreen;
import net.minecraft.network.chat.Style;

import java.util.List;

public class AzuriteChakra extends Chakra {
    public AzuriteChakra(ChakraType type, ChakraStrength chakraStrength) {
        super(type, chakraStrength);
    }

    @Override
    public void openInfoSidePanel(ChakralNexusScreen screen, PoseStack poseStack, int x, int y) {

    }

    @Override
    public List<Style> getColors() {
        return chakraColor("17C252", "35AD35", "0d9b2b", "00895c", "117b95", "0a6fad", "0a4fad");
    }
}