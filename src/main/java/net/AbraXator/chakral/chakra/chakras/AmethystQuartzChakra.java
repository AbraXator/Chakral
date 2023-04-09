package net.AbraXator.chakral.chakra.chakras;

import com.mojang.blaze3d.vertex.PoseStack;
import net.AbraXator.chakral.chakra.Chakra;
import net.AbraXator.chakral.chakra.ChakraStrength;
import net.AbraXator.chakral.chakra.ChakraType;
import net.AbraXator.chakral.client.gui.chakralnexus.ChakralNexusScreen;
import net.AbraXator.chakral.items.ModItems;
import net.AbraXator.chakral.utils.ChakralLocation;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class AmethystQuartzChakra extends Chakra {
    public AmethystQuartzChakra() {
        super(new ChakralLocation("amethyst_quartz"), ChakraType.CROWN, ChakraStrength.FAINT);
    }

    @Override
    public void onFunctionKeyPress(Player player, Level level) {

    }
}
