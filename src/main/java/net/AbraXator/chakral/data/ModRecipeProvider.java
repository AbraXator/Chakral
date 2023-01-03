package net.AbraXator.chakral.data;

import net.AbraXator.chakral.blocks.ModBlocks;
import net.AbraXator.chakral.items.ModItems;
import net.AbraXator.chakral.utils.ModTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.StonecutterRecipe;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(PackOutput p_248933_) {
        super(p_248933_);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> p_251297_) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.GOLDEN_NECKLACE.get()).pattern(" A ").pattern("A A").pattern(" B ").define('A', Items.GOLD_INGOT).define('B', ModTags.Items.SHARDS).unlockedBy("has_shard", has(ModTags.Items.SHARDS)).save(p_251297_);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.DIAMOND_NECKLACE.get()).pattern(" A ").pattern("ABA").pattern(" A ").define('A', Items.DIAMOND).define('B', ModItems.GOLDEN_NECKLACE.get()).unlockedBy("has_golden_necklace", has(ModItems.GOLDEN_NECKLACE.get())).save(p_251297_);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.NETHERITE_NECKLACE.get()).pattern(" A ").pattern("ABA").pattern(" A ").define('A', Items.NETHERITE_INGOT).define('B', ModItems.DIAMOND_NECKLACE.get()).unlockedBy("has_diamond_necklace", has(ModItems.DIAMOND_NECKLACE.get())).save(p_251297_);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.RAINBOW_NECKLACE.get()).pattern(" A ").pattern("ABA").pattern(" A ").define('A', Items.BARRIER).define('B', ModItems.NETHERITE_NECKLACE.get()).unlockedBy("has_netherite_necklace", has(ModItems.NETHERITE_NECKLACE.get())).save(p_251297_);
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.RAW_STEMSHROOM_STEM.get()), RecipeCategory.FOOD, ModItems.COOKED_STEMSHROOM_STEM.get(), 0.35F, 200).unlockedBy("has_raw_stemshroom", has(ModItems.RAW_STEMSHROOM_STEM.get())).save(p_251297_);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.NECKLACE_SLOTTER.get()).pattern("ABA").pattern(" A ").pattern("CCC").define('A', ModBlocks.BLACK_MINERAL.get()).define('B', ModTags.Items.NECKLACES).define('C', Items.GOLD_INGOT).unlockedBy("has_any_necklace", has(ModTags.Items.NECKLACES_LOW));
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.NECKLACE_INSERTER.get()).pattern("AA ").pattern("ABA").pattern("CDC").define('A', Blocks.IRON_BLOCK).define('B', ModBlocks.NECKLACE_SLOTTER.get()).define('C', Blocks.COPPER_BLOCK).define('D', Items.BARRIER).unlockedBy("has_necklace_slotter", has(ModBlocks.NECKLACE_SLOTTER.get())).save(p_251297_);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SHARD_REFINER.get()).pattern("AB").pattern("CC").define('A', Items.IRON_INGOT).define('B', ModTags.Items.SHARDS).define('C', ItemTags.LOGS).unlockedBy("has_shard", has(ModTags.Items.SHARDS)).save(p_251297_);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.MINERAL_ENRICHER.get()).pattern("ABA").pattern("CDE").pattern("AAA").define('A', Blocks.IRON_BLOCK).define('B', Items.BUCKET).define('C', Blocks.TINTED_GLASS).define('D', ModBlocks.TRUE_WHITE_MINERAL.get()).define('E', ModItems.SHARD_DUST.get()).unlockedBy("has_true_white_mineral", has(ModBlocks.TRUE_WHITE_MINERAL.get())).save(p_251297_);
        twoByTwoPacker(p_251297_, RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLACK_MINERAL_BRICKS.get(), ModBlocks.BLACK_MINERAL.get());
        twoByTwoPacker(p_251297_, RecipeCategory.BUILDING_BLOCKS, ModBlocks.WHITE_MINERAL_BRICKS.get(), ModBlocks.WHITE_MINERAL.get());
        twoByTwoPacker(p_251297_, RecipeCategory.BUILDING_BLOCKS, ModBlocks.TRUE_WHITE_MINERAL_BRICKS.get(), ModBlocks.TRUE_WHITE_MINERAL.get());
        twoByTwoPacker(p_251297_, RecipeCategory.BUILDING_BLOCKS, Blocks.AMETHYST_BLOCK, ModBlocks.AMETHYST_BRICKS.get());
        twoByTwoPacker(p_251297_, RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLUE_MINERAL_BRICKS.get(), ModBlocks.BLUE_MINERAL.get());
        twoByTwoPacker(p_251297_, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIGHT_BLUE_MINERAL_BRICKS.get(), ModBlocks.LIGHT_BLUE_MINERAL.get());
        twoByTwoPacker(p_251297_, RecipeCategory.BUILDING_BLOCKS, ModBlocks.GREEN_MINERAL_BRICKS.get(), ModBlocks.GREEN_MINERAL.get());
        twoByTwoPacker(p_251297_, RecipeCategory.BUILDING_BLOCKS, ModBlocks.YELLOW_MINERAL_BRICKS.get(), ModBlocks.YELLOW_MINERAL.get());
        twoByTwoPacker(p_251297_, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ORANGE_MINERAL_BRICKS.get(), ModBlocks.ORANGE_MINERAL.get());
        twoByTwoPacker(p_251297_, RecipeCategory.BUILDING_BLOCKS, ModBlocks.RED_MINERAL_BRICKS.get(), ModBlocks.RED_MINERAL.get());
        stonecutterResultFromBase(p_251297_, RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLACK_MINERAL_BRICKS.get(), ModBlocks.BLACK_MINERAL.get());
        stonecutterResultFromBase(p_251297_, RecipeCategory.BUILDING_BLOCKS, ModBlocks.WHITE_MINERAL_BRICKS.get(), ModBlocks.WHITE_MINERAL.get());
        stonecutterResultFromBase(p_251297_, RecipeCategory.BUILDING_BLOCKS, ModBlocks.TRUE_WHITE_MINERAL_BRICKS.get(), ModBlocks.TRUE_WHITE_MINERAL.get());
        stonecutterResultFromBase(p_251297_, RecipeCategory.BUILDING_BLOCKS, ModBlocks.AMETHYST_BRICKS.get(), Blocks.AMETHYST_BLOCK);
        stonecutterResultFromBase(p_251297_, RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLUE_MINERAL_BRICKS.get(), ModBlocks.BLUE_MINERAL.get());
        stonecutterResultFromBase(p_251297_, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIGHT_BLUE_MINERAL_BRICKS.get(), ModBlocks.LIGHT_BLUE_MINERAL.get());
        stonecutterResultFromBase(p_251297_, RecipeCategory.BUILDING_BLOCKS, ModBlocks.GREEN_MINERAL_BRICKS.get(), ModBlocks.GREEN_MINERAL.get());
        stonecutterResultFromBase(p_251297_, RecipeCategory.BUILDING_BLOCKS, ModBlocks.YELLOW_MINERAL_BRICKS.get(), ModBlocks.YELLOW_MINERAL.get());
        stonecutterResultFromBase(p_251297_, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ORANGE_MINERAL_BRICKS.get(), ModBlocks.ORANGE_MINERAL.get());
        stonecutterResultFromBase(p_251297_, RecipeCategory.BUILDING_BLOCKS, ModBlocks.RED_MINERAL_BRICKS.get(), ModBlocks.RED_MINERAL.get());
    }
}
