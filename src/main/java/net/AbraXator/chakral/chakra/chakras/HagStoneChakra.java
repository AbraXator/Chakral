package net.AbraXator.chakral.chakra.chakras;

import net.AbraXator.chakral.chakra.Chakra;
import net.AbraXator.chakral.chakra.ChakraStrength;
import net.AbraXator.chakral.chakra.ChakraType;
import net.AbraXator.chakral.init.ModTags;
import net.AbraXator.chakral.networking.ModMessages;
import net.AbraXator.chakral.networking.packet.HagStoneS2CPacket;
import net.minecraft.network.chat.Style;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.tags.ITag;

import java.util.List;
import java.util.UUID;

public class HagStoneChakra extends Chakra {
    private static final UUID uuid = UUID.fromString("a2595d43-41b2-4429-80ec-f141ae6d6778");
    public static boolean hasReach;

    public HagStoneChakra(ChakraType type, ChakraStrength chakraStrength) {
        super(type, chakraStrength);
    }

    @Override
    public void onUnequip(Player player, Level level) {
        if(isInTag(player.getMainHandItem())) {
            player.getAttribute(ForgeMod.BLOCK_REACH.get()).removePermanentModifier(uuid);
        }
    }

    @Override
    public void tick(Player player, Level level) {
        if(isInTag(player.getMainHandItem()) && !player.getAttribute(ForgeMod.BLOCK_REACH.get()).hasModifier(new AttributeModifier(uuid, "hag_stone_range", 1.5, AttributeModifier.Operation.ADDITION))) {
            player.getAttribute(ForgeMod.BLOCK_REACH.get()).addPermanentModifier(new AttributeModifier(uuid, "hag_stone_range", 1.5, AttributeModifier.Operation.ADDITION));
            hasReach = true;
        }else if(!isInTag(player.getMainHandItem())) {
            player.getAttribute(ForgeMod.BLOCK_REACH.get()).removeModifier(uuid);
            hasReach = false;
        }
    }

    @Override
    public void onDestroyBlock(BlockEvent.BreakEvent event) {
        ServerPlayer player = ((ServerPlayer) event.getPlayer());
        if (hasReach) {
            LootParams.Builder params = new LootParams.Builder((ServerLevel) event.getLevel()).withParameter(LootContextParams.TOOL, event.getPlayer().getUseItem()).withParameter(LootContextParams.BLOCK_STATE, event.getState()).withOptionalParameter(LootContextParams.BLOCK_ENTITY, event.getLevel().getBlockEntity(event.getPos())).withParameter(LootContextParams.ORIGIN, event.getPlayer().position()).withParameter(LootContextParams.THIS_ENTITY, event.getPlayer());
            for (ItemStack itemStack : event.getState().getDrops(params)) {
                event.setCanceled(true);
                event.getLevel().setBlock(event.getPos(), Blocks.AIR.defaultBlockState(), 3);
                player.addItem(itemStack);
                ModMessages.sendToClients(new HagStoneS2CPacket(event.getPlayer().position(), event.getPos().getCenter()));
            }
        }
    }

    private boolean isInTag(ItemStack itemStack){
        ITag<Item> tag = ForgeRegistries.ITEMS.tags().getTag(ModTags.Items.HAG_TOOLS);
        return tag.contains(itemStack.getItem());
    }

    @Override
    public List<Style> getColors() {
        return chakraColor("8fb2ff", "878ff0", "7c5df3", "4d39b4");
    }
}
