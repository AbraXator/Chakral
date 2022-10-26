package net.AbraXator.chakral.blocks.entity.custom;

import net.AbraXator.chakral.blocks.ModBlocks;
import net.AbraXator.chakral.blocks.entity.ModBlockEntities;
import net.AbraXator.chakral.items.ModItems;
import net.AbraXator.chakral.networking.ModMessages;
import net.AbraXator.chakral.networking.packet.ItemStackSyncS2CPacket;
import net.AbraXator.chakral.recipes.MineralEnricherRecipe;
import net.AbraXator.chakral.recipes.ModRecipes;
import net.AbraXator.chakral.screen.MineralEnricherMenu;
import net.AbraXator.chakral.utils.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.property.Properties;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MineralEnricherBlockEntity extends BlockEntity implements MenuProvider {
    public final ItemStackHandler itemHandler = new ItemStackHandler(3) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if(!level.isClientSide()){
            }
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
    private MineralEnricherRecipe currentRecipe;

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
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
                return switch (pIndex) {
                    case 0 -> MineralEnricherBlockEntity.this.progress;
                    case 1 -> MineralEnricherBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex) {
                    case 0 -> MineralEnricherBlockEntity.this.progress = pValue;
                    case 1 -> MineralEnricherBlockEntity.this.maxProgress = pValue;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    public float getScaledProgress(){
        float size = 1.2f;
        int progress = data.get(0);
        int maxProgress = data.get(1);

        return maxProgress != 0 && MineralEnricherBlockEntity.this.progress != 0 ? progress * size / maxProgress : 0;
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

    public ItemStack getRenderStack(){
        ItemStack stack;
        if(!itemHandler.getStackInSlot(0).isEmpty()){
            stack = itemHandler.getStackInSlot(0);
        }else {
            stack = ItemStack.EMPTY;
        }
        return stack;
    }

    public void setHandler(ItemStackHandler itemStackHandler){
        for (int i = 0; i < itemStackHandler.getSlots(); i++) {
            itemHandler.setStackInSlot(i, itemStackHandler.getStackInSlot(i));
        }
    }

    public static void tick(Level level, BlockPos pos, BlockState state, MineralEnricherBlockEntity entity) {
        if (level.isClientSide()) {
            return;
        }else {
            //updateRecipe(entity);
            ModMessages.sendToClients(new ItemStackSyncS2CPacket(entity.itemHandler, entity.getBlockPos()));
            if (hasRecipe(entity)) {
                entity.progress++;
                setChanged(level, pos, state);
                if (entity.progress >= entity.maxProgress) {
                    craftItem(entity);
                }
            } else {
                entity.resetProgress();
                setChanged(level, pos, state);
            }
        }
    }

    //public static void updateRecipe(MineralEnricherBlockEntity entity) {
    //    Level level = entity.level;
    //    if (level != null && !level.isClientSide()) {
    //        ModRecipes.getRecipesByType(ModRecipes.MINERAL_ENRICHER_TYPE.get(), level).stream()
    //                .filter(recipe -> recipe.getInput().is(entity.itemHandler.getStackInSlot(0).getItem()))
    //                .findFirst()
    //                .ifPresent(recipe -> {
    //                    if (entity.currentRecipe == null || !entity.currentRecipe.equals(recipe)) {
    //                        entity.currentRecipe = recipe;
    //                    }
    //                });
    //    }
    //}

    public Block resultGen(ItemStack mineral){
        if(mineral.is(ModBlocks.ORANGE_MINERAL.get().asItem())){
            return ModBlocks.ORANGE_CRYSTAL.get();
        }
        if(mineral.is(ModBlocks.YELLOW_MINERAL.get().asItem())){
            return ModBlocks.YELLOW_CRYSTAL.get();
        }
        if(mineral.is(ModBlocks.RED_MINERAL.get().asItem())){
            return ModBlocks.BLUE_CRYSTAL.get();
        }
        if(mineral.is(ModBlocks.GREEN_MINERAL.get().asItem())){
            return ModBlocks.GREEN_CRYSTAL.get();
        }
        if(mineral.is(ModBlocks.BLUE_MINERAL.get().asItem())){
            return ModBlocks.BLUE_CRYSTAL.get();
        }
        if(mineral.is(ModBlocks.LIGHT_BLUE_MINERAL.get().asItem())){
            return ModBlocks.LIGHT_BLUE_CRYSTAL.get();
        }
        if(mineral.is(Blocks.AMETHYST_BLOCK.asItem())){
            return Blocks.AMETHYST_CLUSTER;
        }
        return Blocks.AIR;
    }

    private static void craftItem(MineralEnricherBlockEntity entity) {
        Level level = entity.level;
        BlockPos pos = entity.getBlockPos();
        BlockPos crystalPos = new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ());
        BlockState state = entity.resultGen(entity.itemHandler.getStackInSlot(2)).defaultBlockState();
        if (hasRecipe(entity) && level.getBlockState(crystalPos).is(ModTags.Blocks.AIR)) {
            entity.itemHandler.extractItem(1, 16, false);
            level.setBlock(crystalPos, state.setValue(BlockStateProperties.FACING, Direction.DOWN), 2);
            entity.resetProgress();
        }
    }

    private static boolean hasRecipe(MineralEnricherBlockEntity entity) {
        return entity.getDust() >= 16 && !entity.itemHandler.getStackInSlot(2).isEmpty();
    }

    private void resetProgress(){
        this.progress = 0;
    }

    public int getDust(){
        return this.itemHandler.getStackInSlot(1).getCount();
    }

    //public static boolean canInsertItemIntoOutputSlot(SimpleContainer inventory, ItemStack stack){
    //    return inventory.getItem(2).isEmpty();
    //}

    //public static boolean canInsertAmountIntoOutputSlot(SimpleContainer inventory){
    //    return 1 == inventory.getItem(2).getCount();
    //}

    @Override
    public Component getDisplayName() {
        return Component.literal("Mineral Enricher");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory, Player pPlayer) {
        return new MineralEnricherMenu(pContainerId, pInventory, this, this.data);
    }
}
