package net.AbraXator.chakral.chakra.capability;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class PlayerGemsCapProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<PlayerGemsCap> GEMS_CAP = CapabilityManager.get(new CapabilityToken<PlayerGemsCap>() {});

    private PlayerGemsCap gemsCap = null;
    private final LazyOptional<PlayerGemsCap> optional = LazyOptional.of(this::createGemsCap);

    private PlayerGemsCap createGemsCap() {
        if(this.gemsCap == null){
            this.gemsCap = new PlayerGemsCap();
        }
        return this.gemsCap;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == GEMS_CAP){
            return optional.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createGemsCap().saveNBT(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createGemsCap().loadNBT(nbt);
    }
}
