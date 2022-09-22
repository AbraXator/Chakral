package net.AbraXator.chakral.entity;

import net.AbraXator.chakral.Chakral;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntity {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES ,
            Chakral.MOD_ID);

    public static final RegistryObject<EntityType<MineralSnail>> MINERAL_SNAIL = ENTITIES.register("mineral_snail",
            () -> EntityType.Builder.of(MineralSnail::new, MobCategory.AMBIENT).sized(0.5F, 0.5F)
                    .build(new ResourceLocation(Chakral.MOD_ID, "mineral_snail").toString()));
    public static final RegistryObject<EntityType<CrystalFish>> CRYSTAL_FISH = ENTITIES.register("crystal_fish",
            () -> EntityType.Builder.of(CrystalFish::new, MobCategory.WATER_AMBIENT).sized(0.5F, 0.5F)
                    .build(new ResourceLocation(Chakral.MOD_ID, "mineral_snail").toString()));

    public static void register(IEventBus eventBus){
        ENTITIES.register(eventBus);
    }
}