package net.AbraXator.chakral.chakra;

import com.mojang.blaze3d.vertex.PoseStack;
import net.AbraXator.chakral.client.gui.chakralnexus.ChakralNexusScreen;

public interface IChakraSidePanel {
    void openInfoSidePanel(ChakralNexusScreen screen, PoseStack poseStack, int x, int y);
}
