package net.AbraXator.chakral.capability;

import net.AbraXator.chakral.blocks.custom.DirectionalBlock;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;

public class AdditionalHealthCapProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<AdditionalHealthCap> ADD_HEALTH_CAP = CapabilityManager.get(new CapabilityToken<AdditionalHealthCap>() {});

    private AdditionalHealthCap additionalHealthCap = null;
    private final LazyOptional<AdditionalHealthCap> optional = LazyOptional.of(this::createAddHealthCap);

    private AdditionalHealthCap createAddHealthCap(){
        if(this.additionalHealthCap == null){
            this.additionalHealthCap = new AdditionalHealthCap();
        }
        return this.additionalHealthCap;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, Direction direction) {
        if(cap == ADD_HEALTH_CAP){
            return optional.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        createAddHealthCap().saveNBT(tag);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createAddHealthCap().loadNBT(nbt);
    }
}
