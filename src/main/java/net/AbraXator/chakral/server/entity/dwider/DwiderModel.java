package net.AbraXator.chakral.server.entity.dwider;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.AbraXator.chakral.Chakral;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class DwiderModel<T extends DwiderEntity> extends EntityModel<T> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Chakral.MOD_ID, "textures/entity/dwider.png"), "main");
	private final ModelPart all;
	private final ModelPart head;
	private final ModelPart leftLegs;
	//private final ModelPart leftFrontLegs;
	//private final ModelPart leftBackLegs;
	private final ModelPart rightLegs;
	//private final ModelPart rightFrontLegs;
	//private final ModelPart rightBackLegs;
	private final ModelPart tentacle;
	private final ModelPart tentacle1;
	private final ModelPart tentacle2;
	private final ModelPart tentacle3;
	private final ModelPart lantern;

	public DwiderModel(ModelPart root) {
		this.all = root.getChild("all");
		this.head = all.getChild("head");
		this.leftLegs = all.getChild("leftLegs");
		//this.leftFrontLegs = leftLegs.getChild("leftFrontLegs");
		//this.leftBackLegs = leftFrontLegs.getChild("leftBackLegs");
		this.rightLegs = all.getChild("rightLegs");
		//this.rightFrontLegs = rightLegs.getChild("rightFrontLegs");
		//this.rightBackLegs = rightLegs.getChild("rightBackLegs");
		this.tentacle = all.getChild("tentacle");
		this.tentacle1 = tentacle.getChild("tentacle1");
		this.tentacle2 = tentacle.getChild("tentacle2");
		this.tentacle3 = tentacle.getChild("tentacle3");
		this.lantern = tentacle.getChild("lantern");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshDefinition = new MeshDefinition();
		PartDefinition partDefinition = meshDefinition.getRoot();

		PartDefinition all = partDefinition.addOrReplaceChild("all", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -11.0F, -5.0F, 6.0F, 6.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 8.0F, 0.5F));

		PartDefinition leftLegs = all.addOrReplaceChild("leftLegs", CubeListBuilder.create().texOffs(15, 22).addBox(2.0F, -9.0F, -3.0F, 4.0F, 5.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(30, 34).addBox(3.0F, -4.0F, -3.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(30, 23).addBox(3.0F, -4.0F, 2.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, 3.5F, 0.5F));

		PartDefinition rightLegs = all.addOrReplaceChild("rightLegs", CubeListBuilder.create().texOffs(0, 17).addBox(-5.9749F, -1.3319F, 1.5F, 4.0F, 5.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(30, 17).addBox(-4.9749F, 3.6681F, 6.5F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 36).addBox(-4.9749F, 3.6681F, 1.5F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-4F, 3.5F, 0.5F));

		PartDefinition head = all.addOrReplaceChild("head", CubeListBuilder.create().texOffs(23, 0).addBox(-3.0F, -11.0F, -10.0F, 6.0F, 3.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(0, 2).addBox(3.0F, -8.0F, -10.0F, 0.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(15, 17).addBox(-3.0F, -8.0F, -10.0F, 6.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-3.0F, -8.0F, -10.0F, 0.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 7.625F, -8.125F));

		PartDefinition tentacle = all.addOrReplaceChild("tentacle", CubeListBuilder.create().texOffs(0, 30).addBox(-2.0F, -13.0F, -3.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.0003F, 14.0935F, -2.6163F));

		PartDefinition tentacle1 = tentacle.addOrReplaceChild("tentacle1", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, 11.0F, -3.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0, 12, -1));

		PartDefinition tentacle3 = tentacle.addOrReplaceChild("tentacle3", CubeListBuilder.create().texOffs(14, 34).addBox(-0.999F, -1.7F, -0.6F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -15.8362F, -4.9927F, 1.2217F, 0.0F, 0.0F));

		PartDefinition tentacle2 = tentacle.addOrReplaceChild("tentacle2", CubeListBuilder.create().texOffs(22, 34).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -14.1709F, -2.2748F, 0.5236F, 0.0F, 0.0F));

		PartDefinition lantern = tentacle.addOrReplaceChild("lantern", CubeListBuilder.create().texOffs(0, 0).addBox(-0.999F, 12.9276F, -10.5066F, 2, 4, 2, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(1F, 15.4221F, -12.0151F, 2, 2, 2, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshDefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		poseStack.pushPose();
		poseStack.scale(0.7F, 0.7F, 0.7F);
		all.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		poseStack.popPose();
	}
}