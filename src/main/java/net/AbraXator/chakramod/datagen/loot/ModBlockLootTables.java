package net.AbraXator.chakramod.datagen.loot;

import net.AbraXator.chakramod.blocks.ModBlocks;
import net.AbraXator.chakramod.items.ModItems;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockLootTables extends BlockLoot {

    @Override
    protected void addTables() {
        this.dropSelf(ModBlocks.BLACK_MINERAL.get());
        this.dropSelf(ModBlocks.GREEN_MINERAL.get());
        this.dropSelf(ModBlocks.LIGHT_BLUE_MINERAL.get());
        this.dropSelf(ModBlocks.ORANGE_MINERAL.get());
        this.dropSelf(ModBlocks.PURPLE_MINERAL.get());
        this.dropSelf(ModBlocks.BLUE_MINERAL.get());
        this.dropSelf(ModBlocks.RED_MINERAL.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
