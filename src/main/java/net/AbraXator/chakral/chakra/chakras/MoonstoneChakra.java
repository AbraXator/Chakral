package net.AbraXator.chakral.chakra.chakras;

import net.AbraXator.chakral.chakra.Chakra;
import net.AbraXator.chakral.chakra.ChakraType;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class MoonstoneChakra extends Chakra {
    public MoonstoneChakra(Item stone, ChakraType type) {
        super(stone, type);
    }

    @Override
    public void onFunctionKeyPress(Player player, Level level) {

    }
}
