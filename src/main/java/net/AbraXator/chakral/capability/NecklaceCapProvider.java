package net.AbraXator.chakral.capability;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class NecklaceCapProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<NecklaceCap> NECKLACE_CAP = CapabilityManager.get(new CapabilityToken<NecklaceCap>() {});

    private NecklaceCap necklaceCap = null;
    private final LazyOptional<NecklaceCap> optional = LazyOptional.of(this::createNecklaceCap);

    private NecklaceCap createNecklaceCap() {
        if(this.necklaceCap == null){
            this.necklaceCap = new NecklaceCap();
        }
        return this.necklaceCap;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == NECKLACE_CAP){
            return optional.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createNecklaceCap().saveNBT(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createNecklaceCap().loadNBT(nbt);
    }
}
