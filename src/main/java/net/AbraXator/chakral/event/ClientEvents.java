package net.AbraXator.chakral.event;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.AbraXator.chakral.Chakral;
import net.AbraXator.chakral.blocks.ModBlocks;
import net.AbraXator.chakral.blocks.entity.ModBlockEntities;
import net.AbraXator.chakral.blocks.entity.custom.renderer.MineralEnricherRenderer;
import net.AbraXator.chakral.client.ModOverlay;
import net.AbraXator.chakral.client.ModRenderTypes;
import net.AbraXator.chakral.entity.DwiderModel;
import net.AbraXator.chakral.entity.DwiderRenderer;
import net.AbraXator.chakral.entity.ModEntities;
import net.AbraXator.chakral.entity.stemspore.StemSporeModel;
import net.AbraXator.chakral.entity.stemspore.StemSporeRenderer;
import net.AbraXator.chakral.networking.ModMessages;
import net.AbraXator.chakral.networking.packet.NecklaceC2SPacket;
import net.AbraXator.chakral.networking.packet.StoneFunctionC2SPacket;
import net.AbraXator.chakral.client.gui.refiner.ShardRefinerScreen;
import net.AbraXator.chakral.utils.ChakralLocation;
import net.AbraXator.chakral.utils.KeyBindings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.MultiBufferSource.BufferSource;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
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

import java.util.ArrayList;
import java.util.List;

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

        @SubscribeEvent
        public static void renderLevel(RenderLevelStageEvent event){
            if(event.getStage() != RenderLevelStageEvent.Stage.AFTER_TRANSLUCENT_BLOCKS){
                return;
            }

            Player player = Minecraft.getInstance().player;
            if(player == null){
                return;
            }
            render(event, new BlockPos(0, 60, 0));
        }

        public static void render(RenderLevelStageEvent event, BlockPos blockPos){
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

        public static void renderBoxSolid(Matrix4f matrix, VertexConsumer builder, BlockPos pos, float r, float g, float b, float alpha) {
            double x = pos.getX() - 0.001;
            double y = pos.getY() - 0.001;
            double z = pos.getZ() - 0.001;
            double xEnd = pos.getX() + 1.0015;
            double yEnd = pos.getY() + 1.0015;
            double zEnd = pos.getZ() + 1.0015;

            renderBoxSolid(matrix, builder, x, y, z, xEnd, yEnd, zEnd, r, g, b, alpha);
        }

        public static void renderBoxSolid(Matrix4f matrix, VertexConsumer builder, double x, double y, double z, double xEnd, double yEnd, double zEnd, float red, float green, float blue, float alpha) {
            //careful: mc want's it's vertices to be defined CCW - if you do it the other way around weird cullling issues will arise
            //CCW herby counts as if you were looking at it from the outside
            float startX = (float) x;
            float startY = (float) y;
            float startZ = (float) z;
            float endX = (float) xEnd;
            float endY = (float) yEnd;
            float endZ = (float) zEnd;

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
