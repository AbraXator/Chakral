package net.AbraXator.chakral.screen.necklace;

import net.AbraXator.chakral.screen.ModMenuTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;

public class NecklaceMenu extends AbstractContainerMenu {
    public NecklaceMenu(int windowId, Inventory inv, FriendlyByteBuf data){
        this(windowId, inv);
    }

    public NecklaceMenu(int pContainerId, Inventory inventory){
        super(ModMenuTypes.NECKLACE_MENU.get(), pContainerId);
    }

    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        return null;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return true;
    }
}
