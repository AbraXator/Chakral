package net.AbraXator.chakral.chakra.chakras;

import net.AbraXator.chakral.chakra.Chakra;
import net.AbraXator.chakral.chakra.ChakraStrength;
import net.AbraXator.chakral.chakra.ChakraType;
import net.minecraft.network.chat.Style;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.List;

public class MoonstoneChakra extends Chakra {
    public MoonstoneChakra(ChakraType type, ChakraStrength chakraStrength) {
        super(type, chakraStrength);
    }

    @Override
    public void onFunctionKeyPress(Player player, Level level) {

    }

    @Override
    public List<Style> getColors() {
        return chakraColor("daeee2", "73748e", "a4eee7", "91ddeb", "70ccf1", "6fadf6", "787996", "5899f1");
    }
}
