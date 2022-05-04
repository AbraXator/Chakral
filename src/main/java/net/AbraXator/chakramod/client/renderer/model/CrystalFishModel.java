package net.AbraXator.chakramod.client.renderer.model;// Made with Blockbench 4.2.4
// Exported for Minecraft version 1.17 - 1.18 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class CrystalFishModel<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "crystal_fish"), "main");
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart leftFin;
	private final ModelPart rightFin;
	private final ModelPart tail;

	public CrystalFishModel(ModelPart root) {
		this.body = root.getChild("body");
		this.head = root.getChild("head");
		this.leftFin = root.getChild("leftFin");
		this.rightFin = root.getChild("rightFin");
		this.tail = root.getChild("tail");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -8.0F, -4.0F, 2.0F, 4.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(13, 6).addBox(0.0F, -11.0F, -5.0F, 0.0F, 3.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(0.0F, -4.0F, -2.0F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition cube_r1 = body.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(12, 4).addBox(-0.5F, -0.75F, 0.5F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -6.5F, -3.0F, 0.0F, -0.2618F, -0.3054F));

		PartDefinition cube_r2 = body.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(7, 12).addBox(-1.25F, -1.25F, 0.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, -6.0F, 0.0F, 0.0F, 0.0F, 0.3491F));

		PartDefinition cube_r3 = body.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 12).addBox(-0.5F, -1.0F, -1.25F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -5.0F, 4.0F, -0.3491F, -0.1745F, 0.0F));

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(12, 0).addBox(-1.0F, -8.0F, -8.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(13, 16).addBox(-1.0F, -8.0F, -7.0F, 2.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition cube_r4 = head.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(20, 0).addBox(0.0F, -2.75F, -2.25F, 0.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -9.0F, -8.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition leftFin = partdefinition.addOrReplaceChild("leftFin", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition cube_r5 = leftFin.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(0, 23).addBox(-1.25F, -0.25F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.75F, -4.5F, -4.0F, 0.0F, 0.0F, 0.5236F));

		PartDefinition rightFin = partdefinition.addOrReplaceChild("rightFin", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition cube_r6 = rightFin.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(22, 22).addBox(-0.75F, -0.25F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.75F, -4.5F, -4.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition tail = partdefinition.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 12).addBox(0.0F, -8.0F, 3.0F, 0.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		leftFin.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		rightFin.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		tail.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}