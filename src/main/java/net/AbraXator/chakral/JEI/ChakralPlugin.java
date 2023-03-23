package net.AbraXator.chakral.JEI;

import net.AbraXator.chakral.Chakral;
import net.AbraXator.chakral.blocks.ModBlocks;
import net.AbraXator.chakral.client.EdgeClient;
import net.AbraXator.chakral.recipes.MineralEnricherRecipe;
import net.AbraXator.chakral.recipes.ModRecipes;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.List;
import java.util.Objects;

/*@JeiPlugin
public class ChakralPlugin implements IModPlugin {
    public static RecipeType<MineralEnricherRecipe> MINERAL_ENRICHING =
            RecipeType.create(Chakral.MOD_ID, "enriching", MineralEnricherRecipe.class);

    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(Chakral.MOD_ID, "chakral_jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new MineralEnrichingCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();
        List<MineralEnricherRecipe> recipes = recipeManager.getAllRecipesFor(MineralEnricherRecipe.Type.INSTANCE);
        registration.addRecipes(MINERAL_ENRICHING, recipes);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.MINERAL_ENRICHER.get()), MINERAL_ENRICHING);
    }
}*/
