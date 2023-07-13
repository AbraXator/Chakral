package net.AbraXator.chakral.chakra.chakras;

import net.AbraXator.chakral.chakra.Chakra;
import net.AbraXator.chakral.chakra.ChakraStrength;
import net.AbraXator.chakral.chakra.ChakraType;
import net.AbraXator.chakral.client.particle.TravelingParticle;
import net.AbraXator.chakral.init.ModParticles;
import net.AbraXator.chakral.init.ModTags;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Style;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
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
        ServerLevel level = (ServerLevel) event.getLevel();
        if (hasReach) {
            LootParams.Builder params = new LootParams.Builder(level).withParameter(LootContextParams.TOOL, event.getPlayer().getUseItem()).withParameter(LootContextParams.BLOCK_STATE, event.getState()).withOptionalParameter(LootContextParams.BLOCK_ENTITY, event.getLevel().getBlockEntity(event.getPos())).withParameter(LootContextParams.ORIGIN, event.getPlayer().position()).withParameter(LootContextParams.THIS_ENTITY, event.getPlayer());
            for (ItemStack itemStack : event.getState().getDrops(params)) {
                event.setCanceled(true);
                event.getLevel().setBlock(event.getPos(), Blocks.AIR.defaultBlockState(), 3);
                player.addItem(itemStack);
                player.getMainHandItem().hurtAndBreak(1, player, player1 -> {
                    player1.broadcastBreakEvent(InteractionHand.MAIN_HAND);
                });
                Vec3 pos = event.getPos().getCenter();
                Vec3 playerPos = player.getPosition(0).relative(Direction.UP, 1);
                float distanceToPlayer = (float) event.getPos().getCenter().distanceTo(player.position());
                level.sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, event.getState()), pos.x(), pos.y(), pos.z(), 10, 0, 0, 0, 0);
                level.sendParticles(new TravelingParticle(ModParticles.HAGSTONE_FRAGMNETIUM.get(), playerPos, Mth.floor(distanceToPlayer)), pos.x(), pos.y(), pos.z(), 1, 0, 0, 0, 0);
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
