package net.AbraXator.chakramod.blocks;

import net.AbraXator.chakramod.ChakraMod;
import net.AbraXator.chakramod.blocks.custom.GlowstoneCrystal;
import net.AbraXator.chakramod.blocks.custom.StoneBenchBlock;
import net.AbraXator.chakramod.items.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
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

    public static final RegistryObject<Block> BLACK_MINERAL = registerBlock("black_mineral",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(9f).requiresCorrectToolForDrops()), ChakraMod.Tab.CHAKRA_TAB);
    public static final RegistryObject<Block> GREEN_MINERAL = registerBlock("green_mineral",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(9f).requiresCorrectToolForDrops()), ChakraMod.Tab.CHAKRA_TAB);
    public static final RegistryObject<Block> HEART_ORE = registerBlock("heart_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(9).requiresCorrectToolForDrops()), ChakraMod.Tab.CHAKRA_TAB);
    public static final RegistryObject<Block> RED_MINERAL = registerBlock("red_mineral",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(9f).requiresCorrectToolForDrops()), ChakraMod.Tab.CHAKRA_TAB);
    public static final RegistryObject<Block> BLUE_MINERAL = registerBlock("blue_mineral",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(9f).requiresCorrectToolForDrops()), ChakraMod.Tab.CHAKRA_TAB);
    public static final RegistryObject<Block> ORANGE_MINERAL = registerBlock("orange_mineral",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(9f).requiresCorrectToolForDrops()), ChakraMod.Tab.CHAKRA_TAB);
    public static final RegistryObject<Block> LIGHT_BLUE_MINERAL = registerBlock("light_blue_mineral",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(9f).requiresCorrectToolForDrops()), ChakraMod.Tab.CHAKRA_TAB);
    public static final RegistryObject<Block> PURPLE_MINERAL = registerBlock("purple_mineral",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(9f).requiresCorrectToolForDrops()), ChakraMod.Tab.CHAKRA_TAB);

    public static final RegistryObject<Block> GLOWSTONE_CRYSTAL = registerBlock("glowstone_crystal",
            () -> new GlowstoneCrystal(BlockBehaviour.Properties.of(Material.AMETHYST)
                    .strength(9f).lightLevel(value -> 15).requiresCorrectToolForDrops()), ChakraMod.Tab.CHAKRA_TAB);
    public static final RegistryObject<Block> GREEN_CRYSTAL = registerBlock("green_crystal",
            () -> new GlowstoneCrystal(BlockBehaviour.Properties.of(Material.AMETHYST)
                    .strength(9f).requiresCorrectToolForDrops()), ChakraMod.Tab.CHAKRA_TAB);

    public static final RegistryObject<Block> STONE_BENCH = registerBlock("stone_bench",
            () -> new StoneBenchBlock(BlockBehaviour.Properties.of(Material.STONE)
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
