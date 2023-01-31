package net.AbraXator.chakral.data;

import net.AbraXator.chakral.blocks.ModBlocks;
import net.AbraXator.chakral.utils.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends ItemTagsProvider {
    public ModItemTagsProvider(PackOutput p_255871_, CompletableFuture<HolderLookup.Provider> p_256035_, TagsProvider<Block> p_256467_, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_255871_, p_256035_, p_256467_, modId, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider p_256380_) {
        this.tag(ModTags.Items.MINERAL_RICH).add(ModBlocks.MINERAL_RICH_DIRT.get().asItem(), ModBlocks.MINERAL_RICH_GRASS.get().asItem(), ModBlocks.MINERAL_RICH_PERENNIAL.get().asItem());
        this.tag(ModTags.Items.SHROOMS).add(ModBlocks.STEMSHROOM.get().asItem(), ModBlocks.GLEAMSHROOM.get().asItem());
        this.copy(ModTags.Blocks.CRYSTALS, ModTags.Items.CRYSTALS);
    }
}
