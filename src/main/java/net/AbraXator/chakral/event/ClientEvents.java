package net.AbraXator.chakral.event;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.AbraXator.chakral.Chakral;
import net.AbraXator.chakral.blocks.ModBlocks;
import net.AbraXator.chakral.blocks.entity.ModBlockEntities;
import net.AbraXator.chakral.blocks.entity.custom.renderer.MineralEnricherRenderer;
import net.AbraXator.chakral.client.ModOverlay;
import net.AbraXator.chakral.entity.DwiderModel;
import net.AbraXator.chakral.entity.DwiderRenderer;
import net.AbraXator.chakral.entity.ModEntities;
import net.AbraXator.chakral.entity.stemspore.StemSporeModel;
import net.AbraXator.chakral.entity.stemspore.StemSporeRenderer;
import net.AbraXator.chakral.networking.ModMessages;
import net.AbraXator.chakral.networking.packet.NecklaceC2SPacket;
import net.AbraXator.chakral.networking.packet.StoneFunctionC2SPacket;
import net.AbraXator.chakral.client.gui.refiner.ShardRefinerScreen;
import net.AbraXator.chakral.utils.KeyBindings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.MultiBufferSource.BufferSource;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.GrassColor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.*;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.joml.Matrix4f;

@Mod.EventBusSubscriber(modid = Chakral.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class ClientEvents {
    @Mod.EventBusSubscriber(modid = Chakral.MOD_ID, value = Dist.CLIENT)
    public static class ClientForgeEvents {
        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {
            if(KeyBindings.NECKLACE_EQUIP_KEY.consumeClick()) {
                ModMessages.sendToServer(new NecklaceC2SPacket());
            }
            if(KeyBindings.STONE_FUNCTION_KEY.consumeClick()){
                ModMessages.sendToServer(new StoneFunctionC2SPacket());
            }
        }

        public static void render(RenderLevelStageEvent event){
            Level level = Minecraft.getInstance().level;
            BlockPos blockPos = new BlockPos(100, 100, 100);
            BlockState state = level.getBlockState(blockPos);
            VoxelShape voxelShape = state.getShape(level, blockPos, CollisionContext.empty());
            drawSelectionBox(event.getLevelRenderer(), Minecraft.getInstance().renderBuffers().bufferSource(), event.getPoseStack(), blockPos, voxelShape);
        }

        private static void drawSelectionBox(LevelRenderer levelRenderer, MultiBufferSource bufferSource, PoseStack poseStack, BlockPos pos, VoxelShape voxelShape){
            RenderType type = RenderType.outline(new ResourceLocation(Chakral.MOD_ID, "textures/render_helper.png"));
            VertexConsumer vertexConsumer = bufferSource.getBuffer(type);
            Vec3 camPos = Minecraft.getInstance().gameRenderer.getMainCamera().getPosition();
            drawShapeOutline(poseStack, vertexConsumer, voxelShape, pos.getX() - camPos.x, pos.getY() - camPos.y, pos.getZ() - camPos.z);
        }

        private static void drawShapeOutline(PoseStack poseStack, VertexConsumer vertexConsumer, VoxelShape voxelShape, double originX, double originY, double originZ){
            Matrix4f matrix4f = poseStack.last().pose();
            voxelShape.forAllEdges((pMinX, pMinY, pMinZ, pMaxX, pMaxY, pMaxZ) -> {
                vertexConsumer.vertex(matrix4f, (float)(pMinX + originX), (float)(pMinY + originY), (float)(pMinZ + originZ)).color(1, 1, 1,1);
                vertexConsumer.vertex(matrix4f, (float)(pMaxX + originX), (float)(pMaxY + originY), (float)(pMaxZ + originZ)).color(1, 1, 1,1);
            });
        }





        public static void renderOres(RenderLevelStageEvent event) {
            PoseStack pPoseStack = event.getPoseStack();
            BufferSource buffer = Minecraft.getInstance().renderBuffers().bufferSource();
            ResourceLocation PATH = new ResourceLocation(Chakral.MOD_ID, "textures/render_helper.png");
            VertexConsumer builder = buffer.getBuffer(RenderType.outline(PATH));
            //pPoseStack.pushPose();
            Vec3 cam = Minecraft.getInstance().gameRenderer.getMainCamera().getPosition();
            ClientLevel level = Minecraft.getInstance().level;
            renderHitOutline(pPoseStack, builder, Minecraft.getInstance().cameraEntity, cam.x, cam.y, cam.z, new BlockPos(100, 100, 100), level.getBlockState(new BlockPos(100, 100, 100)));
            /*Vec3 cam = Minecraft.getInstance().gameRenderer.getMainCamera().getPosition();
            pPoseStack.translate(100 - cam.x, 100 - cam.y, 100 - cam.z);
            Matrix4f matrix4f = pPoseStack.last().pose();
            RenderSystem.lineWidth(10);
            builder.vertex(matrix4f, 10, 10, -10).color(255, 0, 0, 255).uv(0, 0).endVertex();
            builder.vertex(matrix4f, 10, 10, -10).color(255, 0, 0, 255).uv(0, 0).endVertex();
            builder.vertex(matrix4f, -10, 10, 10).color(255, 0, 0, 255).uv(0, 0).endVertex();
            builder.vertex(matrix4f, -10, -10, 10).color(255, 0, 0, 255).uv(0, 0).endVertex();
            System.out.println(event.getStage().toString());*/
            //pPoseStack.popPose();
            buffer.endBatch(RenderType.outline(PATH));
        }

        private static void renderHitOutline(PoseStack pPoseStack, VertexConsumer pConsumer, Entity pEntity, double pCamX, double pCamY, double pCamZ, BlockPos pPos, BlockState pState) {
            ClientLevel level = Minecraft.getInstance().level;
            renderShape(pPoseStack, pConsumer, pState.getShape(level, pPos, CollisionContext.of(pEntity)), (double)pPos.getX() - pCamX, (double)pPos.getY() - pCamY, (double)pPos.getZ() - pCamZ, 0.0F, 0.0F, 0.0F, 255F);
        }

        private static void renderShape(PoseStack pPoseStack, VertexConsumer pConsumer, VoxelShape pShape, double pX, double pY, double pZ, float pRed, float pGreen, float pBlue, float pAlpha) {
            PoseStack.Pose posestack$pose = pPoseStack.last();
            pShape.forAllEdges((p_234280_, p_234281_, p_234282_, p_234283_, p_234284_, p_234285_) -> {
                float f = (float)(p_234283_ - p_234280_);
                float f1 = (float)(p_234284_ - p_234281_);
                float f2 = (float)(p_234285_ - p_234282_);
                float f3 = Mth.sqrt(f * f + f1 * f1 + f2 * f2);
                f /= f3;
                f1 /= f3;
                f2 /= f3;
                pConsumer.vertex(posestack$pose.pose(), (float)(p_234280_ + pX), (float)(p_234281_ + pY), (float)(p_234282_ + pZ)).color(pRed, pGreen, pBlue, pAlpha).uv(0, 5).endVertex();
                pConsumer.vertex(posestack$pose.pose(), (float)(p_234283_ + pX), (float)(p_234284_ + pY), (float)(p_234285_ + pZ)).color(pRed, pGreen, pBlue, pAlpha).uv(0, 5).endVertex();
            });
        }

        @SubscribeEvent
        public static void onOpenScreen(ScreenEvent.Opening event){
            if(event.getNewScreen() instanceof ShardRefinerScreen && (Minecraft.getInstance().player != null && Minecraft.getInstance().player.isShiftKeyDown())){
                event.setCanceled(true);
            }
        }
    }

    @Mod.EventBusSubscriber(modid = Chakral.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {
        @SubscribeEvent
        public static void registerGUIOverlays(RegisterGuiOverlaysEvent event){
            event.registerAboveAll("chakra_health", ModOverlay.CHAKRA_HEARTS);
        }

        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(KeyBindings.STONE_FUNCTION_KEY);
            event.register(KeyBindings.NECKLACE_EQUIP_KEY);
        }

        @SubscribeEvent
        public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event){
            event.registerBlockEntityRenderer(ModBlockEntities.MINERAL_ENRICHER_BLOCK_ENTITY.get(),
                    MineralEnricherRenderer::new);
        }
    }

    @SubscribeEvent
    public static void layerDefitions(EntityRenderersEvent.RegisterLayerDefinitions event){
        event.registerLayerDefinition(DwiderModel.LAYER_LOCATION, DwiderModel::createBodyLayer);
        event.registerLayerDefinition(StemSporeModel.LAYER_LOCATION, StemSporeModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void entityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.DWIDER.get(), DwiderRenderer::new);
        event.registerEntityRenderer(ModEntities.STEM_SPORE.get(), StemSporeRenderer::new);
    }

    @SubscribeEvent
    public static void colorEvent(RegisterColorHandlersEvent.Block event){
        event.register((pState, pLevel, pPos, pTintIndex) -> pLevel != null && pPos != null ? BiomeColors.getAverageGrassColor(pLevel, pPos) : GrassColor.get(0.5D, 1.0D),
                ModBlocks.MINERAL_RICH_GRASS.get());
    }

    @SubscribeEvent
    public static void colorEvent(RegisterColorHandlersEvent.Item event){
        event.register((pStack, pTintIndex) -> GrassColor.get(0.5D, 1.0D),
                ModBlocks.MINERAL_RICH_GRASS.get());
    }
}
