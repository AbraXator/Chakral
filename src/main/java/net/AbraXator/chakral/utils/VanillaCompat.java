package net.AbraXator.chakral.utils;

import net.AbraXator.chakral.blocks.ModBlocks;
import net.AbraXator.chakral.items.ModItems;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.ComposterBlock;

public class VanillaCompat {
    public static void register(){
        registerCompostable(0.3F, ModBlocks.WILTED_SAPLING.get());
        registerCompostable(0.65F, ModBlocks.MINERAL_RICH_PERENNIAL.get());
        registerCompostable(0.65F, ModBlocks.WILTED_LEAVES.get());
        registerCompostable(0.65F, ModBlocks.GLEAMSHROOM.get());
        registerCompostable(0.65F, ModBlocks.STEMSHROOM.get());
        registerCompostable(0.65F, ModItems.RAW_STEMSHROOM_STEM.get());
        registerCompostable(0.85F, ModItems.COOKED_STEMSHROOM_STEM.get());
    }

    public static void registerCompostable(float chance, ItemLike itemLike){
        ComposterBlock.COMPOSTABLES.put(itemLike.asItem(), chance);
    }
}
