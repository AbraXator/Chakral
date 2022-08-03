package net.AbraXator.chakramod.blocks.entity.custom;

import net.AbraXator.chakramod.blocks.entity.ModBlockEntities;
import net.AbraXator.chakramod.screen.StoneBenchMenu;
import net.AbraXator.chakramod.utils.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;


public class StoneBenchBlockEntity extends BlockEntity implements MenuProvider {
    private final ItemStackHandler itemHandler = new ItemStackHandler(2){
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    private LazyOptional<IItemHandler> lazyOptional = LazyOptional.empty();

    public StoneBenchBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.STONE_BENCH_BLOCK_ENTITY.get(), pWorldPosition, pBlockState);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal(" ");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory, Player pPlayer) {
        return new StoneBenchMenu(pContainerId, pInventory, this);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @org.jetbrains.annotations.Nullable Direction side) {
        if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY){
            return lazyOptional.cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyOptional = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyOptional.invalidate();
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", itemHandler.serializeNBT());
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        itemHandler.deserializeNBT(pTag.getCompound("inventory"));
    }

    public void drops(){
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for(int i = 0; i < itemHandler.getSlots(); i++){
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    public static void tick(Level level, BlockPos blockPos, BlockState state, StoneBenchBlockEntity blockEntity){
        //craftItem(blockEntity);
    }

    public static ItemStack getStoneInSlot(StoneBenchBlockEntity entity){
        return entity.itemHandler.getStackInSlot(1);
    }

    public static List<Item> getStones(){
        List<Item> stones = ForgeRegistries.ITEMS.tags().getTag(ModTags.Items.GEMS).stream().toList();
        System.out.println(stones);
        return stones;
    }

    //public static void craftItem(StoneBenchBlockEntity entity){
    //    ItemStack necklace = entity.itemHandler.getStackInSlot(0);
    //    CompoundTag nbtDataBefore = necklace.getTagElement("chakramod.stones");
    //    ItemStack stone = entity.itemHandler.getStackInSlot(1);
    //    CompoundTag nbtData = new CompoundTag();
    //    if(entity.itemHandler.getStackInSlot(1) != ItemStack.EMPTY) {
    //        nbtData.putString("chakramod.stones", stone.getDisplayName().getString());
    //    }else {
    //        necklace.setTag(nbtDataBefore);
    //    }
    //    necklace.setTag(nbtData);
    //    entity.itemHandler.setStackInSlot(0, necklace);
    //}

    //public static boolean hasNotReachedStackLimit(StoneBenchBlockEntity blockEntity){
    //    return blockEntity.itemHandler.getStackInSlot(2).getCount()
    //            < blockEntity.itemHandler.getStackInSlot(2).getMaxStackSize();
    //}
}