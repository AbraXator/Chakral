package net.AbraXator.chakral.data;

import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockModelGenerator extends BlockModelProvider {
    public ModBlockModelGenerator(PackOutput output, String modid, ExistingFileHelper existingFileHelper) {
        super(output, modid, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        DataGenerators.getCrystals().forEach(block ->
                this.cross(DataGenerators.trimmedId(block.getDescriptionId()), modLoc("block/crystal/" + DataGenerators.trimmedId(block.getDescriptionId()))).renderType("cutout"));
    }
}
