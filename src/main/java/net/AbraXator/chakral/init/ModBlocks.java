package net.AbraXator.chakral.init;

import net.AbraXator.chakral.Chakral;
import net.AbraXator.chakral.blocks.*;
import net.AbraXator.chakral.world.tree.WiltedTreeGrower;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;

import static net.minecraft.world.level.block.Blocks.OAK_PLANKS;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Chakral.MOD_ID);

    //--------------------BLACK---------------------
    public static final RegistryObject<Block> BLACK_MINERAL                 = registerBlock("black_mineral", () -> new Block(BlockBehaviour.Properties.of().strength(3.0F, 6.0F).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> BLACK_MINERAL_COAL_ORE        = registerBlock("black_mineral_coal_ore", () -> new Block(BlockBehaviour.Properties.of().strength(4.5F, 3.0F).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> BLACK_MINERAL_IRON_ORE        = registerBlock("black_mineral_iron_ore", () -> new Block(BlockBehaviour.Properties.of().strength(4.5F, 3.0F).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> BLACK_MINERAL_COPPER_ORE      = registerBlock("black_mineral_copper_ore", () -> new Block(BlockBehaviour.Properties.of().strength(4.5F, 3.0F).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> BLACK_MINERAL_GOLD_ORE        = registerBlock("black_mineral_gold_ore", () -> new Block(BlockBehaviour.Properties.of().strength(4.5F, 3.0F).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> BLACK_MINERAL_REDSTONE_ORE    = registerBlock("black_mineral_redstone_ore", () -> new Block(BlockBehaviour.Properties.of().strength(9f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> BLACK_MINERAL_EMERALD_ORE     = registerBlock("black_mineral_emerald_ore", () -> new Block(BlockBehaviour.Properties.of().strength(4.5F, 3.0F).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> BLACK_MINERAL_LAPIS_ORE       = registerBlock("black_mineral_lapis_ore", () -> new Block(BlockBehaviour.Properties.of().strength(4.5F, 3.0F).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> BLACK_MINERAL_DIAMOND_ORE     = registerBlock("black_mineral_diamond_ore", () -> new Block(BlockBehaviour.Properties.of().strength(4.5F, 3.0F).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> BLACK_MINERAL_BRICKS          = registerBlock("black_mineral_bricks", () -> new Block(BlockBehaviour.Properties.of().strength(3.0F, 6.0F).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> BLACK_MINERAL_BRICK_STAIRS    = registerBlock("black_mineral_brick_stairs", () -> new StairBlock(() -> ModBlocks.BLACK_MINERAL_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of().strength(3.0F, 6.0F).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> BLACK_CRYSTAL                 = registerBlock("black_crystal", () -> new Crystal(BlockBehaviour.Properties.of().strength(3.0F, 6.0F).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> BIG_BLACK_BUD                 = registerBlock("big_black_bud", () -> new Crystal(BlockBehaviour.Properties.of().strength(3.0F, 6.0F).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> SMALL_BLACK_BUD               = registerBlock("small_black_bud", () -> new Crystal(BlockBehaviour.Properties.of().strength(3.0F, 6.0F).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> BUDDING_BLACK_MINERAL         = registerBlock("budding_black_mineral", () -> new ModBuddingBlock(BlockBehaviour.Properties.of().randomTicks().strength(3.0F, 6.0F).requiresCorrectToolForDrops(), List.of(SMALL_BLACK_BUD, BIG_BLACK_BUD, BLACK_CRYSTAL)));
    //---------------------GLOWSTONE-------------------
    public static final RegistryObject<Block> GLOWSTONE_CLUSTER             = registerBlock("glowstone_cluster", () -> new Crystal(BlockBehaviour.Properties.of().strength(9f).requiresCorrectToolForDrops().lightLevel(value -> 15)));
    public static final RegistryObject<Block> BIG_GLOWSTONE_BUD             = registerBlock("big_glowstone_bud", () -> new Crystal(BlockBehaviour.Properties.of().strength(9f).requiresCorrectToolForDrops().lightLevel(value -> 15)));
    public static final RegistryObject<Block> SMALL_GLOWSTONE_BUD           = registerBlock("small_glowstone_bud", () -> new Crystal(BlockBehaviour.Properties.of().strength(9f).requiresCorrectToolForDrops().lightLevel(value -> 15)));
    //---------------------WHITE----------------------
    public static final RegistryObject<Block> WHITE_MINERAL                 = registerBlock("white_mineral", () -> new Block(BlockBehaviour.Properties.of().strength(1.5F).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> WHITE_MINERAL_BRICKS          = registerBlock("white_mineral_bricks", () -> new Block(BlockBehaviour.Properties.of().strength(9f).lightLevel(value -> 6).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> WHITE_CRYSTAL                 = registerBlock("white_crystal", () -> new Crystal(BlockBehaviour.Properties.of().strength(9f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> BIG_WHITE_BUD                 = registerBlock("big_white_bud", () -> new Crystal(BlockBehaviour.Properties.of().strength(9f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> SMALL_WHITE_BUD               = registerBlock("small_white_bud", () -> new Crystal(BlockBehaviour.Properties.of().strength(9f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> BUDDING_WHITE_MINERAL         = registerBlock("budding_white_mineral", () -> new ModBuddingBlock(BlockBehaviour.Properties.of().strength(9f).requiresCorrectToolForDrops().randomTicks(), List.of(SMALL_WHITE_BUD, BIG_WHITE_BUD, WHITE_CRYSTAL)));
    //-----------------TRUE WHITE--------------------
    public static final RegistryObject<Block> TRUE_WHITE_MINERAL            = registerBlock("true_white_mineral", () -> new Block(BlockBehaviour.Properties.of().strength(4.0F, 6.0F).lightLevel(value -> 6).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> TRUE_WHITE_MINERAL_BRICKS     = registerBlock("true_white_mineral_bricks", () -> new Block(BlockBehaviour.Properties.of().strength(4.0F, 6.0F).lightLevel(value -> 6).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> TRUE_WHITE_CRYSTAL            = registerBlock("true_white_crystal", () -> new Crystal(BlockBehaviour.Properties.of().strength(4.0F, 6.0F).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> BIG_TRUE_WHITE_BUD            = registerBlock("big_true_white_bud", () -> new Crystal(BlockBehaviour.Properties.of().strength(4.0F, 6.0F).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> SMALL_TRUE_WHITE_BUD          = registerBlock("small_true_white_bud", () -> new Crystal(BlockBehaviour.Properties.of().strength(4.0F, 6.0F).requiresCorrectToolForDrops()));
    //--------------------PURPLE---------------------
    public static final RegistryObject<Block> AMETHYST_BRICKS               = registerBlock("amethyst_bricks", () -> new Block(BlockBehaviour.Properties.of().strength(1.5F).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> AMETHYST_BRICK_STAIRS         = registerBlock("amethyst_brick_stairs", () -> new StairBlock(() -> ModBlocks.AMETHYST_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of().strength(1.5F).requiresCorrectToolForDrops()));
    //--------------------BLUE---------------------
    public static final RegistryObject<Block> BLUE_MINERAL                  = registerBlock("blue_mineral", () -> new Block(BlockBehaviour.Properties.of().strength(1.5F).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> BLUE_MINERAL_BRICKS           = registerBlock("blue_mineral_bricks", () -> new Block(BlockBehaviour.Properties.of().strength(1.5F).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> BLUE_MINERAL_BRICK_STAIRS     = registerBlock("blue_mineral_brick_stairs", () -> new StairBlock(() -> ModBlocks.BLUE_MINERAL_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of().strength(9f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> BLUE_CRYSTAL                  = registerBlock("blue_crystal", () -> new Crystal(BlockBehaviour.Properties.of().strength(1.5F).lightLevel(value -> 4).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> BIG_BLUE_BUD                  = registerBlock("big_blue_bud", () -> new Crystal(BlockBehaviour.Properties.of().strength(1.5F).lightLevel(value -> 4).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> SMALL_BLUE_BUD                = registerBlock("small_blue_bud", () -> new Crystal(BlockBehaviour.Properties.of().strength(1.5F).lightLevel(value -> 4).requiresCorrectToolForDrops()));
    //public static final RegistryObject<Block> SHATTERED_BLUE_MINERAL        = registerBlock("shattered_blue_mineral", () -> new Block(BlockBehaviour.Properties.of().instabreak().requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> BUDDING_BLUE_MINERAL          = registerBlock("budding_blue_mineral", () -> new ModBuddingBlock(BlockBehaviour.Properties.of().strength(1.5F).requiresCorrectToolForDrops().randomTicks(), List.of(SMALL_BLUE_BUD, BIG_BLUE_BUD, BLUE_CRYSTAL)));
    //--------------------LIGHT BLUE-----------------
    public static final RegistryObject<Block> LIGHT_BLUE_MINERAL            = registerBlock("light_blue_mineral", () -> new Block(BlockBehaviour.Properties.of().strength(1.5F).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> LIGHT_BLUE_MINERAL_BRICKS     = registerBlock("light_blue_mineral_bricks", () -> new Block(BlockBehaviour.Properties.of().strength(1.5F).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block>LIGHT_BLUE_MINERAL_BRICK_STAIRS= registerBlock("light_blue_mineral_brick_stairs", () -> new StairBlock(() -> ModBlocks.LIGHT_BLUE_MINERAL_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of().strength(1.5F).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> LIGHT_BLUE_CRYSTAL            = registerBlock("light_blue_crystal", () -> new Crystal(BlockBehaviour.Properties.of().strength(1.5F).lightLevel(value -> 4).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> BIG_LIGHT_BLUE_BUD            = registerBlock("big_light_blue_bud", () -> new Crystal(BlockBehaviour.Properties.of().strength(1.5F).lightLevel(value -> 4).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> SMALL_LIGHT_BLUE_BUD          = registerBlock("small_light_blue_bud", () -> new Crystal(BlockBehaviour.Properties.of().strength(1.5F).lightLevel(value -> 4).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> BUDDING_LIGHT_BLUE_MINERAL    = registerBlock("budding_light_blue_mineral", () -> new ModBuddingBlock(BlockBehaviour.Properties.of().strength(1.5F).requiresCorrectToolForDrops().randomTicks(), List.of(SMALL_LIGHT_BLUE_BUD, BIG_LIGHT_BLUE_BUD, LIGHT_BLUE_CRYSTAL)));
    //--------------------GREEN---------------------
    public static final RegistryObject<Block> GREEN_MINERAL                 = registerBlock("green_mineral", () -> new Block(BlockBehaviour.Properties.of().strength(1.5F).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> GREEN_MINERAL_BRICKS          = registerBlock("green_mineral_bricks", () -> new Block(BlockBehaviour.Properties.of().strength(1.5F).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> GREEN_MINERAL_BRICK_STAIRS    = registerBlock("green_mineral_brick_stairs", () -> new StairBlock(() -> ModBlocks.GREEN_MINERAL_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of().strength(9f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> GREEN_CRYSTAL                 = registerBlock("green_crystal", () -> new Crystal(BlockBehaviour.Properties.of().strength(1.5F).lightLevel(value -> 4).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> BIG_GREEN_BUD                 = registerBlock("big_green_bud", () -> new Crystal(BlockBehaviour.Properties.of().strength(1.5F).lightLevel(value -> 4).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> SMALL_GREEN_BUD               = registerBlock("small_green_bud", () -> new Crystal(BlockBehaviour.Properties.of().strength(1.5F).lightLevel(value -> 4).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> BUDDING_GREEN_MINERAL         = registerBlock("budding_green_mineral", () -> new ModBuddingBlock(BlockBehaviour.Properties.of().randomTicks().strength(1.5F).requiresCorrectToolForDrops(), List.of(SMALL_GREEN_BUD, BIG_GREEN_BUD, GREEN_CRYSTAL)));
    //--------------------YELLOW---------------------
    public static final RegistryObject<Block> YELLOW_MINERAL                = registerBlock("yellow_mineral", () -> new Block(BlockBehaviour.Properties.of().strength(1.5F).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> YELLOW_MINERAL_BRICKS         = registerBlock("yellow_mineral_bricks", () -> new Block(BlockBehaviour.Properties.of().strength(1.5F).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> YELLOW_MINERAL_BRICK_STAIRS   = registerBlock("yellow_mineral_brick_stairs", () -> new StairBlock(() -> ModBlocks.YELLOW_MINERAL_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of().strength(9f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> YELLOW_CRYSTAL                = registerBlock("yellow_crystal", () -> new Crystal(BlockBehaviour.Properties.of().lightLevel(value -> 4).strength(1.5F).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> BIG_YELLOW_BUD                = registerBlock("big_yellow_bud", () -> new Crystal(BlockBehaviour.Properties.of().lightLevel(value -> 4).strength(1.5F).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> SMALL_YELLOW_BUD              = registerBlock("small_yellow_bud", () -> new Crystal(BlockBehaviour.Properties.of().lightLevel(value -> 4).strength(1.5F).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> BUDDING_YELLOW_MINERAL        = registerBlock("budding_yellow_mineral", () -> new ModBuddingBlock(BlockBehaviour.Properties.of().strength(1.5F).requiresCorrectToolForDrops().randomTicks(), List.of(SMALL_YELLOW_BUD, BIG_YELLOW_BUD, YELLOW_CRYSTAL)));
    //--------------------ORANGE---------------------
    public static final RegistryObject<Block> ORANGE_MINERAL                = registerBlock("orange_mineral", () -> new Block(BlockBehaviour.Properties.of().strength(1.5F).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ORANGE_MINERAL_BRICKS         = registerBlock("orange_mineral_bricks", () -> new Block(BlockBehaviour.Properties.of().strength(1.5F).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ORANGE_MINERAL_BRICK_STAIRS   = registerBlock("orange_mineral_brick_stairs", () -> new StairBlock(() -> ModBlocks.ORANGE_MINERAL_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of().strength(9f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ORANGE_CRYSTAL                = registerBlock("orange_crystal", () -> new Crystal(BlockBehaviour.Properties.of().lightLevel(value -> 4).strength(1.5F).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> BIG_ORANGE_BUD                = registerBlock("big_orange_bud", () -> new Crystal(BlockBehaviour.Properties.of().lightLevel(value -> 4).strength(1.5F).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> SMALL_ORANGE_BUD              = registerBlock("small_orange_bud", () -> new Crystal(BlockBehaviour.Properties.of().lightLevel(value -> 4).strength(1.5F).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> BUDDING_ORANGE_MINERAL        = registerBlock("budding_orange_mineral", () -> new ModBuddingBlock(BlockBehaviour.Properties.of().strength(1.5F).requiresCorrectToolForDrops().randomTicks(), List.of(SMALL_ORANGE_BUD, BIG_ORANGE_BUD, ORANGE_CRYSTAL)));
    //--------------------RED------------------------
    public static final RegistryObject<Block> RED_MINERAL                   = registerBlock("red_mineral", () -> new Block(BlockBehaviour.Properties.of().strength(1.5F).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> RED_MINERAL_BRICKS            = registerBlock("red_mineral_bricks", () -> new Block(BlockBehaviour.Properties.of().strength(1.5F).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> RED_MINERAL_BRICK_STAIRS      = registerBlock("red_mineral_brick_stairs", () -> new StairBlock(() -> ModBlocks.RED_MINERAL_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of().strength(9f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> RED_CRYSTAL                   = registerBlock("red_crystal", () -> new Crystal(BlockBehaviour.Properties.of().lightLevel(value -> 4).strength(1.5F).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> BIG_RED_BUD                   = registerBlock("big_red_bud", () -> new Crystal(BlockBehaviour.Properties.of().lightLevel(value -> 4).strength(1.5F).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> SMALL_RED_BUD                 = registerBlock("small_red_bud", () -> new Crystal(BlockBehaviour.Properties.of().lightLevel(value -> 4).strength(1.5F).requiresCorrectToolForDrops()));
    //public static final RegistryObject<Block> SHATTERED_RED_MINERAL         = registerBlock("shattered_red_mineral", () -> new Block(BlockBehaviour.Properties.of().instabreak()));
    public static final RegistryObject<Block> BUDDING_RED_MINERAL           = registerBlock("budding_red_mineral", () -> new ModBuddingBlock(BlockBehaviour.Properties.of().strength(1.5F).requiresCorrectToolForDrops().randomTicks(), List.of(SMALL_RED_BUD, BIG_RED_BUD, RED_CRYSTAL)));
    //---------------DIVNÝ VĚCI----------------------
    public static final RegistryObject<Block> BROWNSTONE                    = registerBlock("brownstone", () -> new Block(BlockBehaviour.Properties.of().strength(3.0F, 6.0F).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> WILTED_LOG                    = registerBlock("wilted_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().strength(2.0F, 3.0F)));
    public static final RegistryObject<Block> STRIPPED_WILTED_LOG           = registerBlock("stripped_wilted_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().strength(2.0F, 3.0F)));
    public static final RegistryObject<Block> WILTED_WOOD                   = registerBlock("wilted_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().strength(2.0F, 3.0F)));
    public static final RegistryObject<Block> STRIPPED_WILTED_WOOD          = registerBlock("stripped_wilted_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().strength(2.0F, 3.0F)));
    public static final RegistryObject<Block> WILTED_TRAPDOOR               = registerBlock("wilted_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.of().strength(3.0F).noOcclusion(), BlockSetType.OAK));
    public static final RegistryObject<Block> WILTED_DOOR                   = registerBlock("wilted_door", () -> new DoorBlock(BlockBehaviour.Properties.of().strength(3.0F).noOcclusion(), BlockSetType.OAK));
    public static final RegistryObject<Block> WILTED_FENCE                  = registerBlock("wilted_fence", () -> new FenceBlock(BlockBehaviour.Properties.of().strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> WILTED_FENCE_GATE             = registerBlock("wilted_fence_gate", () -> new FenceGateBlock(BlockBehaviour.Properties.of().strength(2.0F, 3.0F), WoodType.OAK));
    public static final RegistryObject<Block> WILTED_SLAB                   = registerBlock("wilted_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> WILTED_STAIRS                 = registerBlock("wilted_stairs", () ->  new StairBlock(OAK_PLANKS::defaultBlockState, BlockBehaviour.Properties.of().strength(2.0F, 3.0F)));
    public static final RegistryObject<Block> WILTED_BUTTON                 = registerBlock("wilted_button", () ->  new ButtonBlock(BlockBehaviour.Properties.of().noCollission().strength(0.5F), BlockSetType.OAK, 30, true));
    public static final RegistryObject<Block> WILTED_PRESSURE_PLATE         = registerBlock("wilted_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of().noCollission().strength(0.5F), BlockSetType.OAK));
    public static final RegistryObject<Block> WILTED_PLANKS                 = registerBlock("wilted_planks", () -> new Block(BlockBehaviour.Properties.of().strength(2.0F, 3.0F)));
    public static final RegistryObject<Block> WILTED_LEAVES                 = registerBlock("wilted_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.of().strength(0.2f).isSuffocating(ModBlocks::never).isViewBlocking(ModBlocks::never).noOcclusion().sound(SoundType.GRASS)));
    public static final RegistryObject<Block> WILTED_SAPLING                = registerBlock("wilted_sapling", () -> new SaplingBlock(new WiltedTreeGrower(), BlockBehaviour.Properties.of().noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
    public static final RegistryObject<Block> MINERAL_RICH_DIRT             = registerBlock("mineral_rich_dirt", () -> new Block(BlockBehaviour.Properties.of().strength(0.5F).sound(SoundType.GRAVEL)));
    public static final RegistryObject<Block> MINERAL_RICH_FARMLAND         = registerBlock("mineral_rich_farmland", () -> new Block(BlockBehaviour.Properties.of().strength(0.5F).sound(SoundType.GRAVEL)));
    public static final RegistryObject<Block> MINERAL_RICH_PERENNIAL        = registerBlock("mineral_rich_perennial", () -> new FlowerBlock(() -> MobEffects.DIG_SPEED, 15, BlockBehaviour.Properties.of().noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)));
    public static final RegistryObject<Block> MINERAL_RICH_GRASS            = registerBlock("mineral_rich_grass", () -> new MineralRichGrassBlock(BlockBehaviour.Properties.of().sound(SoundType.GRASS).instabreak()));
    public static final RegistryObject<Block> BURGEONING_ROOTS              = registerBlock("burgeoning_roots", () -> new Block(BlockBehaviour.Properties.of().strength(0.5F).sound(SoundType.GRAVEL)));
    public static final RegistryObject<Block> GLEAMSHROOM                   = registerBlock("gleamshroom", () -> new GleamshroomBlock(BlockBehaviour.Properties.of().lightLevel(value -> 10).strength(0.2F).noOcclusion()) {});
    public static final RegistryObject<Block> STEMSHROOM                    = registerBlock("stemshroom", () -> new StemshroomBlock(BlockBehaviour.Properties.of().randomTicks().lightLevel(value -> 4).strength(0.2F)));
    public static final RegistryObject<Block> PARAGENETIC_CORE              = registerBlock("paragenetic_core", () -> new ParageneticCoreBlock(BlockBehaviour.Properties.of().strength(8f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> NECKLACE_SLOTTER = registerBlock("necklace_slotter",
            () -> new NecklaceSlotterBlock(BlockBehaviour.Properties.of()
                    .strength(3.0F, 6.0F)));
    public static final RegistryObject<Block> NECKLACE_INSERTER = registerBlock("necklace_inserter",
            () -> new NecklaceInserterBlock(BlockBehaviour.Properties.of()
                    .strength(3.0F, 6.0F)));
    public static final RegistryObject<Block> SHARD_REFINER = registerBlock("shard_refiner",
            () -> new ShardRefinerBlock(BlockBehaviour.Properties.of()
                    .strength(2.5F)));
    public static final RegistryObject<Block> MINERAL_ENRICHER = registerBlock("mineral_enricher",
            () -> new MineralEnricherBlock(BlockBehaviour.Properties.of()
                    .strength(5.0F, 6.0F).noOcclusion().sound(SoundType.METAL)));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }
    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties()));
    }

    private static ToIntFunction<BlockState> litBlockEmission(int pLightValue) {
        return (p_50763_) -> p_50763_.getValue(BlockStateProperties.LIT) ? pLightValue : 0;
    }

    private static boolean never(BlockState p50806, BlockGetter p50807, BlockPos p50808) {
        return false;
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
