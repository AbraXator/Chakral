package net.AbraXator.chakral.chakra.chakras;

import net.AbraXator.chakral.chakra.Chakra;
import net.AbraXator.chakral.chakra.ChakraStrength;
import net.AbraXator.chakral.chakra.ChakraType;
import net.AbraXator.chakral.utils.ChakralLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.entity.living.LivingDamageEvent;

import java.util.*;

public class DumortieriteChakra extends Chakra {
    public DumortieriteChakra() {
        super(new ChakralLocation("dumortierite"), ChakraType.THIRD_EYE, ChakraStrength.WEAKENED);
    }

    public static void onFallDamage(LivingDamageEvent event){
        Player player = ((Player) event.getEntity());
        Level level = event.getEntity().level;
        DamageSource damageSource = event.getSource();
        if(damageSource.is(DamageTypes.FALL)){
            float amount = event.getAmount();
            int range = Mth.ceil(amount * 0.5);

        }
    }

    public List<BlockPos> searchForOreBlock(Player player, Level level, int range){
        List<BlockPos> returnList = new ArrayList<>();
        for(BlockPos blockPos : BlockPos.betweenClosed(
                (int) (player.getX() + range), (int) (player.getY() + range), (int) (player.getZ() + range),
                (int) (player.getX() - range), (int) (player.getY() - range), (int) (player.getZ() - range))){
            if(level.getBlockState(blockPos).is(Tags.Blocks.ORES)){
                returnList.add(blockPos);
            }
        }
        return returnList;
    }

    public static void renderBlockOutline(){

    }
}
