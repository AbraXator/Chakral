package net.AbraXator.chakral.chakra.chakras;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.AbraXator.chakral.chakra.Chakra;
import net.AbraXator.chakral.chakra.ChakraStrength;
import net.AbraXator.chakral.chakra.ChakraType;
import net.AbraXator.chakral.init.ModRenderTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Style;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import org.joml.Matrix4f;

import java.util.List;

public class DumortieriteChakra extends Chakra {
    private NonNullList<BlockPos> blocks = NonNullList.create();

    public DumortieriteChakra(ChakraType type, ChakraStrength chakraStrength) {
        super(type, chakraStrength);
    }

    @Override
    public void onDamage(LivingDamageEvent event) {
        blocks = NonNullList.create();
        if(event.getSource().is(DamageTypes.FALL)) searchForBlocks(event.getEntity().getOnPos(), event.getEntity().level(), (float) (event.getAmount() * 0.5));
    }

    private void searchForBlocks(BlockPos pos, Level level, float range){
        for (BlockPos blockPos : BlockPos.betweenClosed(
                (int) (pos.getX() + range), (int) (pos.getY() + range), (int) (pos.getZ() + range),
                (int) (pos.getX() - range), (int) (pos.getY() - range), (int) (pos.getZ() - range))) {
            if(level.getBlockState(blockPos).is(Tags.Blocks.ORES)) blocks.add(blockPos);
        }
    }

    public NonNullList<BlockPos> getBlocks() {
        return blocks;
    }

    public void render(RenderLevelStageEvent event, BlockPos blockPos){
        if(blockPos == null) return;
        Vec3 renderPos = Minecraft.getInstance().gameRenderer.getMainCamera().getPosition()
                .subtract(blockPos.getX(), blockPos.getY(), blockPos.getZ())
                .add(.005F, .005F, .005F);

        MultiBufferSource.BufferSource buffer = Minecraft.getInstance().renderBuffers().bufferSource();
        PoseStack stack = event.getPoseStack();
        stack.pushPose();
        stack.translate(-renderPos.x(), -renderPos.y(), -renderPos.z());
        stack.scale(1.01F, 1.01F, 1.01F);
        renderBoxSolid(stack.last().pose(), buffer.getBuffer(ModRenderTypes.BlockOverlay), BlockPos.ZERO, 0, 1, 0, 1);
    }

    public void renderBoxSolid(Matrix4f matrix, VertexConsumer builder, BlockPos pos, float red, float green, float blue, float alpha) {
        //careful: mc want's it's vertices to be defined CCW - if you do it the other way around weird cullling issues will arise
        //CCW herby counts as if you were looking at it from the outside
        float startX = (float) (pos.getX() - 0.001);
        float startY = (float) (pos.getY() - 0.001);
        float startZ = (float) (pos.getZ() - 0.001);
        float endX = (float) (pos.getX() + 1.0015);
        float endY = (float) (pos.getY() + 1.0015);
        float endZ = (float) (pos.getZ() + 1.0015);

//        float startX = 0, startY = 0, startZ = -1, endX = 1, endY = 1, endZ = 0;

        //down
        builder.vertex(matrix, startX, startY, startZ).color(red, green, blue, alpha).endVertex();
        builder.vertex(matrix, endX, startY, startZ).color(red, green, blue, alpha).endVertex();
        builder.vertex(matrix, endX, startY, endZ).color(red, green, blue, alpha).endVertex();
        builder.vertex(matrix, startX, startY, endZ).color(red, green, blue, alpha).endVertex();

        //up
        builder.vertex(matrix, startX, endY, startZ).color(red, green, blue, alpha).endVertex();
        builder.vertex(matrix, startX, endY, endZ).color(red, green, blue, alpha).endVertex();
        builder.vertex(matrix, endX, endY, endZ).color(red, green, blue, alpha).endVertex();
        builder.vertex(matrix, endX, endY, startZ).color(red, green, blue, alpha).endVertex();

        //east
        builder.vertex(matrix, startX, startY, startZ).color(red, green, blue, alpha).endVertex();
        builder.vertex(matrix, startX, endY, startZ).color(red, green, blue, alpha).endVertex();
        builder.vertex(matrix, endX, endY, startZ).color(red, green, blue, alpha).endVertex();
        builder.vertex(matrix, endX, startY, startZ).color(red, green, blue, alpha).endVertex();

        //west
        builder.vertex(matrix, startX, startY, endZ).color(red, green, blue, alpha).endVertex();
        builder.vertex(matrix, endX, startY, endZ).color(red, green, blue, alpha).endVertex();
        builder.vertex(matrix, endX, endY, endZ).color(red, green, blue, alpha).endVertex();
        builder.vertex(matrix, startX, endY, endZ).color(red, green, blue, alpha).endVertex();

        //south
        builder.vertex(matrix, endX, startY, startZ).color(red, green, blue, alpha).endVertex();
        builder.vertex(matrix, endX, endY, startZ).color(red, green, blue, alpha).endVertex();
        builder.vertex(matrix, endX, endY, endZ).color(red, green, blue, alpha).endVertex();
        builder.vertex(matrix, endX, startY, endZ).color(red, green, blue, alpha).endVertex();

        //north
        builder.vertex(matrix, startX, startY, startZ).color(red, green, blue, alpha).endVertex();
        builder.vertex(matrix, startX, startY, endZ).color(red, green, blue, alpha).endVertex();
        builder.vertex(matrix, startX, endY, endZ).color(red, green, blue, alpha).endVertex();
        builder.vertex(matrix, startX, endY, startZ).color(red, green, blue, alpha).endVertex();
    }

    @Override
    public List<Style> getColors() {
        return chakraColor("3b54b5", "374daa", "2b3093", "231e89");
    }
}
