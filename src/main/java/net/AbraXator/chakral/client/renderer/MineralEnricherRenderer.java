package net.AbraXator.chakral.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.AbraXator.chakral.init.ModBlocks;
import net.AbraXator.chakral.blocks.entity.MineralEnricherBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class MineralEnricherRenderer implements BlockEntityRenderer<MineralEnricherBlockEntity> {
    //ItemRenderer itemRenderer;
    public MineralEnricherRenderer(BlockEntityRendererProvider.Context context) {
        //this.itemRenderer = context.getItemRenderer();
    }

    @Override
    public void render(MineralEnricherBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        BlockRenderDispatcher blockRenderDispatcher = Minecraft.getInstance().getBlockRenderer();
        //--------------BLOCK-----------------
        BlockState block = getBudding(Block.byItem(getCrystal(pBlockEntity))).defaultBlockState();
        pPoseStack.pushPose();
        pPoseStack.scale(0.7f, 0.7f, 0.7f);
        pPoseStack.translate(0.2175f, 0.1f, 0.2175f);
        blockRenderDispatcher.renderSingleBlock(block, pPoseStack, pBufferSource, pPackedLight, pPackedOverlay, net.minecraftforge.client.model.data.ModelData.EMPTY, RenderType.solid());
        pPoseStack.popPose();
        //---------------CRYSTAL--------------
        BlockState block2 = Block.byItem(getCrystal(pBlockEntity)).defaultBlockState();
        pPoseStack.pushPose();
        //pPoseStack.translate(0.5, 0, 0.5);
        pPoseStack.mulPose(Axis.XP.rotationDegrees(180F));
        pPoseStack.translate(0f, 0f, -1f);
        float k = ((float) pBlockEntity.data.get(0)) / 160;
        System.out.println(k);
        float translate = ((float) (0.5 - (k * 0.5)));
        pPoseStack.translate(translate, 0, translate);
        pPoseStack.scale(k, k, k);
        blockRenderDispatcher.renderSingleBlock(block2, pPoseStack, pBufferSource, pPackedLight, pPackedOverlay, net.minecraftforge.client.model.data.ModelData.EMPTY, RenderType.cutout());
        pPoseStack.popPose();
    }

    private Item getCrystal(MineralEnricherBlockEntity pBlockEntity) {
        Item[] item = new Item[1];
        MineralEnricherBlockEntity.generateRecipe(pBlockEntity).ifPresentOrElse(recipe -> {
            item[0] = recipe.getResultItem(pBlockEntity.getLevel().registryAccess()).getItem();
        }, () -> {
            item[0] = Blocks.AIR.asItem();
        });
        return item[0];
    }

    private Block getBudding(Block crystal){
        if (crystal.equals(ModBlocks.BLACK_CRYSTAL.get())) {
            return ModBlocks.BUDDING_BLACK_MINERAL.get();
        } else if (crystal.equals(ModBlocks.TRUE_WHITE_CRYSTAL.get())) {
            return ModBlocks.TRUE_WHITE_MINERAL.get();
        } else if (crystal.equals(ModBlocks.WHITE_CRYSTAL.get())) {
            return ModBlocks.BUDDING_WHITE_MINERAL.get();
        } else if (crystal.equals(Blocks.AMETHYST_CLUSTER)) {
            return Blocks.BUDDING_AMETHYST;
        } else if (crystal.equals(ModBlocks.BLUE_CRYSTAL.get())) {
            return ModBlocks.BUDDING_BLUE_MINERAL.get();
        } else if (crystal.equals(ModBlocks.LIGHT_BLUE_CRYSTAL.get())) {
            return ModBlocks.BUDDING_LIGHT_BLUE_MINERAL.get();
        } else if (crystal.equals(ModBlocks.GREEN_CRYSTAL.get())) {
            return ModBlocks.BUDDING_GREEN_MINERAL.get();
        } else if (crystal.equals(ModBlocks.YELLOW_CRYSTAL.get())) {
            return ModBlocks.BUDDING_YELLOW_MINERAL.get();
        } else if (crystal.equals(ModBlocks.ORANGE_CRYSTAL.get())) {
            return ModBlocks.BUDDING_ORANGE_MINERAL.get();
        } else if (crystal.equals(ModBlocks.RED_CRYSTAL.get())) {
            return ModBlocks.BUDDING_RED_MINERAL.get();
        }
        return Blocks.AIR;
    }

     private int getLightLevel(Level level, BlockPos pos){
        int bLight = level.getBrightness(LightLayer.BLOCK, pos);
        int sLight = level.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack(bLight, sLight);
     }
}