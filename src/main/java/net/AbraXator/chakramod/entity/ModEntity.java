package net.AbraXator.chakramod.entity;

import net.AbraXator.chakramod.ChakraMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntity {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES,
            ChakraMod.MOD_ID);

    public static final RegistryObject<EntityType<MineralSnail>> MINERAL_SNAIL = ENTITIES.register("mineral_snail",
            () -> EntityType.Builder.of(MineralSnail::new, MobCategory.AMBIENT).sized(0.5F, 0.5F)
                    .build(new ResourceLocation(ChakraMod.MOD_ID, "mineral_snail").toString()));
    public static final RegistryObject<EntityType<CrystalFish>> CRYSTAL_FISH = ENTITIES.register("mineral_snail",
            () -> EntityType.Builder.of(CrystalFish::new, MobCategory.WATER_AMBIENT).sized(0.5F, 0.5F)
                    .build(new ResourceLocation(ChakraMod.MOD_ID, "mineral_snail").toString()));
}