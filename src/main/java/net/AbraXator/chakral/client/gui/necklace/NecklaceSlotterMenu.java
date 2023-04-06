package net.AbraXator.chakral.client.gui.necklace;

import net.AbraXator.chakral.blocks.ModBlocks;
import net.AbraXator.chakral.chakra.ChakraStrength;
import net.AbraXator.chakral.client.gui.ModMenuTypes;
import net.AbraXator.chakral.items.ModItems;
import net.AbraXator.chakral.utils.ModTags;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.tags.ITag;

public class NecklaceSlotterMenu extends AbstractContainerMenu {
    private final ContainerLevelAccess access;
    private final Level level;
    public static boolean isDiamond;
    final Container necklaceSlot = new SimpleContainer(4) {
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
        this.level = inv.player.level;
        //slot 1 - necklace
        this.addSlot(new Slot(this.necklaceSlot, 0, 80, 22) {
            @Override
            public boolean mayPlace(ItemStack necklace) {
                if(isDiamond){
                    return necklace.is(ModItems.DIAMOND_NECKLACE.get());
                }else {
                    return necklace.is(ModItems.GOLDEN_NECKLACE.get());
                }
            }

            @Override
            public void onTake(Player pPlayer, ItemStack pStack) {
                if(NecklaceSlotterMenu.this.getSlot(1).hasItem()){
                    NecklaceSlotterMenu.this.getSlot(1).remove(1);
                }
            }

            @Override
            public void setChanged() {
                ItemStack necklace = NecklaceSlotterMenu.this.getSlot(0).getItem();
                if(ForgeRegistries.ITEMS.tags().getTag(ModTags.Items.NECKLACES).contains(necklace.getItem())) {
                    if (necklace.hasTag()) {
                        CompoundTag tag = necklace.getTag();
                        if(isDiamond(necklace)){
                            ItemStack stone1 = tag.get("Stone1") != null ? ItemStack.of(tag.getCompound("Stone1")) : ItemStack.EMPTY;
                            ItemStack stone2 = tag.get("Stone2") != null ? ItemStack.of(tag.getCompound("Stone2")) : ItemStack.EMPTY;
                            NecklaceSlotterMenu.this.getSlot(2).set(stone1);
                            NecklaceSlotterMenu.this.getSlot(3).set(stone2);
                        }else {
                            ItemStack stone = tag.get("Stone1") != null ? ItemStack.of(tag.getCompound("Stone1")) : ItemStack.EMPTY;
                            NecklaceSlotterMenu.this.getSlot(1).set(stone);
                        }
                    }
                }
            }
        });
        //slot 2 - stone golden necklace
        this.addSlot(new Slot(this.necklaceSlot, 1, 80, 46){
            @Override
            public boolean mayPlace(ItemStack stone){
                return NecklaceSlotterUtil.mayPlace(ChakraStrength.FAINT, stone);
            }

            @Override
            public boolean isActive() {
                return NecklaceSlotterMenu.this.getSlot(0).getItem().is(ModItems.GOLDEN_NECKLACE.get());
            }

            @Override
            public void setChanged() {
                NecklaceSlotterUtil.setChanged(NecklaceSlotterMenu.this.necklaceSlot.getItem(1), NecklaceSlotterMenu.this.necklaceSlot.getItem(0),
                        ModItems.GOLDEN_NECKLACE.get(), "Stone1", ChakraStrength.FAINT);
            }
            @Override
            public int getMaxStackSize() {
                return 1;
            }

            @Override
            public void onTake(Player pPlayer, ItemStack pStack) {
                NecklaceSlotterUtil.onTake(NecklaceSlotterMenu.this.necklaceSlot.getItem(0), ModItems.GOLDEN_NECKLACE.get(), "Stone1");
            }
        });
        //slot 3 - weakened stone diamond necklace
        this.addSlot(new Slot(this.necklaceSlot, 2, 71, 50){
            @Override
            public boolean mayPlace(ItemStack stone){
                return ForgeRegistries.ITEMS.tags().getTag(ModTags.Items.WEAKENED).contains(stone.getItem())
                        && NecklaceSlotterMenu.this.getSlot(0).hasItem();
            }

            @Override
            public boolean isActive() {
                return NecklaceSlotterMenu.this.getSlot(0).getItem().is(ModItems.DIAMOND_NECKLACE.get());
            }

            @Override
            public void setChanged() {
                ItemStack stone = NecklaceSlotterMenu.this.necklaceSlot.getItem(2);
                ItemStack necklace = NecklaceSlotterMenu.this.necklaceSlot.getItem(0);
                ITag<Item> weakenedItems = ForgeRegistries.ITEMS.tags().getTag(ModTags.Items.WEAKENED);
                if(necklace.is(ModItems.DIAMOND_NECKLACE.get())){
                    if(weakenedItems.contains(stone.getItem())){
                        CompoundTag nbt = necklace.getOrCreateTag();
                        nbt.put("Stone2", stone.serializeNBT());
                        necklace.setTag(nbt);
                    }
                }
            }

            @Override
            public int getMaxStackSize() {
                return 1;
            }

            @Override
            public void onTake(Player pPlayer, ItemStack pStack) {
                if (NecklaceSlotterMenu.this.getSlot(0).getItem().is(ModItems.DIAMOND_NECKLACE.get())) {
                    ItemStack necklace = NecklaceSlotterMenu.this.getSlot(0).getItem();
                    necklace.addTagElement("Stone2", ItemStack.EMPTY.serializeNBT());
                }
            }
        });
        //slot 4 - faint stone diamond necklace
        this.addSlot(new Slot(this.necklaceSlot, 3, 89, 50){
            @Override
            public boolean mayPlace(ItemStack stone){
                return ForgeRegistries.ITEMS.tags().getTag(ModTags.Items.FAINT).contains(stone.getItem())
                        && NecklaceSlotterMenu.this.getSlot(0).hasItem();
            }

            @Override
            public boolean isActive() {
                return NecklaceSlotterMenu.this.getSlot(0).getItem().is(ModItems.DIAMOND_NECKLACE.get());
            }

            @Override
            public void setChanged() {
                ItemStack stone = NecklaceSlotterMenu.this.necklaceSlot.getItem(3);
                ItemStack necklace = NecklaceSlotterMenu.this.necklaceSlot.getItem(0);
                ITag<Item> faintItems = ForgeRegistries.ITEMS.tags().getTag(ModTags.Items.FAINT);
                if(necklace.is(ModItems.DIAMOND_NECKLACE.get())){
                    if(faintItems.contains(stone.getItem())){
                        CompoundTag nbt = necklace.getOrCreateTag();
                        nbt.put("Stone1", stone.serializeNBT());
                        necklace.setTag(nbt);
                    }
                }
            }

            @Override
            public int getMaxStackSize() {
                return 1;
            }

            @Override
            public void onTake(Player pPlayer, ItemStack pStack) {
                if (NecklaceSlotterMenu.this.getSlot(0).getItem().is(ModItems.DIAMOND_NECKLACE.get())) {
                    ItemStack necklace = NecklaceSlotterMenu.this.getSlot(0).getItem();
                    necklace.addTagElement("Stone1", ItemStack.EMPTY.serializeNBT());
                }
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
        if(!pPlayer.level.isClientSide){
            ItemStack necklace = this.necklaceSlot.removeItem(0, this.necklaceSlot.getMaxStackSize());
            ItemStack stone = this.necklaceSlot.removeItem(1, this.necklaceSlot.getMaxStackSize());
            if(!necklace.isEmpty() && !stone.isEmpty() && necklace.hasTag()){
                necklace.removeTagKey("chakral.stones");
                pPlayer.addItem(stone);
                pPlayer.addItem(necklace);
            }else if(!necklace.isEmpty()){
                pPlayer.addItem(necklace);
            }
        }
    }

    public boolean isDiamond(ItemStack stack){
        return stack.is(ModItems.DIAMOND_NECKLACE.get());
    }

    // CREDIT GOES TO: diesieben07 | https://github.com/diesieben07/SevenCommons
    // must assign a slot number  to each of the slots used by the GUI.
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
    private static final int TE_INVENTORY_SLOT_COUNT = 2;  // must be the number of slots you have!

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
        return stillValid(this.access, pPlayer, ModBlocks.NECKLACE_SLOTTER.get());
    }
}
