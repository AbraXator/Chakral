package net.AbraXator.chakral.items;

import net.AbraXator.chakral.init.ModItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.List;

public class Shard extends Item {
    private final Block crystal;

    public Shard(Properties pProperties, Block crystal) {
        super(pProperties);
        this.crystal = crystal;
    }

    private static List<Item> shards(){
        return List.of(ModItems.BLUE_SHARD.get(), ModItems.LIGHT_BLUE_SHARD.get(), ModItems.GREEN_SHARD.get(), ModItems.YELLOW_SHARD.get(), ModItems.ORANGE_SHARD.get(), ModItems.RED_SHARD.get());
    }

    public static Item shardFromCrystal(Block crystal){
        for (Item item : shards()) {
            if(item instanceof Shard shard){
                if(shard.crystal.equals(crystal)){
                    return item;
                }
            }
        }
        return null;
    }
}
