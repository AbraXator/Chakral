package net.AbraXator.chakral.chakra.chakras;

import net.AbraXator.chakral.chakra.Chakra;
import net.AbraXator.chakral.chakra.ChakraType;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;

public class AmethystQuartzChakra extends Chakra {
    public AmethystQuartzChakra(Item stone, ChakraType type) {
        super(stone, type);
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
