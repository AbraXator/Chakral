package net.AbraXator.chakral.chakra.chakras;

import net.AbraXator.chakral.chakra.Chakra;
import net.AbraXator.chakral.chakra.ChakraStrength;
import net.AbraXator.chakral.chakra.ChakraType;
import net.AbraXator.chakral.utils.ChakralLocation;
import net.minecraft.client.renderer.debug.DebugRenderer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import java.util.Optional;
import java.util.UUID;

public class BlackOnyx extends Chakra {
    private static int countdown = 0;
    private static int charge = 0;
    private static final UUID uuid = UUID.fromString("a9fa91ef-9368-46be-bf36-9a342a662dec") ;


    public BlackOnyx() {
        super(new ChakralLocation("black_onyx"), ChakraType.ROOT, ChakraStrength.FAINT);
    }

    @Override
    public void onAttack(AttackEntityEvent event) {

    }

    @Override
    public void onFunctionKeyPress(Player player, Level level) {

    }

    public void onAttackEntity(Player player, Level level, Entity target){
        if(charge > 1){
            //target.hurt();
        }
    }

    @Override
    public void tick(Player player, Level level) {
        if(player.isShiftKeyDown() && countdown == 0) {
            Optional<Entity> optionalEntity = DebugRenderer.getTargetedEntity(player, 15);
            optionalEntity.ifPresent(entity -> {
                Vec3 dash = new Vec3(entity.getX() - player.getX(), entity.getY() - player.getY() + 5, entity.getZ() - player.getZ()).scale(0.15D);
                player.setDeltaMovement(player.getDeltaMovement().add(dash));
                countdown += 10;
                charge = 50;
            });
        }
        if(countdown != 0){
            countdown--;
        }
        if(charge != 0){
            charge--;
        }
        //player.getAttributes().getDirtyAttributes().stream().toList();
    }
}
