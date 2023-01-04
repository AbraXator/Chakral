package net.AbraXator.chakral.screen;

import com.mojang.blaze3d.vertex.PoseStack;

public class ImageButton
{
    private final int X;
    private final int Y;
    private final int W;
    private final int H;

    public ImageButton(int U, int V, int W, int H)
    {
        this.X = U;
        this.Y = V;
        this.W = W;
        this.H = H;
    }

    public void draw(PoseStack poseStack, NecklaceInserterScreen screen, int U, int V)
    {
        screen.blit(poseStack, screen.getGuiLeft() + X, screen.getGuiTop() + Y, U, V, W, H);
    }

    public boolean inButton(NecklaceInserterScreen screen, int mouseX, int mouseY)
    {
        mouseX -= screen.getGuiLeft();
        mouseY -= screen.getGuiTop();
        return X <= mouseX && mouseX <= X + W && Y <= mouseY && mouseY <= Y + H;
    }

    public boolean inButton(NecklaceInserterScreen screen, int mouseX, int mouseY, int X)
    {
        mouseX -= screen.getGuiLeft();
        mouseY -= screen.getGuiTop();
        return X <= mouseX && mouseX <= X + 9 && -6 <= mouseY && mouseY <= -6 + 9;
    }
}