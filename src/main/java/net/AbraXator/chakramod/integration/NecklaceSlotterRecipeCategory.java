package net.AbraXator.chakramod.integration;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.builder.IRecipeSlotBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.AbraXator.chakramod.ChakraMod;
import net.AbraXator.chakramod.blocks.ModBlocks;
import net.AbraXator.chakramod.items.ModItems;
import net.AbraXator.chakramod.utils.ChakraType;
import net.AbraXator.chakramod.utils.ModTags;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

public class NecklaceSlotterRecipeCategory implements IRecipeCategory<INecklaceSlotterRecipe> {
    public final static ResourceLocation TEXTURE = new ResourceLocation(ChakraMod.MOD_ID, "textures/gui/container/necklace_slotter.png");
    private final IDrawable background;
    private final IDrawable icon;

    public NecklaceSlotterRecipeCategory(IGuiHelper guiHelper){
        background = guiHelper.drawableBuilder(TEXTURE, 0, 0, 220, 125).build();
        icon = guiHelper.createDrawableItemStack(new ItemStack(ModBlocks.NECKLACE_SLOTTER.get()));
    }

    @Override
    public RecipeType<INecklaceSlotterRecipe> getRecipeType() {
        return RecipeType.create(ChakraMod.MOD_ID, "necklace_slotter", INecklaceSlotterRecipe.class);
    }

    @Override
    public Component getTitle() {
        return ModBlocks.NECKLACE_SLOTTER.get().getName();
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
    public void setRecipe(IRecipeLayoutBuilder builder, INecklaceSlotterRecipe recipe, IFocusGroup focuses) {
        ItemStack necklace = ModItems.GOLDEN_NECKLACE.get().getDefaultInstance();
        List<Item> stone = ForgeRegistries.ITEMS.tags().getTag(ModTags.Items.GEMS).stream().toList();
        CompoundTag tag = new CompoundTag();
        //tag.putString(stone.toString());

        IRecipeSlotBuilder necklaceSlot = builder.addSlot(RecipeIngredientRole.OUTPUT, 80, 22)
                .addItemStack(necklace)
                .setSlotName("necklaceSlot");
        for(Item itemStack : stone) {
            IRecipeSlotBuilder stoneSlot = builder.addSlot(RecipeIngredientRole.INPUT, 80, 46)
                    .addItemStack(itemStack.getDefaultInstance())
                    .setSlotName("necklaceSlot");
        }
    }
}
