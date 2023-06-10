package net.AbraXator.chakral.init;

import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

public class ModWoodTypes {
    public static final WoodType WILTED = WoodType.register(new WoodType("wilted", BlockSetType.register(new BlockSetType("wilted"))));
}
