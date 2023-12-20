package net.modzy.testmod.entity.client;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;
import net.modzy.testmod.entity.animation.ModAnimations;
import net.modzy.testmod.entity.custom.NautiverdeEntity;

public class NautiverdeModel<T extends NautiverdeEntity> extends SinglePartEntityModel<T> {
	private final ModelPart nautiverde;

	public NautiverdeModel(ModelPart root) {
		this.nautiverde = root.getChild("nautiverde");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData nautiverde = modelPartData.addChild("nautiverde", ModelPartBuilder.create(), ModelTransform.pivot(-2.0F, 18.0F, 1.0F));

		ModelPartData LeftTentacle = nautiverde.addChild("LeftTentacle", ModelPartBuilder.create(), ModelTransform.pivot(0.2629F, 6.4553F, 0.1039F));

		ModelPartData LTSeg1 = LeftTentacle.addChild("LTSeg1", ModelPartBuilder.create().uv(30, 32).cuboid(0.0F, -1.0F, 0.0F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.0F, -8.0F, 10.0F));

		ModelPartData LTSeg2 = LTSeg1.addChild("LTSeg2", ModelPartBuilder.create().uv(18, 27).cuboid(0.0F, -1.0F, 0.0F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F))
				.uv(8, 6).cuboid(0.0F, 0.0F, 1.0F, 0.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 3.0F));

		ModelPartData LTSeg3 = LTSeg2.addChild("LTSeg3", ModelPartBuilder.create().uv(18, 26).cuboid(0.0F, -1.0F, 0.0F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 3.0F));

