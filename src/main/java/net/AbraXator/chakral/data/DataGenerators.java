package net.AbraXator.chakral.data;

import net.AbraXator.chakral.Chakral;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Chakral.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event){
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        generator.addProvider(event.includeServer(), new ModLootTableProvider(generator.getPackOutput()));
        generator.addProvider(event.includeServer(), new ModRecipeProvider(generator.getPackOutput()));
        //generator.addProvider(event.includeClient(), new ItemModelGenerator(generator.getPackOutput(), Chakral.MOD_ID, existingFileHelper));
        TagsProvider<Block> blockTagsProvider = new ModBlockTagsProvider(event.getGenerator().getPackOutput(), event.getLookupProvider(), Chakral.MOD_ID, existingFileHelper);
        generator.addProvider(event.includeServer(), blockTagsProvider);
        generator.addProvider(event.includeServer(), new ModItemTagsProvider(event.getGenerator().getPackOutput(), event.getLookupProvider(), blockTagsProvider, Chakral.MOD_ID, existingFileHelper));
        generator.addProvider(event.includeServer(), new ModBlockModelGenerator(generator.getPackOutput(), Chakral.MOD_ID, existingFileHelper));
    }
}
