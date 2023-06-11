package net.AbraXator.chakral.data;

import net.AbraXator.chakral.init.ModBlocks;
import net.AbraXator.chakral.init.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, modId, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider p_256380_) {
        List<RegistryObject<Block>> iron_tier_block = List.of(ModBlocks.BLACK_MINERAL_DIAMOND_ORE, ModBlocks.BLACK_MINERAL_LAPIS_ORE, ModBlocks.BLACK_MINERAL_GOLD_ORE, ModBlocks.BLACK_MINERAL_REDSTONE_ORE, ModBlocks.BLACK_MINERAL_EMERALD_ORE);
        List<RegistryObject<Block>> stone_tier_block = List.of(ModBlocks.BLACK_MINERAL_IRON_ORE, ModBlocks.BLACK_MINERAL_COPPER_ORE);
        List<RegistryObject<Block>> axe = List.of(ModBlocks.WILTED_LOG, ModBlocks.WILTED_PLANKS, ModBlocks.SHARD_REFINER, ModBlocks.WILTED_SLAB, ModBlocks.WILTED_STAIRS, ModBlocks.WILTED_DOOR, ModBlocks.WILTED_TRAPDOOR, ModBlocks.WILTED_BUTTON, ModBlocks.WILTED_PRESSURE_PLATE, ModBlocks.WILTED_WOOD, ModBlocks.STRIPPED_WILTED_WOOD, ModBlocks.STRIPPED_WILTED_LOG, ModBlocks.WILTED_FENCE, ModBlocks.WILTED_FENCE_GATE);
        List<RegistryObject<Block>> hoe = List.of(ModBlocks.WILTED_LEAVES, ModBlocks.GLEAMSHROOM, ModBlocks.STEMSHROOM);
        List<RegistryObject<Block>> dirt = List.of(ModBlocks.MINERAL_RICH_DIRT, ModBlocks.MINERAL_RICH_FARMLAND, ModBlocks.BURGEONING_ROOTS, ModBlocks.MINERAL_RICH_GRASS);
        List<RegistryObject<Block>> logs = List.of(ModBlocks.WILTED_LOG);
        List<RegistryObject<Block>> planks = List.of(ModBlocks.WILTED_PLANKS);
        List<RegistryObject<Block>> leaves = List.of(ModBlocks.WILTED_LEAVES);
        List<RegistryObject<Block>> fence = List.of(ModBlocks.WILTED_FENCE);

        DataGenerators.getCrystals().forEach(block -> this.tag(ModTags.Blocks.CRYSTALS).add(block));
        iron_tier_block.forEach(blockRegistryObject -> this.tag(BlockTags.NEEDS_IRON_TOOL).add(blockRegistryObject.get()));
        stone_tier_block.forEach(blockRegistryObject -> this.tag(BlockTags.NEEDS_STONE_TOOL).add(blockRegistryObject.get()));
        axe.forEach(blockRegistryObject -> this.tag(BlockTags.MINEABLE_WITH_AXE).add(blockRegistryObject.get()));
        dirt.forEach(blockRegistryObject -> this.tag(BlockTags.MINEABLE_WITH_SHOVEL).add(blockRegistryObject.get()));
        hoe.forEach(blockRegistryObject -> this.tag(BlockTags.MINEABLE_WITH_HOE).add(blockRegistryObject.get()));
        logs.forEach(blockRegistryObject -> this.tag(BlockTags.LOGS_THAT_BURN).add(blockRegistryObject.get()));
        planks.forEach(blockRegistryObject -> this.tag(BlockTags.PLANKS).add(blockRegistryObject.get()));
        leaves.forEach(blockRegistryObject -> this.tag(BlockTags.LEAVES).add(blockRegistryObject.get()));
        fence.forEach(blockRegistryObject -> this.tag(BlockTags.WOODEN_FENCES).add(blockRegistryObject.get()));
        fence.forEach(blockRegistryObject -> this.tag(BlockTags.FENCES).add(blockRegistryObject.get()));

        ModBlocks.BLOCKS.getEntries().forEach(blockRegistryObject -> {
            if(!Stream.concat(axe.stream(), hoe.stream()).toList().contains(blockRegistryObject)){
                this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(blockRegistryObject.get());
            }
        });
        this.tag(ModTags.Blocks.MINERAL_RICH_TURFS).add(ModBlocks.MINERAL_RICH_DIRT.get(), ModBlocks.MINERAL_RICH_GRASS.get(), ModBlocks.MINERAL_RICH_FARMLAND.get());
        this.tag(BlockTags.DIRT).add(ModBlocks.MINERAL_RICH_DIRT.get(), ModBlocks.MINERAL_RICH_GRASS.get());
    }
}
