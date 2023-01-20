package net.AbraXator.chakral.chakra.chakras;

import net.AbraXator.chakral.chakra.Chakra;
import net.AbraXator.chakral.chakra.ChakraStrength;
import net.AbraXator.chakral.chakra.ChakraType;
import net.AbraXator.chakral.items.ModItems;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class AmethystQuartzChakra extends Chakra {
    public AmethystQuartzChakra() {
        super(ModItems.AMETHYST_QUARTZ.get(), ChakraType.CROWN, ChakraStrength.FAINT);
    }

    @Override
    public void onFunctionKeyPress(Player player, Level level) {
        int radius = 5;
        double playerX = player.getX();
        double playerZ = player.getZ();
        float playerRot = player.getYRot();
        double x = 5 * Mth.sin(playerRot) + playerX;
        double z = 5 * Mth.cos(playerRot) + playerZ;
        player.getDeltaMovement().add(x, player.getY(), z);
    }
}
