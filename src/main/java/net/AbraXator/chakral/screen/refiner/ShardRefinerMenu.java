package net.AbraXator.chakral.screen.refiner;

import net.AbraXator.chakral.blocks.ModBlocks;
import net.AbraXator.chakral.blocks.entity.custom.ShardRefinerBlockEntity;
import net.AbraXator.chakral.screen.ModMenuTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.SlotItemHandler;

public class ShardRefinerMenu extends AbstractContainerMenu {
    public final ShardRefinerBlockEntity blockEntity;
    private final Level level;
    public final ContainerData data;

    public ShardRefinerMenu(int pContainerId, Inventory inv, FriendlyByteBuf friendlyByteBuf) {
        this(pContainerId, inv, inv.player.level.getBlockEntity(friendlyByteBuf.readBlockPos()), new SimpleContainerData(4));
    }

    public ShardRefinerMenu(int pContainerId, Inventory inv, BlockEntity entity, ContainerData data) {
        super(ModMenuTypes.SHARD_REFINER_MENU.get(), pContainerId);
        checkContainerSize(inv,8);
        blockEntity = ((ShardRefinerBlockEntity) entity);
        level = inv.player.level;
        this.data = data;

        addPlayerInventory(inv);
        addPlayerHotbar(inv);

        this.blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler -> {
            this.addSlot(new SlotItemHandler(handler, 0, 26 , 15));     //DIAMOND
            this.addSlot(new SlotItemHandler(handler, 1, 80 , 35));     //SHARD
            this.addSlot(new SlotItemHandler(handler, 2, 152, 35)); //DUST_1
            this.addSlot(new ShardRefinerSlot(handler, 3, 134, 35, 2, data)); //DUST_2
            this.addSlot(new ShardRefinerSlot(handler, 4, 152, 53, 0, data)); //FAINT
            this.addSlot(new ShardRefinerSlot(handler, 5, 152, 17, 1, data)); //WEAKENED
            this.addSlot(new ShardRefinerSlot(handler, 6, 134, 53, 2, data)); //POWERFUL
            this.addSlot(new ShardRefinerSlot(handler, 7, 134, 17, 3, data)); //WEAKENED
        });

        addDataSlots(data);
    }

    public boolean isCrafting(){
        return data.get(1)>0;
    }

    public int getScaledProgress() {
        int progress = this.data.get(1);
        int maxProgress = 66;  // Max Progress
        int progressArrowSize = 22; // This is the height in pixels of your arrow

        return progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }

    public int dataToInt(){
        int chakraTypeData = this.data.get(2);
        return switch (chakraTypeData){
            case 1 -> 14;
            case 2 -> 31;
            case 3 -> 48;
            case 4 -> 65;
            case 5 -> 82;
            case 6 -> 99;
            case 7 -> 116;
            default -> 0;
        };
    }

    // CREDIT GOES TO: diesieben07 | https://github.com/diesieben07/SevenCommons
    // must assign a slot number to each of the slots used by the GUI.
    // For this container, we can see both the tile inventory's slots as well as the player inventory slots and the hotbar.
    // Each time we add a Slot to the container, it automatically increases the slotIndex, which means
    //  0 - 8 = hotbar slots (which will map to the InventoryPlayer slot numbers 0 - 8)
    //  9 - 35 = player inventory slots (which map to the InventoryPlayer slot numbers 9 - 35)
    //  36 - 44 = TileInventory slots, which map to our TileEntity slot numbers 0 - 8)
    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

    // THIS YOU HAVE TO DEFINE!
    private static final int TE_INVENTORY_SLOT_COUNT = 3;  // must be the number of slots you have!

    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
        Slot sourceSlot = slots.get(index);
        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;  //EMPTY_ITEM
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        // Check if the slot clicked is one of the vanilla container slots
        if (index < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            // This is a vanilla container slot so merge the stack into the tile inventory
            if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX
                    + TE_INVENTORY_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;  // EMPTY_ITEM
            }
        } else if (index < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
            // This is a TE slot so merge the stack into the players inventory
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            System.out.println("Invalid slotIndex:" + index);
            return ItemStack.EMPTY;
        }
        // If stack size == 0 (the entire stack was moved) set slot contents to null
        if (sourceStack.getCount() == 0) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }
        sourceSlot.onTake(playerIn, sourceStack);
        return copyOfSourceStack;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()),
                pPlayer, ModBlocks.SHARD_REFINER.get());
    }

    private void addPlayerInventory(Inventory playerInventory) {
        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for(int k = 0; k < 9; ++k) {
            this.addSlot(new Slot(playerInventory, k, 8 + k * 18, 142));
        }
    }
}
