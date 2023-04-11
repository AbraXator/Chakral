package net.AbraXator.chakral.chakra.chakras;

import net.AbraXator.chakral.chakra.Chakra;
import net.AbraXator.chakral.chakra.ChakraStrength;
import net.AbraXator.chakral.chakra.ChakraType;
import net.AbraXator.chakral.utils.ChakralLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import java.util.UUID;

public class BlackOnyx extends Chakra {
    private static int countdown;
    private static int count;
    private static final UUID uuid = UUID.fromString("a9fa91ef-9368-46be-bf36-9a342a662dec") ;


    public BlackOnyx() {
        super(new ChakralLocation("black_onyx"), ChakraType.ROOT, ChakraStrength.FAINT);
        countdown = 0;
        count = 0;
    }



    @Override
    public void onAttack(AttackEntityEvent event) {

    }

        @Override
        public void interact(PlayerInteractEvent event) {
            if(event.getItemStack().is(Tags.Items.TOOLS_SWORDS) && countdown <= 0){
                Player player = event.getEntity();
                Vec3 vec3 = player.getViewVector(1);
                Vec3 dash = new Vec3(vec3.x(), player.getDeltaMovement().y(), vec3.z());
                player.setDeltaMovement(dash);
                countdown = 50;
            }
        }

    @Override
    public void tick(Player player, Level level) {
        while(countdown < 0){
            --countdown;
        }
    }
}
