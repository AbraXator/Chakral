package net.AbraXator.chakral.integration;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.AbraXator.chakral.Chakral;
import net.minecraft.resources.ResourceLocation;

@JeiPlugin
public class JEIModPlugin implements IModPlugin {
    public static final ResourceLocation UID =  new ResourceLocation(Chakral.MOD_ID, "jei_plugin");

    @Override
    public ResourceLocation getPluginUid() {
        return UID;
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new
                NecklaceSlotterRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {

    }
}
