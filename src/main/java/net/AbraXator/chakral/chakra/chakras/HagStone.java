package net.AbraXator.chakral.chakra.chakras;

import net.AbraXator.chakral.chakra.Chakra;
import net.AbraXator.chakral.chakra.ChakraStrength;
import net.AbraXator.chakral.chakra.ChakraType;
import net.AbraXator.chakral.items.ModItems;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.Tags;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HagStone extends Chakra {
    UUID uuid;

    public HagStone(UUID uuid) {
        super(ModItems.HAG_STONE.get(), ChakraType.THIRD_EYE, ChakraStrength.FAINT);
        this.uuid = uuid;
    }

    @Override
    public void onUnequip(Player player, Level level) {
        if(isInTag(player, Tags.Items.TOOLS_AXES, Tags.Items.TOOLS_PICKAXES, Tags.Items.TOOLS_SHOVELS)) {
            player.getAttribute(ForgeMod.REACH_DISTANCE.get()).removePermanentModifier(uuid);
        }
    }

    @Override
    public void tick(Player player, Level level) {
        if(isInTag(player, Tags.Items.TOOLS_AXES, Tags.Items.TOOLS_PICKAXES, Tags.Items.TOOLS_SHOVELS) && !player.getAttribute(ForgeMod.REACH_DISTANCE.get()).hasModifier(new AttributeModifier(uuid, "hag_stone_range", 1.5, AttributeModifier.Operation.ADDITION))) {
            player.getAttribute(ForgeMod.REACH_DISTANCE.get()).addPermanentModifier(new AttributeModifier(uuid, "hag_stone_range", 1.5, AttributeModifier.Operation.ADDITION));
        }else if(!isInTag(player, Tags.Items.TOOLS_AXES, Tags.Items.TOOLS_PICKAXES, Tags.Items.TOOLS_SHOVELS)) {
            player.getAttribute(ForgeMod.REACH_DISTANCE.get()).removeModifier(uuid);
        }
    }

    @SafeVarargs
    private boolean isInTag(Player player, TagKey<Item>... tagKey){
        List<Boolean> list = new ArrayList<>();
        for(TagKey<Item> tagKey1 : tagKey){
            list.add(player.getItemInHand(InteractionHand.MAIN_HAND).is(tagKey1));
        }
        return list.contains(Boolean.TRUE);
    }
}
