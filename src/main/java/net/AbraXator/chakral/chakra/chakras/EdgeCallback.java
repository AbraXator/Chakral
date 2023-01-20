package net.AbraXator.chakral.chakra.chakras;

import com.mojang.blaze3d.vertex.BufferBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.shapes.Shapes;
import org.joml.Matrix4f;

public class EdgeCallback implements Shapes.DoubleLineConsumer{
    private final BufferBuilder buffer;
    private final Matrix4f matrix4f;
    private final BlockPos pos;
    private final int alpha;

    public EdgeCallback(BufferBuilder buffer, Matrix4f matrix4f, BlockPos pos, int alpha) {
        this.buffer = buffer;
        this.matrix4f = matrix4f;
        this.pos = pos;
        this.alpha = alpha;
    }

    @Override
    public void consume(double pMinX, double pMinY, double pMinZ, double pMaxX, double pMaxY, double pMaxZ) {
        buffer.vertex(matrix4f, (float) (pMinX + pos.getX()), (float) (pMinY + pos.getY()), (float) (pMinZ + pos.getZ())).color(255, 255, 255, alpha).endVertex();
        buffer.vertex(matrix4f, (float) (pMaxX + pos.getX()), (float) (pMaxY + pos.getY()), (float) (pMaxZ + pos.getZ())).color(255, 255, 255, alpha).endVertex();
    }
}
