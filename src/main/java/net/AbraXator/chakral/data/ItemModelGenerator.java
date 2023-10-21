package net.AbraXator.chakral.data;

import net.AbraXator.chakral.Chakral;
import net.AbraXator.chakral.server.init.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemModelGenerator extends ItemModelProvider {
    private ExistingFileHelper existingModelFile;
    public ItemModelGenerator(PackOutput output, String modid, ExistingFileHelper existingFileHelper) {
        super(output, modid, existingFileHelper);
        this.existingModelFile = existingFileHelper;
    }

    @Override
    protected void registerModels() {
        necklaces();

        DataGenerators.getCrystals().forEach(block -> {
            this.getBuilder(DataGenerators.trimmedId(block.getDescriptionId()))
                    .parent(new ModelFile.UncheckedModelFile("item/generated"))
                    .texture("layer0", modLoc("block/crystal/" + DataGenerators.trimmedId(block.getDescriptionId())));
        });
    }

    private void necklaces(){
        goldenNecklace();
        diamondNecklace();
    }

    private ItemModelBuilder goldenNecklace(){
        return withExistingParent(ModItems.GOLDEN_NECKLACE.getId().getPath(), new ResourceLocation("item/generated"))
                .texture("layer0", new ResourceLocation(Chakral.MOD_ID, "item/necklace/golden_necklace"))
                .texture("layer1", new ResourceLocation(Chakral.MOD_ID, "item/necklace/golden_necklace_slot_1"));
    }

    private ItemModelBuilder diamondNecklace(){
        return withExistingParent(ModItems.DIAMOND_NECKLACE.getId().getPath(), new ResourceLocation("item/generated"))
                .texture("layer0", new ResourceLocation(Chakral.MOD_ID, "item/necklace/diamond_necklace"))
                .texture("layer1", new ResourceLocation(Chakral.MOD_ID, "item/necklace/diamond_necklace_slot_1"))
                .texture("layer2", new ResourceLocation(Chakral.MOD_ID, "item/necklace/diamond_necklace_slot_2"));
    }
}
