package net.AbraXator.chakramod.items;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class Tab {
    public static final CreativeModeTab CHAKRA_TAB = new CreativeModeTab("chakramod") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.MALACHITE.get());
        }
    };
}
