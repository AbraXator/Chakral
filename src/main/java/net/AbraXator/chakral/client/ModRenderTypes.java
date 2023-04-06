package net.AbraXator.chakral.client;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraftforge.client.event.ScreenEvent;

public class ModRenderTypes extends RenderType {
    public static final RenderType BlockOverlay = create("BGBlockOverlay",
            DefaultVertexFormat.POSITION_COLOR, VertexFormat.Mode.QUADS, 256, false, false,
            RenderType.CompositeState.builder()
                    .setShaderState(RenderStateShard.RENDERTYPE_OUTLINE_SHADER)
                    //.setLayeringState(VIEW_OFFSET_Z_LAYERING) // view_offset_z_layering
                    .setTextureState(new RenderStateShard.TextureStateShard(TextureAtlas.LOCATION_BLOCKS, false, false))
                    .setCullState(CULL)
                    .setDepthTestState(LEQUAL_DEPTH_TEST)
                    .setOutputState(OUTLINE_TARGET)
                    .createCompositeState(true));
                    //.setTransparencyState(TRANSLUCENT_TRANSPARENCY)
                    //.setLightmapState(NO_LIGHTMAP)
                    //.setWriteMaskState(COLOR_DEPTH_WRITE)
                    //.createCompositeState(false));

    public ModRenderTypes(String pName, VertexFormat pFormat, VertexFormat.Mode pMode, int pBufferSize, boolean pAffectsCrumbling, boolean pSortOnUpload, Runnable pSetupState, Runnable pClearState) {
        super(pName, pFormat, pMode, pBufferSize, pAffectsCrumbling, pSortOnUpload, pSetupState, pClearState);
    }
}
