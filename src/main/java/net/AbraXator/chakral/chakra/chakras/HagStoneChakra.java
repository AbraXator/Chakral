package net.AbraXator.chakral.chakra.chakras;

import com.mojang.blaze3d.vertex.PoseStack;
import net.AbraXator.chakral.chakra.Chakra;
import net.AbraXator.chakral.chakra.ChakraStrength;
import net.AbraXator.chakral.chakra.ChakraType;
import net.AbraXator.chakral.client.gui.chakralnexus.ChakralNexusScreen;
import net.AbraXator.chakral.init.ModParticles;
import net.AbraXator.chakral.init.ModTags;
import net.AbraXator.chakral.networking.ModMessages;
import net.AbraXator.chakral.networking.packet.HagStoneS2CPacket;
import net.AbraXator.chakral.utils.ChakralLocation;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Style;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.ItemTags;
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
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.tags.ITag;

import javax.management.modelmbean.ModelMBean;
import java.util.ArrayList;
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
            LootContext.Builder lootcontext$builder = (new LootContext.Builder(((ServerLevel) event.getLevel()))).withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(event.getPos())).withParameter(LootContextParams.BLOCK_STATE, event.getState()).withOptionalParameter(LootContextParams.BLOCK_ENTITY, event.getLevel().getBlockEntity(event.getPos())).withOptionalParameter(LootContextParams.THIS_ENTITY, player).withParameter(LootContextParams.TOOL, player.getMainHandItem());
            for (ItemStack itemStack : event.getState().getDrops(lootcontext$builder)) {
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
    public void openInfoSidePanel(ChakralNexusScreen screen, PoseStack poseStack, int x, int y) {

    }

    @Override
    public List<Style> getColors() {
        return chakraColor("8fb2ff", "878ff0", "7c5df3", "4d39b4");
    }
}
