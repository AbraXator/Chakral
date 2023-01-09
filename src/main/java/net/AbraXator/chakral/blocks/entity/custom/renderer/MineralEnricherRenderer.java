package net.AbraXator.chakral.blocks.entity.custom.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.AbraXator.chakral.blocks.entity.custom.MineralEnricherBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;

public class MineralEnricherRenderer implements BlockEntityRenderer<MineralEnricherBlockEntity> {
    //ItemRenderer itemRenderer;
    public MineralEnricherRenderer(BlockEntityRendererProvider.Context context) {
        //this.itemRenderer = context.getItemRenderer();
    }

    @Override
    public void render(MineralEnricherBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        //--------------BLOCK-----------------
        ItemStack itemStack = pBlockEntity.buddingGen(pBlockEntity.itemHandler.getStackInSlot(2)).asItem().getDefaultInstance();
        pPoseStack.pushPose();
        pPoseStack.translate(0.5f, 0.325f, 0.5f);
        pPoseStack.scale(1.3f, 1.3f, 1.3f);
        itemRenderer.renderStatic(itemStack, ItemTransforms.TransformType.FIXED, 15728880,
                pPackedOverlay, pPoseStack, pBufferSource, 1);
        pPoseStack.popPose();
        //---------------CRYSTAL--------------
        ItemStack crystal = pBlockEntity.resultGen(pBlockEntity.itemHandler.getStackInSlot(2)).getFirst().asItem().getDefaultInstance();
        pPoseStack.pushPose();
        pPoseStack.translate(0.5f, 0.0f, 0.5f);
        float j = pBlockEntity.getScaledProgress(pBlockEntity);
        pPoseStack.scale(j, j, j);
        itemRenderer.renderStatic(crystal, ItemTransforms.TransformType.FIXED, 15728880,
                pPackedOverlay, pPoseStack, pBufferSource, 1);
        pPoseStack.popPose();
    }

     private int getLightLevel(Level level, BlockPos pos){
        int bLight = level.getBrightness(LightLayer.BLOCK, pos);
        int sLight = level.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack(bLight, sLight);
     }
}