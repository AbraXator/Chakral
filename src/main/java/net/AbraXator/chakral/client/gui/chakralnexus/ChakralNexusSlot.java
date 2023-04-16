package net.AbraXator.chakral.client.gui.chakralnexus;

import net.AbraXator.chakral.capability.NecklaceCapProvider;
import net.AbraXator.chakral.chakra.IChakraProvider;
import net.AbraXator.chakral.items.NecklaceItem;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class ChakralNexusSlot extends Slot {
    private final Player player;

    public ChakralNexusSlot(Container pContainer, int pSlot, int pX, int pY, Player player) {
        super(pContainer, pSlot, pX, pY);
        this.player = player;
    }

    @Override
    public void setChanged() {
        if(this.getItem().getItem() instanceof NecklaceItem necklaceItem){
            player.getCapability(NecklaceCapProvider.NECKLACE_CAP).ifPresent(necklaceCap -> {
                for(int i = 0; i <= 3; i++){
                    String s = String.valueOf((i + 1));
                    CompoundTag tag = this.getItem().getTagElement("Stone" + s);
                    necklaceCap.setStone(ItemStack.of(tag), i);
                }
                necklaceCap.getStones().forEach(itemStack -> {
                    if (itemStack.getItem() instanceof IChakraProvider iChakraProvider){
                        iChakraProvider.getChakra().setEnabled(true);
                    }
                });
                necklaceCap.setNecklace(getItem());
            });
        }

        super.setChanged();
    }

    @Override
    public void onTake(Player pPlayer, ItemStack pStack) {
        pPlayer.getCapability(NecklaceCapProvider.NECKLACE_CAP).ifPresent(necklaceCap -> {
            necklaceCap.setStones(NonNullList.withSize(4, ItemStack.EMPTY));
            necklaceCap.setNecklace(ItemStack.EMPTY);
        });
        super.onTake(pPlayer, pStack);
    }
}
