package net.modzy.testmod.entity.animation;

import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.render.entity.animation.AnimationHelper;
import net.minecraft.client.render.entity.animation.Keyframe;
import net.minecraft.client.render.entity.animation.Transformation;
import java.util.Random;

public class ModAnimations {

	public static Animation DUCK_IDLE;

	//region DUCK_WADDLE
	public static final Animation DUCK_WADDLE = Animation.Builder.create(1f).looping()
			.addBoneAnimation("LeftLeg",
					new Transformation(Transformation.Targets.ROTATE,
							new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.25f, AnimationHelper.createRotationalVector(0f, 0f, -27.5f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.5f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.75f, AnimationHelper.createRotationalVector(0f, 0f, 47.71f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(1f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR)))
			.addBoneAnimation("RightLeg",
					new Transformation(Transformation.Targets.ROTATE,
							new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.25f, AnimationHelper.createRotationalVector(0f, 0f, 50f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.5f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.75f, AnimationHelper.createRotationalVector(0f, 0f, -25f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(1f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR)))
			.addBoneAnimation("body",
					new Transformation(Transformation.Targets.TRANSLATE,
							new Keyframe(0f, AnimationHelper.createTranslationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.25f, AnimationHelper.createTranslationalVector(0f, 1f, 0f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.5f, AnimationHelper.createTranslationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.75f, AnimationHelper.createTranslationalVector(0f, 1f, 0f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(1f, AnimationHelper.createTranslationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR)))
			.addBoneAnimation("body",
					new Transformation(Transformation.Targets.ROTATE,
							new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.25f, AnimationHelper.createRotationalVector(5f, 0f, 0f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.75f, AnimationHelper.createRotationalVector(-5f, 0f, 0f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(1f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR)))
			.addBoneAnimation("Head",
					new Transformation(Transformation.Targets.ROTATE,
							new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.20834334f, AnimationHelper.createRotationalVector(0f, 0f, -5f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.4583433f, AnimationHelper.createRotationalVector(0f, 0f, 5f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.7083434f, AnimationHelper.createRotationalVector(0f, 0f, -5f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.9167666f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR))).build();
	//endregion

	//region DUCK_IDLE_1
	public static final Animation DUCK_IDLE_1 = Animation.Builder.create(4.676667f)
			.addBoneAnimation("body",
					new Transformation(Transformation.Targets.ROTATE,
							new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR)))
			.addBoneAnimation("Neck",
					new Transformation(Transformation.Targets.ROTATE,
							new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.5f, AnimationHelper.createRotationalVector(0f, 0f, -97.5f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.5834334f, AnimationHelper.createRotationalVector(0f, 0f, -110f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.6766666f, AnimationHelper.createRotationalVector(0f, 0f, -97.5f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.75f, AnimationHelper.createRotationalVector(0f, 0f, -110f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.8343334f, AnimationHelper.createRotationalVector(0f, 0f, -97.5f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.9167666f, AnimationHelper.createRotationalVector(0f, 0f, -110f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(1f, AnimationHelper.createRotationalVector(0f, 0f, -97.5f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(1.0834333f, AnimationHelper.createRotationalVector(0f, 0f, -110f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(1.1676667f, AnimationHelper.createRotationalVector(0f, 0f, -97.5f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(1.25f, AnimationHelper.createRotationalVector(0f, 0f, -110f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(1.3433333f, AnimationHelper.createRotationalVector(0f, 0f, -97.5f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(1.8343333f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR)))
			.addBoneAnimation("Head",
					new Transformation(Transformation.Targets.ROTATE,
							new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(2.5f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(2.7083435f, AnimationHelper.createRotationalVector(0f, -40f, 0f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(3.375f, AnimationHelper.createRotationalVector(0f, -40f, 0f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(3.6766665f, AnimationHelper.createRotationalVector(0f, 45f, 0f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(4.343333f, AnimationHelper.createRotationalVector(0f, 45f, 0f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(4.676667f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR)))
			.addBoneAnimation("Beak",
					new Transformation(Transformation.Targets.ROTATE,
							new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(1.5f, AnimationHelper.createRotationalVector(0f, 0f, -25f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(1.6766667f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(1.8343333f, AnimationHelper.createRotationalVector(0f, 0f, -25f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(2f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(2.1676665f, AnimationHelper.createRotationalVector(0f, 0f, -25f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(2.3433335f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(2.5f, AnimationHelper.createRotationalVector(0f, 0f, -25f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(2.6766665f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(2.8343335f, AnimationHelper.createRotationalVector(0f, 0f, -25f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(3f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(3.1676665f, AnimationHelper.createRotationalVector(0f, 0f, -25f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(3.3433335f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(3.5f, AnimationHelper.createRotationalVector(0f, 0f, -25f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(3.6766665f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(3.8343335f, AnimationHelper.createRotationalVector(0f, 0f, -25f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(4f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(4.167667f, AnimationHelper.createRotationalVector(0f, 0f, -25f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(4.343333f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(4.5f, AnimationHelper.createRotationalVector(0f, 0f, -25f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(4.676667f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR)))
			.addBoneAnimation("Base",
					new Transformation(Transformation.Targets.TRANSLATE,
							new Keyframe(0f, AnimationHelper.createTranslationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.25f, AnimationHelper.createTranslationalVector(0f, -1f, 0f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(1.5834333f, AnimationHelper.createTranslationalVector(0f, -1f, 0f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(1.8343333f, AnimationHelper.createTranslationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR))).build();
	//endregion

	//region DUCK_IDLE_2
	public static final Animation DUCK_IDLE_2 = Animation.Builder.create(1.7083433f)
			.addBoneAnimation("Neck",
					new Transformation(Transformation.Targets.ROTATE,
							new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.125f, AnimationHelper.createRotationalVector(0f, 0f, -20f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(1f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR)))
			.addBoneAnimation("Head",
					new Transformation(Transformation.Targets.ROTATE,
							new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.125f, AnimationHelper.createRotationalVector(0f, -15f, 0f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.25f, AnimationHelper.createRotationalVector(0f, 15f, 0f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.375f, AnimationHelper.createRotationalVector(0f, -15f, 0f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.5f, AnimationHelper.createRotationalVector(0f, 15f, 0f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.625f, AnimationHelper.createRotationalVector(0f, -15f, 0f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.75f, AnimationHelper.createRotationalVector(0f, 15f, 0f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.875f, AnimationHelper.createRotationalVector(0f, -15f, 0f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(1f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR)))
			.addBoneAnimation("Beak",
					new Transformation(Transformation.Targets.ROTATE,
							new Keyframe(0.9167666f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(1.2083433f, AnimationHelper.createRotationalVector(0f, 0f, -32.5f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(1.375f, AnimationHelper.createRotationalVector(0f, 0f, -32.5f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(1.7083433f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR)))
			.addBoneAnimation("RightWing",
					new Transformation(Transformation.Targets.ROTATE,
							new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.125f, AnimationHelper.createRotationalVector(147.12f, -23.67f, 2.94f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.25f, AnimationHelper.createRotationalVector(100.9f, -35.97f, -0.38f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.375f, AnimationHelper.createRotationalVector(147.12f, -23.67f, 2.94f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.5f, AnimationHelper.createRotationalVector(100.9f, -35.97f, -0.38f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.625f, AnimationHelper.createRotationalVector(147.12f, -23.67f, 2.94f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.75f, AnimationHelper.createRotationalVector(100.9f, -35.97f, -0.38f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(1.4167667f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR)))
			.addBoneAnimation("LeftWing",
					new Transformation(Transformation.Targets.ROTATE,
							new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.125f, AnimationHelper.createRotationalVector(-147.12f, 23.67f, 2.94f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.25f, AnimationHelper.createRotationalVector(-100.9f, 35.97f, 0.38f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.375f, AnimationHelper.createRotationalVector(-147.12f, 23.67f, 2.94f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.5f, AnimationHelper.createRotationalVector(-100.9f, 35.97f, 0.38f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.625f, AnimationHelper.createRotationalVector(-147.12f, 23.67f, 2.94f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(0.75f, AnimationHelper.createRotationalVector(-100.9f, 35.97f, 0.38f),
									Transformation.Interpolations.LINEAR),
							new Keyframe(1.4167667f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
									Transformation.Interpolations.LINEAR))).build();
	//endregion

	//  DuckIdleChooseRandom
	public static void AnimationRandomChoose() {
		Random randomNum = new Random();
		int result = randomNum.nextInt(2);
		if(result==0) {
			DUCK_IDLE = DUCK_IDLE_1;
		} else {
			DUCK_IDLE = DUCK_IDLE_2;
		}
	}

	public static void main() {
		System.out.println(DUCK_IDLE==null);
	}
}

