package net.AbraXator.chakral.client;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.AbraXator.chakral.chakra.chakras.CachedEdge;
import net.AbraXator.chakral.utils.ShapeMerger;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderStateShard.OverlayStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import org.joml.Matrix4f;

import java.util.*;

public class EdgeClient extends EdgeCommon{
    private List<CachedEdge> cachedEdges = null;
    private BlockPos cachedPos = null;
    private List<BlockPos> shapeBlocks = Collections.emptyList();

    public EdgeClient(){
    }

    @Override
    public void setShape(List<BlockPos> blocks) {
        shapeBlocks = blocks;
        cachedEdges = null;
        updateEdges();
    }

    public void renderInGame(PoseStack poseStack){
        if(cachedEdges == null || cachedEdges == null || cachedEdges.isEmpty()){
            return;
        }

        Minecraft mc = Minecraft.getInstance();
        Camera activeRenderInfo = mc.getEntityRenderDispatcher().camera;
        Vec3 projectedView = activeRenderInfo.getPosition();
        poseStack.pushPose();
        poseStack.translate(cachedPos.getX() - projectedView.x, cachedPos.getY() - projectedView.y, cachedPos.getZ() - projectedView.z);
        Matrix4f matrix4f = poseStack.last().pose();
        VertexConsumer buffer = mc.renderBuffers().bufferSource().getBuffer(ModRenderType.LINES);
        for(CachedEdge edge : cachedEdges){
            buffer.vertex(matrix4f, edge.x1, edge.x1, edge.z1).color(255, 255, 255, 255).endVertex();
            buffer.vertex(matrix4f, edge.x2, edge.y2, edge.z2).color(255, 255, 255, 255).endVertex();
        }
        mc.renderBuffers().bufferSource().endBatch(ModRenderType.LINES);
        poseStack.popPose();
    }

    private void updateEdges(){
        if(cachedEdges != null){
            return;
        }
        if(shapeBlocks.isEmpty()){
            cachedEdges = Collections.emptyList();
            return;
        }
        cachedPos = shapeBlocks.get(0);
        double d = 0.005D;
        cachedEdges = new ArrayList<>();
        Collection<VoxelShape> shapes = new HashSet<>();
        for(AABB aabb : ShapeMerger.merge(shapeBlocks, cachedPos)){
            shapes.add(Shapes.create(aabb.inflate(d)));
        }
        orShapes(shapes).forAllEdges((pMinX, pMinY, pMinZ, pMaxX, pMaxY, pMaxZ) -> {
            CachedEdge edge = new CachedEdge();
            edge.x1 = (float) pMinX;
            edge.y1 = (float) pMinY;
            edge.z1 = (float) pMinZ;
            edge.x2 = (float) pMaxX;
            edge.y2 = (float) pMaxY;
            edge.z2 = (float) pMaxZ;
            cachedEdges.add(edge);
        });
    }

    static VoxelShape orShapes(Collection<VoxelShape> shapes) {
        VoxelShape combinedShape = Shapes.empty();
        for (VoxelShape shape : shapes) {
            combinedShape = Shapes.joinUnoptimized(combinedShape, shape, BooleanOp.OR);
        }
        return combinedShape;
    }

    public class ModRenderType extends RenderType{
        private static final RenderType LINES = RenderType.create("chakral_lines", DefaultVertexFormat.POSITION_COLOR, VertexFormat.Mode.DEBUG_LINES,
                256, false, false, RenderType.CompositeState.builder()
                        .setShaderState(new RenderStateShard.ShaderStateShard(GameRenderer::getPositionColorShader))
                        .setLineState(RenderStateShard.DEFAULT_LINE)
                        .setLayeringState(RenderStateShard.VIEW_OFFSET_Z_LAYERING)
                        .setTransparencyState(RenderStateShard.NO_TRANSPARENCY)
                        .setWriteMaskState(RenderStateShard.COLOR_WRITE)
                        .setCullState(NO_CULL)
                        .createCompositeState(false));

        public ModRenderType(String pName, VertexFormat pFormat, VertexFormat.Mode pMode, int pBufferSize, boolean pAffectsCrumbling, boolean pSortOnUpload, Runnable pSetupState, Runnable pClearState) {
            super(pName, pFormat, pMode, pBufferSize, pAffectsCrumbling, pSortOnUpload, pSetupState, pClearState);
        }
    }
}
