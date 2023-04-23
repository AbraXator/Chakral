package net.AbraXator.chakral.chakra.chakras;

import com.mojang.blaze3d.vertex.PoseStack;
import net.AbraXator.chakral.chakra.Chakra;
import net.AbraXator.chakral.chakra.ChakraStrength;
import net.AbraXator.chakral.chakra.ChakraType;
import net.AbraXator.chakral.client.gui.chakralnexus.ChakralNexusScreen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectUtil;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingDamageEvent;

import java.util.List;

public class BlueLaceAgateChakra extends Chakra {
    public BlueLaceAgateChakra(ChakraType type, ChakraStrength chakraStrength) {
        super(type, chakraStrength);
    }

    @Override
    public void tick(Player player, Level level) {
        if (!player.getEyeInFluidType().isAir() && !level.getBlockState(BlockPos.containing(player.getX(), player.getEyeY(), player.getZ())).is(Blocks.BUBBLE_COLUMN)) {
            boolean flag1 = player.canDrownInFluidType(player.getEyeInFluidType()) && !MobEffectUtil.hasWaterBreathing(player) && !player.getAbilities().invulnerable;
            if (flag1) {
                player.setAirSupply(decreaseAirSupply(player.getAirSupply(), level));
                if (player.getAirSupply() == -20) {
                    player.setAirSupply(0);
                    Vec3 vec3 = player.getDeltaMovement();

                    for(int i = 0; i < 8; ++i) {
                        double d2 = level.random.nextDouble() - level.random.nextDouble();
                        double d3 = level.random.nextDouble() - level.random.nextDouble();
                        double d4 = level.random.nextDouble() - level.random.nextDouble();
                        player.level.addParticle(ParticleTypes.BUBBLE, player.getX() + d2, player.getY() + d3, player.getZ() + d4, vec3.x, vec3.y, vec3.z);
                    }

                    player.hurt(player.damageSources().drown(), 2.0F);
                }
            }
        }
    }

    private int decreaseAirSupply(int pCurrentAir, Level level) {
        return level.random.nextInt(1) > 0 ? pCurrentAir : pCurrentAir - 1;
    }

    @Override
    public void onDamage(LivingDamageEvent event){
        List<ResourceKey<DamageType>> damageTypes = List.of(DamageTypes.LAVA, DamageTypes.ON_FIRE, DamageTypes.IN_FIRE);
        if(damageTypes.contains(event.getSource())){
            event.setCanceled(true);
            event.getEntity().hurt(event.getSource(), event.getAmount() / 2);
        }
    }

    @Override
    public void openInfoSidePanel(ChakralNexusScreen screen, PoseStack poseStack, int x, int y) {

    }

    @Override
    public List<Style> getColors() {
        return chakraColor("cef4ff", "bfedff", "a7d7ff", "9ecdfc", "92bffa", "84b2f7", "678ce3");
    }
}
