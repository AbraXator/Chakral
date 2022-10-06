package net.AbraXator.chakral.items.custom;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.stream.Stream;

public class NecklaceItem extends Item {
    public NecklaceItem(Properties pProperties) {
        super(pProperties);
    }



    private static Stream<ItemStack> getGems(ItemStack pStack) {
        CompoundTag compoundtag = pStack.getTag();
        if (compoundtag == null) {
            return Stream.empty();
        } else {
            ListTag listtag = compoundtag.getList("Items", 10);
            return listtag.stream().map(CompoundTag.class::cast).map(ItemStack::of);
        }
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        CompoundTag compoundtag = pStack.getTag();
        if(compoundtag != null){
            ListTag listtag = compoundtag.getList("chakral.gems", 10);
            for(int i = 0; i < listtag.size(); i++){
                pTooltipComponents.add(Component.literal(listtag.getString(i)));
            }
        }
    }
}
