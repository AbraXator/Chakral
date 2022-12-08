package net.AbraXator.chakral.entity;

import net.AbraXator.chakral.Chakral;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class MineralSnailRenderer extends MobRenderer<MineralSnail, MineralSnailModel> {
    public static final ModelLayerLocation MODEL_LAYER = new ModelLayerLocation(new ResourceLocation(Chakral.MOD_ID, "mineral_snail"), "main");
    public static final ResourceLocation TEXTURE = new ResourceLocation(Chakral.MOD_ID, "mineral_snail");
    public MineralSnailRenderer(EntityRendererProvider.Context context) {
        super(context, new MineralSnailModel(context.bakeLayer(MODEL_LAYER)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(MineralSnail pEntity) {
        return TEXTURE;
    }
}
