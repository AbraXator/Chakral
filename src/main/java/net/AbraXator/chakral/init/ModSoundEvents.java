package net.AbraXator.chakral.init;

import net.AbraXator.chakral.Chakral;
import net.AbraXator.chakral.utils.ChakralLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSoundEvents {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(
            ForgeRegistries.SOUND_EVENTS, Chakral.MOD_ID);

    public static final RegistryObject<SoundEvent> SOUND1 = create("music_disc.chakral.music1");

    private static RegistryObject<SoundEvent> create(String sound){
        return SOUNDS.register(sound, () -> SoundEvent.createVariableRangeEvent(new ChakralLocation(sound)));
    }

    public static void register(IEventBus eventBus){
        SOUNDS.register(eventBus);
    }
}
