package net.AbraXator.chakral.server.chakra.chakras;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.AbraXator.chakral.Chakral;
import net.AbraXator.chakral.server.chakra.Chakra;
import net.AbraXator.chakral.server.chakra.ChakraStrength;
import net.AbraXator.chakral.server.chakra.ChakraType;
import net.AbraXator.chakral.server.init.ModRenderTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Style;
import net.minecraft.util.FastColor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import org.joml.Matrix4f;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DumortieriteChakra extends Chakra {
    private Map<BlockPos, MapColor> blocks = new LinkedHashMap<>();

    public DumortieriteChakra(ChakraType type, ChakraStrength chakraStrength) {
        super(type, chakraStrength);
    }

    @Override
    public void onDamage(LivingDamageEvent event) {
        //blocks = NonNullList.create();
        //if(event.getSource().is(DamageTypes.FALL)) searchForBlocks(event.getEntity().getOnPos(), event.getEntity().level(), (float) (event.getAmount() * 5));
    }

    private void searchForBlocks(BlockPos pos, Level level){
        AABB aabb = new AABB(pos).inflate(5);
        BlockPos.betweenClosedStream(aabb).forEach(blockPos -> {
            blocks.put(blockPos, MapColor.COLOR_BLACK);
        });
    }

    public Map<BlockPos, MapColor> getBlocks() {
        return blocks;
    }

    @Override
    public void onEquip(Player player, Level level) {
        this.blocks = new LinkedHashMap<>();
        searchForBlocks(player.getOnPos(), level);
    }

    public void render(RenderLevelStageEvent event){
        this.getBlocks().forEach((blockPos, mapColor) -> {
            Chakral.LOGGER.info("{} {} {}", blockPos.getX(), blockPos.getY(), blockPos.getZ());
            Vec3 camPos = Minecraft.getInstance().gameRenderer.getMainCamera().getPosition();
            if(!(camPos.distanceTo(blockPos.getCenter()) > 20)) {
                Vec3 renderPos = Minecraft.getInstance().gameRenderer.getMainCamera().getPosition()
                        .subtract(blockPos.getX(), blockPos.getY(), blockPos.getZ())
                        .add(.005F, .005F, .005F);

                MultiBufferSource.BufferSource buffer = Minecraft.getInstance().renderBuffers().bufferSource();
                PoseStack stack = event.getPoseStack();
                stack.pushPose();
                stack.translate(-renderPos.x(), -renderPos.y(), -renderPos.z());
                stack.scale(1.01F, 1.01F, 1.01F);
                renderBoxSolid(stack.last().pose(), buffer.getBuffer(ModRenderTypes.BLOCK_OVERLAY), blockPos, FastColor.ARGB32.color(255, 0, 255, 0));
                stack.popPose();
            }
        });
    }

    public void renderBoxSolid(Matrix4f matrix, VertexConsumer builder, BlockPos pos, int color) {
        float startX = (float) (pos.getX() - 0.001);
        float startY = (float) (pos.getY() - 0.001);
        float startZ = (float) (pos.getZ() - 0.001);
        float endX = (float) (pos.getX() + 1.001);
        float endY = (float) (pos.getY() + 1.001);
        float endZ = (float) (pos.getZ() + 1.001);

        builder.vertex(matrix, startX, startY, startZ).color(color).endVertex();
        builder.vertex(matrix, endX, startY, startZ).color(color).endVertex();
        builder.vertex(matrix, endX, startY, endZ).color(color).endVertex();
        builder.vertex(matrix, startX, startY, endZ).color(color).endVertex();

        //up
        builder.vertex(matrix, startX, endY, startZ).color(color).endVertex();
        builder.vertex(matrix, startX, endY, endZ).color(color).endVertex();
        builder.vertex(matrix, endX, endY, endZ).color(color).endVertex();
        builder.vertex(matrix, endX, endY, startZ).color(color).endVertex();

        //east
        builder.vertex(matrix, startX, startY, startZ).color(color).endVertex();
        builder.vertex(matrix, startX, endY, startZ).color(color).endVertex();
        builder.vertex(matrix, endX, endY, startZ).color(color).endVertex();
        builder.vertex(matrix, endX, startY, startZ).color(color).endVertex();

        //west
        builder.vertex(matrix, startX, startY, endZ).color(color).endVertex();
        builder.vertex(matrix, endX, startY, endZ).color(color).endVertex();
        builder.vertex(matrix, endX, endY, endZ).color(color).endVertex();
        builder.vertex(matrix, startX, endY, endZ).color(color).endVertex();

        //south
        builder.vertex(matrix, endX, startY, startZ).color(color).endVertex();
        builder.vertex(matrix, endX, endY, startZ).color(color).endVertex();
        builder.vertex(matrix, endX, endY, endZ).color(color).endVertex();
        builder.vertex(matrix, endX, startY, endZ).color(color).endVertex();

        //north
        builder.vertex(matrix, startX, startY, startZ).color(color).endVertex();
        builder.vertex(matrix, startX, startY, endZ).color(color).endVertex();
        builder.vertex(matrix, startX, endY, endZ).color(color).endVertex();
        builder.vertex(matrix, startX, endY, startZ).color(color).endVertex();
    }

    @Override
    public List<Style> getColors() {
        return chakraColor("3b54b5", "374daa", "2b3093", "231e89");
    }
}
