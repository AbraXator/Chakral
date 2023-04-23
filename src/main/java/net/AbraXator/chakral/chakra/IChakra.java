package net.AbraXator.chakral.chakra;

import com.mojang.blaze3d.vertex.PoseStack;
import net.AbraXator.chakral.client.gui.chakralnexus.ChakralNexusScreen;
import net.minecraft.network.chat.Style;

import java.util.List;

public interface IChakra {
    void openInfoSidePanel(ChakralNexusScreen screen, PoseStack poseStack, int x, int y);

    List<Style> getColors();
}
