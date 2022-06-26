package net.AbraXator.chakramod.renderer.model;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.AbraXator.chakramod.ChakraMod;
import net.AbraXator.chakramod.entity.MineralSnail;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class MineralSnailModel<Type extends MineralSnail> extends EntityModel<Type> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(ChakraMod.MOD_ID, "mineral_snail"), "main");
	private final ModelPart body;
	private final ModelPart crystals;
	private final ModelPart crystals2;
	private final ModelPart shell;
	private final ModelPart eyes;

	public MineralSnailModel(ModelPart root) {
		this.body = root.getChild("body");
		this.crystals = root.getChild("crystals");
		this.crystals2 = root.getChild("crystals2");
		this.shell = root.getChild("shell");
		this.eyes = root.getChild("eyes");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(49, 57).addBox(-1.0F, -2.0F, -2.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition crystals = partdefinition.addOrReplaceChild("crystals", CubeListBuilder.create().texOffs(21, 1).addBox(-4.0F, -4.0F, 0.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		    .texOffs(21, 1).addBox(-5.0F, -1.0F, 0.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		    .texOffs(21, 1).addBox(-4.0F, -1.0F, 1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		    .texOffs(21, 1).addBox(-4.0F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.25F, 18.0F, 3.4F, 0.0881F, -0.8018F, -0.6608F));

		PartDefinition crystals2 = partdefinition.addOrReplaceChild("crystals2", CubeListBuilder.create().texOffs(21, 7).addBox(-3.6F, -3.5F, -0.2F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		    .texOffs(21, 7).addBox(-4.6F, -0.5F, -0.2F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		    .texOffs(21, 7).addBox(-3.6F, -0.5F, 0.8F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		    .texOffs(21, 7).addBox(-3.6F, -1.5F, -1.2F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.45F, 22.75F, 4.0F, -1.0364F, -0.192F, 0.7307F));

		PartDefinition shell = partdefinition.addOrReplaceChild("shell", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.3054F, 0.0F, 0.0F));

		PartDefinition cube_r1 = shell.addOrReplaceChild("cube_r1", CubeListBuilder.create()
                .texOffs(0, 0).addBox(-2.0F, -3.0F, 1.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.5236F, 0.0F, 0.0F));

		PartDefinition eyes = partdefinition.addOrReplaceChild("eyes", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition cube_r2 = eyes.addOrReplaceChild("cube_r2", CubeListBuilder.create()
                .texOffs(60, 1).addBox(1.05F, -5.0F, 1.0F, 0.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(60, 1).addBox(-1.05F, -5.0F, 1.0F, 0.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.5236F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(Type entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		crystals.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		crystals2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		shell.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		eyes.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}