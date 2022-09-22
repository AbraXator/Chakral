package net.AbraXator.chakral.chakra.capability;

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