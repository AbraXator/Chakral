package net.AbraXator.chakral.server.init;

import net.AbraXator.chakral.Chakral;
import net.AbraXator.chakral.server.entity.boat.ChakralBoat;
import net.AbraXator.chakral.server.entity.boat.ChakralChestBoat;
import net.AbraXator.chakral.server.entity.dwider.DwiderEntity;
import net.AbraXator.chakral.server.entity.stemspore.MenacingStemshroomSporeEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
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

    public static final RegistryObject<EntityType<MenacingStemshroomSporeEntity>> MENACING_STEMSHROOM_SPORE = ENTITIES.register("menacing_stemshroom_spore", () -> EntityType.Builder.of(MenacingStemshroomSporeEntity::new, MobCategory.MISC)
            .noSave()
            .sized(1F, 1F)
            .clientTrackingRange(4)
            .updateInterval(5)
            .build("menacing_stemshroom_spore"));

    public static final RegistryObject<EntityType<ChakralBoat>> BOAT = buildNoEgg(Chakral.loc("boat"), makeCastedBuilder(ChakralBoat.class, ChakralBoat::new, 1.375F, 0.562F, 10, 3), false);
    public static final RegistryObject<EntityType<ChakralChestBoat>> CHEST_BOAT = buildNoEgg(Chakral.loc("chest_boat"), makeCastedBuilder(ChakralChestBoat.class, ChakralChestBoat::new, 1.375F, 0.562F, 10, 3), false);

    public static void register(IEventBus eventBus){
        ENTITIES.register(eventBus);
        eventBus.addListener(ModEntities::createAttributes);
    }

    private static <E extends Entity> RegistryObject<EntityType<E>> buildNoEgg(ResourceLocation id, EntityType.Builder<E> builder, boolean fireproof) {
        if (fireproof) builder.fireImmune();
        return ENTITIES.register(id.getPath(), () -> builder.build(id.toString()));
    }

    private static <E extends Entity> EntityType.Builder<E> makeCastedBuilder(@SuppressWarnings("unused") Class<E> cast, EntityType.EntityFactory<E> factory, float width, float height, int range, int interval) {
        return makeBuilder(factory, MobCategory.MISC, width, height, range, interval);
    }

    private static <E extends Entity> EntityType.Builder<E> makeBuilder(EntityType.EntityFactory<E> factory, MobCategory classification, float width, float height, int range, int interval) {
        return EntityType.Builder.of(factory, classification).
                sized(width, height).
                setTrackingRange(range).
                setUpdateInterval(interval).
                setShouldReceiveVelocityUpdates(true);
    }

    public static void createAttributes(EntityAttributeCreationEvent event){
        event.put(DWIDER.get(), DwiderEntity.setAttributes());
    }
}
