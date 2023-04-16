package net.AbraXator.chakral.blocks.entity;

import net.AbraXator.chakral.blocks.Crystal;
import net.AbraXator.chakral.blocks.MineralEnricherBlock;
import net.AbraXator.chakral.init.ModBlockEntities;
import net.AbraXator.chakral.init.ModItems;
import net.AbraXator.chakral.networking.ModMessages;
import net.AbraXator.chakral.networking.packet.FluidSyncS2CPacket;
import net.AbraXator.chakral.networking.packet.EnricherSyncS2CPacket;
import net.AbraXator.chakral.recipes.MineralEnricherRecipe;
import net.AbraXator.chakral.client.gui.enricher.MineralEnricherMenu;
import net.AbraXator.chakral.init.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

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
    public int progress;
    private int maxProgress = 120;
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

    public static int getProgress(MineralEnricherBlockEntity entity) {
        return entity.data.get(0);
    }

    public int getMaxProgress() {
        return maxProgress;
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
            //System.out.println("ENTITY: " + MineralEnricherBlockEntity.getProgress(entity));
            ModMessages.sendToClients(new EnricherSyncS2CPacket(entity.itemHandler, entity.getBlockPos(), getProgress(entity)));
             entity.updateDust(entity.getDust());
            if (hasRecipe(entity) && canPlace(entity)) {
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

    private static Item getCrystal(MineralEnricherBlockEntity pBlockEntity) {
        Item[] item = new Item[1];
        generateRecipe(pBlockEntity).ifPresentOrElse(recipe -> {
            item[0] = recipe.getResultItem(pBlockEntity.level.registryAccess()).getItem();
        }, () -> {
            item[0] = Blocks.AIR.asItem();
        });
        return item[0];
    }

    private static void craftItem(MineralEnricherBlockEntity entity) {
        Level level = entity.level;
        Optional<MineralEnricherRecipe> recipe = generateRecipe(entity);
        BlockPos pos = entity.getBlockPos();
        BlockPos crystalPos = new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ());
        BlockState state = crystalGen(recipe, entity);
        if (state.is(ModTags.Blocks.CRYSTALS)) {
            level.setBlock(crystalPos, state.setValue(BlockStateProperties.FACING, Direction.DOWN).setValue(Crystal.WATERLOGGED, level.getBlockState(crystalPos).is(Blocks.WATER)), 2);
            level.gameEvent(GameEvent.BLOCK_CHANGE, crystalPos, GameEvent.Context.of(state));
            entity.itemHandler.extractItem(1, 16, false);
            entity.fluidTank.drain(recipe.get().getFluidStack().getAmount(), IFluidHandler.FluidAction.EXECUTE);
            entity.resetProgress();
        }
    }

    private static boolean hasRecipe(MineralEnricherBlockEntity entity) {
        Optional<MineralEnricherRecipe> recipe = generateRecipe(entity);

        return recipe.isPresent()
                && entity.fluidTank.getFluid().isFluidEqual(recipe.get().getFluidStack())
                && entity.fluidTank.getFluidAmount() >= recipe.get().getFluidStack().getAmount()
                && entity.getDust() >= recipe.get().getDust().getCount();
    }

    private static boolean canPlace(MineralEnricherBlockEntity entity){
        Level level = entity.level;
        BlockPos pos = entity.getBlockPos();
        return level.getBlockState(pos.below()).is(Blocks.AIR) || level.getBlockState(pos.below()).is(Blocks.WATER);
    }

    private static BlockState crystalGen(Optional<MineralEnricherRecipe> recipe, MineralEnricherBlockEntity entity){
        for (Block block : ForgeRegistries.BLOCKS.tags().getTag(ModTags.Blocks.CRYSTALS)) {
            if (block.asItem().equals(recipe.get().getResultItem(entity.level.registryAccess()).getItem())) {
                return block.defaultBlockState();
            }
        }
        return Blocks.AIR.defaultBlockState();
    }

    public static Optional<MineralEnricherRecipe> generateRecipe(MineralEnricherBlockEntity entity){
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }
        return entity.level.getRecipeManager().getRecipeFor(MineralEnricherRecipe.Type.INSTANCE, inventory, entity.level);
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
