package net.AbraXator.chakral.chakra.chakras;

import com.mojang.blaze3d.vertex.PoseStack;
import net.AbraXator.chakral.chakra.Chakra;
import net.AbraXator.chakral.chakra.ChakraStrength;
import net.AbraXator.chakral.chakra.ChakraType;
import net.AbraXator.chakral.client.gui.chakralnexus.ChakralNexusScreen;
import net.AbraXator.chakral.utils.ChakralLocation;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class MoonstoneChakra extends Chakra {
    public MoonstoneChakra(ResourceLocation id, ChakraType type, ChakraStrength chakraStrength) {
        super(id, type, chakraStrength);
    }

    @Override
    public void onFunctionKeyPress(Player player, Level level) {

    }

    @Override
    public void openInfoSidePanel(ChakralNexusScreen screen, PoseStack poseStack, int x, int y) {

    }

    @Override
    public Style getColor() {
        return null;
    }
}
