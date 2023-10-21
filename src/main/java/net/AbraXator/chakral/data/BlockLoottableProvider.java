package net.AbraXator.chakral.data;

import net.AbraXator.chakral.server.init.ModBlocks;
import net.AbraXator.chakral.server.blocks.Crystal;
import net.AbraXator.chakral.server.init.ModItems;
import net.AbraXator.chakral.server.items.Shard;
import net.AbraXator.chakral.server.init.ModTags;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BlockLoottableProvider extends BlockLootSubProvider {
    private static final Set<Item> EXPLOSION_RESISTANT = Stream.of(Blocks.DRAGON_EGG, Blocks.BEACON, Blocks.CONDUIT, Blocks.SKELETON_SKULL, Blocks.WITHER_SKELETON_SKULL, Blocks.PLAYER_HEAD, Blocks.ZOMBIE_HEAD, Blocks.CREEPER_HEAD, Blocks.DRAGON_HEAD, Blocks.PIGLIN_HEAD, Blocks.SHULKER_BOX, Blocks.BLACK_SHULKER_BOX, Blocks.BLUE_SHULKER_BOX, Blocks.BROWN_SHULKER_BOX, Blocks.CYAN_SHULKER_BOX, Blocks.GRAY_SHULKER_BOX, Blocks.GREEN_SHULKER_BOX, Blocks.LIGHT_BLUE_SHULKER_BOX, Blocks.LIGHT_GRAY_SHULKER_BOX, Blocks.LIME_SHULKER_BOX, Blocks.MAGENTA_SHULKER_BOX, Blocks.ORANGE_SHULKER_BOX, Blocks.PINK_SHULKER_BOX, Blocks.PURPLE_SHULKER_BOX, Blocks.RED_SHULKER_BOX, Blocks.WHITE_SHULKER_BOX, Blocks.YELLOW_SHULKER_BOX).map(ItemLike::asItem).collect(Collectors.toSet());

    protected BlockLoottableProvider() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        ModBlocks.BLOCKS.getEntries().forEach(blockObject -> {
            Block block = blockObject.get();

            if(DataGenerators.getCrystals().contains(block)) {
                crystalLoot(block);
            }else if(ForgeRegistries.BLOCKS.tags().getTag(ModTags.Blocks.BUDDING_BLOCKS).contains(block)) {
                this.add(block, noDrop());
            }else if(ForgeRegistries.BLOCKS.tags().getTag(BlockTags.SLABS).contains(block)) {
                this.createSlabItemTable(block);
            }else if(block.asItem().getDefaultInstance().is(ModBlocks.STEMSHROOM.get().asItem())){
                this.add(block, this.createSingleItemTableWithSilkTouch(block, ModItems.RAW_STEMSHROOM_STEM.get()));
            }else {
                this.dropSelf(block);
            }
        });
    }

    private void crystalLoot(Block block) {
        if(block instanceof Crystal crystal){
            Item shard = Shard.shardFromCrystal(crystal);
            if(shard != null){
                this.add(block, createSilkTouchDispatchTable(block, this.applyExplosionDecay(block, LootItem.lootTableItem(shard).apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE)))));
            }else {
                this.dropWhenSilkTouch(crystal);
            }
        }
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;

    }
}
