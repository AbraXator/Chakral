package net.AbraXator.chakral.server.entity.boat;

import net.AbraXator.chakral.server.init.ModBlocks;
import net.AbraXator.chakral.server.init.ModItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class ChakralBoat extends Boat {
    private static final EntityDataAccessor<Integer> TYPE = SynchedEntityData.defineId(ChakralBoat.class, EntityDataSerializers.INT);

    public ChakralBoat(EntityType<? extends Boat> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public ChakralBoat(Level pLevel, double pX, double pY, double pZ) {
        super(pLevel, pX, pY, pZ);
        this.setPos(pX, pY, pZ);
        this.xo = pX;
        this.yo = pY;
        this.zo = pZ;
    }

    public ChakralBoat.Type getBoatType(){
        return ChakralBoat.Type.byId(this.getEntityData().get(TYPE));
    }

    @Override
    public Item getDropItem() {
        return switch (this.getBoatType()){
            case WILTED -> ModItems.WILTED_BOAT.get();
        };
    }

    public void setBoatType(ChakralBoat.Type type){
        this.getEntityData().set(TYPE, type.ordinal());
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(TYPE, ChakralBoat.Type.WILTED.ordinal());
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag pCompound) {
        pCompound.putString("Type", this.getVariant().getName());
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag pCompound) {
        if(pCompound.contains("Type", 8)) {
            this.setBoatType(ChakralBoat.Type.byString(pCompound.getString("Type")));
        }
    }

    @Override
    @NotNull
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    public enum Type implements StringRepresentable {
        WILTED("wilted", ModBlocks.WILTED_PLANKS);

        private final String name;
        private final Supplier<Block> planks;

        Type(String name, Supplier<Block> planks) {
            this.name = name;
            this.planks = planks;
        }

        public Block getPlanks() {
            return planks.get();
        }

        public String getName() {
            return name;
        }

        @Override
        public String getSerializedName() {
            return name;
        }

        public static ChakralBoat.Type byId(int id){
            ChakralBoat.Type[] types = values();
            if(id < 0 || id >= types.length){
                id = 0;
            }

            return types[id];
        }

        public static ChakralBoat.Type byString(String string){
            ChakralBoat.Type[] types = values();

            for(ChakralBoat.Type type : types){
                if(type.getName().equals(string)){
                    return type;
                }
            }

            return types[0];
        }
    }
}
