package net.AbraXator.chakral.networking.packet;

import net.AbraXator.chakral.screen.necklace.NecklaceMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import org.jetbrains.annotations.Nullable;

public class ModMenuProvider implements MenuProvider {
    @Override
    public Component getDisplayName() {
        return Component.literal("");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new NecklaceMenu(pContainerId, pPlayerInventory);
    }
}
