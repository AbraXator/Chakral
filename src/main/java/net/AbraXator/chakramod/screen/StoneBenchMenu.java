package net.AbraXator.chakramod.screen;

import net.AbraXator.chakramod.blocks.entity.custom.StoneBenchBlockEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jetbrains.annotations.Nullable;

public class StoneBenchMenu extends AbstractContainerMenu {
    private final StoneBenchBlockEntity blockEntity;
    private final Level level;

    public StoneBenchMenu(int pContainerId, Inventory inv, FriendlyByteBuf extraData) {
        this(pContainerId, inv, inv.player.level.getBlockEntity(extraData.readBlockPos()));
    }

    public StoneBenchMenu(int pContainerId, Inventory inv, BlockEntity entity) {
        super(pMenuType, pContainerId);
        checkContainerSize(inv, 3);
        blockEntity = ((StoneBenchBlockEntity) entity);
        this.level = inv.player.level;


    }

    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        return null;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return false;
    }
}
