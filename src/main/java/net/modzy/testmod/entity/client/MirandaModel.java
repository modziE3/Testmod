package net.modzy.testmod.entity.client;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.modzy.testmod.entity.animation.ModAnimations;
import net.modzy.testmod.entity.custom.MirandaEntity;

public class MirandaModel<T extends MirandaEntity> extends SinglePartEntityModel<T> {
	private final ModelPart miranda;
	private final ModelPart Head;

	public MirandaModel(ModelPart root) {
		this.miranda = root.getChild("miranda");
		this.Head = root.getChild("miranda").getChild("body").getChild("Base").getChild("Neck").getChild("Head");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData miranda = modelPartData.addChild("miranda", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData body = miranda.addChild("body", ModelPartBuilder.create(), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		ModelPartData Base = body.addChild("Base", ModelPartBuilder.create().uv(0, 7).cuboid(1.0F, -6.0F, -2.0F, 2.0F, 2.0F, 4.0F, new Dilation(0.0F))
				.uv(18, 4).cuboid(3.0F, -6.0F, -1.0F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F))
				.uv(0, 0).cuboid(-4.0F, -7.0F, -2.0F, 5.0F, 3.0F, 4.0F, new Dilation(0.0F))
				.uv(8, 18).cuboid(-1.0F, -4.0F, -3.0F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F))
				.uv(14, 19).cuboid(-1.0F, -4.0F, 1.0F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData Neck = Base.addChild("Neck", ModelPartBuilder.create().uv(16, 16).cuboid(-1.0F, -1.0F, -1.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
				.uv(20, 9).cuboid(-2.0F, 0.0F, -1.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
				.uv(0, 13).cuboid(-3.0F, -3.0F, -1.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F))
				.uv(16, 16).cuboid(-1.0F, 0.0F, -1.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-3.0F, -7.0F, 0.0F));

		ModelPartData Head = Neck.addChild("Head", ModelPartBuilder.create().uv(10, 11).cuboid(-3.0F, 0.0F, -1.0F, 4.0F, 1.0F, 2.0F, new Dilation(0.0F))
				.uv(0, 18).cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-2.0F, -4.0F, 0.0F));

		ModelPartData Beak = Head.addChild("Beak", ModelPartBuilder.create(), ModelTransform.pivot(-1.0F, 1.0F, 0.0F));

		ModelPartData cube_r1 = Beak.addChild("cube_r1", ModelPartBuilder.create().uv(10, 11).cuboid(-2.0F, 0.0F, -1.0F, 2.0F, 0.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.0436F));

		ModelPartData RightWing = Base.addChild("RightWing", ModelPartBuilder.create().uv(8, 14).cuboid(0.0F, 2.0F, 0.0F, 4.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(8, 9).cuboid(-1.0F, 1.0F, 0.0F, 6.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(8, 16).cuboid(-1.0F, 0.0F, 0.0F, 4.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(-3.0F, -7.0F, 2.0F));

		ModelPartData LeftWing = Base.addChild("LeftWing", ModelPartBuilder.create().uv(14, 2).cuboid(-1.0F, 0.0F, -1.0F, 4.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(8, 7).cuboid(-1.0F, 1.0F, -1.0F, 6.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(14, 0).cuboid(0.0F, 2.0F, -1.0F, 4.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(-3.0F, -7.0F, -2.0F));

		ModelPartData RightLeg = body.addChild("RightLeg", ModelPartBuilder.create().uv(5, 19).cuboid(-2.0F, 3.0F, -1.0F, 2.0F, 0.0F, 1.0F, new Dilation(0.0F))
				.uv(19, 12).cuboid(-2.0F, 3.0F, 1.0F, 2.0F, 0.0F, 1.0F, new Dilation(0.0F))
				.uv(5, 18).cuboid(-1.0F, 3.0F, 0.0F, 2.0F, 0.0F, 1.0F, new Dilation(0.0F))
				.uv(2, 0).cuboid(0.0F, 0.0F, 0.0F, 0.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -3.0F, 2.0F));

		ModelPartData LeftLeg = body.addChild("LeftLeg", ModelPartBuilder.create().uv(0, 0).cuboid(0.0F, 0.0F, -1.0F, 0.0F, 3.0F, 1.0F, new Dilation(0.0F))
				.uv(16, 14).cuboid(-2.0F, 3.0F, 0.0F, 2.0F, 0.0F, 1.0F, new Dilation(0.0F))
				.uv(5, 13).cuboid(-1.0F, 3.0F, -1.0F, 2.0F, 0.0F, 1.0F, new Dilation(0.0F))
				.uv(17, 15).cuboid(-2.0F, 3.0F, -2.0F, 2.0F, 0.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -3.0F, -2.0F));
		return TexturedModelData.of(modelData, 32, 32);
	}
	@Override
	public void setAngles(MirandaEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.getPart().traverse().forEach(ModelPart::resetTransform);
		this.setHeadAngles(netHeadYaw, headPitch);

		this.animateMovement(ModAnimations.DUCK_WADDLE, limbSwing, limbSwingAmount, 2f, 2.5f);
		this.updateAnimation(entity.idleAnimationState, ModAnimations.DUCK_IDLE_2, ageInTicks, 1f);
	}

	private void setHeadAngles(float headYaw, float headPitch) {
		headYaw = MathHelper.clamp(headYaw, -30.0f, 30.0f);
		headPitch = MathHelper.clamp(headPitch, -25.0f, 45.0f);

		this.Head.yaw = headYaw * 0.017453292F;
		this.Head.pitch = headPitch * 0.017453292F;
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		miranda.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart getPart() {
		return miranda;
	}
}