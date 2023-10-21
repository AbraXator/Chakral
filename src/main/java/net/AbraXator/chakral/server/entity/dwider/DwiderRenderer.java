package net.AbraXator.chakral.server.entity.dwider;

import net.AbraXator.chakral.Chakral;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class DwiderRenderer extends MobRenderer<DwiderEntity, DwiderModel<DwiderEntity>> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(Chakral.MOD_ID, "texture/entity/dwider.png");

    public DwiderRenderer(EntityRendererProvider.Context context) {
        super(context, new DwiderModel<>(context.bakeLayer(DwiderModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(DwiderEntity pEntity) {
        return TEXTURE;
    }
}
