package net.AbraXator.chakramod.event;

import net.AbraXator.chakramod.ChakraMod;
import net.AbraXator.chakramod.entity.CrystalFish;
import net.AbraXator.chakramod.entity.ModEntity;
import net.AbraXator.chakramod.entity.MineralSnail;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = ChakraMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonModEvents {

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event){
        event.put(ModEntity.MINERAL_SNAIL.get(), MineralSnail.createAttributes().build());
        event.put(ModEntity.CRYSTAL_FISH.get(), CrystalFish.createAttributes().build());
    }
}
