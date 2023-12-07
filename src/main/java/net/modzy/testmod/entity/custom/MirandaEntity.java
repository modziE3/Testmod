package net.modzy.testmod.entity.custom;

import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import net.modzy.testmod.entity.ModEntities;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class MirandaEntity extends AnimalEntity {
    public final AnimationState idle1AnimationState = new AnimationState();
    public final AnimationState idle2AnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
    public String gender;

    public MirandaEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
        this.gender = getRandomGender();
    }

    private String getRandomGender() {
        Random random = new Random();
        String[] genders = {"Male", "Female"};
        return genders[random.nextInt(genders.length)];
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        if (nbt.contains("Gender", 8)) {
            this.gender = nbt.getString("Gender");
        }
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putString("Gender", this.gender);
    }

    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
            Random randomNum = new Random();
            int result = randomNum.nextInt(2);
            if(result==0) {
                this.idle1AnimationState.start(this.age);
            } else {
                this.idle2AnimationState.start(this.age);
            }
        } else {
            --this.idleAnimationTimeout;
        }
    }

    @Override
    protected void updateLimbs(float posDelta) {
        float f = this.getPose() == EntityPose.STANDING ? Math.min(posDelta * 6.0f, 1.0f) : 0.0f;
        this.limbAnimator.updateLimbs(f, 0.2f);
    }

    @Override
    public void tick() {
        super.tick();
        if(this.getWorld().isClient()) {
            setupAnimationStates();
        }
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new AnimalMateGoal(this, 1.15D));
        this.goalSelector.add(2, new TemptGoal(this, 1.25D, Ingredient.ofItems(Items.BEETROOT_SEEDS), false));
        this.goalSelector.add(3, new FollowParentGoal(this, 1.15D));
        this.goalSelector.add(4, new WanderAroundFarGoal(this, 1D));
        this.goalSelector.add(5, new LookAtEntityGoal(this, PlayerEntity.class, 4f));
        this.goalSelector.add(6, new LookAroundGoal(this));

    }

    public static DefaultAttributeContainer.Builder createMirandaAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 4)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2f)
                .add(EntityAttributes.GENERIC_ARMOR, 0.4f);
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isOf(Items.BEETROOT_SEEDS);
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return ModEntities.MIRANDA.create(world);
    }
}
