package net.AbraXator.chakramod.blocks;

import net.AbraXator.chakramod.ChakraMod;
import net.AbraXator.chakramod.blocks.custom.Crystal;
import net.AbraXator.chakramod.blocks.custom.ModBuddingBlock;
import net.AbraXator.chakramod.blocks.custom.ShardRefinerBlock;
import net.AbraXator.chakramod.blocks.custom.NecklaceSlotterBlock;
import net.AbraXator.chakramod.items.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BuddingAmethystBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, ChakraMod.MOD_ID);

    //--------------------BLACK---------------------
    public static final RegistryObject<Block> BLACK_MINERAL                 = registerBlock("black_mineral", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(9f).requiresCorrectToolForDrops()), ChakraMod.Tab.CHAKRA_TAB);
    public static final RegistryObject<Block> BLACK_MINERAL_BRICKS          = registerBlock("black_mineral_bricks", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(9f).requiresCorrectToolForDrops()), ChakraMod.Tab.CHAKRA_TAB);
    public static final RegistryObject<Block> BLACK_MINERAL_BRICK_STAIRS    = registerBlock("black_mineral_brick_stairs", () -> new StairBlock(() -> ModBlocks.BLACK_MINERAL_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).strength(9f).requiresCorrectToolForDrops()), ChakraMod.Tab.CHAKRA_TAB);
    public static final RegistryObject<Block> GLOWSTONE_CRYSTAL             = registerBlock("glowstone_crystal", () -> new Crystal(BlockBehaviour.Properties.of(Material.AMETHYST).strength(9f).lightLevel(value -> 15).requiresCorrectToolForDrops()), ChakraMod.Tab.CHAKRA_TAB);
    //--------------------PURPLE---------------------
    public static final RegistryObject<Block> AMETHYST_BRICKS               = registerBlock("amethyst_bricks", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(9f).requiresCorrectToolForDrops()), ChakraMod.Tab.CHAKRA_TAB);
    public static final RegistryObject<Block> AMETHYST_BRICK_STAIRS         = registerBlock("amethyst_brick_stairs", () -> new StairBlock(() -> ModBlocks.AMETHYST_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).strength(9f).requiresCorrectToolForDrops()), ChakraMod.Tab.CHAKRA_TAB);
    //--------------------BLUE---------------------
    public static final RegistryObject<Block> BLUE_MINERAL                  = registerBlock("blue_mineral", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(9f).requiresCorrectToolForDrops()), ChakraMod.Tab.CHAKRA_TAB);
    public static final RegistryObject<Block> BLUE_BUDDING_MINERAL          = registerBlock("blue_budding_mineral", () -> new BuddingAmethystBlock(BlockBehaviour.Properties.of(Material.STONE).strength(9f).requiresCorrectToolForDrops()), ChakraMod.Tab.CHAKRA_TAB);
    public static final RegistryObject<Block> BLUE_MINERAL_BRICKS           = registerBlock("blue_mineral_bricks", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(9f).requiresCorrectToolForDrops()), ChakraMod.Tab.CHAKRA_TAB);
    public static final RegistryObject<Block> BLUE_MINERAL_BRICK_STAIRS     = registerBlock("blue_mineral_brick_stairs", () -> new StairBlock(() -> ModBlocks.BLUE_MINERAL_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).strength(9f).requiresCorrectToolForDrops()), ChakraMod.Tab.CHAKRA_TAB);
    public static final RegistryObject<Block> BLUE_CRYSTAL                  = registerBlock("blue_crystal", () -> new Crystal(BlockBehaviour.Properties.of(Material.STONE).strength(9f).requiresCorrectToolForDrops()), ChakraMod.Tab.CHAKRA_TAB);
    //--------------------LIGHT BLUE-----------------
    public static final RegistryObject<Block> LIGHT_BLUE_MINERAL            = registerBlock("light_blue_mineral", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(9f).requiresCorrectToolForDrops()), ChakraMod.Tab.CHAKRA_TAB);
    public static final RegistryObject<Block> LIGHT_BLUE_BUDDING_MINERAL    = registerBlock("light_blue_budding_mineral", () -> new ModBuddingBlock(BlockBehaviour.Properties.of(Material.STONE).strength(9f).requiresCorrectToolForDrops(), ModBlocks.LIGHT_BLUE_CRYSTAL), ChakraMod.Tab.CHAKRA_TAB);
    public static final RegistryObject<Block> LIGHT_BLUE_MINERAL_BRICKS     = registerBlock("light_blue_mineral_bricks", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(9f).requiresCorrectToolForDrops()), ChakraMod.Tab.CHAKRA_TAB);
    public static final RegistryObject<Block>LIGHT_BLUE_MINERAL_BRICK_STAIRS= registerBlock("light_blue_mineral_brick_stairs", () -> new StairBlock(() -> ModBlocks.LIGHT_BLUE_MINERAL_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).strength(9f).requiresCorrectToolForDrops()), ChakraMod.Tab.CHAKRA_TAB);
    public static final RegistryObject<Block> LIGHT_BLUE_CRYSTAL            = registerBlock("light_blue_crystal", () -> new Crystal(BlockBehaviour.Properties.of(Material.STONE).strength(9f).requiresCorrectToolForDrops()), ChakraMod.Tab.CHAKRA_TAB);
    //--------------------GREEN---------------------
    public static final RegistryObject<Block> GREEN_MINERAL                 = registerBlock("green_mineral", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(9f).requiresCorrectToolForDrops()), ChakraMod.Tab.CHAKRA_TAB);
    public static final RegistryObject<Block> GREEN_BUDDING_MINERAL         = registerBlock("green_budding_mineral", () -> new ModBuddingBlock(BlockBehaviour.Properties.of(Material.STONE).strength(9f).requiresCorrectToolForDrops(), ModBlocks.GREEN_CRYSTAL), ChakraMod.Tab.CHAKRA_TAB);
    public static final RegistryObject<Block> GREEN_MINERAL_BRICKS          = registerBlock("green_mineral_bricks", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(9f).requiresCorrectToolForDrops()), ChakraMod.Tab.CHAKRA_TAB);
    public static final RegistryObject<Block> GREEN_MINERAL_BRICK_STAIRS    = registerBlock("green_mineral_brick_stairs", () -> new StairBlock(() -> ModBlocks.GREEN_MINERAL_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).strength(9f).requiresCorrectToolForDrops()), ChakraMod.Tab.CHAKRA_TAB);
    public static final RegistryObject<Block> GREEN_CRYSTAL                 = registerBlock("green_crystal", () -> new Crystal(BlockBehaviour.Properties.of(Material.AMETHYST).strength(9f).requiresCorrectToolForDrops()), ChakraMod.Tab.CHAKRA_TAB);
    //--------------------YELLOW---------------------
    public static final RegistryObject<Block> YELLOW_MINERAL                = registerBlock("yellow_mineral", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(9f).requiresCorrectToolForDrops()), ChakraMod.Tab.CHAKRA_TAB);
    public static final RegistryObject<Block> YELLOW_BUDDING_MINERAL        = registerBlock("yellow_budding_mineral", () -> new ModBuddingBlock(BlockBehaviour.Properties.of(Material.STONE).strength(9f).requiresCorrectToolForDrops(), ModBlocks.YELLOW_CRYSTAL), ChakraMod.Tab.CHAKRA_TAB);
    public static final RegistryObject<Block> YELLOW_MINERAL_BRICKS         = registerBlock("yellow_mineral_bricks", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(9f).requiresCorrectToolForDrops()), ChakraMod.Tab.CHAKRA_TAB);
    public static final RegistryObject<Block> YELLOW_MINERAL_BRICK_STAIRS   = registerBlock("yellow_mineral_brick_stairs", () -> new StairBlock(() -> ModBlocks.YELLOW_MINERAL_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).strength(9f).requiresCorrectToolForDrops()), ChakraMod.Tab.CHAKRA_TAB);
    public static final RegistryObject<Block> YELLOW_CRYSTAL                = registerBlock("yellow_crystal", () -> new Crystal(BlockBehaviour.Properties.of(Material.STONE).strength(9f).requiresCorrectToolForDrops()), ChakraMod.Tab.CHAKRA_TAB);
    //--------------------ORANGE---------------------
    public static final RegistryObject<Block> ORANGE_MINERAL                = registerBlock("orange_mineral", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(9f).requiresCorrectToolForDrops()), ChakraMod.Tab.CHAKRA_TAB);
    public static final RegistryObject<Block> ORANGE_BUDDING_MINERAL        = registerBlock("orange_budding_mineral", () -> new ModBuddingBlock(BlockBehaviour.Properties.of(Material.STONE).strength(9f).requiresCorrectToolForDrops(), ModBlocks.ORANGE_CRYSTAL), ChakraMod.Tab.CHAKRA_TAB);
    public static final RegistryObject<Block> ORANGE_MINERAL_BRICKS         = registerBlock("orange_mineral_bricks", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(9f).requiresCorrectToolForDrops()), ChakraMod.Tab.CHAKRA_TAB);
    public static final RegistryObject<Block> ORANGE_MINERAL_BRICK_STAIRS   = registerBlock("orange_mineral_brick_stairs", () -> new StairBlock(() -> ModBlocks.ORANGE_MINERAL_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).strength(9f).requiresCorrectToolForDrops()), ChakraMod.Tab.CHAKRA_TAB);
    public static final RegistryObject<Block> ORANGE_CRYSTAL                = registerBlock("orange_crystal", () -> new Crystal(BlockBehaviour.Properties.of(Material.STONE).strength(9f).requiresCorrectToolForDrops()), ChakraMod.Tab.CHAKRA_TAB);
    //--------------------RED------------------------
    public static final RegistryObject<Block> RED_MINERAL                   = registerBlock("red_mineral", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(9f).requiresCorrectToolForDrops()), ChakraMod.Tab.CHAKRA_TAB);
    public static final RegistryObject<Block> RED_BUDDING_MINERAL           = registerBlock("red_budding_mineral", () -> new ModBuddingBlock(BlockBehaviour.Properties.of(Material.STONE).strength(9f).requiresCorrectToolForDrops(), ModBlocks.RED_CRYSTAL), ChakraMod.Tab.CHAKRA_TAB);
    public static final RegistryObject<Block> RED_MINERAL_BRICKS            = registerBlock("red_mineral_bricks", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(9f).requiresCorrectToolForDrops()), ChakraMod.Tab.CHAKRA_TAB);
    public static final RegistryObject<Block> RED_MINERAL_BRICK_STAIRS      = registerBlock("red_mineral_brick_stairs", () -> new StairBlock(() -> ModBlocks.RED_MINERAL_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).strength(9f).requiresCorrectToolForDrops()), ChakraMod.Tab.CHAKRA_TAB);
    public static final RegistryObject<Block> RED_CRYSTAL                   = registerBlock("red_crystal", () -> new Crystal(BlockBehaviour.Properties.of(Material.STONE).strength(9f).requiresCorrectToolForDrops()), ChakraMod.Tab.CHAKRA_TAB);



    public static final RegistryObject<Block> NECKLACE_SLOTTER = registerBlock("necklace_slotter",
            () -> new NecklaceSlotterBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(9f).requiresCorrectToolForDrops()), ChakraMod.Tab.CHAKRA_TAB);
    public static final RegistryObject<Block> SHARD_REFINER = registerBlock("shard_refiner",
            () -> new ShardRefinerBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(9f).requiresCorrectToolForDrops()), ChakraMod.Tab.CHAKRA_TAB);



    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }
    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(tab)));
    }
    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
