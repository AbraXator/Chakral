package net.AbraXator.chakral.chakra.chakras;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.AbraXator.chakral.chakra.Chakra;
import net.AbraXator.chakral.chakra.ChakraStrength;
import net.AbraXator.chakral.chakra.ChakraType;
import net.AbraXator.chakral.client.gui.chakralnexus.ChakralNexusMenu;
import net.AbraXator.chakral.client.gui.chakralnexus.ChakralNexusScreen;
import net.AbraXator.chakral.utils.ChakralLocation;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AmethystQuartzChakra extends Chakra {
    public AmethystQuartzChakra(ChakraType type, ChakraStrength chakraStrength) {
        super(type, chakraStrength);
    }

    @Override
    public void onFunctionKeyPress(Player player, Level level) {

    }

    @Override
    public AbstractWidget openInfoSidePanel(int x, int y) {
        return new AbstractWidget(x + 173, y, 67, 85, CommonComponents.EMPTY) {
            @Override
            public void renderWidget(@NotNull PoseStack poseStack, int mouseX, int mouseY, float partialTicks) {
                blit(poseStack, x + 173, y, 176, 0, 67, 85);
            }

            @Override
            protected void updateWidgetNarration(NarrationElementOutput p_259858_) {
                this.defaultButtonNarrationText(p_259858_);
            }
        };
    }

    @Override
    public List<Style> getColors() {
        return chakraColor( "581384", "964DA2", "B677B7", "CE9FCF", "EACFDB", "F3E3E1");
    }
}
