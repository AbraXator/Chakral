package net.AbraXator.chakral.blocks.entity.custom;

import net.AbraXator.chakral.blocks.entity.ModBlockEntities;
import net.AbraXator.chakral.items.ModItems;
import net.AbraXator.chakral.recipes.MineralEnricherRecipe;
import net.AbraXator.chakral.screen.MineralEnricherMenu;
import net.AbraXator.chakral.utils.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class MineralEnricherBlockEntity extends BlockEntity implements MenuProvider {
    public final ItemStackHandler itemHandler = new ItemStackHandler(3){
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }

        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            return switch (slot){
                case 0 -> stack.is(ModTags.Items.MINERALS);
                case 1 -> stack.is(ModItems.SHARD_DUST.get());
                case 2 -> true;
                default -> super.isItemValid(slot, stack);
            };
        }
    };

    private LazyOptional<IItemHandler> lazyOptional = LazyOptional.empty();

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
        if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY){
            return lazyOptional.cast();
        }

        return super.getCapability(cap);
    }

    public final ContainerData data;
    private int progress = 0;
    private int maxProgress = 60;

    public MineralEnricherBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.MINERAL_ENRICHER_BLOCK_ENTITY.get(), pWorldPosition, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex){
                    case 0 -> MineralEnricherBlockEntity.this.progress;
                    case 1 -> MineralEnricherBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex){
                    case 0 -> MineralEnricherBlockEntity.this.progress = pValue;
                    case 1 -> MineralEnricherBlockEntity.this.maxProgress = pValue;
                };
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
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
        pTag.putInt("progress", this.progress);
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        itemHandler.deserializeNBT(pTag.getCompound("inventory"));
        progress = pTag.getInt("progress");
    }

    public static void tick(Level level, BlockPos pos, BlockState state, MineralEnricherBlockEntity entity){
        if(level.isClientSide()){
            return;
        }

        if(hasRecipe(entity)){
            entity.progress++;
            setChanged(level, pos, state);
            if(entity.progress >= entity.maxProgress){
                craftItem(entity);
            }
        }
        else {
            entity.resetProgress();
            setChanged(level, pos, state);
        }
    }

    private static void craftItem(MineralEnricherBlockEntity entity){
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        Optional<MineralEnricherRecipe> recipe = level.getRecipeManager()
                .getRecipeFor(MineralEnricherRecipe.Type.INSTANCE, inventory, level);

        if(hasRecipe(entity)){
            entity.itemHandler.extractItem(0, recipe.get().getDustAmount(), false);
            entity.itemHandler.extractItem(1, 1, false);
            entity.itemHandler.setStackInSlot(2, new ItemStack(recipe.get().getResultItem().getItem(),
                    entity.itemHandler.getStackInSlot(2).getCount() + 1));
            entity.resetProgress();

        }
    }

    private static boolean hasRecipe(MineralEnricherBlockEntity entity){
        Level level = entity.level;
        SimpleContainer container = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            container.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        Optional<MineralEnricherRecipe> recipe = level.getRecipeManager()
                .getRecipeFor(MineralEnricherRecipe.Type.INSTANCE, container, level);

        return recipe.isPresent() && container.getItem(1).getCount() >= recipe.get().getDustAmount();
        //&& canInsertAmountIntoOutputSlot(container) && canInsertItemIntoOutputSlot(container, recipe.get().getResultItem());
    }

    private void resetProgress(){
        this.progress = 0;
    }

    public static boolean canInsertItemIntoOutputSlot(SimpleContainer inventory, ItemStack stack){
        return inventory.getItem(2).isEmpty();
    }

    public static boolean canInsertAmountIntoOutputSlot(SimpleContainer inventory){
        return 1 == inventory.getItem(2).getCount();
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Mineral Builder");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory, Player pPlayer) {
        return new MineralEnricherMenu(pContainerId, pInventory, this, this.data);
    }
}
