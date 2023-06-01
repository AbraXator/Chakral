package net.AbraXator.chakral.chakra.chakras;

import com.mojang.blaze3d.vertex.PoseStack;
import net.AbraXator.chakral.chakra.Chakra;
import net.AbraXator.chakral.chakra.ChakraStrength;
import net.AbraXator.chakral.chakra.ChakraType;
import net.AbraXator.chakral.client.gui.chakralnexus.ChakralNexusScreen;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.List;

public class AmethystQuartzChakra extends Chakra {
    public AmethystQuartzChakra(ChakraType type, ChakraStrength chakraStrength) {
        super(type, chakraStrength);
    }

    @Override
    public void onFunctionKeyPress(Player player, Level level) {

    }

    @Override
    public void openInfoSidePanel(ChakralNexusScreen screen, PoseStack poseStack, int x, int y) {
        screen.blit(poseStack, x + 173, y, 176, 0, 68, 85);
    }

    @Override
    public List<Style> getColors() {
        return chakraColor( "581384", "964DA2", "B677B7", "CE9FCF", "EACFDB", "F3E3E1");
    }
}
