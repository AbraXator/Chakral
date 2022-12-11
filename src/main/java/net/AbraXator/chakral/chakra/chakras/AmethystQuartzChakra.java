package net.AbraXator.chakral.chakra.chakras;

import net.AbraXator.chakral.chakra.Chakra;
import net.AbraXator.chakral.chakra.ChakraType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeMod;

public class AmethystQuartzChakra extends Chakra {
    public AmethystQuartzChakra(Item stone, ChakraType type) {
        super(stone, type);
    }

    @Override
    public void onEquip(Player player, Level level) {
        
        super.onEquip(player, level);
    }
}
