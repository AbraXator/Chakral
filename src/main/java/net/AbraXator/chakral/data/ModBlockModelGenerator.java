package net.AbraXator.chakral.data;

import com.google.gson.JsonElement;
import net.AbraXator.chakral.blocks.ModBlocks;
import net.AbraXator.chakral.utils.ModTags;
import net.minecraft.data.PackOutput;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.blockstates.BlockStateGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ModBlockModelGenerator extends BlockModelProvider {
    public ModBlockModelGenerator(PackOutput output, String modid, ExistingFileHelper existingFileHelper) {
        super(output, modid, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        DataGenerators.getCrystals().forEach(block -> this.cross(DataGenerators.trimmedId(block.getDescriptionId()), modLoc("block/crystal/" + DataGenerators.trimmedId(block.getDescriptionId()))));
    }


}
