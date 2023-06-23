package net.AbraXator.chakral.client.gui.necklace.slotter;

import net.AbraXator.chakral.chakra.ChakraStrength;
import net.AbraXator.chakral.client.gui.necklace.NecklaceSlot;
import net.AbraXator.chakral.client.gui.necklace.StoneSlot;
import net.AbraXator.chakral.init.ModBlocks;
import net.AbraXator.chakral.init.ModMenuTypes;
import net.AbraXator.chakral.items.ChakraItem;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class NecklaceSlotterMenu extends AbstractContainerMenu {
    private final ContainerLevelAccess access;
    private final Level level;
    public static boolean isDiamond;
    final Container container = new SimpleContainer(4) {
        public void setChanged() {
            super.setChanged();
            NecklaceSlotterMenu.this.slotsChanged(this);
        }
    };

    public NecklaceSlotterMenu(int pContainerId, Inventory inv, FriendlyByteBuf buf) {
        this(pContainerId, inv, ContainerLevelAccess.NULL);
    }

    public NecklaceSlotterMenu(int pContainerId, Inventory inv, final ContainerLevelAccess access) {
        super(ModMenuTypes.NECKLACE_SLOTTER_MENU.get(), pContainerId);
        this.access = access;
        this.level = inv.player.level();
        //slot 1 - necklace
        this.addSlot(new NecklaceSlot(this.container, 0, 80, 22));
        //slot 2 - stone golden necklace
        this.addSlot(new StoneSlot(this.container, 1, 80, 46, ChakraStrength.FAINT){
            @Override
            public boolean isActive() {
                return !isDiamond && checkForNeklace();
            }
        });
        //slot 3 - weakened stone diamond necklace
        this.addSlot(new StoneSlot(this.container, 2, 89, 50, ChakraStrength.FAINT){
            @Override
            public boolean isActive() {
                return isDiamond && checkForNeklace();
            }
        });
        //slot 4 - faint stone diamond necklace
        this.addSlot(new StoneSlot(this.container, 3,71, 50, ChakraStrength.WEAKENED){
            @Override
            public boolean isActive() {
                return isDiamond && checkForNeklace();
            }
        });

        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(inv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for(int k = 0; k < 9; ++k) {
            this.addSlot(new Slot(inv, k, 8 + k * 18, 142));
        }
    }

    @Override
    public void removed(Player pPlayer) {
        super.removed(pPlayer);
        for(int i = 0; i < container.getContainerSize(); i++){
            ItemStack itemStack = container.getItem(i);
            if(itemStack.getItem() instanceof ChakraItem){
                container.removeItem(i, 1);
            } else {
                pPlayer.addItem(itemStack);
            }
        }
        if(!pPlayer.level().isClientSide){
            ItemStack necklace = this.container.removeItem(0, this.container.getMaxStackSize());
            ItemStack stone = this.container.removeItem(1, this.container.getMaxStackSize());
            if(!necklace.isEmpty() && !stone.isEmpty() && necklace.hasTag()){
                necklace.removeTagKey("chakral.stones");
                pPlayer.addItem(stone);
                pPlayer.addItem(necklace);
            }else if(!necklace.isEmpty()){
                pPlayer.addItem(necklace);
            }
        }
    }

    /*CREDIT GOES TO: diesieben07 | https://github.com/diesieben07/SevenCommons
    must assign a slot number  to each of the slots used by the GUI.
    For this container, we can see both the tile inventory's slots as well as the player inventory slots and the hotbar.
    Each time we add a Slot to the container, it automatically increases the slotIndex, which means
    0 - 8 = hotbar slots (which will map to the InventoryPlayer slot numbers 0 - 8)
    9 - 35 = player inventory slots (which map to the InventoryPlayer slot numbers 9 - 35)
    36 - 44 = TileInventory slots, which map to our TileEntity slot numbers 0 - 8)*/
    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

    // THIS YOU HAVE TO DEFINE!
    private static final int TE_INVENTORY_SLOT_COUNT = 4;  // must be the number of slots you have!

    @Override
    public ItemStack quickMoveStack(@NotNull Player playerIn, int index) {
        Slot sourceSlot = slots.get(index);
        if (!sourceSlot.hasItem()) return ItemStack.EMPTY;  //EMPTY_ITEM
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
        if(playerIn instanceof ServerPlayer) sourceSlot.onTake(playerIn, copyOfSourceStack);
        return copyOfSourceStack;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return stillValid(this.access, pPlayer, ModBlocks.NECKLACE_SLOTTER.get());
    }
}
