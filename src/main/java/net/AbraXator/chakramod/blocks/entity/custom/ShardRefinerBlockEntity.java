package net.AbraXator.chakramod.blocks.entity.custom;

import net.AbraXator.chakramod.blocks.custom.ShardRefinerBlock;
import net.AbraXator.chakramod.blocks.entity.ModBlockEntities;
import net.AbraXator.chakramod.items.ModItems;
import net.AbraXator.chakramod.screen.ShardRefinerMenu;
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
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
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
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class ShardRefinerBlockEntity extends BlockEntity implements MenuProvider{
    public int diamondCharge = 0;
    public int progress;
    public int maxProgress = 66;
    private final ItemStackHandler itemHandler = new ItemStackHandler(3){
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }

        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            return switch (slot){
                case 0 -> stack.is(Items.DIAMOND);
                case 1 -> ForgeRegistries.ITEMS.tags().getTag(ModTags.Items.SHARDS).stream().toList().contains(stack.getItem());
                case 2 -> true;
                default -> super.isItemValid(slot, stack);
            };
        }
    };

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
    private final Map<Direction, LazyOptional<WrappedHandler>> directionLazyOptionalMap =
            Map.of(Direction.DOWN, LazyOptional.of(() -> new WrappedHandler(itemHandler, (i) -> i == 2, (i, s) -> false)),
                    Direction.NORTH, LazyOptional.of(() -> new WrappedHandler(itemHandler, (index) -> index == 0, (index, stack) -> true)),
                    Direction.SOUTH, LazyOptional.of(() -> new WrappedHandler(itemHandler, (index) -> index == 0, (index, stack) -> true)),
                    Direction.WEST, LazyOptional.of(() -> new WrappedHandler(itemHandler, (index) -> index == 0, (index, stack) -> true)),
                    Direction.EAST, LazyOptional.of(() -> new WrappedHandler(itemHandler, (index) -> index == 0, (index, stack) -> true)));

    protected final ContainerData data;

    public ShardRefinerBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.SHARD_REFINER_BLOCK_ENTITY.get(), pWorldPosition, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex) {
                    case 0 -> diamondCharge;
                    case 1 -> progress;
                    case 2 -> shardToInt();
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex){
                    case 0:
                        diamondCharge = pValue;
                        break;
                    case 1:
                        progress = pValue;
                };
            }

            @Override
            public int getCount() {
                return 3;
            }
        };
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("");
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @org.jetbrains.annotations.Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            if (side == null) {
                return lazyItemHandler.cast();
            }

            if (directionLazyOptionalMap.containsKey(side)) {
                Direction localDir = this.getBlockState().getValue(ShardRefinerBlock.FACING);

                if (side == Direction.UP || side == Direction.DOWN) {
                    return directionLazyOptionalMap.get(side).cast();
                }

                return switch (localDir) {
                    default -> directionLazyOptionalMap.get(side.getOpposite()).cast();
                    case EAST -> directionLazyOptionalMap.get(side.getClockWise()).cast();
                    case SOUTH -> directionLazyOptionalMap.get(side).cast();
                    case WEST -> directionLazyOptionalMap.get(side.getCounterClockWise()).cast();
                };
            }
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", itemHandler.serializeNBT());
        pTag.putInt("shard_refiner.charge", diamondCharge);
        pTag.putInt("shard_refiner.crafting", progress);
        pTag.putInt("shard_refiner.shard", shardToInt());
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        itemHandler.deserializeNBT(pTag.getCompound("inventory"));
        diamondCharge = pTag.getInt("shard_refiner.charge");
        progress = pTag.getInt("shard_refiner.crafting");
        pTag.getInt("shard_refiner.shard");
    }

    public int shardToInt(){
        Item shard = this.itemHandler.getStackInSlot(1).getItem();
        if(shard.getDefaultInstance().is(Items.AMETHYST_SHARD)){
            return 1;
        }
        if(shard.getDefaultInstance().is(ModItems.BLUE_SHARD.get())){
            return 2;
        }
        if(shard.getDefaultInstance().is(ModItems.LIGHT_BLUE_SHARD.get())){
            return 3;
        }
        if(shard.getDefaultInstance().is(ModItems.GREEN_SHARD.get())){
            return 4;
        }
        if(shard.getDefaultInstance().is(ModItems.YELLOW_SHARD.get())){
            return 5;
        }
        if(shard.getDefaultInstance().is(ModItems.ORANGE_SHARD.get())){
            return 6;
        }
        if(shard.getDefaultInstance().is(ModItems.RED_SHARD.get())){
            return 7;
        }else {
            return 0;
        }
    }

    public static void loadFuel(ShardRefinerBlockEntity entity){
        if(entity.itemHandler.getStackInSlot(0).is(Items.DIAMOND) && entity.data.get(0)==0){
            entity.itemHandler.extractItem(0, 1, false);
            entity.data.set(0, 15);
        }
    }

    public boolean isCharged(){
        return this.data.get(0) > 0;
    }

    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, ShardRefinerBlockEntity pBlockEntity) {
        loadFuel(pBlockEntity);
        if(hasRecipe(pBlockEntity)){
            pBlockEntity.progress++;
            if(pBlockEntity.progress == pBlockEntity.maxProgress){
                craftItem(pBlockEntity);
            }
        }else {
            pBlockEntity.resetProgress();
            setChanged(pLevel, pPos, pState);
        }
    }

    public static void craftItem(ShardRefinerBlockEntity entity){
        Random random = new Random();
        ItemStack shard = entity.itemHandler.getStackInSlot(1);
        if(shard.is(Items.AMETHYST_SHARD)){
            entity.resetProgress();
            List<Item> items = ForgeRegistries.ITEMS.tags().getTag(ModTags.Items.CROWN).stream().toList();
            //items.add(items.size(), ModItems.SHARD_DUST.get());
            //items.add(ModItems.SHARD_DUST.get());
            System.out.println(entity.data.get(0));
            entity.itemHandler.extractItem(1, 1, false);
            --entity.diamondCharge;
            if(random.nextInt(5) == 1){
                entity.itemHandler.insertItem(2, items.get(random.nextInt(items.size())).getDefaultInstance(), false);
                System.out.println("CRAFTED");
            }else {
                entity.itemHandler.insertItem(2, ModItems.SHARD_DUST.get().getDefaultInstance(), false);
                System.out.println("CRAFTED");
            }
        }
        if(shard.is(ModItems.GREEN_SHARD.get())){
            entity.resetProgress();
            List<Item> items = ForgeRegistries.ITEMS.tags().getTag(ModTags.Items.HEART).stream().toList();
            System.out.println(entity.data.get(0));
            entity.itemHandler.extractItem(1, 1, false);
            --entity.diamondCharge;
            if(random.nextInt(5) == 1){
                entity.itemHandler.insertItem(2, items.get(random.nextInt(items.size())).getDefaultInstance(), false);
            }else {
                entity.itemHandler.insertItem(2, ModItems.SHARD_DUST.get().getDefaultInstance(), false);
            }
        }
        if(shard.is(ModItems.RED_SHARD.get())){
            entity.resetProgress();
            List<Item> items = ForgeRegistries.ITEMS.tags().getTag(ModTags.Items.ROOT).stream().toList();
            System.out.println(entity.data.get(0));
            entity.itemHandler.extractItem(1, 1, false);
            --entity.diamondCharge;
            if(random.nextInt(5) == 1){
                entity.itemHandler.insertItem(2, items.get(random.nextInt(items.size())).getDefaultInstance(), false);
            }else {
                entity.itemHandler.insertItem(2, ModItems.SHARD_DUST.get().getDefaultInstance(), false);
            }
        }
        if(shard.is(ModItems.ORANGE_SHARD.get())){
            entity.resetProgress();
            List<Item> items = ForgeRegistries.ITEMS.tags().getTag(ModTags.Items.SACRAL).stream().toList();
            System.out.println(entity.data.get(0));
            entity.itemHandler.extractItem(1, 1, false);
            --entity.diamondCharge;
            if(random.nextInt(5) == 1){
                entity.itemHandler.insertItem(2, items.get(random.nextInt(items.size())).getDefaultInstance(), false);
            }else {
                entity.itemHandler.insertItem(2, ModItems.SHARD_DUST.get().getDefaultInstance(), false);
            }
        }
        if(shard.is(ModItems.YELLOW_SHARD.get())){
            entity.resetProgress();
            List<Item> items = ForgeRegistries.ITEMS.tags().getTag(ModTags.Items.SOLAR).stream().toList();
            System.out.println(entity.data.get(0));
            entity.itemHandler.extractItem(1, 1, false);
            --entity.diamondCharge;
            if(random.nextInt(5) == 1){
                entity.itemHandler.insertItem(2, items.get(random.nextInt(items.size())).getDefaultInstance(), false);
            }else {
                entity.itemHandler.insertItem(2, ModItems.SHARD_DUST.get().getDefaultInstance(), false);
            }
        }
        if(shard.is(ModItems.BLUE_SHARD.get())){
            entity.resetProgress();
            List<Item> items = ForgeRegistries.ITEMS.tags().getTag(ModTags.Items.THIRD_EYE).stream().toList();
            System.out.println(entity.data.get(0));
            entity.itemHandler.extractItem(1, 1, false);
            --entity.diamondCharge;
            if(random.nextInt(5) == 1){
                entity.itemHandler.insertItem(2, items.get(random.nextInt(items.size())).getDefaultInstance(), false);
            }else {
                entity.itemHandler.insertItem(2, ModItems.SHARD_DUST.get().getDefaultInstance(), false);
            }
        }
        if(shard.is(ModItems.LIGHT_BLUE_SHARD.get())){
            entity.resetProgress();
            List<Item> items = ForgeRegistries.ITEMS.tags().getTag(ModTags.Items.THORAT).stream().toList();
            System.out.println(entity.data.get(0));
            entity.itemHandler.extractItem(1, 1, false);
            --entity.diamondCharge;
            if(random.nextInt(5) == 1){
                entity.itemHandler.insertItem(2, items.get(random.nextInt(items.size())).getDefaultInstance(), false);
            }else {
                entity.itemHandler.insertItem(2, ModItems.SHARD_DUST.get().getDefaultInstance(), false);
            }
        }
    }

    public static boolean hasRecipe(ShardRefinerBlockEntity entity){
        return !entity.itemHandler.getStackInSlot(1).isEmpty()
                && entity.itemHandler.getStackInSlot(2).isEmpty()
                && entity.data.get(0)>0;
    }

    private void resetProgress(){
        this.progress = 0;
    }

    public void drops(){
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for(int i = 0; i < itemHandler.getSlots(); i++){
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory, Player pPlayer) {
        return new ShardRefinerMenu(pContainerId, pInventory, this, this.data);
    }
}
