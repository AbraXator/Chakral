package net.AbraXator.chakral.client.gui.necklace;

import net.AbraXator.chakral.chakra.ChakraUtil;
import net.AbraXator.chakral.init.ModItems;
import net.AbraXator.chakral.init.ModTags;
import net.AbraXator.chakral.items.NecklaceItem;
import net.AbraXator.chakral.items.StoneHoldingItem;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class NecklaceSlot extends Slot {

    public NecklaceSlot(Container pContainer, int pSlot, int pX, int pY) {
        super(pContainer, pSlot, pX, pY);
    }

    @Override
    public boolean mayPlace(ItemStack pStack) {
        return pStack.is(ModTags.Items.NECKLACES);
    }

    @Override
    public void setChanged() {
        ChakraUtil.stoneIndexInSlot(getItem()).forEach((itemStack, integer) -> this.container.setItem(integer + getIndex(getItem()), itemStack));
    }

    private int getIndex(ItemStack stack) {
        return getItem().is(ModItems.GOLDEN_NECKLACE.get()) ? 0 : 1;
    }

    @Override
    public void onTake(Player pPlayer, ItemStack pStack) {
        if (pStack.getItem() instanceof StoneHoldingItem stoneHoldingItem) {
            for (int i = 1; i <= stoneHoldingItem.stonesAmount(); i++) {
                container.setItem(i + getIndex(pStack), ItemStack.EMPTY);
            }
        }
    }
}
