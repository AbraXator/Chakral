package net.AbraXator.chakral.server.chakra;

import net.AbraXator.chakral.server.init.ModItems;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

public enum NecklaceType implements StringRepresentable {
    GOLDEN(ModItems.GOLDEN_NECKLACE, 1, "golden"),
    DIAMOND(ModItems.DIAMOND_NECKLACE, 2, "diamond"),
    NETHERITE(ModItems.NETHERITE_NECKLACE,3, "netherite"),
    RAINBOW(ModItems.RAINBOW_NECKLACE, 4, "rainbow");

    final RegistryObject<Item> item;
    final int number;
    final String name;

    NecklaceType(RegistryObject<Item> item, int number, String name){
        this.item = item;
        this.number = number;
        this.name = name;
    }

    public RegistryObject<Item> getItem() {
        return item;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String getSerializedName() {
        return name;
    }
}
