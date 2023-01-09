package net.AbraXator.chakral.blocks.entity.custom;

import com.mojang.datafixers.util.Pair;
import net.AbraXator.chakral.blocks.ModBlocks;
import net.AbraXator.chakral.blocks.custom.MineralEnricherBlock;
import net.AbraXator.chakral.blocks.entity.ModBlockEntities;
import net.AbraXator.chakral.items.ModItems;
import net.AbraXator.chakral.networking.ModMessages;
import net.AbraXator.chakral.networking.packet.FluidSyncS2CPacket;
import net.AbraXator.chakral.networking.packet.ItemStackSyncS2CPacket;
import net.AbraXator.chakral.screen.enricher.MineralEnricherMenu;
import net.AbraXator.chakral.utils.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
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
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MineralEnricherBlockEntity extends BlockEntity implements MenuProvider {
    public final ItemStackHandler itemHandler = new ItemStackHandler(3) {

        @Override
        protected void onContentsChanged(int slot) {
            super.onContentsChanged(slot);
            setChanged();
            if(!level.isClientSide()){
                updateDust(this.getStackInSlot(2).getCount());
            }
        }

        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            return switch (slot){
                case 0 -> stack.getCapability(ForgeCapabilities.FLUID_HANDLER_ITEM).isPresent();
                case 1 -> stack.is(ModItems.SHARD_DUST.get());
                case 2 -> stack.is(ModTags.Items.MINERALS);
                default -> super.isItemValid(slot, stack);
            };
        }
    };

    private LazyOptional<IItemHandler> lazyOptional = LazyOptional.empty();
    private LazyOptional<IFluidHandler> lazyFluidHandler = LazyOptional.empty();

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyOptional.cast();
        }
        if(cap == ForgeCapabilities.FLUID_HANDLER){
            return lazyFluidHandler.cast();
        }
        return super.getCapability(cap);
    }

    public final ContainerData data;
    private int progress = 0;
    private int maxProgress = 60;
    private final FluidTank fluidTank = new FluidTank(5000){
        @Override
        protected void onContentsChanged() {
            setChanged();
            if(!level.isClientSide()){
                ModMessages.sendToClients(new FluidSyncS2CPacket(this.fluid, worldPosition));
                level.setBlock(worldPosition, getBlockState().setValue(MineralEnricherBlock.WATER, !this.isEmpty()), 3);
            }
        }

        @Override
        public boolean isFluidValid(FluidStack stack) {
            return stack.getFluid().isSame(Fluids.WATER);
        }
    };

    public void setFluid(FluidStack stack){
        this.fluidTank.setFluid(stack);
    }

    public FluidStack getFluidStack(){
        return this.fluidTank.getFluid();
    }
    private int dustAmount = 16;

    public MineralEnricherBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.MINERAL_ENRICHER_BLOCK_ENTITY.get(), pWorldPosition, pBlockState);
        MineralEnricherBlockEntity entity = MineralEnricherBlockEntity.this;
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex) {
                    case 0 -> entity.progress;
                    case 1 -> entity.maxProgress;
                    case 2 -> entity.resultGen(entity.itemHandler.getStackInSlot(2)).getSecond();
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex) {
                    case 0 -> entity.progress = pValue;
                    case 1 -> entity.maxProgress = pValue;
                }
            }

            @Override
            public int getCount() {
                return 3;
            }
        };
    }

    public float getScaledProgress(MineralEnricherBlockEntity entity){
        float size = 1.2f;
        int progress = entity.progress;
        int maxProgress = data.get(1);

        return maxProgress != 0 && entity.progress != 0 ? progress * size / maxProgress : 0;
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyOptional = LazyOptional.of(() -> itemHandler);
        lazyFluidHandler = LazyOptional.of(() -> fluidTank);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyOptional.invalidate();
        lazyFluidHandler.invalidate();
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", itemHandler.serializeNBT());
        pTag.putInt("progress", this.progress);
        pTag = fluidTank.writeToNBT(pTag);
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        itemHandler.deserializeNBT(pTag.getCompound("inventory"));
        progress = pTag.getInt("progress");
        fluidTank.readFromNBT(pTag);
    }

    public int updateDust(int dustAmount){
        int j = 0;
        if(dustAmount <= 16 && dustAmount != 0){
            j  = 1;
        }
        if(dustAmount <= 32 && dustAmount > 16){
            j  = 2;
        }
        if(dustAmount <= 48 && dustAmount > 32){
            j  = 3;
        }
        if(dustAmount <= 64 && dustAmount > 48){
            j  = 4;
        }
        level.setBlock(worldPosition, getBlockState().setValue(MineralEnricherBlock.DUST, j), 3);
        return j;
    }

    public int getDustAmount(){
        dustAmount = this.getDust();
        int j = 0;
        if(dustAmount <= 16 && dustAmount != 0){
            j  = 1;
        }
        if(dustAmount <= 32 && dustAmount > 16){
            j  = 2;
        }
        if(dustAmount <= 48 && dustAmount > 32){
            j  = 3;
        }
        if(dustAmount <= 64 && dustAmount > 48){
            j  = 4;
        }
        return j;
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
             entity.updateDust(entity.getDust());
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
        if(hasFluidInSourceSlot(entity)){
            transferItemFluidToFluidTank(entity);
        }
    }

    public Pair<Block, Integer> resultGen(ItemStack mineral){
        if(mineral.is(ModBlocks.TRUE_WHITE_MINERAL.get().asItem())){
            return new Pair<>(ModBlocks.TRUE_WHITE_CRYSTAL.get(), 1);
        }
        if(mineral.is(ModBlocks.WHITE_MINERAL.get().asItem())){
            return new Pair<>(ModBlocks.WHITE_CRYSTAL.get(), 2);
        }
        if(mineral.is(ModBlocks.BLACK_MINERAL.get().asItem())){
            return new Pair<>(ModBlocks.BLACK_CRYSTAL.get(), 3);
        }
        if(mineral.is(ModBlocks.RED_MINERAL.get().asItem())){
            return new Pair<>(ModBlocks.RED_CRYSTAL.get(), 4);
        }
        if(mineral.is(ModBlocks.ORANGE_MINERAL.get().asItem())){
            return new Pair<>(ModBlocks.ORANGE_CRYSTAL.get(), 5);
        }
        if(mineral.is(ModBlocks.YELLOW_MINERAL.get().asItem())){
            return new Pair<>(ModBlocks.YELLOW_CRYSTAL.get(), 6);
        }
        if(mineral.is(ModBlocks.GREEN_MINERAL.get().asItem())){
            return new Pair<>(ModBlocks.GREEN_CRYSTAL.get(), 7);
        }
        if(mineral.is(ModBlocks.LIGHT_BLUE_MINERAL.get().asItem())){
            return new Pair<>(ModBlocks.LIGHT_BLUE_CRYSTAL.get(), 8);
        }
        if(mineral.is(ModBlocks.BLUE_MINERAL.get().asItem())){
            return new Pair<>(ModBlocks.BLACK_CRYSTAL.get(), 9);
        }
        if(mineral.is(Blocks.AMETHYST_BLOCK.asItem())){
            return new Pair<>(Blocks.AMETHYST_CLUSTER, 10);
        }
        return new Pair<>(Blocks.AIR, 0);
    }

    public Block buddingGen(ItemStack mineral){
        if(mineral.is(ModBlocks.TRUE_WHITE_MINERAL.get().asItem())){
            return ModBlocks.TRUE_WHITE_MINERAL.get();
        }
        if(mineral.is(ModBlocks.WHITE_MINERAL.get().asItem())){
            return ModBlocks.BUDDING_WHITE_MINERAL.get();
        }
        if(mineral.is(ModBlocks.BLACK_MINERAL.get().asItem())){
            return ModBlocks.BUDDING_BLACK_MINERAL.get();
        }
        if(mineral.is(ModBlocks.RED_MINERAL.get().asItem())){
            return ModBlocks.BUDDING_RED_MINERAL.get();
        }
        if(mineral.is(ModBlocks.ORANGE_MINERAL.get().asItem())){
            return ModBlocks.BUDDING_ORANGE_MINERAL.get();
        }
        if(mineral.is(ModBlocks.YELLOW_MINERAL.get().asItem())){
            return ModBlocks.BUDDING_YELLOW_MINERAL.get();
        }
        if(mineral.is(ModBlocks.GREEN_MINERAL.get().asItem())){
            return ModBlocks.BUDDING_GREEN_MINERAL.get();
        }
        if(mineral.is(ModBlocks.LIGHT_BLUE_MINERAL.get().asItem())){
            return ModBlocks.BUDDING_LIGHT_BLUE_MINERAL.get();
        }
        if(mineral.is(ModBlocks.BLUE_MINERAL.get().asItem())){
            return ModBlocks.BUDDING_BLUE_MINERAL.get();
        }
        if(mineral.is(Blocks.AMETHYST_BLOCK.asItem())){
            return Blocks.BUDDING_AMETHYST;
        }
        return Blocks.AIR;
    }

    private static void craftItem(MineralEnricherBlockEntity entity) {
        Level level = entity.level;
        BlockPos pos = entity.getBlockPos();
        BlockPos crystalPos = new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ());
        BlockState state = entity.resultGen(entity.itemHandler.getStackInSlot(2)).getFirst().defaultBlockState();
        if (hasRecipe(entity) && level.getBlockState(crystalPos).is(ModTags.Blocks.AIR) && hasEnoughFluid(entity)) {
            entity.itemHandler.extractItem(1, 16, false);
            level.setBlock(crystalPos, state.setValue(BlockStateProperties.FACING, Direction.DOWN), 2);
            entity.fluidTank.drain(1000, IFluidHandler.FluidAction.EXECUTE);
            entity.resetProgress();
        }
    }

    private static boolean hasEnoughFluid(MineralEnricherBlockEntity entity) {
        return entity.fluidTank.getFluidAmount() >= 100;
    }

    private static boolean hasRecipe(MineralEnricherBlockEntity entity) {
        //return entity.getDust() >= 16;
        return !entity.itemHandler.getStackInSlot(2).isEmpty();
    }

    private static void transferItemFluidToFluidTank(MineralEnricherBlockEntity entity){
        entity.itemHandler.getStackInSlot(0).getCapability(ForgeCapabilities.FLUID_HANDLER_ITEM).ifPresent(iFluidHandlerItem -> {
            int drainAmount = Math.min(entity.fluidTank.getSpace(), 1000);
            FluidStack fluidStack = iFluidHandlerItem.drain(drainAmount, IFluidHandler.FluidAction.SIMULATE);
            if(entity.fluidTank.isFluidValid(fluidStack)){
                fluidStack = iFluidHandlerItem.drain(drainAmount, IFluidHandler.FluidAction.EXECUTE);
                fillTank(entity, fluidStack, iFluidHandlerItem.getContainer());
            }
        });
    }

    private static void fillTank(MineralEnricherBlockEntity entity, FluidStack fluidStack, ItemStack container) {
        entity.fluidTank.fill(fluidStack, IFluidHandler.FluidAction.EXECUTE);
        entity.itemHandler.extractItem(0, 1, false);
        entity.itemHandler.insertItem(0, container, false);
    }

    private static boolean hasFluidInSourceSlot(MineralEnricherBlockEntity entity){
        return entity.itemHandler.getStackInSlot(0).getCount() > 0;
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
        ModMessages.sendToClients(new FluidSyncS2CPacket(this.getFluidStack(), worldPosition));
        return new MineralEnricherMenu(pContainerId, pInventory, this, this.data);
    }
}
