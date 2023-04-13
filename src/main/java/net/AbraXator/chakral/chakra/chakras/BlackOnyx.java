package net.AbraXator.chakral.chakra.chakras;

import net.AbraXator.chakral.chakra.Chakra;
import net.AbraXator.chakral.chakra.ChakraStrength;
import net.AbraXator.chakral.chakra.ChakraType;
import net.AbraXator.chakral.utils.ChakralLocation;
import net.minecraft.client.renderer.debug.DebugRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class BlackOnyx extends Chakra {
    private static int countdown = 0;
    private static int countdownForAttack = 0;
    private static int charge = 0;
    private boolean canCauseDamage = false;
    private static final UUID uuid = UUID.fromString("a9fa91ef-9368-46be-bf36-9a342a662dec") ;

    public BlackOnyx() {
        super(new ChakralLocation("black_onyx"), ChakraType.ROOT, ChakraStrength.FAINT);
    }

    public void onAttackEntity(Player player, Level level, Entity target){
    }

    @Override
    public void tick(Player player, Level level) {
        Optional<Entity> optionalEntity = DebugRenderer.getTargetedEntity(player, 15);
        if(countdown == 0) dash(optionalEntity, player);

        if(player.fallDistance > 0.0F && player.isOnGround() && canCauseDamage){
            land(player.getOnPos(), level, player);
        }

        if(countdown != 0){
            countdown--;
        }

        if(countdownForAttack != 0){
            charge--;
        }
    }

    private void land(BlockPos pos, Level level, Player player){
        int xMin = Mth.floor(pos.getX() - 4);
        int yMin = Mth.floor(pos.getY() - 4);
        int zMin = Mth.floor(pos.getZ() - 4);
        int xMax = Mth.floor(pos.getX() + 4);
        int yMax = Mth.floor(pos.getY() + 4);
        int zMax = Mth.floor(pos.getZ() + 4);
        List<Entity> entities = level.getEntities(player, new AABB(xMin, yMin, zMin, xMax, yMax, zMax));

        entities.forEach(entity -> {
            entity.hurt(level.damageSources().playerAttack(player), 5);
            Vec3 vec3 = new Vec3(entity.getX() - pos.getX(), entity.getY() - pos.getY(), entity.getZ() - pos.getZ()).scale(0.2);
            entity.addDeltaMovement(entity.getDeltaMovement().add(vec3));
            canCauseDamage = false;
        });
    }

    private void dash(Optional<Entity> optionalEntity, Player player){
        optionalEntity.ifPresent(entity -> {
            if(player.isShiftKeyDown()) {
                Vec3 dash = new Vec3(entity.getX() - player.getX(), entity.getY() - player.getY() + 3.33, entity.getZ() - player.getZ()).normalize();
                player.setDeltaMovement(player.getDeltaMovement().add(dash));
                canCauseDamage = true;
                countdown = 50;
            }
        });
    }
}
