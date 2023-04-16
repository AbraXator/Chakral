package net.AbraXator.chakral.blocks.entity;

import net.AbraXator.chakral.blocks.ShardRefinerBlock;
import net.AbraXator.chakral.init.ModBlockEntities;
import net.AbraXator.chakral.init.ModItems;
import net.AbraXator.chakral.recipes.ShardRefinerRecipe;
import net.AbraXator.chakral.client.gui.refiner.ShardRefinerMenu;
import net.AbraXator.chakral.init.ModTags;
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
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class ShardRefinerBlockEntity extends BlockEntity implements MenuProvider {
    public int diamondCharge = 0;
    public int tier = 0;
    public int progress;
    //public int maxProgress = 66;
    public int maxProgress = 66;
    private final ItemStackHandler itemHandler = new ItemStackHandler(8) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }

        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            return switch (slot) {
                case 0 -> stack.is(Items.DIAMOND);
                case 1 -> ForgeRegistries.ITEMS.tags().getTag(ModTags.Items.SHARDS).stream().toList().contains(stack.getItem());
                case 2, 3, 4, 5, 6, 7, 8 -> true;
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

    public final ContainerData data;

    public ShardRefinerBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.SHARD_REFINER_BLOCK_ENTITY.get(), pWorldPosition, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex) {
                    case 0 -> diamondCharge;
                    case 1 -> progress;
                    case 2 -> shardToInt();
                    case 3 -> tier;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex) {
                    case 0:
                        diamondCharge = pValue;
                        break;
                    case 1:
                        progress = pValue;
                    case 3:
                        tier = pValue;
                }
            }

            @Override
            public int getCount() {
                return 4;
            }
        };
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("");
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @org.jetbrains.annotations.Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
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
        pTag.putInt("shard_refiner.tier", tier);
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        itemHandler.deserializeNBT(pTag.getCompound("inventory"));
        diamondCharge = pTag.getInt("shard_refiner.charge");
        progress = pTag.getInt("shard_refiner.crafting");
        pTag.getInt("shard_refiner.shard");
        tier = pTag.getInt("shard_refiner.tier");
    }

    public int shardToInt() {
        Item shard = this.itemHandler.getStackInSlot(1).getItem();
        if (shard.getDefaultInstance().is(Items.AMETHYST_SHARD)) {
            return 1;
        }
        if (shard.getDefaultInstance().is(ModItems.BLUE_SHARD.get())) {
            return 2;
        }
        if (shard.getDefaultInstance().is(ModItems.LIGHT_BLUE_SHARD.get())) {
            return 3;
        }
        if (shard.getDefaultInstance().is(ModItems.GREEN_SHARD.get())) {
            return 4;
        }
        if (shard.getDefaultInstance().is(ModItems.YELLOW_SHARD.get())) {
            return 5;
        }
        if (shard.getDefaultInstance().is(ModItems.ORANGE_SHARD.get())) {
            return 6;
        }
        if (shard.getDefaultInstance().is(ModItems.RED_SHARD.get())) {
            return 7;
        } else {
            return 0;
        }
    }

    public static void loadFuel(ShardRefinerBlockEntity entity) {
        if (entity.itemHandler.getStackInSlot(0).is(Items.DIAMOND) && entity.data.get(0) == 0) {
            entity.itemHandler.extractItem(0, 1, false);
            entity.data.set(0, 15);
        }
    }

    public boolean isCharged() {
        return this.data.get(0) > 0;
    }

    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, ShardRefinerBlockEntity pBlockEntity) {
        if(pLevel.isClientSide){
            return;
        }
        loadFuel(pBlockEntity);
        if(hasRecipe(pBlockEntity)){
            pBlockEntity.progress++;
            if(pBlockEntity.progress >= pBlockEntity.maxProgress){
                craftItem(pBlockEntity);
            }
        }else {
            pBlockEntity.resetProgress();
            setChanged(pLevel, pPos, pState);
        }
    }

    public void upgradeTier(int tier){
        switch (tier){
            case 1 -> this.data.set(3, 1);
            case 2 -> this.data.set(3, 2);
            case 3 -> this.data.set(3, 3);
            default -> this.data.set(3, 0);
        }
    }

    public static void craftItem(ShardRefinerBlockEntity entity) {
        ItemStack shard = entity.itemHandler.getStackInSlot(1);
        List<Item> possibleResults = new ArrayList<>();
        ItemStack result = ItemStack.EMPTY;
        int tierForCrafting = entity.tier + 1;
        ShardRefinerRecipe recipe = generateRecipe(entity).get();

        entity.itemHandler.extractItem(1, 1, false);
        entity.itemHandler.setStackInSlot(recipe.getRandomStone(tierForCrafting) + 4,
                new ItemStack(recipe.getResultItem(entity.level.registryAccess()).getItem(),
                entity.itemHandler.getStackInSlot(recipe.getRandomStone(tierForCrafting)).getCount() + 1));
        entity.resetProgress();
        --entity.diamondCharge;
    }

    public static boolean hasRecipe(ShardRefinerBlockEntity entity){
        return entity.isCharged() && generateRecipe(entity).isPresent();
    }

    public static Optional<ShardRefinerRecipe> generateRecipe(ShardRefinerBlockEntity entity){
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }
        return entity.level.getRecipeManager().getRecipeFor(ShardRefinerRecipe.Type.INSTANCE, inventory, entity.level);
    }

    private static boolean canInsertItemIntoOutputSlot(SimpleContainer inventory, ItemStack stack) {
        return inventory.getItem(2).getItem() == stack.getItem() || inventory.getItem(2).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleContainer inventory) {
        return inventory.getItem(2).getMaxStackSize() > inventory.getItem(2).getCount();
    }

    private void resetProgress() {
        this.progress = 0;
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
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