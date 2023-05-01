package net.AbraXator.chakral.init;

import net.AbraXator.chakral.Chakral;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks{
        public static final TagKey<Block> CRYSTALS = tag("crystals");
        public static final TagKey<Block> BUDDING_BLOCKS = tag("budding_blocks");
        public static final TagKey<Block> BW_MINERALS = tag("bw_minerals");
        public static final TagKey<Block> MINERALS = tag("minerals");
        public static final TagKey<Block> AIR = tag("air");

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(Chakral.MOD_ID, name));
        }

        private static TagKey<Block> forgeTag(String name) {
            return BlockTags.create(new ResourceLocation("forge", name));
        }
    }

    public static class Items{
        public static final TagKey<Item> CROWN = tag("crown");
        public static final TagKey<Item> THIRD_EYE = tag("third_eye");
        public static final TagKey<Item> THROAT = tag("throat");
        public static final TagKey<Item> HEART = tag("heart");
        public static final TagKey<Item> SOLAR = tag("solar");
        public static final TagKey<Item> SACRAL = tag("sacral");
        public static final TagKey<Item> ROOT = tag("root");

        public static final TagKey<Item> FAINT = tag("faint");
        public static final TagKey<Item> WEAKENED = tag("weakened");
        public static final TagKey<Item> POWERFUL = tag("powerful");
        public static final TagKey<Item> ENLIGHTENED = tag("enlightened");

        public static final TagKey<Item> GEMS = tag("gems");
        public static final TagKey<Item> SHARDS = tag("shards");
        public static final TagKey<Item> MINERALS = tag("minerals");
        public static final TagKey<Item> CRYSTALS = tag("crystals");


        public static final TagKey<Item> NECKLACES = tag("necklaces");
        public static final TagKey<Item> NECKLACES_LOW = tag("necklaces_low");
        public static final TagKey<Item> NECKLACES_HIGH = tag("necklaces_high");

        public static final TagKey<Item> SHROOMS = tag("shrooms");

        public static final TagKey<Item> MINERAL_RICH = tag("mineral_rich");

        public static final TagKey<Item> REFINER_KITS = tag("refiner_kits");

        public static final TagKey<Item> HAG_TOOLS = tag("hag_tools");

        private static TagKey<Item> tag(String name){
            return ItemTags.create(new ResourceLocation(Chakral.MOD_ID, name));
        }

        private static TagKey<Item> forgeTag(String name){
            return ItemTags.create(new ResourceLocation("forge", name));
        }
    }
}
