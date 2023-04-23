package net.AbraXator.chakral.chakra.chakras;

import com.mojang.blaze3d.vertex.PoseStack;
import net.AbraXator.chakral.chakra.Chakra;
import net.AbraXator.chakral.chakra.ChakraStrength;
import net.AbraXator.chakral.chakra.ChakraType;
import net.AbraXator.chakral.client.gui.chakralnexus.ChakralNexusScreen;
import net.AbraXator.chakral.networking.ModMessages;
import net.AbraXator.chakral.networking.packet.BlackOnyxLandS2CPacket;
import net.minecraft.client.renderer.debug.DebugRenderer;
import net.minecraft.network.chat.Style;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class BlackOnyxChakra extends Chakra {
    private static int countdown = 0;
    private static int countdownForAttack = 0;
    private static int charge = 0;
    private boolean canCauseDamage = false;
    private static final UUID uuid = UUID.fromString("a9fa91ef-9368-46be-bf36-9a342a662dec") ;

    public BlackOnyxChakra(ChakraType type, ChakraStrength chakraStrength) {
        super(type, chakraStrength);
    }

    public void onAttackEntity(Player player, Level level, Entity target){
    }

    @Override
    public void tick(Player player, Level level) {
        Optional<Entity> optionalEntity = DebugRenderer.getTargetedEntity(player, 15);
        if(countdown == 0) dash(optionalEntity, player);
        //if(!player.isOnGround()) System.out.println(player.getDeltaMovement());
        if(player.fallDistance > 0.0F && player.isOnGround() && canCauseDamage){
            land(player.getOnPos().getCenter(), level, player);
        }

        if(countdown != 0){
            countdown--;
        }

        if(countdownForAttack != 0){
            charge--;
        }
    }

    private void land(Vec3 pos, Level level, Player player){
        int xMin = Mth.floor(pos.x() - 4);
        int yMin = Mth.floor(pos.y() - 4);
        int zMin = Mth.floor(pos.z() - 4);
        int xMax = Mth.floor(pos.x() + 4);
        int yMax = Mth.floor(pos.y() + 4);
        int zMax = Mth.floor(pos.z() + 4);
        List<Entity> entities = level.getEntities(player, new AABB(xMin, yMin, zMin, xMax, yMax, zMax));

        ModMessages.sendToClients(new BlackOnyxLandS2CPacket(pos));
        entities.forEach(entity -> {
            entity.hurt(level.damageSources().playerAttack(player), 5);
            Vec3 vec3 = new Vec3(entity.getX() - pos.x(), entity.getY() - pos.y(), entity.getZ() - pos.z()).scale(0.2);
            entity.addDeltaMovement(entity.getDeltaMovement().add(vec3));
            canCauseDamage = false;
        });

    }

    private void dash(Optional<Entity> optionalEntity, Player player){
        optionalEntity.ifPresent(entity -> {
            if(player.isShiftKeyDown()) {
                float powX = (float) Math.pow(entity.getX() - player.getX(), 2);
                float powY = (float) Math.pow(entity.getY() - player.getY(), 2);
                float powZ = (float) Math.pow(entity.getZ() - player.getZ(), 2);

                float xfactor = (float) Mth.clamp((Mth.sqrt(powY + powY + powZ)) * 0.7 , 0.10F, 3F);
                float yfactor = (float) Mth.clamp((Mth.sqrt(powY + powY + powZ)) * 0.5 , 0.10F, 1.5F);
                float zfactor = (float) Mth.clamp((Mth.sqrt(powY + powY + powZ)) * 0.7 , 0.10F, 3F);

                float x = (float) (entity.getX() - player.getX());
                float y = (float) ((entity.getY() - player.getY() + (5 * yfactor)));
                float z = (float) (entity.getZ() - player.getZ());
                Vec3 dash = new Vec3(x, y, z).normalize().multiply(xfactor, yfactor, zfactor);

                player.setDeltaMovement(player.getDeltaMovement().add(dash));
                canCauseDamage = true;
                countdown = 50;
            }
        });
    }

    @Override
    public void openInfoSidePanel(ChakralNexusScreen screen, PoseStack poseStack, int x, int y) {

    }

    @Override
    public List<Style> getColors() {
        return chakraColor("192d84", "281f6c");
    }
}
