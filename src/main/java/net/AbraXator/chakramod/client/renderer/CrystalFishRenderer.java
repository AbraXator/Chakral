package net.AbraXator.chakramod.client.renderer;

import net.AbraXator.chakramod.ChakraMod;
import net.AbraXator.chakramod.client.renderer.model.CrystalFishModel;
import net.AbraXator.chakramod.client.renderer.model.MineralSnailModel;
import net.AbraXator.chakramod.entity.CrystalFish;
import net.AbraXator.chakramod.entity.MineralSnail;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class CrystalFishRenderer<Type extends CrystalFish> extends MobRenderer<Type, CrystalFishModel<Type>> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(ChakraMod.MOD_ID, "textures/entity/crystal_fish.png");

    public CrystalFishRenderer(EntityRendererProvider.Context context) {
        super(context, new CrystalFishModel<>(context.bakeLayer(CrystalFishModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(Type entity) {
        return TEXTURE;
    }
}
