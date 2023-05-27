package net.AbraXator.chakral.init;

import net.AbraXator.chakral.Chakral;
import net.AbraXator.chakral.utils.ChakralLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModGameEvents {
    public static final DeferredRegister<GameEvent> GAME_EVENTS = DeferredRegister.create(
            BuiltInRegistries.GAME_EVENT.key(), Chakral.MOD_ID);

    public static final RegistryObject<GameEvent>
}
