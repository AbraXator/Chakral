package net.AbraXator.chakral.chakra.chakras;

import net.AbraXator.chakral.chakra.Chakra;
import net.AbraXator.chakral.chakra.ChakraType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class AmazoniteChakra extends Chakra {
    public AmazoniteChakra(Item stone, ChakraType type) {
        super(stone, type);
    }

    @Override
    public void tick(Player player, Level level){

        super.tick(player, level);
    }

    @Override
    public void onEquip(Player player, Level level) {

        super.onEquip(player, level);
    }
}
