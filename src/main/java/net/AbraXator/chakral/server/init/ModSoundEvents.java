package net.AbraXator.chakral.server.init;

import net.AbraXator.chakral.Chakral;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSoundEvents {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(
            ForgeRegistries.SOUND_EVENTS, Chakral.MOD_ID);

    public static final RegistryObject<SoundEvent> CRYSTALLIZE = create("crystallize");

    private static RegistryObject<SoundEvent> create(String name) {
        return SOUNDS.register(name, () -> SoundEvent.createVariableRangeEvent(Chakral.loc(name)));
    }

    public static void register(IEventBus eventBus){
        SOUNDS.register(eventBus);
    }
}
