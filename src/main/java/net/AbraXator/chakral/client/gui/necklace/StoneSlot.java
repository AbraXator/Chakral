package net.AbraXator.chakral.client.gui.necklace;

import net.AbraXator.chakral.chakra.ChakraStrength;
import net.AbraXator.chakral.chakra.ChakraUtil;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

public class StoneSlot extends Slot {
    private final ChakraStrength tier;
    private boolean isActive;

    public StoneSlot(Container pContainer, int pSlot, int pX, int pY, ChakraStrength tier, boolean isActive) {
        super(pContainer, pSlot, pX, pY);
        this.tier = tier;
        this.isActive = isActive;
    }

    @Override
    public boolean mayPlace(ItemStack stone){
        return ForgeRegistries.ITEMS.tags().getTag(ChakraStrength.getTag(tier)).contains(stone.getItem());
    }

    @Override
    public boolean isActive() {
        return isActive;
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
