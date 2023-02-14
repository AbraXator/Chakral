package net.AbraXator.chakral.chakra.chakras;

import net.AbraXator.chakral.capability.AdditionalHealthCap;
import net.AbraXator.chakral.capability.AdditionalHealthCapProvider;
import net.AbraXator.chakral.chakra.Chakra;
import net.AbraXator.chakral.chakra.ChakraStrength;
import net.AbraXator.chakral.chakra.ChakraType;
import net.AbraXator.chakral.client.ChakraHeartData;
import net.AbraXator.chakral.items.ModItems;
import net.AbraXator.chakral.networking.ModMessages;
import net.AbraXator.chakral.networking.packet.ChakraHeartsS2CPacket;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;

public class AmazoniteChakra extends Chakra {
    public static float hp = 0;
    public AmazoniteChakra() {
        super(ModItems.AMAZONITE.get(), ChakraType.HEART, ChakraStrength.FAINT);
    }

    @Override
    public void tick(Player player, Level level){
        ChakraHeartData.setEnabled(this.isEnabled());
        if(level.getGameTime() % 20 == 0){
            player.getCapability(AdditionalHealthCapProvider.ADD_HEALTH_CAP).ifPresent(additionalHealthCap -> {
                System.out.println(additionalHealthCap.getHealth());
                heal(player, level, additionalHealthCap);
            });
        }
    }

    private void heal(Player player, Level level, AdditionalHealthCap additionalHealthCap){
        additionalHealthCap.setHealth(Mth.clamp(additionalHealthCap.getHealth() + 1, additionalHealthCap.getHealth(), additionalHealthCap.getMaxHealth()));
        ModMessages.sendToClients(new ChakraHeartsS2CPacket(additionalHealthCap.getHealth()));

    }

    @Override
    public void onEquip(Player player, Level level) {
        player.getCapability(AdditionalHealthCapProvider.ADD_HEALTH_CAP).ifPresent(additionalHealthCap -> {
            additionalHealthCap.setHealth(hp);
            ChakraHeartData.setHealth(hp);
            ChakraHeartData.setEnabled(true);
        });
    }

    @Override
    public void onUnequip(Player player, Level level) {
        player.getCapability(AdditionalHealthCapProvider.ADD_HEALTH_CAP).ifPresent(additionalHealthCap -> {
            AmazoniteChakra.hp = additionalHealthCap.getHealth();
            additionalHealthCap.setHealth(0);
            ChakraHeartData.setHealth(0);
            ChakraHeartData.setEnabled(false);
        });
    }
}
