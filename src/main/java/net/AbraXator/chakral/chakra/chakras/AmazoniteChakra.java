package net.AbraXator.chakral.chakra.chakras;

import com.mojang.blaze3d.vertex.PoseStack;
import net.AbraXator.chakral.capability.AdditionalHealthCap;
import net.AbraXator.chakral.capability.AdditionalHealthCapProvider;
import net.AbraXator.chakral.chakra.Chakra;
import net.AbraXator.chakral.chakra.ChakraStrength;
import net.AbraXator.chakral.chakra.ChakraType;
import net.AbraXator.chakral.client.gui.chakralnexus.ChakralNexusScreen;
import net.AbraXator.chakral.client.overlays.ChakraHeartData;
import net.AbraXator.chakral.networking.ModMessages;
import net.AbraXator.chakral.networking.packet.ChakraHeartsS2CPacket;
import net.AbraXator.chakral.utils.ChakralLocation;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDamageEvent;

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
        ModMessages.sendToClients(new ChakraHeartsS2CPacket(additionalHealthCap.getHealth()));
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
    public void openInfoSidePanel(ChakralNexusScreen screen, PoseStack poseStack, int x, int y) {

    }

    @Override
    public Style getColor() {
        return chakraColor("24A78E");
    }
}
