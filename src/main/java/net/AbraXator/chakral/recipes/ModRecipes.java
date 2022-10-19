package net.AbraXator.chakral.recipes;

import net.AbraXator.chakral.Chakral;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Chakral.MOD_ID);
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES =
            DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, Chakral.MOD_ID);

    public static RegistryObject<RecipeType<MineralEnricherRecipe>> MINERAL_ENRICHER_TYPE = registerRecipeType("mineral_enriching");

    public static final RegistryObject<RecipeSerializer<ShardRefinerRecipe>> SHARD_REFINING_SERIALIZER =
            SERIALIZERS.register("shard_refining", () -> ShardRefinerRecipe.Serializer.INSTANCE);

    public static final RegistryObject<RecipeSerializer<MineralEnricherRecipe>> MINERAL_ENRICHER_SERIALIZER =
            SERIALIZERS.register("mineral_enriching", () -> MineralEnricherRecipe.Serializer.INSTANCE);

    private static <T extends Recipe<SimpleContainer>> RegistryObject<RecipeType<T>> registerRecipeType(String pType) {
        return RECIPE_TYPES.register(pType, () -> new RecipeType<>() {
            @Override
            public String toString() {
                return pType;
            }
        });
    }

    private static final Map<RecipeType<? extends Recipe<SimpleContainer>>, List<? extends Recipe<SimpleContainer>>> recipesMap = new HashMap<>();

    @SuppressWarnings("unchecked")
    public static <T extends Recipe<SimpleContainer>> List<T> getRecipesByType(RecipeType<T> pRecipeType, Level pLevel) {
        if (recipesMap.get(pRecipeType) == null) {
            List<T> recipes = pLevel.getRecipeManager().getRecipes().stream()
                    .filter(recipe -> recipe.getType().equals(pRecipeType))
                    .map(recipe -> (T) recipe)
                    .collect(Collectors.toList());
            recipesMap.put(pRecipeType, recipes);
        }
        return (List<T>) recipesMap.get(pRecipeType);
    }

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
        RECIPE_TYPES.register(eventBus);
    }
}
