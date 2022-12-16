package net.AbraXator.chakral.chakra.chakras;

import net.AbraXator.chakral.chakra.Chakra;
import net.AbraXator.chakral.chakra.ChakraType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class GarnetSpessartineChakra extends Chakra {
    public GarnetSpessartineChakra(Item stone, ChakraType type) {
        super(stone, type);
    }

    @Override
    public void tick(Player player, Level level) {
        System.out.println("TICK");
    }

    @Override
    public void onFunctionKeyPress(Player player, Level level) {
        if(player.isInLava()){
            player.move(MoverType.SELF, new Vec3(0D, 2D, 0D));
        }
    }
}
