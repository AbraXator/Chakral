package net.AbraXator.chakral.chakra.chakras;

import net.AbraXator.chakral.capability.AdditionalHealthCapProvider;
import net.AbraXator.chakral.chakra.Chakra;
import net.AbraXator.chakral.chakra.ChakraStrength;
import net.AbraXator.chakral.chakra.ChakraType;
import net.AbraXator.chakral.items.ModItems;
import net.AbraXator.chakral.networking.ModMessages;
import net.AbraXator.chakral.networking.packet.ChakraHeartsS2CPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class AmazoniteChakra extends Chakra {
    public AmazoniteChakra() {
        super(ModItems.AMAZONITE.get(), ChakraType.HEART, ChakraStrength.FAINT);
    }

    @Override
    public void tick(Player player, Level level){
        if(level.getGameTime() % 20 == 0){
            player.getCapability(AdditionalHealthCapProvider.ADD_HEALTH_CAP).ifPresent(additionalHealthCap -> {
                if(additionalHealthCap.getHealth() != additionalHealthCap.maxHealth){
                    additionalHealthCap.setHealth(additionalHealthCap.getHealth() + 1);
                }
            });
        }
    }

    @Override
    public void onEquip(Player player, Level level) {
        player.getCapability(AdditionalHealthCapProvider.ADD_HEALTH_CAP).ifPresent(additionalHealthCap -> {
            additionalHealthCap.setHealth(additionalHealthCap.getHealth());
        });
    }
}
