package net.AbraXator.chakramod.recipes;

import net.AbraXator.chakramod.ChakraMod;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, ChakraMod.MOD_ID);

    public static final RegistryObject<RecipeSerializer<ShardRefinerRecipe>> GEM_INFUSING_SERIALIZER =
            SERIALIZERS.register("shard_refining", () -> ShardRefinerRecipe.Serializer.INSTANCE);


    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}
