package net.AbraXator.chakramod.screen;

import net.AbraXator.chakramod.blocks.ModBlocks;
import net.AbraXator.chakramod.blocks.entity.custom.StoneBenchBlockEntity;
import net.AbraXator.chakramod.items.ModItems;
import net.AbraXator.chakramod.utils.ModTags;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.stream.Stream;

public class StoneBenchMenu extends AbstractContainerMenu {
    private final StoneBenchBlockEntity blockEntity;
    private final Level level;
    final Container necklaceSlot = new SimpleContainer(2) {
        public void setChanged() {
            super.setChanged();
            StoneBenchMenu.this.slotsChanged(this);
        }
    };

    public StoneBenchMenu(int pContainerId, Inventory inv, FriendlyByteBuf friendlyByteBuf) {
        this(pContainerId, inv, inv.player.level.getBlockEntity(friendlyByteBuf.readBlockPos()));
    }

    public StoneBenchMenu(int pContainerId, Inventory inv, BlockEntity entity) {
        super(ModMenuTypes.STONE_BENCH_MENU.get(), pContainerId);
        blockEntity = ((StoneBenchBlockEntity) entity);
        this.level = inv.player.level;
        //slot 1 - necklace
        this.addSlot(new Slot(this.necklaceSlot, 0, 80, 22) {

            @Override
            public boolean mayPlace(ItemStack necklace) {
                if(necklace.is(ModItems.GOLDEN_NECKLACE.get())){
                    return true;
                }else {
                    return false;
                }
            }

            @Override
            public void onTake(Player pPlayer, ItemStack pStack) {
                ItemStack stone = StoneBenchMenu.this.necklaceSlot.getItem(1);
                List<Item> tagList = ForgeRegistries.ITEMS.tags().getTag(ModTags.Items.GEMS).stream().toList();
                Stream<Item> tagStream = ForgeRegistries.ITEMS.tags().getTag(ModTags.Items.GEMS).stream();;
                if(tagList.contains(stone.getItem())){
                    CompoundTag nbt = new CompoundTag();
                    nbt.putString("chakramod.stones", stone.getDisplayName().getString());
                    pStack.setTag(nbt);
                    StoneBenchMenu.this.necklaceSlot.removeItem(1, 1);
                }
            }

            @Override
            public void setChanged() {
                if(StoneBenchMenu.this.getSlot(0).getItem().is(ModItems.GOLDEN_NECKLACE.get())) {
                    ItemStack necklace = StoneBenchMenu.this.getSlot(0).getItem();
                    if (necklace.hasTag()) {
                        String nbt = necklace.getTag().getString("chakramod.stones").toString().replace("[", "").replace("]", "").replace(" ", "_").toLowerCase();
                        ItemStack stone = ForgeRegistries.ITEMS.getValue(new ResourceLocation("chakramod:" + nbt)).getDefaultInstance();
                        if(!StoneBenchMenu.this.getSlot(1).hasItem()){
                            StoneBenchMenu.this.setItem(1, 1, stone);
                        }
                    }
                }
            }
        });
        //slot 2 - stone
        this.addSlot(new Slot(this.necklaceSlot, 1, 80, 46){
            @Override
            public boolean mayPlace(ItemStack stone){
                if(ForgeRegistries.ITEMS.tags().getTag(ModTags.Items.GEMS).stream().toList().contains(stone.getItem()) && StoneBenchMenu.this.getSlot(0).hasItem()){
                    return true;
                }else {
                    return false;
                }
            }

            

            @Override
            public int getMaxStackSize() {
                return 1;
            }

            @Override
            public void onTake(Player pPlayer, ItemStack pStack) {
                if (StoneBenchMenu.this.getSlot(0).getItem().is(ModItems.GOLDEN_NECKLACE.get())) {
                    ItemStack necklace = StoneBenchMenu.this.getSlot(0).getItem();
                    if (necklace.hasTag()) {
                        necklace.removeTagKey("chakramod.stones");
                    }
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
            if(!stone.isEmpty() || !necklace.isEmpty()){
                pPlayer.addItem(necklace);
                pPlayer.addItem(stone);
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
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()), pPlayer, ModBlocks.STONE_BENCH.get());
    }
}