		ModelPartData LTSeg4 = LTSeg3.addChild("LTSeg4", ModelPartBuilder.create().uv(18, 24).cuboid(0.0F, -1.0F, 0.0F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F))
				.uv(8, 8).cuboid(0.0F, -2.0F, 0.0F, 0.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 3.0F));

		ModelPartData LTSeg5 = LTSeg4.addChild("LTSeg5", ModelPartBuilder.create().uv(30, 31).cuboid(0.0F, -1.0F, 0.0F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F))
				.uv(18, 29).cuboid(0.0F, -2.0F, 1.0F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F))
				.uv(18, 28).cuboid(0.0F, -1.0F, 3.0F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F))
				.uv(0, 30).cuboid(0.0F, 0.0F, 1.0F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 3.0F));

		ModelPartData RightTentacle = nautiverde.addChild("RightTentacle", ModelPartBuilder.create(), ModelTransform.pivot(2.2629F, 6.4553F, 0.1039F));

		ModelPartData RTSeg1 = RightTentacle.addChild("RTSeg1", ModelPartBuilder.create().uv(29, 31).cuboid(0.0F, -1.0F, 0.0F, 0.0F, 1.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -8.0F, 10.0F));

		ModelPartData RTSeg2 = RTSeg1.addChild("RTSeg2", ModelPartBuilder.create().uv(18, 21).cuboid(0.0F, -1.0F, 0.0F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F))
				.uv(0, 0).cuboid(0.0F, -2.0F, 0.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 4.0F));

		ModelPartData RTSeg3 = RTSeg2.addChild("RTSeg3", ModelPartBuilder.create().uv(18, 22).cuboid(0.0F, -1.0F, 0.0F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 3.0F));

		ModelPartData RTSeg4 = RTSeg3.addChild("RTSeg4", ModelPartBuilder.create().uv(18, 23).cuboid(0.0F, -1.0F, 0.0F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F))
				.uv(7, 7).cuboid(0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 3.0F));

		ModelPartData RTSeg5 = RTSeg4.addChild("RTSeg5", ModelPartBuilder.create().uv(30, 33).cuboid(0.0F, -1.0F, 0.0F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F))
				.uv(0, 29).cuboid(0.0F, -2.0F, 1.0F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F))
				.uv(30, 4).cuboid(0.0F, 0.0F, 1.0F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F))
				.uv(0, 28).cuboid(0.0F, -1.0F, 3.0F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 3.0F));

		ModelPartData Shell = nautiverde.addChild("Shell", ModelPartBuilder.create().uv(0, 24).cuboid(3.0F, -10.0F, -11.0F, 1.0F, 10.0F, 10.0F, new Dilation(0.0F))
				.uv(46, 31).cuboid(3.0F, -11.0F, -11.0F, 1.0F, 1.0F, 10.0F, new Dilation(0.0F))
				.uv(12, 50).cuboid(-3.0F, -11.0F, -11.0F, 1.0F, 1.0F, 10.0F, new Dilation(0.0F))
				.uv(42, 50).cuboid(-3.0F, -10.0F, -12.0F, 1.0F, 10.0F, 1.0F, new Dilation(0.0F))
				.uv(22, 0).cuboid(2.0F, -6.0F, -13.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
				.uv(12, 24).cuboid(-2.0F, -6.0F, -13.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
				.uv(0, 44).cuboid(3.0F, 0.0F, -11.0F, 1.0F, 1.0F, 10.0F, new Dilation(0.0F))
				.uv(36, 13).cuboid(0.0F, -7.0F, -14.0F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F))
				.uv(34, 50).cuboid(3.0F, -10.0F, -1.0F, 1.0F, 10.0F, 1.0F, new Dilation(0.0F))
				.uv(0, 24).cuboid(-1.0F, -8.0F, -13.0F, 3.0F, 6.0F, 1.0F, new Dilation(0.0F))
				.uv(12, 29).cuboid(0.0F, -2.0F, -13.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
				.uv(46, 53).cuboid(-3.0F, -10.0F, -1.0F, 1.0F, 10.0F, 1.0F, new Dilation(0.0F))
				.uv(46, 42).cuboid(-3.0F, 0.0F, -11.0F, 1.0F, 1.0F, 10.0F, new Dilation(0.0F))
				.uv(24, 34).cuboid(4.0F, -10.0F, -8.0F, 1.0F, 1.0F, 4.0F, new Dilation(0.0F))
				.uv(6, 31).cuboid(0.0F, -10.0F, -13.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
				.uv(24, 50).cuboid(-4.0F, -10.0F, -8.0F, 1.0F, 1.0F, 4.0F, new Dilation(0.0F))
				.uv(0, 0).cuboid(-2.0F, -11.0F, -12.0F, 5.0F, 12.0F, 12.0F, new Dilation(0.0F))
				.uv(24, 39).cuboid(4.0F, -9.0F, -10.0F, 1.0F, 1.0F, 4.0F, new Dilation(0.0F))
				.uv(0, 49).cuboid(-4.0F, -9.0F, -6.0F, 1.0F, 1.0F, 4.0F, new Dilation(0.0F))
				.uv(46, 31).cuboid(-4.0F, -1.0F, -8.0F, 1.0F, 1.0F, 4.0F, new Dilation(0.0F))
				.uv(32, 2).cuboid(4.0F, -2.0F, -10.0F, 1.0F, 1.0F, 4.0F, new Dilation(0.0F))
				.uv(0, 44).cuboid(-4.0F, -2.0F, -6.0F, 1.0F, 1.0F, 4.0F, new Dilation(0.0F))
				.uv(34, 34).cuboid(4.0F, -9.0F, -6.0F, 1.0F, 1.0F, 4.0F, new Dilation(0.0F))
				.uv(34, 39).cuboid(-4.0F, -2.0F, -10.0F, 1.0F, 1.0F, 4.0F, new Dilation(0.0F))
				.uv(12, 34).cuboid(4.0F, -8.0F, -11.0F, 1.0F, 6.0F, 10.0F, new Dilation(0.0F))
				.uv(12, 29).cuboid(4.0F, -2.0F, -6.0F, 1.0F, 1.0F, 4.0F, new Dilation(0.0F))
				.uv(12, 24).cuboid(4.0F, -1.0F, -8.0F, 1.0F, 1.0F, 4.0F, new Dilation(0.0F))
				.uv(38, 50).cuboid(3.0F, -10.0F, -12.0F, 1.0F, 10.0F, 1.0F, new Dilation(0.0F))
				.uv(34, 34).cuboid(-4.0F, -8.0F, -11.0F, 1.0F, 6.0F, 10.0F, new Dilation(0.0F))
				.uv(46, 36).cuboid(-4.0F, -9.0F, -10.0F, 1.0F, 1.0F, 4.0F, new Dilation(0.0F))
				.uv(46, 22).cuboid(-2.0F, -12.0F, -10.0F, 5.0F, 1.0F, 8.0F, new Dilation(0.0F))
				.uv(36, 13).cuboid(-2.0F, 1.0F, -10.0F, 5.0F, 1.0F, 8.0F, new Dilation(0.0F))
				.uv(24, 14).cuboid(-3.0F, -10.0F, -11.0F, 1.0F, 10.0F, 10.0F, new Dilation(0.0F)), ModelTransform.pivot(0.2629F, 5.4553F, 6.1039F));

		ModelPartData Face = Shell.addChild("Face", ModelPartBuilder.create(), ModelTransform.pivot(0.7371F, 0.5447F, -0.1039F));

		ModelPartData cube_r1 = Face.addChild("cube_r1", ModelPartBuilder.create().uv(22, 0).cuboid(-2.0F, -1.4871F, -0.5839F, 3.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.2629F, -8.5447F, 3.1039F, -1.1781F, 0.0F, 0.0F));

		ModelPartData cube_r2 = Face.addChild("cube_r2", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -5.0F, -4.0F, 3.0F, 5.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.2629F, 0.4553F, 0.1039F, -1.0036F, 0.0F, 0.0F));

		ModelPartData cube_r3 = Face.addChild("cube_r3", ModelPartBuilder.create().uv(34, 0).cuboid(-3.0F, -4.6699F, -9.7679F, 5.0F, 5.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(0.2629F, -1.5447F, 5.1039F, -0.5672F, 0.0F, 0.0F));

		ModelPartData cube_r4 = Face.addChild("cube_r4", ModelPartBuilder.create().uv(0, 9).cuboid(-3.0F, -1.5624F, -0.6105F, 3.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(1.2629F, -8.5447F, 3.1039F, -0.5672F, 0.0F, 0.0F));

		ModelPartData cube_r5 = Face.addChild("cube_r5", ModelPartBuilder.create().uv(22, 6).cuboid(-2.0F, -0.3756F, 0.3301F, 3.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.2629F, -10.5447F, 3.1039F, -1.8326F, 0.0F, 0.0F));

		ModelPartData Grass = nautiverde.addChild("Grass", ModelPartBuilder.create(), ModelTransform.pivot(1.2629F, 6.4553F, 0.1039F));

		ModelPartData Shorts = Grass.addChild("Shorts", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -13.0F, 0.0F));

		ModelPartData Strand1 = Shorts.addChild("Strand1", ModelPartBuilder.create().uv(9, 1).cuboid(0.0F, -1.0F, 3.1039F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(8, 26).cuboid(0.0F, -1.0F, 1.1039F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(8, 10).cuboid(0.0F, -2.0F, 1.1039F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(24, 2).cuboid(0.0F, -1.0F, -1.8961F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(8, 25).cuboid(0.0F, -1.0F, 0.1039F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(8, 24).cuboid(0.0F, -2.0F, -0.8961F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, -1.1039F));

		ModelPartData Tips1 = Strand1.addChild("Tips1", ModelPartBuilder.create().uv(9, 0).cuboid(0.0F, -3.0F, -1.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(10, 10).cuboid(0.0F, -2.0F, -2.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(22, 2).cuboid(0.0F, -1.0F, -1.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -2.0F, 1.1039F));

		ModelPartData Strand2 = Shorts.addChild("Strand2", ModelPartBuilder.create().uv(7, 23).cuboid(0.0F, -2.0F, 1.1039F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(12, 26).cuboid(0.0F, -1.0F, 0.1039F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(14, 26).cuboid(0.0F, -1.0F, 2.1039F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(0, 1).cuboid(0.0F, -1.0F, -1.8961F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.0F, 0.0F, -1.1039F));

		ModelPartData Tips2 = Strand2.addChild("Tips2", ModelPartBuilder.create().uv(22, 5).cuboid(0.0F, -2.0F, -1.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(22, 6).cuboid(0.0F, -1.0F, -1.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -2.0F, 1.1039F));
		return TexturedModelData.of(modelData, 128, 128);
	}

	@Override
	public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
		this.getPart().traverse().forEach(ModelPart::resetTransform);
		this.setHeadAngles(headYaw, headPitch);

		this.animateMovement(ModAnimations.NAUTIVERDE_SWIM, limbAngle, limbDistance, 2f, 2.5f);
		this.updateAnimation(entity.hidingAnimationState, ModAnimations.NAUTIVERDE_HIDE, animationProgress, 1f);

	}

	private void setHeadAngles(float headYaw, float headPitch) {
		headYaw = MathHelper.clamp(headYaw, -30.0f, 30.0f);
		headPitch = MathHelper.clamp(headPitch, -25.0f, 45.0f);

		this.nautiverde.yaw = headYaw * 0.017453292F;
		this.nautiverde.pitch = headPitch * 0.017453292F;
	}


	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		nautiverde.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart getPart() {
		return nautiverde;
	}


}