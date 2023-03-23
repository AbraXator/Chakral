package net.AbraXator.chakral.JEI;

import net.AbraXator.chakral.Chakral;
import net.AbraXator.chakral.blocks.ModBlocks;
import net.AbraXator.chakral.recipes.MineralEnricherRecipe;
import net.AbraXator.chakral.recipes.ShardRefinerRecipe;
import net.AbraXator.chakral.screen.enricher.MineralEnricherScreen;
import net.AbraXator.chakral.screen.refiner.ShardRefinerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
/*
public class MineralEnrichingCategory implements IRecipeCategory<MineralEnricherRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(Chakral.MOD_ID, "enriching");
    public static final int width = 116;
    public static final int height = 54;
    private IDrawable background;
    private IDrawable icon;

    public MineralEnrichingCategory(IGuiHelper guiHelper) {
        this.background = guiHelper.createDrawable(MineralEnricherScreen.TEXTURE, 0, 60, width, height);
        this.icon = guiHelper.createDrawableItemStack(ModBlocks.MINERAL_ENRICHER.get().asItem().getDefaultInstance());
    }

    @Override
    public RecipeType<MineralEnricherRecipe> getRecipeType() {
        return ChakralPlugin.MINERAL_ENRICHING;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("chakral.jei.recipe.mineral_enriching");
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, MineralEnricherRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.CATALYST, 26, 12).addItemStack(Items.WATER_BUCKET.asItem().getDefaultInstance());
        builder.addSlot(RecipeIngredientRole.INPUT, 86, 24).addFluidStack(recipe.getFluidStack().getFluid(), recipe.getFluidStack().getAmount());
        builder.addSlot(RecipeIngredientRole.INPUT, 134, 12).addItemStack(recipe.getDust());
        builder.addSlot(RecipeIngredientRole.INPUT, 134, 53).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 77, 66).addItemStack(recipe.getResultItem());
    }
}*/
