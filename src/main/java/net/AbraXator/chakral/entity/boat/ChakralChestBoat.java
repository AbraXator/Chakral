package net.AbraXator.chakral.entity.boat;

import net.AbraXator.chakral.init.ModItems;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.HasCustomInventoryScreen;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.entity.vehicle.ContainerEntity;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.Nullable;

public class ChakralChestBoat extends ChakralBoat implements HasCustomInventoryScreen, ContainerEntity {
    private NonNullList<ItemStack> itemStacks = NonNullList.withSize(27, ItemStack.EMPTY);
    private ResourceLocation lootTable;
    private long lootTableSeed;

    public ChakralChestBoat(EntityType<? extends Boat> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public ChakralChestBoat(Level pLevel, double x, double y, double z) {
        super(pLevel, x, y, z);
        this.setPos(x, y, z);
        this.xo = x;
        this.yo = y;
        this.zo = z;
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        this.addChestVehicleSaveData(pCompound);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.addChestVehicleSaveData(pCompound);
    }

    @Override
    protected void destroy(DamageSource pDamageSource) {
        super.destroy(pDamageSource);
        this.chestVehicleDestroyed(pDamageSource, this.level(), this);
    }

    @Override
    public void remove(RemovalReason pReason) {
        if(!this.level().isClientSide() && pReason.shouldDestroy()){
            Containers.dropContents(this.level(), this, this);
        }
        super.remove(pReason);
    }

    @Override
    public InteractionResult interact(Player pPlayer, InteractionHand pHand) {
        if(this.canAddPassenger(pPlayer) && !pPlayer.isSecondaryUseActive()){
            return super.interact(pPlayer, pHand);
        }else {
            InteractionResult interactionResult = this.interactWithContainerVehicle(pPlayer);
            if(interactionResult.consumesAction()){
                this.gameEvent(GameEvent.CONTAINER_OPEN);
                PiglinAi.angerNearbyPiglins(pPlayer, true);
            }

            return interactionResult;
        }
    }

    @Override
    public void openCustomInventoryScreen(Player pPlayer) {
        pPlayer.openMenu(this);
        if(!pPlayer.level().isClientSide()){
            this.gameEvent(GameEvent.CONTAINER_OPEN, pPlayer);
            PiglinAi.angerNearbyPiglins(pPlayer, true);
        }
    }

    @Override
    public Item getDropItem() {
        return switch (this.getBoatType()) {
            case WILTED -> ModItems.WILTED_CHEST_BOAT.get();
        };
    }

    @Nullable
    @Override
    public ResourceLocation getLootTable() {
        return this.lootTable;
    }

    @Override
    public void setLootTable(@Nullable ResourceLocation pLootTable) {
        this.lootTable = pLootTable;
    }

    @Override
    public long getLootTableSeed() {
        return this.lootTableSeed;
    }

    @Override
    public void setLootTableSeed(long pLootTableSeed) {
        this.lootTableSeed = pLootTableSeed;
    }

    @Override
    public NonNullList<ItemStack> getItemStacks() {
        return this.itemStacks;
    }

    @Override
    public void clearItemStacks() {
        this.itemStacks = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
    }

    @Override
    public int getContainerSize() {
        return 27;
    }

    @Override
    public ItemStack getItem(int pSlot) {
        return this.getChestVehicleItem(pSlot);
    }

    @Override
    public ItemStack removeItem(int pSlot, int pAmount) {
        return this.removeChestVehicleItem(pSlot, pAmount);
    }

    @Override
    public ItemStack removeItemNoUpdate(int pSlot) {
        return this.removeItemNoUpdate(pSlot);
    }

    @Override
    public void setItem(int pSlot, ItemStack pStack) {
        this.setChestVehicleItem(pSlot, pStack);
    }

    @Override
    public void setChanged(){}

    @Override
    public boolean stillValid(Player pPlayer) {
        return this.isChestVehicleStillValid(pPlayer);
    }

    @Override
    public void clearContent() {
        this.clearChestVehicleContent();
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        if(this.lootTable != null && pPlayer.isSpectator()){
            return null;
        }else {
            this.unpackChestVehicleLootTable(pPlayerInventory.player);
            return ChestMenu.threeRows(pContainerId, pPlayerInventory, this);
        }
    }
}
