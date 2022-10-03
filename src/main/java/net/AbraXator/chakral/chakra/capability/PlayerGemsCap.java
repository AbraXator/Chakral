package net.AbraXator.chakral.chakra.capability;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class PlayerGemsCap {
    private List<ItemStack> gems;

    public List<ItemStack> getGems(){
        return gems;
    }

    public void setGems(List<ItemStack> gems){
        this.gems = gems;
    }

    public void copyFrom(PlayerGemsCap source){
        this.gems = source.gems;
    }

    public void saveNBT(CompoundTag tag){
        ListTag gemsTag = new ListTag();
        for(ItemStack gem : gems){
            gemsTag.add(gem.serializeNBT());
        }
        tag.put("player.gems", gemsTag);
    }

    public void loadNBT(CompoundTag tag){
        List<ItemStack> gemsList = new ArrayList<>();
        ListTag listTag = ((ListTag) tag.get("player.gems"));
        for (int i = 0; i < listTag.size(); i++){
            gemsList.add(ItemStack.of((CompoundTag) listTag.get(i)));
        }
    }
}
