package net.AbraXator.chakramod.blocks.entity;

import net.AbraXator.chakramod.ChakraMod;
import net.AbraXator.chakramod.blocks.ModBlocks;
import net.AbraXator.chakramod.blocks.entity.custom.StoneBenchBlockEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class    ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, ChakraMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<StoneBenchBlockEntity>> STONE_BENCH_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("stone_bench_block_entity", () ->
                    BlockEntityType.Builder.of(StoneBenchBlockEntity::new,
                            ModBlocks.STONE_BENCH.get()).build(null));

    public static void register(IEventBus eventBus){
        BLOCK_ENTITIES.register(eventBus);
    }
}
