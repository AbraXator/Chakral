package net.AbraXator.chakramod.items.custom;

import net.AbraXator.chakramod.utils.ModTags;
import net.minecraft.server.commands.SayCommand;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

public class TestItem extends Item {
    public TestItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        List<Item> stones = ForgeRegistries.ITEMS.tags().getTag(ModTags.Items.MINERALS).stream().toList();
        System.out.println(stones);
        return super.use(pLevel, pPlayer, pUsedHand);
    }
}
