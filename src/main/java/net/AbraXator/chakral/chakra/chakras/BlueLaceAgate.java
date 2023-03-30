package net.AbraXator.chakral.chakra.chakras;

import net.AbraXator.chakral.capability.AdditionalHealthCapProvider;
import net.AbraXator.chakral.chakra.Chakra;
import net.AbraXator.chakral.chakra.ChakraStrength;
import net.AbraXator.chakral.chakra.ChakraType;
import net.AbraXator.chakral.client.ChakraHeartData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.entity.living.LivingDamageEvent;

public class BlueLaceAgate extends Chakra {
    public BlueLaceAgate(Item stone, ChakraType type, ChakraStrength chakraStrength) {
        super(stone, type, chakraStrength);
    }



    public static void onHurt(LivingDamageEvent event){
        event.getEntity().getCapability(AdditionalHealthCapProvider.ADD_HEALTH_CAP).ifPresent(additionalHealthCap -> {
            if(event.getSource().is(DamageTypes.LAVA)){
                event.setCanceled(true);
                event.getEntity().hurt(event.getSource(), event.getAmount() / 2);
            }
        });
    }
}
