package net.AbraXator.chakral.chakra.chakras;

import net.AbraXator.chakral.chakra.Chakra;
import net.AbraXator.chakral.chakra.ChakraType;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeMod;

import java.util.UUID;

public class HagStone extends Chakra {
    UUID uuid;

    public HagStone(Item stone, ChakraType type, UUID uuid) {
        super(stone, type);
        this.uuid = uuid;
    }

    @Override
    public void onEquip(Player player, Level level) {
        player.getAttribute(ForgeMod.REACH_DISTANCE.get()).addPermanentModifier(new AttributeModifier(uuid, "hag_stone_range", 1.5, AttributeModifier.Operation.ADDITION));
    }

    @Override
    public void onUnequip(Player player, Level level) {
        System.out.println("UNEQUIP");
        player.getAttribute(ForgeMod.REACH_DISTANCE.get()).removePermanentModifier(uuid);
    }
}
