package net.AbraXator.chakral.server.entity.mineralsnail;
// Made with Blockbench 4.5.2
// Exported for Minecraft version 1.17 - 1.18 with Mojang mappings
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class MineralSnailModel extends HierarchicalModel<MineralSnail> {
	private final ModelPart root;
	private final ModelPart body;
	private final ModelPart eyeRight;
	private final ModelPart eyeLeft;
	private final ModelPart shell;
	private final ModelPart crystal;

	public MineralSnailModel(ModelPart part) {
		this.root = part;
		this.body = part.getChild("body");
		this.eyeRight = part.getChild("eyeRight");
		this.eyeLeft = part.getChild("eyeLeft");
		this.shell = part.getChild("shell");
		this.crystal = part.getChild("crystal");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition mesh = new MeshDefinition();
		PartDefinition root = mesh.getRoot();

		PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(49, 57).addBox(-2.0F, -2.0F, -4.0F, 4.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));
		PartDefinition shell = root.addOrReplaceChild("shell", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -7.0F, -1.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));
		PartDefinition eyeRight = body.addOrReplaceChild("eyeRight", CubeListBuilder.create().texOffs(60, 1).addBox(0.0F, -1.5F, -0.5F, 0.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.75F, -3.0F, -3.5F, 0.0F, 0.0F, -0.1745F));
		PartDefinition eyeLeft = body.addOrReplaceChild("eyeLeft", CubeListBuilder.create().texOffs(60, 1).addBox(0.0F, -1.5F, -0.5F, 0.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.75F, -3.0F, -3.5F, 0.0F, 0.0F, 0.1745F));
		PartDefinition cube_r3 = body.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -2.0F, -4.0F, 0.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)).addBox(0.0F, -2.0F, -4.0F, 0.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -9.0F, 2.0F, 0.0F, 0.7854F, 0.0F));
		return LayerDefinition.create(mesh, 32, 32);
	}

	@Override
	public ModelPart root() {
		return this.root;
	}

	@Override
	public void setupAnim(MineralSnail entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}
}