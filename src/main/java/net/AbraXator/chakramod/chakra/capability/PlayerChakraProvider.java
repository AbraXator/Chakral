package net.AbraXator.chakramod.chakra.capability;

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

//public class PlayerChakraProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
//    public static Capability<PlayerChakra> PLAYER_CHAKRA = CapabilityManager.get(new CapabilityToken<PlayerChakra>() {});
//    private PlayerChakra playerChakra = null;
//    private final LazyOptional<PlayerChakra> optional = LazyOptional.of(this::createPlayerChakra);
//
//    private PlayerChakra createPlayerChakra() {
//        if(this.playerChakra == null){
//            this.playerChakra = new PlayerChakra();
//        }
//
//        return this.playerChakra;
//    }
//
//    @Override
//    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
//        if(cap == PLAYER_CHAKRA){
//            return optional.cast();
//        }else {
//            return LazyOptional.empty();
//        }
//    }
//
//    @Override
//    public CompoundTag serializeNBT() {
//        CompoundTag nbt = new CompoundTag();
//        createPlayerChakra().saveNBTData(nbt);
//        return nbt;
//    }
//
//    @Override
//    public void deserializeNBT(CompoundTag nbt) {
//        createPlayerChakra().loadNBTData(nbt);
//    }
//}
//