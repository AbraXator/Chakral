package net.AbraXator.chakral.screen;

import net.AbraXator.chakral.chakra.ChakraStrength;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.tags.ITag;

public class StoneSlot extends Slot {
    public ChakraStrength strength;
    public ItemStack necklace;
    public ItemStack wantedNecklace;
    public ItemStack stone;
    ITag<Item> list = ForgeRegistries.ITEMS.tags().getTag(ChakraStrength.getTag(strength));
    public StoneSlot(Container pContainer, int pIndex, int pX, int pY) {
        super(pContainer, pIndex, pX, pY);
    }

    public void setValues(ChakraStrength strength, ItemStack necklace, ItemStack wantedNecklace, ItemStack stone){
        this.strength = strength;
        this.necklace = necklace;
        this.wantedNecklace = wantedNecklace;
        this.stone = stone;
    }

    public String tagNameInit(){
        int k = switch (strength){
            case FAINT -> 1;
            case WEAKENED -> 2;
            case POWERFUL -> 3;
            case ENLIGHTENED -> 4;
        };
        return "Stone" + k;
    }

    @Override
    public boolean mayPlace(ItemStack pStack) {
        return list.contains(pStack.getItem());
    }

    @Override
    public boolean isActive() {
        return necklace.is(wantedNecklace.getItem());
    }

    @Override
    public void setChanged() {
        if(list.contains(stone.getItem()) && necklace.is(wantedNecklace.getItem())){
            CompoundTag nbt = necklace.getOrCreateTag();
            nbt.put(tagNameInit(), stone.serializeNBT());
            necklace.setTag(nbt);
        }
    }

    @Override
    public void onTake(Player pPlayer, ItemStack pStack) {
        if (necklace.is(wantedNecklace.getItem())) {
            necklace.addTagElement(tagNameInit(), ItemStack.EMPTY.serializeNBT());
        }
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }
}
