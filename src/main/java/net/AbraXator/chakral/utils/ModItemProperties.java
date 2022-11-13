package net.AbraXator.chakral.utils;

import net.AbraXator.chakral.items.ModItems;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.tags.ITag;

public class ModItemProperties {
    public static void addCustomProperties(){
        goldenChangeColors(ModItems.GOLDEN_NECKLACE.get());
        //diamondChangeColors(ModItems.DIAMOND_NECKLACE.get());
    }

    private static void goldenChangeColors(Item item) {
        ItemProperties.register(item, new ResourceLocation("crown"), (itemStack, p_234979_, p_234980_, p_234981_) -> {
            ITag<Item> list = ForgeRegistries.ITEMS.tags().getTag(ModTags.Items.CROWN);
            Item nbt = itemStack != null && itemStack.hasTag() && itemStack.getTag().get("Stone1") != null ? ItemStack.of(itemStack.getTag().getCompound("Stone1")).getItem() : ItemStack.EMPTY.getItem();
            return list.contains(nbt) ? 1.0F : 0.0F;
        });
        ItemProperties.register(item, new ResourceLocation("heart"), (itemStack, p_234979_, p_234980_, p_234981_) -> {
            ITag<Item> list = ForgeRegistries.ITEMS.tags().getTag(ModTags.Items.HEART);
            Item nbt = itemStack != null && itemStack.hasTag() && itemStack.getTag().get("Stone1") != null ? ItemStack.of(itemStack.getTag().getCompound("Stone1")).getItem() : ItemStack.EMPTY.getItem();
            return list.contains(nbt) ? 1.0F : 0.0F;
        });
        ItemProperties.register(item, new ResourceLocation("root"), (itemStack, p_234979_, p_234980_, p_234981_) -> {
            ITag<Item> list = ForgeRegistries.ITEMS.tags().getTag(ModTags.Items.ROOT);
            Item nbt = itemStack != null && itemStack.hasTag() && itemStack.getTag().get("Stone1") != null ? ItemStack.of(itemStack.getTag().getCompound("Stone1")).getItem() : ItemStack.EMPTY.getItem();
            return list.contains(nbt) ? 1.0F : 0.0F;
        });
        ItemProperties.register(item, new ResourceLocation("sacral"), (itemStack, p_234979_, p_234980_, p_234981_) -> {
            ITag<Item> list = ForgeRegistries.ITEMS.tags().getTag(ModTags.Items.SACRAL);
            Item nbt = itemStack != null && itemStack.hasTag() && itemStack.getTag().get("Stone1") != null ? ItemStack.of(itemStack.getTag().getCompound("Stone1")).getItem() : ItemStack.EMPTY.getItem();
            return list.contains(nbt) ? 1.0F : 0.0F;
        });
        ItemProperties.register(item, new ResourceLocation("solar"), (itemStack, p_234979_, p_234980_, p_234981_) -> {
            ITag<Item> list = ForgeRegistries.ITEMS.tags().getTag(ModTags.Items.SOLAR);
            Item nbt = itemStack != null && itemStack.hasTag() && itemStack.getTag().get("Stone1") != null ? ItemStack.of(itemStack.getTag().getCompound("Stone1")).getItem() : ItemStack.EMPTY.getItem();
            return list.contains(nbt) ? 1.0F : 0.0F;
        });
        ItemProperties.register(item, new ResourceLocation("third_eye"), (itemStack, p_234979_, p_234980_, p_234981_) -> {
            ITag<Item> list = ForgeRegistries.ITEMS.tags().getTag(ModTags.Items.THIRD_EYE);
            Item nbt = itemStack != null && itemStack.hasTag() && itemStack.getTag().get("Stone1") != null ? ItemStack.of(itemStack.getTag().getCompound("Stone1")).getItem() : ItemStack.EMPTY.getItem();
            return list.contains(nbt) ? 1.0F : 0.0F;
        });
        ItemProperties.register(item, new ResourceLocation("throat"), (itemStack, p_234979_, p_234980_, p_234981_) -> {
            ITag<Item> list = ForgeRegistries.ITEMS.tags().getTag(ModTags.Items.THROAT);
            Item nbt = itemStack != null && itemStack.hasTag() && itemStack.getTag().get("Stone1") != null ? ItemStack.of(itemStack.getTag().getCompound("Stone1")).getItem() : ItemStack.EMPTY.getItem();
            return list.contains(nbt) ? 1.0F : 0.0F;
        });
    }
}
