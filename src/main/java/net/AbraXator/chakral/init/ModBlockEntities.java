package net.AbraXator.chakral.init;

import net.AbraXator.chakral.Chakral;
import net.AbraXator.chakral.blocks.entity.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class    ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Chakral.MOD_ID);

   public static final RegistryObject<BlockEntityType<NecklaceSlotterBlockEntity>> STONE_BENCH_BLOCK_ENTITY =
           BLOCK_ENTITIES.register("stone_bench_block_entity", () ->
                   BlockEntityType.Builder.of(NecklaceSlotterBlockEntity::new,
                           ModBlocks.NECKLACE_SLOTTER.get()).build(null));

    public static final RegistryObject<BlockEntityType<ShardRefinerBlockEntity>> SHARD_REFINER_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("shard_refiner_block_entity", () ->
                    BlockEntityType.Builder.of(ShardRefinerBlockEntity::new,
                            ModBlocks.SHARD_REFINER.get()).build(null));

    public static final RegistryObject<BlockEntityType<MineralEnricherBlockEntity>> MINERAL_ENRICHER_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("mineral_enricher_block_entity", () ->
                    BlockEntityType.Builder.of(MineralEnricherBlockEntity::new,
                            ModBlocks.MINERAL_ENRICHER.get()).build(null));

    public static final RegistryObject<BlockEntityType<GleamshroomBlockEntity>> GLEAM_SHROOM_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("gleamshroom_block_entity", () ->
                    BlockEntityType.Builder.of(GleamshroomBlockEntity::new,
                            ModBlocks.GLEAMSHROOM.get()).build(null));

    public static final RegistryObject<BlockEntityType<StemShroomBlockEntity>> STEMSHROOM_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("stemshroom_block_entity", () ->
                    BlockEntityType.Builder.of(StemShroomBlockEntity::new,
                            ModBlocks.STEMSHROOM.get()).build(null));

    public static void register(IEventBus eventBus){
        BLOCK_ENTITIES.register(eventBus);
    }
}
