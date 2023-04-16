package net.AbraXator.chakral.chakra.chakras;

import net.AbraXator.chakral.chakra.Chakra;
import net.AbraXator.chakral.chakra.ChakraStrength;
import net.AbraXator.chakral.chakra.ChakraType;
import net.AbraXator.chakral.utils.ChakralLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class MoonstoneChakra extends Chakra {
    public MoonstoneChakra() {
        super(new ChakralLocation("moon_stone"), ChakraType.CROWN, ChakraStrength.WEAKENED);
    }

    @Override
    public void onFunctionKeyPress(Player player, Level level) {

    }
}
