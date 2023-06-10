package net.AbraXator.chakral.items;

import net.AbraXator.chakral.entity.boat.ChakralBoat;
import net.AbraXator.chakral.entity.boat.ChakralChestBoat;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.function.Predicate;

public class ChakralBoatItem extends Item {
    private static final Predicate<Entity> ENTITY_PREDICATE = EntitySelector.NO_SPECTATORS.and(Entity::isPickable);
    private final ChakralBoat.Type type;
    private final boolean chest;

    public ChakralBoatItem(Properties pProperties, ChakralBoat.Type type, boolean chest) {
        super(pProperties);
        this.type = type;
        this.chest = chest;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack itemStack = pPlayer.getItemInHand(pUsedHand);
        HitResult hitResult = getPlayerPOVHitResult(pLevel, pPlayer, ClipContext.Fluid.ANY);
        if(hitResult.getType() == HitResult.Type.MISS) return InteractionResultHolder.pass(itemStack);
        else {
            Vec3 vec3 = pPlayer.getViewVector(1.0F);
            List<Entity> list = pLevel.getEntities(pPlayer, pPlayer.getBoundingBox().expandTowards(vec3.scale(5.0D)).inflate(1.0D), ENTITY_PREDICATE);
            if(!list.isEmpty()){
                Vec3 vec31 = pPlayer.getEyePosition(1.0F);
                for (Entity entity : list){
                    AABB aabb = entity.getBoundingBox().inflate(entity.getPickRadius());
                    if(aabb.contains(vec31)){
                        return InteractionResultHolder.pass(itemStack);
                    }
                }
            }
            if (hitResult.getType() == HitResult.Type.BLOCK){
                ChakralBoat chakralBoat = new ChakralChestBoat(pLevel, vec3.x, vec3.y, vec3.z);
                chakralBoat.setBoatType(this.type);
                chakralBoat.setYRot(pPlayer.getYRot());
                if(!pLevel.noCollision(chakralBoat, chakralBoat.getBoundingBox())) return InteractionResultHolder.fail(itemStack);
                else {
                    if(!pLevel.isClientSide()){
                        pLevel.addFreshEntity(chakralBoat);
                        pLevel.gameEvent(pPlayer, GameEvent.ENTITY_PLACE, hitResult.getLocation());
                        if(!pPlayer.getAbilities().instabuild){
                            itemStack.shrink(1);
                        }
                    }
                    pPlayer.awardStat(Stats.ITEM_USED.get(this));
                    return InteractionResultHolder.sidedSuccess(itemStack, pLevel.isClientSide());
                }
            }else {
                return InteractionResultHolder.pass(itemStack);
            }
        }
    }

    /*private ChakralBoat getBoat(Level pLevel, HitResult hitResult) {
        Vec3 vec3 = hitResult.getLocation();
        return this.chest ? : new ChakralBoat(pLevel, vec3.x, vec3.y, vec3.z);
    }*/
}
