package net.AbraXator.chakral.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class MineralSnail extends AgeableMob {
    protected MineralSnail(EntityType<? extends AgeableMob> p_146738_, Level p_146739_) {
        super(p_146738_, p_146739_);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
        return null;
    }

    @Override
    protected void defineSynchedData() {

    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return null;
    }
}
