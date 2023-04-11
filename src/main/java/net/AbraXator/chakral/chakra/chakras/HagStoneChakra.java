package net.AbraXator.chakral.chakra.chakras;

import net.AbraXator.chakral.chakra.Chakra;
import net.AbraXator.chakral.chakra.ChakraStrength;
import net.AbraXator.chakral.chakra.ChakraType;
import net.AbraXator.chakral.utils.ChakralLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.level.BlockEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HagStoneChakra extends Chakra {
    private static final  UUID uuid = UUID.fromString("a2595d43-41b2-4429-80ec-f141ae6d6778");
    public static boolean hasReach;

    public HagStoneChakra(UUID uuid) {
        super(new ChakralLocation("hag_stone"), ChakraType.THIRD_EYE, ChakraStrength.FAINT);
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
            hasReach = true;
        }else if(!isInTag(player, Tags.Items.TOOLS_AXES, Tags.Items.TOOLS_PICKAXES, Tags.Items.TOOLS_SHOVELS)) {
            player.getAttribute(ForgeMod.REACH_DISTANCE.get()).removeModifier(uuid);
            hasReach = false;
        }
    }

    @Override
    public void onDestroyBlock(BlockEvent.BreakEvent event) {
        ServerPlayer player = ((ServerPlayer) event.getPlayer());
        if (player.gameMode.isSurvival() && isEnabled() && hasReach) {
            LootContext.Builder lootcontext$builder = (new LootContext.Builder(((ServerLevel) event.getLevel()))).withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(event.getPos())).withParameter(LootContextParams.BLOCK_STATE, event.getState()).withOptionalParameter(LootContextParams.BLOCK_ENTITY, event.getLevel().getBlockEntity(event.getPos())).withOptionalParameter(LootContextParams.THIS_ENTITY, player).withParameter(LootContextParams.TOOL, player.getMainHandItem());
            for (ItemStack itemStack : event.getState().getDrops(lootcontext$builder)) {
                event.setCanceled(true);
                event.getLevel().setBlock(event.getPos(), Blocks.AIR.defaultBlockState(), 3);
                player.addItem(itemStack);
            }
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
