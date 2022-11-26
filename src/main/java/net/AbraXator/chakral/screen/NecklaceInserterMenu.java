package net.AbraXator.chakral.screen;

import net.AbraXator.chakral.blocks.ModBlocks;
import net.AbraXator.chakral.chakra.ChakraStrenght;
import net.AbraXator.chakral.items.ModItems;
import net.AbraXator.chakral.utils.ModTags;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

public class NecklaceInserterMenu extends AbstractContainerMenu {
    private final ContainerLevelAccess access;
    private final Level level;
    public boolean isRainbow;
    final Container necklaceSlot = new SimpleContainer(8) {
        public void setChanged() {
            super.setChanged();
            NecklaceInserterMenu.this.slotsChanged(this);
        }
    };

    public NecklaceInserterMenu(int pContainerId, Inventory inv, FriendlyByteBuf buf) {
        this(pContainerId, inv, ContainerLevelAccess.NULL);
    }

    public NecklaceInserterMenu(int pContainerId, Inventory inv, final ContainerLevelAccess access) {
        super(ModMenuTypes.NECKLACE_SLOTTER_MENU.get(), pContainerId);
        this.access = access;
        this.level = inv.player.level;
        ItemStack NECKLACE = NecklaceInserterMenu.this.necklaceSlot.getItem(0);
        //slot 0 - necklace
        this.addSlot(new Slot(this.necklaceSlot, 0, 80, 37) {
            @Override
            public boolean mayPlace(ItemStack necklace) {
                if(ForgeRegistries.ITEMS.tags().getTag(ModTags.Items.NECKLACES).contains(necklace.getItem())){
                    return true;
                }else {
                    return false;
                }
            }

            @Override
            public void onTake(Player pPlayer, ItemStack pStack) {
                if(NecklaceInserterMenu.this.getSlot(1).hasItem()){
                    NecklaceInserterMenu.this.getSlot(1).remove(1);
                }
            }

            @Override
            public void setChanged() {
                ItemStack necklace = NecklaceInserterMenu.this.getSlot(0).getItem();
                if(ForgeRegistries.ITEMS.tags().getTag(ModTags.Items.NECKLACES).contains(necklace.getItem())) {
                    if (necklace.hasTag()) {
                        CompoundTag tag = necklace.getTag();
                        if(isRainbow){
                            ItemStack stone1 = tag.get("Stone1") != null ? ItemStack.of(tag.getCompound("Stone1")) : ItemStack.EMPTY;
                            ItemStack stone2 = tag.get("Stone2") != null ? ItemStack.of(tag.getCompound("Stone2")) : ItemStack.EMPTY;
                            ItemStack stone3 = tag.get("Stone3") != null ? ItemStack.of(tag.getCompound("Stone3")) : ItemStack.EMPTY;
                            NecklaceInserterMenu.this.getSlot(2).set(stone1);
                            NecklaceInserterMenu.this.getSlot(3).set(stone2);
                            NecklaceInserterMenu.this.getSlot(4).set(stone3);
                        }else {
                            ItemStack stone1 = tag.get("Stone1") != null ? ItemStack.of(tag.getCompound("Stone1")) : ItemStack.EMPTY;
                            ItemStack stone2 = tag.get("Stone2") != null ? ItemStack.of(tag.getCompound("Stone2")) : ItemStack.EMPTY;
                            ItemStack stone3 = tag.get("Stone3") != null ? ItemStack.of(tag.getCompound("Stone3")) : ItemStack.EMPTY;
                            ItemStack stone4 = tag.get("Stone4") != null ? ItemStack.of(tag.getCompound("Stone4")) : ItemStack.EMPTY;
                            NecklaceInserterMenu.this.getSlot(5).set(stone1);
                            NecklaceInserterMenu.this.getSlot(6).set(stone2);
                            NecklaceInserterMenu.this.getSlot(7).set(stone3);
                            NecklaceInserterMenu.this.getSlot(8).set(stone4);
                        }
                    }
                }
            }
        });
        //slot 1 - stone1 netherite necklace
        this.addSlot(new Slot(this.necklaceSlot, 1, 134, 72){
            @Override
            public boolean mayPlace(ItemStack stone){
                return NecklaceSlotterUtil.mayPlace(ChakraStrenght.FAINT, stone);
            }

            @Override
            public boolean isActive() {
                return NecklaceInserterMenu.this.getSlot(0).getItem().is(ModItems.NETHERITE_NECKLACE.get());
            }

            @Override
            public void setChanged() {
                NecklaceSlotterUtil.setChanged(NecklaceInserterMenu.this.necklaceSlot.getItem(1), NECKLACE,
                        ModItems.NETHERITE_NECKLACE.get(), "Stone1", ChakraStrenght.FAINT);
            }
            @Override
            public int getMaxStackSize() {
                return 1;
            }
            @Override
            public void onTake(Player pPlayer, ItemStack pStack) {
                NecklaceSlotterUtil.onTake(NECKLACE, ModItems.NETHERITE_NECKLACE.get(), "Stone1");
            }
        });
        //slot 2 - stone2 netherite necklace
        this.addSlot(new Slot(this.necklaceSlot, 2, 26, 72){
            @Override
            public boolean mayPlace(ItemStack stone){
                return NecklaceSlotterUtil.mayPlace(ChakraStrenght.WEAKENED, stone);
            }

            @Override
            public boolean isActive() {
                return NecklaceInserterMenu.this.getSlot(0).getItem().is(ModItems.NETHERITE_NECKLACE.get());
            }

            @Override
            public void setChanged() {
                NecklaceSlotterUtil.setChanged(NecklaceInserterMenu.this.necklaceSlot.getItem(2), NECKLACE,
                        ModItems.NETHERITE_NECKLACE.get(), "Stone2", ChakraStrenght.WEAKENED);
            }
            @Override
            public int getMaxStackSize() {
                return 1;
            }

            @Override
            public void onTake(Player pPlayer, ItemStack pStack) {
                NecklaceSlotterUtil.onTake(NECKLACE, ModItems.NETHERITE_NECKLACE.get(), "Stone2");
            }
        });
        //slot 3 - stone3 netherite necklace
        this.addSlot(new Slot(this.necklaceSlot, 3, 80, 90){
            @Override
            public boolean mayPlace(ItemStack stone){
                return NecklaceSlotterUtil.mayPlace(ChakraStrenght.POWERFUL, stone);
            }

            @Override
            public boolean isActive() {
                return NecklaceInserterMenu.this.getSlot(0).getItem().is(ModItems.NETHERITE_NECKLACE.get());
            }

            @Override
            public void setChanged() {
                NecklaceSlotterUtil.setChanged(NecklaceInserterMenu.this.necklaceSlot.getItem(3), NECKLACE,
                        ModItems.NETHERITE_NECKLACE.get(), "Stone3", ChakraStrenght.POWERFUL);
            }
            @Override
            public int getMaxStackSize() {
                return 1;
            }

            @Override
            public void onTake(Player pPlayer, ItemStack pStack) {
                NecklaceSlotterUtil.onTake(NECKLACE, ModItems.NETHERITE_NECKLACE.get(), "Stone3");
            }
        });
        //slot 4 - stone1 rainbow necklace
        this.addSlot(new Slot(this.necklaceSlot, 4, 134, 72){
            @Override
            public boolean mayPlace(ItemStack stone){
                return NecklaceSlotterUtil.mayPlace(ChakraStrenght.FAINT, stone);
            }

            @Override
            public boolean isActive() {
                return NecklaceInserterMenu.this.getSlot(0).getItem().is(ModItems.RAINBOW_NECKLACE.get());
            }

            @Override
            public void setChanged() {
                NecklaceSlotterUtil.setChanged(NecklaceInserterMenu.this.necklaceSlot.getItem(4), NECKLACE,
                        ModItems.RAINBOW_NECKLACE.get(), "Stone1", ChakraStrenght.FAINT);
            }
            @Override
            public int getMaxStackSize() {
                return 1;
            }

            @Override
            public void onTake(Player pPlayer, ItemStack pStack) {
                NecklaceSlotterUtil.onTake(NECKLACE, ModItems.RAINBOW_NECKLACE.get(), "Stone1");
            }
        });
        //slot 5 - stone2 rainbow necklace
        this.addSlot(new Slot(this.necklaceSlot, 5, 26, 72){
            @Override
            public boolean mayPlace(ItemStack stone){
                return NecklaceSlotterUtil.mayPlace(ChakraStrenght.WEAKENED, stone);
            }

            @Override
            public boolean isActive() {
                return NecklaceInserterMenu.this.getSlot(0).getItem().is(ModItems.RAINBOW_NECKLACE.get());
            }

            @Override
            public void setChanged() {
                NecklaceSlotterUtil.setChanged(NecklaceInserterMenu.this.necklaceSlot.getItem(5), NECKLACE,
                        ModItems.RAINBOW_NECKLACE.get(), "Stone1", ChakraStrenght.WEAKENED);
            }
            @Override
            public int getMaxStackSize() {
                return 1;
            }

            @Override
            public void onTake(Player pPlayer, ItemStack pStack) {
                NecklaceSlotterUtil.onTake(NECKLACE, ModItems.RAINBOW_NECKLACE.get(), "Stone2");
            }
        });
        //slot 6 - stone3 rainbow necklace
        this.addSlot(new Slot(this.necklaceSlot, 6, 89, 90){
            @Override
            public boolean mayPlace(ItemStack stone){
                return NecklaceSlotterUtil.mayPlace(ChakraStrenght.POWERFUL, stone);
            }

            @Override
            public boolean isActive() {
                return NecklaceInserterMenu.this.getSlot(0).getItem().is(ModItems.RAINBOW_NECKLACE.get());
            }

            @Override
            public void setChanged() {
                NecklaceSlotterUtil.setChanged(NecklaceInserterMenu.this.necklaceSlot.getItem(6), NECKLACE,
                        ModItems.RAINBOW_NECKLACE.get(), "Stone1", ChakraStrenght.POWERFUL);
            }
            @Override
            public int getMaxStackSize() {
                return 1;
            }

            @Override
            public void onTake(Player pPlayer, ItemStack pStack) {
                NecklaceSlotterUtil.onTake(NECKLACE, ModItems.RAINBOW_NECKLACE.get(), "Stone3");
            }
        });
        //slot 7 - stone4 rainbow necklace
        this.addSlot(new Slot(this.necklaceSlot, 7, 71, 90){
            @Override
            public boolean mayPlace(ItemStack stone){
                return NecklaceSlotterUtil.mayPlace(ChakraStrenght.ENLIGHTENED, stone);
            }

            @Override
            public boolean isActive() {
                return NecklaceInserterMenu.this.getSlot(0).getItem().is(ModItems.RAINBOW_NECKLACE.get());
            }

            @Override
            public void setChanged() {
                NecklaceSlotterUtil.setChanged(NecklaceInserterMenu.this.necklaceSlot.getItem(7), NECKLACE,
                        ModItems.RAINBOW_NECKLACE.get(), "Stone4", ChakraStrenght.ENLIGHTENED);
            }
            @Override
            public int getMaxStackSize() {
                return 1;
            }

            @Override
            public void onTake(Player pPlayer, ItemStack pStack) {
                NecklaceSlotterUtil.onTake(NECKLACE, ModItems.RAINBOW_NECKLACE.get(), "Stone4");
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

    public boolean isAbleToChangeStates(){
        List<Boolean> list = new ArrayList<>();
        for (int i = 0; i < this.slots.size(); i++) {
            list.add(this.slots.get(i).hasItem());
        }
        return !list.contains(true);
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
        return stillValid(this.access, pPlayer, ModBlocks.NECKLACE_INSERTER.get());
    }
}
