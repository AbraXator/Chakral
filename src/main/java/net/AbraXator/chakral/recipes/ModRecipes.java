package net.AbraXator.chakral.recipes;

import net.AbraXator.chakral.Chakral;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Chakral.MOD_ID);

    public static final RegistryObject<RecipeSerializer<ShardRefinerRecipe>> SHARD_REFINING_SERIALIZER =
            SERIALIZERS.register("shard_refining", () -> ShardRefinerRecipe.Serializer.INSTANCE);

    public static final RegistryObject<RecipeSerializer<MineralEnricherRecipe>> MINERAL_ENRICHER_SERIALIZER =
            SERIALIZERS.register("mineral_enriching", () -> MineralEnricherRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}
