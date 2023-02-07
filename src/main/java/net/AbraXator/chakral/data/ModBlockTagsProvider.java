package net.AbraXator.chakral.data;

import net.AbraXator.chakral.blocks.ModBlocks;
import net.AbraXator.chakral.items.custom.GoldenNecklace;
import net.AbraXator.chakral.utils.ModTags;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, modId, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider p_256380_) {
        this.tag(ModTags.Blocks.CRYSTALS).add(ModBlocks.BLACK_CRYSTAL.get(), ModBlocks.BIG_BLACK_BUD.get(), ModBlocks.SMALL_BLACK_BUD.get(),
                ModBlocks.GLOWSTONE_CLUSTER.get(),
                ModBlocks.WHITE_CRYSTAL.get(), ModBlocks.BIG_WHITE_BUD.get(), ModBlocks.SMALL_BLUE_BUD.get(),
                ModBlocks.TRUE_WHITE_CRYSTAL.get(), ModBlocks.BIG_TRUE_WHITE_BUD.get(), ModBlocks.SMALL_WHITE_BUD.get(),
                Blocks.SMALL_AMETHYST_BUD, Blocks.MEDIUM_AMETHYST_BUD, Blocks.MEDIUM_AMETHYST_BUD, Blocks.LARGE_AMETHYST_BUD, Blocks.AMETHYST_CLUSTER,
                ModBlocks.BLUE_CRYSTAL.get(), ModBlocks.BIG_BLUE_BUD.get(), ModBlocks.SMALL_BLUE_BUD.get(),
                ModBlocks.LIGHT_BLUE_CRYSTAL.get(), ModBlocks.BIG_LIGHT_BLUE_BUD.get(), ModBlocks.SMALL_LIGHT_BLUE_BUD.get(),
                ModBlocks.GREEN_CRYSTAL.get(), ModBlocks.BIG_GREEN_BUD.get(), ModBlocks.SMALL_GREEN_BUD.get(),
                ModBlocks.YELLOW_CRYSTAL.get(), ModBlocks.BIG_YELLOW_BUD.get(), ModBlocks.SMALL_YELLOW_BUD.get(),
                ModBlocks.ORANGE_CRYSTAL.get(), ModBlocks.BIG_ORANGE_BUD.get(), ModBlocks.SMALL_ORANGE_BUD.get(),
                ModBlocks.RED_CRYSTAL.get(), ModBlocks.BIG_RED_BUD.get(), ModBlocks.SMALL_RED_BUD.get());
        ModBlocks.BLOCKS.getEntries().forEach(blockRegistryObject -> {
            Block block = blockRegistryObject.get();
            List<RegistryObject<Block>> stone_block = List.of(ModBlocks.BLACK_MINERAL_IRON_ORE, ModBlocks.BLACK_MINERAL_COPPER_ORE);
            List<RegistryObject<Block>> iron_block = List.of(ModBlocks.BLACK_MINERAL_DIAMOND_ORE, ModBlocks.BLACK_MINERAL_LAPIS_ORE, ModBlocks.BLACK_MINERAL_GOLD_ORE, ModBlocks.BLACK_MINERAL_REDSTONE_ORE, ModBlocks.BLACK_MINERAL_EMERALD_ORE);
            if(!(block.asItem().getDefaultInstance().is(ModTags.Items.MINERAL_RICH) || block.asItem().getDefaultInstance().is(ModTags.Items.SHROOMS))) {
                this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(block);
            } else if(stone_block.contains(blockRegistryObject)){
                this.tag(BlockTags.NEEDS_STONE_TOOL).add(block);
            } else if(iron_block.contains(blockRegistryObject)){
                this.tag(BlockTags.NEEDS_IRON_TOOL).add(block);
            }
        });
        this.tag(BlockTags.DIRT).add(ModBlocks.MINERAL_RICH_DIRT.get(), ModBlocks.MINERAL_RICH_GRASS.get());
    }
}
