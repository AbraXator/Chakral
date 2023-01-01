package net.AbraXator.chakral.entity;

import net.AbraXator.chakral.Chakral;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Chakral.MOD_ID);

    public static final RegistryObject<EntityType<DwiderEntity>> DWIDER = ENTITIES.register("dwider", () -> EntityType.Builder.of(DwiderEntity::new, MobCategory.MONSTER)
            .sized(1.5F, 1.5F)
            .build("dwider"));

    public static void register(IEventBus eventBus){
        ENTITIES.register(eventBus);
        eventBus.addListener(ModEntities::createAttributes);
    }

    public static void createAttributes(EntityAttributeCreationEvent event){
        event.put(DWIDER.get(), DwiderEntity.setAttributes());
    }
}
