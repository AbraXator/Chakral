package net.AbraXator.chakral.client.gui.chakralnexus;

import net.AbraXator.chakral.capability.NecklaceCapProvider;
import net.AbraXator.chakral.chakra.Chakra;
import net.AbraXator.chakral.chakra.IChakraProvider;
import net.AbraXator.chakral.items.custom.NecklaceItem;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class ChakralNexusSlot extends Slot {
    public ChakralNexusSlot(Container pContainer, int pSlot, int pX, int pY) {
        super(pContainer, pSlot, pX, pY);
    }

    @Override
    public void setChanged() {
        if(this.getItem().getItem() instanceof NecklaceItem necklaceItem){
            Player player = Minecraft.getInstance().player;
            player.getCapability(NecklaceCapProvider.NECKLACE_CAP).ifPresent(necklaceCap -> {
                for(int i = 0; i <= 4; i++){
                    CompoundTag tag = this.getItem().getTagElement("Stone" + i);
                    necklaceCap.setStone(ItemStack.of(tag), i);
                }
                necklaceCap.getStones().forEach(itemStack -> {
                    if (itemStack.getItem() instanceof IChakraProvider iChakraProvider){
                        iChakraProvider.getChakra().setEnabled(true);
                    }
                });
            });
        }
    }
}
