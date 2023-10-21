package net.AbraXator.chakral.server.capability;

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

public class ChakraMasteryCapProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<ChakraMasteryCap> CHAKRA_MASTERY_CAP = CapabilityManager.get(new CapabilityToken<ChakraMasteryCap>() {});

    private ChakraMasteryCap masteryCap = null;
    private final LazyOptional<ChakraMasteryCap> optional = LazyOptional.of(this::createChakraMasteryCap);

    private ChakraMasteryCap createChakraMasteryCap() {
        if(this.masteryCap == null){
            this.masteryCap = new ChakraMasteryCap();
        }
        return this.masteryCap;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == CHAKRA_MASTERY_CAP){
            return optional.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createChakraMasteryCap().saveNBT(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createChakraMasteryCap().loadNBT(nbt);
    }
}
