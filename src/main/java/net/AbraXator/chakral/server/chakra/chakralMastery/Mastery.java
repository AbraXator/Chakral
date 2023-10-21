package net.AbraXator.chakral.server.chakra.chakralMastery;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.tags.ITag;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

public abstract class Mastery {
    public ChakraMasteryTiers chakraMasteryTiers;

    public Mastery(ChakraMasteryTiers chakraMasteryTiers) {
        this.chakraMasteryTiers = chakraMasteryTiers;
    }

    public void unlockedBy(Player player){

    }

    public void onUnlock(){

    }

    public boolean hasStone(Player player, ITag<Item> tag, int amount){
        List<Boolean> list = new ArrayList<>();

        AtomicReference<Item> itemStack = new AtomicReference<>(ItemStack.EMPTY.getItem());
        tag.forEach(item -> {
            if(player.getInventory().hasAnyOf(Set.of(item))){
                itemStack.set(item);
            }
        });

        Set<Item> set = new HashSet<>(tag.stream().toList());
        for(int i = 0; i <= amount - 1; i++){
            set.remove(itemStack.get());
            list.add(player.getInventory().hasAnyOf(set));
        }

        return !list.contains(false);
    }
}
