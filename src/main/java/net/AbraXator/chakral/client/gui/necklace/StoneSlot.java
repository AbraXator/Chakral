package net.AbraXator.chakral.client.gui.necklace;

import net.AbraXator.chakral.chakra.ChakraStrength;
import net.AbraXator.chakral.chakra.ChakraUtil;
import net.AbraXator.chakral.init.ModTags;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class StoneSlot extends Slot {
    private final ChakraStrength tier;
    private boolean isActive;

    public StoneSlot(Container pContainer, int pSlot, int pX, int pY, ChakraStrength tier) {
        super(pContainer, pSlot, pX, pY);
        this.tier = tier;
    }

    @Override
    public boolean mayPlace(ItemStack stone){
        return stone.is(ChakraStrength.getTag(tier)) && checkForNeklace();
    }

    @Override
    public boolean isActive() {
        return isActive && container.getItem(0).is(ModTags.Items.NECKLACES);
    }

    public boolean checkForNeklace(){
       return container.getItem(0).is(ModTags.Items.NECKLACES);
    }

    @Override
    public void setChanged() {
        ChakraUtil.addChakra(container.getItem(0), getItem(), tier.getIndex());
    }

    @Override
    public void onTake(Player pPlayer, ItemStack pStack) {
        ChakraUtil.removeChakra(pStack, tier.getIndex());
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }
}
