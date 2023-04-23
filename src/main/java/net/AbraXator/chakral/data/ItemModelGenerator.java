package net.AbraXator.chakral.data;

import net.AbraXator.chakral.Chakral;
import net.AbraXator.chakral.chakra.ChakraType;
import net.AbraXator.chakral.init.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ItemModelGenerator extends ItemModelProvider {
    private ExistingFileHelper existingModelFile;
    public ItemModelGenerator(PackOutput output, String modid, ExistingFileHelper existingFileHelper) {
        super(output, modid, existingFileHelper);
        this.existingModelFile = existingFileHelper;
    }

    @Override
    protected void registerModels() {
        for(ChakraType chakraType : ChakraType.values()){
            oneNecklacePart(ModItems.GOLDEN_NECKLACE, chakraType);
            goldenNecklace();

        }

        DataGenerators.getCrystals().forEach(block -> {
            this.getBuilder(DataGenerators.trimmedId(block.getDescriptionId()))
                    .parent(new ModelFile.UncheckedModelFile("item/generated"))
                    .texture("layer0", modLoc("block/crystal/" + DataGenerators.trimmedId(block.getDescriptionId())));
        });
    }

    private ItemModelBuilder goldenNecklace(){
        return withExistingParent(ModItems.GOLDEN_NECKLACE.getId().getPath(), new ResourceLocation("item/generated"))
                .texture("layer0", new ResourceLocation(Chakral.MOD_ID, "item/necklace/golden_necklace"))
                .texture("layer1", new ResourceLocation(Chakral.MOD_ID, "item/necklace/golden_necklace_slot_1"));
    }

    private ItemModelBuilder oneNecklacePart(RegistryObject<Item> item, ChakraType chakraType){
        return withExistingParent(item.getId().getPath() + "_" + chakraType.getSerializedName(),
                new ResourceLocation("item/generated"))
                .texture("layer0", new ResourceLocation(Chakral.MOD_ID, "item/necklace/" + item.getId().getPath()))
                .texture("layer1", new ResourceLocation(Chakral.MOD_ID, "item/necklace/" + item.getId().getPath() + "_" + chakraType.getSerializedName() + "_1"));
    }

    private ItemModelBuilder twoNecklacePart(RegistryObject<Item> item, ChakraType chakraType){
        return withExistingParent(item.getId().getPath() + "_" + chakraType.getSerializedName(),
                new ResourceLocation("item/generated"))
                .texture("layer0", new ResourceLocation(Chakral.MOD_ID, "item/necklace/" + item.getId().getPath()))
                .texture("layer1", new ResourceLocation(Chakral.MOD_ID, "item/necklace/" + item.getId().getPath() + "_" + chakraType.getSerializedName() + "_1"))
                .texture("layer2", new ResourceLocation(Chakral.MOD_ID, "item/necklace/" + item.getId().getPath() + "_" + chakraType.getSerializedName() + "_2"));
    }

    private ItemModelBuilder threeNecklacePart(RegistryObject<Item> item, ChakraType chakraType){
        return withExistingParent(item.getId().getPath() + "_" + chakraType.getSerializedName(),
                new ResourceLocation("item/generated"))
                .texture("layer0", new ResourceLocation(Chakral.MOD_ID, "item/necklace/" + item.getId().getPath()))
                .texture("layer1", new ResourceLocation(Chakral.MOD_ID, "item/necklace/" + item.getId().getPath() + "_" + chakraType.getSerializedName() + "_1"))
                .texture("layer2", new ResourceLocation(Chakral.MOD_ID, "item/necklace/" + item.getId().getPath() + "_" + chakraType.getSerializedName() + "_2"))
                .texture("layer3", new ResourceLocation(Chakral.MOD_ID, "item/necklace/" + item.getId().getPath() + "_" + chakraType.getSerializedName() + "_3"));
    }

    private ItemModelBuilder fourNecklacePart(RegistryObject<Item> item, ChakraType chakraType){
        return withExistingParent(item.getId().getPath() + "_" + chakraType.getSerializedName(),
                new ResourceLocation("item/generated"))
                .texture("layer0", new ResourceLocation(Chakral.MOD_ID, "item/necklace/" + item.getId().getPath()))
                .texture("layer1", new ResourceLocation(Chakral.MOD_ID, "item/necklace/" + item.getId().getPath() + "_" + chakraType.getSerializedName() + "_1"))
                .texture("layer2", new ResourceLocation(Chakral.MOD_ID, "item/necklace/" + item.getId().getPath() + "_" + chakraType.getSerializedName() + "_2"))
                .texture("layer3", new ResourceLocation(Chakral.MOD_ID, "item/necklace/" + item.getId().getPath() + "_" + chakraType.getSerializedName() + "_3"))
                .texture("layer4", new ResourceLocation(Chakral.MOD_ID, "item/necklace/" + item.getId().getPath() + "_" + chakraType.getSerializedName() + "_4"));
    }
}
