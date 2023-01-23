package net.AbraXator.chakral.blocks;

import net.AbraXator.chakral.Chakral;
import net.AbraXator.chakral.blocks.custom.*;
import net.AbraXator.chakral.items.ModItems;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Chakral.MOD_ID);

    //--------------------BLACK---------------------
    public static final RegistryObject<Block> BLACK_MINERAL                 = registerBlock("black_mineral", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).strength(9f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> BLACK_MINERAL_BRICKS          = registerBlock("black_mineral_bricks", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).strength(9f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> BLACK_MINERAL_BRICK_STAIRS    = registerBlock("black_mineral_brick_stairs", () -> new StairBlock(() -> ModBlocks.BLACK_MINERAL_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).strength(9f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> BLACK_CRYSTAL                 = registerBlock("black_crystal", () -> new Crystal(BlockBehaviour.Properties.of(Material.AMETHYST, MaterialColor.COLOR_BLACK).strength(9f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> GLOWSTONE_CRYSTAL             = registerBlock("glowstone_crystal", () -> new Crystal(BlockBehaviour.Properties.of(Material.AMETHYST, MaterialColor.SAND).strength(9f).requiresCorrectToolForDrops().lightLevel(value -> 15)));
    public static final RegistryObject<Block> BUDDING_BLACK_MINERAL         = registerBlock("budding_black_mineral", () -> new ModBuddingBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).randomTicks().strength(9f).requiresCorrectToolForDrops(), List.of(ModBlocks.GLOWSTONE_CRYSTAL.get(), ModBlocks.BLACK_CRYSTAL.get(), ModBlocks.BLACK_CRYSTAL.get(), ModBlocks.BLACK_CRYSTAL.get())));
    //---------------------WHITE----------------------
    public static final RegistryObject<Block> WHITE_MINERAL                 = registerBlock("white_mineral", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_WHITE).strength(9f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> BUDDING_WHITE_MINERAL         = registerBlock("budding_white_mineral", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_WHITE).strength(9f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> WHITE_MINERAL_BRICKS          = registerBlock("white_mineral_bricks", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_WHITE).strength(9f).lightLevel(value -> 6).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> WHITE_CRYSTAL                 = registerBlock("white_crystal", () -> new Crystal(BlockBehaviour.Properties.of(Material.AMETHYST, MaterialColor.TERRACOTTA_WHITE).strength(9f).requiresCorrectToolForDrops()));
    //-----------------TRUE WHITE--------------------
    public static final RegistryObject<Block> TRUE_WHITE_MINERAL            = registerBlock("true_white_mineral", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.SNOW).strength(9f).lightLevel(value -> 6).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> TRUE_WHITE_MINERAL_BRICKS     = registerBlock("true_white_mineral_bricks", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.SNOW).strength(9f).lightLevel(value -> 6).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> TRUE_WHITE_CRYSTAL            = registerBlock("true_white_crystal", () -> new Crystal(BlockBehaviour.Properties.of(Material.AMETHYST, MaterialColor.SNOW).strength(9f).requiresCorrectToolForDrops()));
    //--------------------PURPLE---------------------
    public static final RegistryObject<Block> AMETHYST_BRICKS               = registerBlock("amethyst_bricks", () -> new Block(BlockBehaviour.Properties.of(Material.AMETHYST, MaterialColor.COLOR_PURPLE).strength(9f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> AMETHYST_BRICK_STAIRS         = registerBlock("amethyst_brick_stairs", () -> new StairBlock(() -> ModBlocks.AMETHYST_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.AMETHYST, MaterialColor.COLOR_PURPLE).strength(9f).requiresCorrectToolForDrops()));
    //--------------------BLUE---------------------
    public static final RegistryObject<Block> BLUE_MINERAL                  = registerBlock("blue_mineral", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(9f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> BUDDING_BLUE_MINERAL          = registerBlock("budding_blue_mineral", () -> new BuddingAmethystBlock(BlockBehaviour.Properties.of(Material.STONE).strength(9f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> BLUE_MINERAL_BRICKS           = registerBlock("blue_mineral_bricks", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(9f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> BLUE_MINERAL_BRICK_STAIRS     = registerBlock("blue_mineral_brick_stairs", () -> new StairBlock(() -> ModBlocks.BLUE_MINERAL_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).strength(9f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> BLUE_CRYSTAL                  = registerBlock("blue_crystal", () -> new Crystal(BlockBehaviour.Properties.of(Material.AMETHYST).strength(9f).lightLevel(value -> 4).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> SHATTERED_BLUE_MINERAL        = registerBlock("shattered_blue_mineral", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(9f).requiresCorrectToolForDrops()));
    //--------------------LIGHT BLUE-----------------
    public static final RegistryObject<Block> LIGHT_BLUE_MINERAL            = registerBlock("light_blue_mineral", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(9f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> LIGHT_BLUE_MINERAL_BRICKS     = registerBlock("light_blue_mineral_bricks", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(9f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> BUDDING_LIGHT_BLUE_MINERAL    = registerBlock("budding_light_blue_mineral", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(9f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block>LIGHT_BLUE_MINERAL_BRICK_STAIRS= registerBlock("light_blue_mineral_brick_stairs", () -> new StairBlock(() -> ModBlocks.LIGHT_BLUE_MINERAL_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).strength(9f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> LIGHT_BLUE_CRYSTAL            = registerBlock("light_blue_crystal", () -> new Crystal(BlockBehaviour.Properties.of(Material.STONE).strength(9f).lightLevel(value -> 4).requiresCorrectToolForDrops()));
    //--------------------GREEN---------------------
    public static final RegistryObject<Block> GREEN_MINERAL                 = registerBlock("green_mineral", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(9f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> GREEN_MINERAL_BRICKS          = registerBlock("green_mineral_bricks", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(9f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> GREEN_MINERAL_BRICK_STAIRS    = registerBlock("green_mineral_brick_stairs", () -> new StairBlock(() -> ModBlocks.GREEN_MINERAL_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).strength(9f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> GREEN_CRYSTAL                 = registerBlock("green_crystal", () -> new Crystal(BlockBehaviour.Properties.of(Material.AMETHYST).strength(9f).lightLevel(value -> 4).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> BUDDING_GREEN_MINERAL         = registerBlock("budding_green_mineral", () -> new ModBuddingBlock(BlockBehaviour.Properties.of(Material.STONE).randomTicks().strength(9f).requiresCorrectToolForDrops(), List.of(ModBlocks.GREEN_CRYSTAL.get())));
    //--------------------YELLOW---------------------
    public static final RegistryObject<Block> YELLOW_MINERAL                = registerBlock("yellow_mineral", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(9f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> BUDDING_YELLOW_MINERAL        = registerBlock("budding_yellow_mineral", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(9f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> YELLOW_MINERAL_BRICKS         = registerBlock("yellow_mineral_bricks", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(9f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> YELLOW_MINERAL_BRICK_STAIRS   = registerBlock("yellow_mineral_brick_stairs", () -> new StairBlock(() -> ModBlocks.YELLOW_MINERAL_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).strength(9f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> YELLOW_CRYSTAL                = registerBlock("yellow_crystal", () -> new Crystal(BlockBehaviour.Properties.of(Material.AMETHYST).lightLevel(value -> 4).strength(9f).requiresCorrectToolForDrops()));
    //--------------------ORANGE---------------------
    public static final RegistryObject<Block> ORANGE_MINERAL                = registerBlock("orange_mineral", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(9f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> BUDDING_ORANGE_MINERAL        = registerBlock("budding_orange_mineral", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(9f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ORANGE_MINERAL_BRICKS         = registerBlock("orange_mineral_bricks", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(9f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ORANGE_MINERAL_BRICK_STAIRS   = registerBlock("orange_mineral_brick_stairs", () -> new StairBlock(() -> ModBlocks.ORANGE_MINERAL_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).strength(9f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ORANGE_CRYSTAL                = registerBlock("orange_crystal", () -> new Crystal(BlockBehaviour.Properties.of(Material.AMETHYST).lightLevel(value -> 4).strength(9f).requiresCorrectToolForDrops()));
    //--------------------RED------------------------
    public static final RegistryObject<Block> RED_MINERAL                   = registerBlock("red_mineral", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(9f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> BUDDING_RED_MINERAL           = registerBlock("budding_red_mineral", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(9f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> RED_MINERAL_BRICKS            = registerBlock("red_mineral_bricks", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(9f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> RED_MINERAL_BRICK_STAIRS      = registerBlock("red_mineral_brick_stairs", () -> new StairBlock(() -> ModBlocks.RED_MINERAL_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).strength(9f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> RED_CRYSTAL                   = registerBlock("red_crystal", () -> new Crystal(BlockBehaviour.Properties.of(Material.AMETHYST).lightLevel(value -> 4).strength(9f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> SHATTERED_RED_MINERAL         = registerBlock("shattered_red_mineral", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(9f).requiresCorrectToolForDrops()));
    //---------------DIVNÝ VĚCI----------------------
    public static final RegistryObject<Block> BROWNSTONE                    = registerBlock("brownstone", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(9f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> MINERAL_RICH_DIRT             = registerBlock("mineral_rich_dirt", () -> new Block(BlockBehaviour.Properties.of(Material.AMETHYST).strength(9f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> MINERAL_RICH_PERENNIAL        = registerBlock("mineral_rich_perennial", () -> new FlowerBlock(() -> MobEffects.DIG_SPEED, 15, BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)));
    public static final RegistryObject<Block> MINERAL_RICH_GRASS            = registerBlock("mineral_rich_grass", () -> new MineralRichGrassBlock(BlockBehaviour.Properties.of(Material.GRASS).sound(SoundType.GRASS).strength(9f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> BURGEONING_ROOTS              = registerBlock("burgeoning_roots", () -> new Block(BlockBehaviour.Properties.of(Material.AMETHYST).strength(9f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> PLACEHOLDER_SHROOM_BLOCK      = registerBlock("placeholder_shroom_block", () -> new ShroomBlock(BlockBehaviour.Properties.of(Material.AMETHYST).strength(9f).requiresCorrectToolForDrops().randomTicks()));
    public static final RegistryObject<Block> GLEAMSHROOM                   = registerBlock("gleamshroom", () -> new GleamshroomBlock(BlockBehaviour.Properties.of(Material.AMETHYST).lightLevel(GleamshroomBlock.LIGHT_AMOUNT).strength(9f).noOcclusion().requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> STEMSHROOM                    = registerBlock("stemshroom", () -> new StemshroomBlock(BlockBehaviour.Properties.of(Material.AMETHYST).lightLevel(value -> 4).strength(9f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> PARAGENETIC_CORE              = registerBlock("paragenetic_core", () -> new ParageneticCoreBlock(BlockBehaviour.Properties.of(Material.AMETHYST).strength(9f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> NECKLACE_SLOTTER = registerBlock("necklace_slotter",
            () -> new NecklaceSlotterBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(9f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> NECKLACE_INSERTER = registerBlock("necklace_inserter",
            () -> new NecklaceInserterBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(9f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> SHARD_REFINER = registerBlock("shard_refiner",
            () -> new ShardRefinerBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(9f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> MINERAL_ENRICHER = registerBlock("mineral_enricher",
            () -> new MineralEnricherBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(9f).noOcclusion().requiresCorrectToolForDrops()));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }
    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties()));
    }
    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
