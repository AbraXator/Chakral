package net.AbraXator.chakramod.renderer;

import net.AbraXator.chakramod.ChakraMod;
import net.AbraXator.chakramod.renderer.model.MineralSnailModel;
import net.AbraXator.chakramod.entity.MineralSnail;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class MineralSnailRenderer<Type extends MineralSnail> extends MobRenderer<Type, MineralSnailModel<Type>> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(ChakraMod.MOD_ID, "textures/entity/mineral_snail.png");

    public MineralSnailRenderer(EntityRendererProvider.Context context) {
        super(context, new MineralSnailModel<>(context.bakeLayer(MineralSnailModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(Type entity) {
        return TEXTURE;
    }
}
