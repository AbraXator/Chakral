package net.AbraXator.chakral.data;

import com.google.gson.JsonElement;
import net.AbraXator.chakral.Chakral;
import net.AbraXator.chakral.chakra.ChakraType;
import net.AbraXator.chakral.items.ModItems;
import net.AbraXator.chakral.utils.NecklaceType;
import net.minecraft.data.PackOutput;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

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

            oneNecklacePart(ModItems.DIAMOND_NECKLACE, chakraType);
            twoNecklacePart(ModItems.DIAMOND_NECKLACE, chakraType);

            goldenNecklace();
                    //case NETHERITE:
                    //    oneNecklacePart(necklaceType.getItem(), chakraType);
                    //    twoNecklacePart(necklaceType.getItem(), chakraType);
                    //    threeNecklacePart(necklaceType.getItem(), chakraType);
                    //case RAINBOW:
                    //    oneNecklacePart(necklaceType.getItem(), chakraType);
                    //    twoNecklacePart(necklaceType.getItem(), chakraType);
                    //    threeNecklacePart(necklaceType.getItem(), chakraType);
                    //    fourNecklacePart(necklaceType.getItem(), chakraType);
        }
    }

    private ItemModelBuilder goldenNecklace(){
        return withExistingParent(ModItems.GOLDEN_NECKLACE.getId().getPath(), new ResourceLocation("item/generated"))
                .texture("layer0", new ResourceLocation(Chakral.MOD_ID, "item/necklace/golden_necklace"))
                .texture("layer1", new ResourceLocation(Chakral.MOD_ID, "item/necklace/golden_necklace_slot_1"))
                .override()
                .predicate(new ResourceLocation("crown"), 1.0F)
                //.model(new ItemModelBuilder(new ResourceLocation(Chakral.MOD_ID, "item/golden_necklace_crown"), existingModelFile))
                .end()
                .override()
                .predicate(new ResourceLocation("heart"), 1.0F)
                .model(new ItemModelBuilder(new ResourceLocation(Chakral.MOD_ID, "item/golden_necklace_heart"), existingModelFile))
                .end()
                .override()
                .predicate(new ResourceLocation("root"), 1.0F)
                .model(new ItemModelBuilder(new ResourceLocation(Chakral.MOD_ID, "item/golden_necklace_root"), existingModelFile))
                .end()
                .override()
                .predicate(new ResourceLocation("sacral"), 1.0F)
                .model(new ItemModelBuilder(new ResourceLocation(Chakral.MOD_ID, "item/golden_necklace_sacral"), existingModelFile))
                .end()
                .override()
                .predicate(new ResourceLocation("solar"), 1.0F)
                .model(new ItemModelBuilder(new ResourceLocation(Chakral.MOD_ID, "item/golden_necklace_solar"), existingModelFile))
                .end()
                .override()
                .predicate(new ResourceLocation("third_eye"), 1.0F)
                .model(new ItemModelBuilder(new ResourceLocation(Chakral.MOD_ID, "item/golden_necklace_third_eye"), existingModelFile))
                .end()
                .override()
                .predicate(new ResourceLocation("throat"), 1.0F)
                .model(new ItemModelBuilder(new ResourceLocation(Chakral.MOD_ID, "item/golden_necklace_throat"), existingModelFile))
                .end();
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
