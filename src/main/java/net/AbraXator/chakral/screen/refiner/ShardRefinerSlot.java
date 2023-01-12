package net.AbraXator.chakral.screen.refiner;

import net.minecraft.CharPredicate;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ShardRefinerSlot extends SlotItemHandler {
    int tier;
    ContainerData data;

    public ShardRefinerSlot(IItemHandler itemHandler, int pSlot, int pX, int pY, int tier, ContainerData data) {
        super(itemHandler, pSlot, pX, pY);
        this.tier = tier;
        this.data = data;
    }

    @Override
    public boolean isActive() {
        return data.get(3) >= tier;
    }
}
