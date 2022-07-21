package net.AbraXator.chakramod.utils;

import net.AbraXator.chakramod.ChakraMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;

public class ModTags {
    public static class Items{
        public static final TagKey<Item> MINERALS = tag("minerals");
        public static final TagKey<Item> CROWN = tag("crown");
        public static final TagKey<Item> THIRD_EYE = tag("third_eye");
        public static final TagKey<Item> THORAT = tag("throat");
        public static final TagKey<Item> HEART = tag("heart");
        public static final TagKey<Item> SOLAR = tag("solar");
        public static final TagKey<Item> SACRAL = tag("sacral");
        public static final TagKey<Item> ROOT = tag("root");

        private static TagKey<Item> tag(String name){
            return ItemTags.create(new ResourceLocation(ChakraMod.MOD_ID, name));
        }

        private static TagKey<Item> forgeTag(String name){
            return ItemTags.create(new ResourceLocation("forge", name));
        }
    }
}
