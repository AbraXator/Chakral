package net.AbraXator.chakral.server.chakra.chakras;

import net.AbraXator.chakral.server.capability.AdditionalHealthCap;
import net.AbraXator.chakral.server.capability.AdditionalHealthCapProvider;
import net.AbraXator.chakral.server.chakra.Chakra;
import net.AbraXator.chakral.server.chakra.ChakraStrength;
import net.AbraXator.chakral.server.chakra.ChakraType;
import net.AbraXator.chakral.client.overlays.ChakraHeartData;
import net.AbraXator.chakral.server.networking.ModPacketHandler;
import net.AbraXator.chakral.server.networking.packet.ChakraHeartsS2CPacket;
import net.minecraft.network.chat.Style;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDamageEvent;

import java.util.List;

public class AmazoniteChakra extends Chakra {
    public static float hp = 0;
    private Level level;

    public AmazoniteChakra(ChakraType type, ChakraStrength chakraStrength) {
        super(type, chakraStrength);
    }

    @Override
    public void tick(Player player, Level level){
        ChakraHeartData.setEnabled(this.isEnabled());
        this.level = level;
        if(level.getGameTime() % 200 == 0){
            //System.out.println(additionalHealthCap.getHealth());
            player.getCapability(AdditionalHealthCapProvider.ADD_HEALTH_CAP).ifPresent(this::heal);
        }
    }

    private void heal(AdditionalHealthCap additionalHealthCap){
        additionalHealthCap.setHealth(Mth.clamp(additionalHealthCap.getHealth() + 1, additionalHealthCap.getHealth(), additionalHealthCap.getMaxHealth()));
        ModPacketHandler.sendToClients(new ChakraHeartsS2CPacket(additionalHealthCap.getHealth()));
    }

    private void blink(AdditionalHealthCap additionalHealthCap){
        if(level.getGameTime() % 22 == 0){

        }
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

    public static void hurt(LivingDamageEvent event){
        if(event.getEntity() instanceof Player player && ChakraHeartData.getEnabled()){
            player.getCapability(AdditionalHealthCapProvider.ADD_HEALTH_CAP).ifPresent(additionalHealthCap -> {
                float dmgAmount = event.getAmount();
                float hp = additionalHealthCap.getHealth();
                float toHurt = 0;
                if(hp > 0){
                    if(dmgAmount > hp){
                        additionalHealthCap.setHealth(0);
                        ChakraHeartData.setHealth(ChakraHeartData.getHealth());
                        toHurt = dmgAmount - hp;
                    }else {
                        float toSetHp = hp - dmgAmount;
                        additionalHealthCap.setHealth(toSetHp);
                        ChakraHeartData.setHealth(ChakraHeartData.getHealth());
                        toHurt = 0;
                    }
                    ChakraHeartData.setHealth(ChakraHeartData.getHealth());
                    player.hurt(event.getSource(), toHurt);
                    event.setCanceled(true);
                }else {
                    event.setCanceled(false);
                }
            });
        }
    }

    @Override
    public List<Style> getColors() {
        return chakraColor("5FBFB0", "24A78E", "0A8F85", "208084", "166D76", "125A6C");
    }
}
