package net.AbraXator.chakral.data;

import net.AbraXator.chakral.Chakral;
import net.AbraXator.chakral.blocks.ModBlocks;
import net.AbraXator.chakral.utils.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends ItemTagsProvider {
    public ModItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> future, CompletableFuture<TagLookup<Block>> provider, ExistingFileHelper helper) {
        super(output, future, provider, Chakral.MOD_ID, helper);
    }

    @Override
    protected void addTags(HolderLookup.Provider p_256380_) {
        this.tag(ModTags.Items.MINERAL_RICH).add(ModBlocks.MINERAL_RICH_DIRT.get().asItem(), ModBlocks.MINERAL_RICH_GRASS.get().asItem(), ModBlocks.MINERAL_RICH_PERENNIAL.get().asItem());
        this.tag(ModTags.Items.SHROOMS).add(ModBlocks.STEMSHROOM.get().asItem(), ModBlocks.GLEAMSHROOM.get().asItem());
        this.tag(ItemTags.LOGS).add(ModBlocks.WILTED_LOG.get().asItem());
        this.tag(ItemTags.PLANKS).add(ModBlocks.WILTED_PLANKS.get().asItem());
        this.copy(ModTags.Blocks.CRYSTALS, ModTags.Items.CRYSTALS);
    }
}
