package net.AbraXator.chakral.client.gui.necklace;

import net.AbraXator.chakral.chakra.ChakraUtil;
import net.AbraXator.chakral.init.ModItems;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class NecklaceSlot extends Slot {

    public NecklaceSlot(Container pContainer, int pSlot, int pX, int pY) {
        super(pContainer, pSlot, pX, pY);
    }

    @Override
    public void setChanged() {
        ChakraUtil.stoneIndexInSlot(getItem()).forEach((itemStack, integer) -> this.container.setItem(integer, itemStack));
    }

    @Override
    public void onTake(Player pPlayer, ItemStack pStack) {
        ChakraUtil.stoneIndexInSlot(pStack).forEach((itemStack, integer) -> this.container.setItem(integer, ItemStack.EMPTY));
    }
}
