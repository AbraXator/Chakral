//package net.AbraXator.chakral.chakra.capability;
//
//import net.AbraXator.chakral.items.Gems;
//import net.AbraXator.chakral.items.custom.Gem;
//import net.minecraft.client.multiplayer.chat.LoggedChatMessage;
//import net.minecraft.nbt.CompoundTag;
//import net.minecraft.nbt.NbtUtils;
//import net.minecraft.nbt.Tag;
//import net.minecraft.world.item.Item;
//
//import java.util.List;
//
//public class PlayerChakra {
//    private List<Item> gems;
//    private int gemAmount;
//    private final int MIN_GEMS = 0;
//    private final int MAX_GEMS = 4;
//
//    public List<Item> getGem(){
//        return gems;
//    }
//
//    //public int getGemAmount(){
//    //    return gems.size();
//    //}
//
//
//    public void setGems(List<Item> gems) {
//        this.gems = gems;
//    }
//
//    public void addGem(int add){
//        this.gemAmount = Math.min(gemAmount + add, MAX_GEMS);
//    }
//
//    public void copyFrom(PlayerChakra source){
//        this.gems = source.gems;
//    }
//
//    //public String getGemsName(){
//    //    for(Item item : gems){
//    //        List<String>
//    //    }
//    //}
//
//    public void saveNBTData(CompoundTag nbt){
//        String string = gems.get(1).getDefaultInstance().getDisplayName().getString();
//        nbt.put("chakral.playerGems", gems.get(1).getDefaultInstance().serializeNBT());
//    }
//
//    public void loadNBTData(CompoundTag nbt){
//        String string = gems.get(1).getDefaultInstance().getDisplayName().getString();
//        nbt.put("chakral.playerGems", gems.get(1).getDefaultInstance().serializeNBT());
//    }
//}
//