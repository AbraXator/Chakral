package net.AbraXator.chakral.data;

import net.AbraXator.chakral.Chakral;
import net.AbraXator.chakral.blocks.ModBlocks;
import net.AbraXator.chakral.chakra.Chakra;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

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
        //generator.addProvider(event.includeServer(), new ModItemTagsProvider(event.getGenerator().getPackOutput(), event.getLookupProvider(), blockTagsProvider, Chakral.MOD_ID, existingFileHelper));
        generator.addProvider(event.includeServer(), new ModBlockModelGenerator(generator.getPackOutput(), Chakral.MOD_ID, existingFileHelper));
        generator.addProvider(event.includeServer(), new ItemModelGenerator(generator.getPackOutput(), Chakral.MOD_ID, existingFileHelper));
        generator.addProvider(event.includeServer(), new ModBlockStateProvider(generator.getPackOutput(), Chakral.MOD_ID, existingFileHelper));
    }

    public static List<Block> getCrystals(){
        return List.of(ModBlocks.BLACK_CRYSTAL.get(), ModBlocks.BIG_BLACK_BUD.get(), ModBlocks.SMALL_BLACK_BUD.get(),
                ModBlocks.GLOWSTONE_CLUSTER.get(),
                ModBlocks.WHITE_CRYSTAL.get(), ModBlocks.BIG_WHITE_BUD.get(), ModBlocks.SMALL_WHITE_BUD.get(),
                ModBlocks.TRUE_WHITE_CRYSTAL.get(), ModBlocks.BIG_TRUE_WHITE_BUD.get(), ModBlocks.SMALL_TRUE_WHITE_BUD.get(),
                ModBlocks.BLUE_CRYSTAL.get(), ModBlocks.BIG_BLUE_BUD.get(), ModBlocks.SMALL_BLUE_BUD.get(),
                ModBlocks.LIGHT_BLUE_CRYSTAL.get(), ModBlocks.BIG_LIGHT_BLUE_BUD.get(), ModBlocks.SMALL_LIGHT_BLUE_BUD.get(),
                ModBlocks.GREEN_CRYSTAL.get(), ModBlocks.BIG_GREEN_BUD.get(), ModBlocks.SMALL_GREEN_BUD.get(),
                ModBlocks.YELLOW_CRYSTAL.get(), ModBlocks.BIG_YELLOW_BUD.get(), ModBlocks.SMALL_YELLOW_BUD.get(),
                ModBlocks.ORANGE_CRYSTAL.get(), ModBlocks.BIG_ORANGE_BUD.get(), ModBlocks.SMALL_ORANGE_BUD.get(),
                ModBlocks.RED_CRYSTAL.get(), ModBlocks.BIG_RED_BUD.get(), ModBlocks.SMALL_RED_BUD.get());
    }

    public static String trimmedId(String id){
        return id.replace("chakral.", "").replace("block.", "");
    }
}
