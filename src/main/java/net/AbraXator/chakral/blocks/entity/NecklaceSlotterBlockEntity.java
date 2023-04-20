package net.AbraXator.chakral.blocks.entity;

import net.AbraXator.chakral.chakra.ChakraUtil;
import net.AbraXator.chakral.init.ModBlockEntities;
import net.AbraXator.chakral.init.ModTags;
import net.AbraXator.chakral.items.ChakraItem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.NotNull;


public class NecklaceSlotterBlockEntity extends BlockEntity {
    private final ItemStackHandler itemHandler = new ItemStackHandler(4){
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    private LazyOptional<IItemHandler> lazyOptional = LazyOptional.empty();

   public NecklaceSlotterBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
       super(ModBlockEntities.STONE_BENCH_BLOCK_ENTITY.get(), pWorldPosition, pBlockState);
   }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @org.jetbrains.annotations.Nullable Direction side) {
        if(cap == ForgeCapabilities.ITEM_HANDLER){
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
            if(itemHandler.getStackInSlot(i).getItem() instanceof ChakraItem chakraItem){
                inventory.setItem(i, ItemStack.EMPTY);
            } else {
                inventory.setItem(i, itemHandler.getStackInSlot(i));
            }
        }
        /*ItemStack itemStack = inventory.getItem(0);
        if(itemStack.is(ModTags.Items.NECKLACES)){
            ChakraUtil.getChakras(itemStack);
            inventory.setItem(0, itemStack);
        }*/
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    public static void tick(Level level, BlockPos blockPos, BlockState state, NecklaceSlotterBlockEntity blockEntity){
        //craftItem(blockEntity);
    }

    //public static void craftItem(NecklaceSlotterBlockEntity entity){
    //    ItemStack necklace = entity.itemHandler.getStackInSlot(0);
    //    CompoundTag nbtDataBefore = necklace.getTagElement("chakral.stones");
    //    ItemStack stone = entity.itemHandler.getStackInSlot(1);
    //    CompoundTag nbtData = new CompoundTag();
    //    if(entity.itemHandler.getStackInSlot(1) != ItemStack.EMPTY) {
    //        nbtData.putString("chakral.stones", stone.getDisplayName().getString());
    //    }else {
    //        necklace.setTag(nbtDataBefore);
    //    }
    //    necklace.setTag(nbtData);
    //    entity.itemHandler.setStackInSlot(0, necklace);
    //}

    //public static boolean hasNotReachedStackLimit(NecklaceSlotterBlockEntity blockEntity){
    //    return blockEntity.itemHandler.getStackInSlot(2).getCount()
    //            < blockEntity.itemHandler.getStackInSlot(2).getMaxStackSize();
    //}
}