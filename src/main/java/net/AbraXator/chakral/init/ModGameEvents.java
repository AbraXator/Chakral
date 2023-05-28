package net.AbraXator.chakral.init;

import net.AbraXator.chakral.Chakral;
import net.AbraXator.chakral.utils.ChakralLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModGameEvents {
    public static final DeferredRegister<GameEvent> GAME_EVENTS = DeferredRegister.create(
            BuiltInRegistries.GAME_EVENT.key(), Chakral.MOD_ID);

    public static final RegistryObject<GameEvent> LIGHT_RAY_HIT = registerEvent("light_ray_hit");

    private static RegistryObject<GameEvent> registerEvent(String name){
        return registerEvent(name, 16);
    }

    private static RegistryObject<GameEvent> registerEvent(String name, int radius){
        return GAME_EVENTS.register(name, () -> new GameEvent(name, radius));
    }

    public static void register(IEventBus eventBus){
        GAME_EVENTS.register(eventBus);
    }
}
