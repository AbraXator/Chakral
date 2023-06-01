package net.AbraXator.chakral.init;

import net.AbraXator.chakral.Chakral;
import net.AbraXator.chakral.entity.dwider.DwiderEntity;
import net.AbraXator.chakral.entity.stemspore.MenacingStemshroomSporeEntity;
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

    public static final RegistryObject<EntityType<MenacingStemshroomSporeEntity>> MENACING_STEMSHROOM_SPORE = ENTITIES.register("menacing_stemshroom_spore", () -> EntityType.Builder.<MenacingStemshroomSporeEntity>of(MenacingStemshroomSporeEntity::new, MobCategory.MISC)
            .noSave()
            .sized(1F, 1F)
            .clientTrackingRange(4)
            .updateInterval(5)
            .build("menacing_stemshroom_spore"));

    public static void register(IEventBus eventBus){
        ENTITIES.register(eventBus);
        eventBus.addListener(ModEntities::createAttributes);
    }

    public static void createAttributes(EntityAttributeCreationEvent event){
        event.put(DWIDER.get(), DwiderEntity.setAttributes());
    }
}
