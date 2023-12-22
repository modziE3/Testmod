package net.modzy.testmod.entity.custom;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.control.AquaticMoveControl;
import net.minecraft.entity.ai.control.YawAdjustingLookControl;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.ai.pathing.SwimNavigation;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.mob.WaterCreatureEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.modzy.testmod.sound.ModSounds;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;
import java.util.List;
import java.util.function.Predicate;

public class NautiverdeEntity
        extends WaterCreatureEntity {

    private static final TrackedData<Integer> MOISTNESS = DataTracker.registerData(NautiverdeEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Boolean> ATTACKING = DataTracker.registerData(NautiverdeEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Boolean> HIDING = DataTracker.registerData(NautiverdeEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    static final TargetPredicate CLOSE_PLAYER_PREDICATE = TargetPredicate.createNonAttackable().setBaseMaxDistance(10.0).ignoreVisibility();
    public static final Predicate<ItemEntity> CAN_TAKE = item -> !item.cannotPickup() && item.isAlive() && item.isTouchingWater();
    public final AnimationState attackAnimationState = new AnimationState();
    public final AnimationState hidingAnimationState = new AnimationState();
    public int attackAnimationTimeout = 0;
    public boolean canStartHiding = true;
    public static final float MIN_HIDE_HEALTH = 10.0f;

    public NautiverdeEntity(EntityType<? extends NautiverdeEntity> entityType, World world) {
        super(entityType, world);
        this.moveControl = new AquaticMoveControl(this, 85, 40, 0.01f, 0.1f, false);
        this.lookControl = new YawAdjustingLookControl(this, 7);
        this.setCanPickUpLoot(true);
    }

    @Override
    @Nullable
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable NbtCompound entityNbt) {
        this.setAir(this.getMaxAir());
        this.setPitch(0.0f);
        return super.initialize(world, difficulty, spawnReason, entityData, entityNbt);
    }

    @Override
    protected void tickWaterBreathingAir(int air) {
    }

    @Override
    public void onPlayerCollision(PlayerEntity player) {
        this.setVelocity(this.getVelocity().multiply(0.5));
        super.onPlayerCollision(player);
    }

    private void setupAnimationStates() {
        if (this.isAttacking() && this.attackAnimationTimeout <= 0) {
            this.attackAnimationTimeout = 40;
            this.attackAnimationState.start(this.age);
        } else {
            --this.attackAnimationTimeout;
        }
        if (!this.isAttacking()) {
            this.attackAnimationState.stop();
        }

        if (this.isHiding() && this.canStartHiding) {
            this.hidingAnimationState.start(this.age);
            this.canStartHiding = false;
        }
        if (!this.isHiding() && !this.canStartHiding) {
            this.hidingAnimationState.stop();
            this.canStartHiding = true;
        }
    }

    @Override
    protected void updateLimbs(float posDelta) {
        float f = this.getPose() == EntityPose.STANDING ? Math.min(posDelta * 6.0f, 1.0f) : 0.0f;
        this.limbAnimator.updateLimbs(f, 0.2f);
    }

    public int getMoistness() {
        return this.dataTracker.get(MOISTNESS);
    }

    public void setMoistness(int moistness) {
        this.dataTracker.set(MOISTNESS, moistness);
    }

    public boolean isAttacking() {
        return this.dataTracker.get(ATTACKING);
    }

    public void setAttacking(boolean attacking) {
        this.dataTracker.set(ATTACKING, attacking);
    }

    public boolean isHiding() {
        return this.dataTracker.get(HIDING);
    }

    public void setHiding(boolean hiding) {
        this.dataTracker.set(HIDING, hiding);
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(ATTACKING, false);
        this.dataTracker.startTracking(HIDING, false);
        this.dataTracker.startTracking(MOISTNESS, 2400);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("Moistness", this.getMoistness());
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.setMoistness(nbt.getInt("Moistness"));
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new MoveIntoWaterGoal(this));
        this.goalSelector.add(1, new HideGoal(this));
        this.goalSelector.add(2, new AttackGoal(this, 2f, true));
        this.goalSelector.add(4, new NautiverdeSwimAroundGoal(this, 1.0, 10));
        this.goalSelector.add(4, new NautiverdeLookAroundGoal(this));
        this.targetSelector.add(1, new RevengeGoal(this));
    }

    public static DefaultAttributeContainer.Builder createNautiverdeAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 50.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 1.5f)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 5.0)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 3.0);
    }

    @Override
    protected EntityNavigation createNavigation(World world) {
        return new SwimNavigation(this, world);
    }

    @Override
    protected float getActiveEyeHeight(EntityPose pose, EntityDimensions dimensions) {
        return 0.4f;
    }

    @Override
    public int getMaxLookPitchChange() {
        return 1;
    }

    @Override
    public int getMaxHeadRotation() {
        return 1;
    }

    @Override
    protected boolean canStartRiding(Entity entity) {
        return true;
    }

    @Override
    public boolean canEquip(ItemStack stack) {
        EquipmentSlot equipmentSlot = MobEntity.getPreferredEquipmentSlot(stack);
        if (!this.getEquippedStack(equipmentSlot).isEmpty()) {
            return false;
        }
        return equipmentSlot == EquipmentSlot.MAINHAND && super.canEquip(stack);
    }

    @Override
    protected void loot(ItemEntity item) {
        ItemStack itemStack;
        if (this.getEquippedStack(EquipmentSlot.MAINHAND).isEmpty() && this.canPickupItem(itemStack = item.getStack())) {
            this.triggerItemPickedUpByEntityCriteria(item);
            this.equipStack(EquipmentSlot.MAINHAND, itemStack);
            this.updateDropChances(EquipmentSlot.MAINHAND);
            this.sendPickup(item, itemStack.getCount());
            item.discard();
        }
    }

    @Override
    public void tick() {
        super.tick();
        if(this.getWorld().isClient()) {
            setupAnimationStates();
        }
        if (this.isAiDisabled()) {
            this.setAir(this.getMaxAir());
            return;
        }
        if (this.isWet()) {
            this.setMoistness(2400);
        } else {
            this.setMoistness(this.getMoistness() - 1);
            if (this.getMoistness() <= 0) {
                this.damage(this.getDamageSources().dryOut(), 1.0f);
            }
            if (this.isOnGround()) {
                this.setVelocity(this.getVelocity().add((this.random.nextFloat() * 2.0f - 1.0f) * 0.2f, 0.5, (this.random.nextFloat() * 2.0f - 1.0f) * 0.2f));
                this.setYaw(this.random.nextFloat() * 360.0f);
                this.setOnGround(false);
                this.velocityDirty = true;
            }
        }
        if (this.getWorld().isClient && this.isTouchingWater() && this.getVelocity().lengthSquared() > 0.03) {
            Vec3d vec3d = this.getRotationVec(0.0f);
            float f = MathHelper.cos(this.getYaw() * ((float)Math.PI / 180)) * 0.3f;
            float g = MathHelper.sin(this.getYaw() * ((float)Math.PI / 180)) * 0.3f;
            float h = 1.2f - this.random.nextFloat() * 0.7f;
            for (int i = 0; i < 2; ++i) {
                this.getWorld().addParticle(ParticleTypes.DOLPHIN, this.getX() - vec3d.x * (double)h + (double)f, this.getY() - vec3d.y, this.getZ() - vec3d.z * (double)h + (double)g, 0.0, 0.0, 0.0);
                this.getWorld().addParticle(ParticleTypes.DOLPHIN, this.getX() - vec3d.x * (double)h - (double)f, this.getY() - vec3d.y, this.getZ() - vec3d.z * (double)h - (double)g, 0.0, 0.0, 0.0);
            }
        }
    }

    @Override
    public void handleStatus(byte status) {
        if (status == EntityStatuses.ADD_DOLPHIN_HAPPY_VILLAGER_PARTICLES) {
            this.spawnParticlesAround();
        } else {
            super.handleStatus(status);
        }
    }

    private void spawnParticlesAround() {
        for (int i = 0; i < 7; ++i) {
            double d = this.random.nextGaussian() * 0.01;
            double e = this.random.nextGaussian() * 0.01;
            double f = this.random.nextGaussian() * 0.01;
            this.getWorld().addParticle(ParticleTypes.HAPPY_VILLAGER, this.getParticleX(1.0), this.getRandomBodyY() + 0.2, this.getParticleZ(1.0), d, e, f);
        }
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        if (source.isIn(DamageTypeTags.IS_PROJECTILE)) {
            this.playSound(SoundEvents.ENTITY_ITEM_BREAK, 0.5f, 2.0f);
            return false;
        }
        if (source.getAttacker()!=null && source.getAttacker().isPlayer()) {
            PlayerEntity player = (PlayerEntity) source.getAttacker();
            ItemStack heldItem = player.getMainHandStack();
            if (this.getHealth() < MIN_HIDE_HEALTH && !heldItem.isEmpty() && heldItem.isIn(ItemTags.TOOLS)) {
                heldItem.damage(50, player, entity -> entity.sendToolBreakStatus(player.getActiveHand()));
                this.playSound(SoundEvents.ENTITY_ITEM_BREAK, 1, 1);
            } else if (!player.isCreative() && (heldItem.isEmpty() || !heldItem.isIn(ItemTags.TOOLS))) {
                this.playSound(SoundEvents.BLOCK_STONE_BREAK, 0.85f, 1.0f);
                return false;
            }
        }
        return super.damage(source, amount);
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_CAT_HURT;
    }

    @Override
    @Nullable
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_CAT_DEATH;
    }

    @Override
    @Nullable
    protected SoundEvent getAmbientSound() {
        return ModSounds.ENTITY_NAUTIVERDE_AMBIENT;
    }

    @Override
    protected SoundEvent getSplashSound() {
        return SoundEvents.ENTITY_DOLPHIN_SPLASH;
    }

    @Override
    protected SoundEvent getSwimSound() {
        return SoundEvents.ENTITY_DOLPHIN_SWIM;
    }

    @Override
    public void travel(Vec3d movementInput) {
        if (this.canMoveVoluntarily() && this.isTouchingWater()) {
            this.updateVelocity(this.getMovementSpeed(), movementInput);
            this.move(MovementType.SELF, this.getVelocity());
            this.setVelocity(this.getVelocity().multiply(0.9));
            if (this.getTarget() == null) {
                this.setVelocity(this.getVelocity().add(0.0, 0.0, 0.0));
            }
        } else {
            super.travel(movementInput);
        }
    }

    static class HideGoal
            extends Goal {
        private final NautiverdeEntity nautiverde;
        @Nullable
        private PlayerEntity closestPlayer;

        HideGoal(NautiverdeEntity nautiverde) {
            this.nautiverde = nautiverde;
        }

        @Override
        public boolean canStart() {
            this.closestPlayer = this.nautiverde.getWorld().getClosestPlayer(CLOSE_PLAYER_PREDICATE, this.nautiverde);
            if (this.closestPlayer == null) {
                return false;
            }
            return this.nautiverde.getHealth() <= MIN_HIDE_HEALTH;
        }

        @Override
        public boolean shouldContinue() {
            return this.closestPlayer != null && this.nautiverde.getHealth() <= MIN_HIDE_HEALTH && this.nautiverde.squaredDistanceTo(this.closestPlayer) < 2500.0;
        }

        @Override
        public void tick() {
            super.tick();
            this.nautiverde.setTarget(null);
        }

        @Override
        public void start() {
            super.start();
            this.nautiverde.setHiding(true);
            this.nautiverde.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 1000000, 3, false, false, false));
        }

        @Override
        public void stop() {
            this.nautiverde.setHiding(false);
            this.closestPlayer = null;
            this.nautiverde.clearStatusEffects();
            super.stop();
        }
    }

    static class AttackGoal
            extends MeleeAttackGoal {
        private final NautiverdeEntity nautiverde;
        private int startDelay = 20;
        private int nextDelay = 20;
        private boolean shouldCountTillNextAttack = false;

        public AttackGoal(PathAwareEntity mob, double speed, boolean pauseWhenMobIdle) {
            super(mob, speed, pauseWhenMobIdle);
            nautiverde = (NautiverdeEntity) mob;
            this.setControls(EnumSet.of(Goal.Control.MOVE, Goal.Control.LOOK));
        }

        @Override
        public boolean canStart() {
            return super.canStart() && !this.nautiverde.isHiding();
        }

        @Override
        public boolean shouldContinue() {
            return super.shouldContinue() && !this.nautiverde.isHiding();
        }

        @Override
        public void start() {
            super.start();
            startDelay = 20;
            nextDelay = 20;
        }

        @Override
        protected void attack(LivingEntity Enemy) {
            if (isEnemyWithinAttackDistance(Enemy)) {
                this.shouldCountTillNextAttack = true;

                if(isTimeToStartAttackAnimation()) {
                    this.nautiverde.setAttacking(true);
                }
                if(isTimeToAttack()) {
                    this.mob.getLookControl().lookAt(Enemy.getX(), Enemy.getEyeY(), Enemy.getZ());
                    performAttack(Enemy);
                    Vec3d direction = Enemy.getPos().subtract(this.nautiverde.getPos()).normalize();
                    this.nautiverde.setVelocity(direction.x, direction.y, direction.z);
                }
            } else {
                resetAttackCooldown();
                shouldCountTillNextAttack = false;
                this.nautiverde.setAttacking(false);
                this.nautiverde.attackAnimationTimeout = 0;
            }
        }

        private boolean isEnemyWithinAttackDistance(LivingEntity Enemy) {
            return this.nautiverde.distanceTo(Enemy) <= 5f;
        }

        protected void resetAttackCooldown() {
            this.nextDelay = this.getTickCount(startDelay * 2);
        }

        protected boolean isTimeToStartAttackAnimation() {
            return this.nextDelay <= startDelay;
        }

        protected boolean isTimeToAttack() {
            return this.nextDelay <= 0;
        }

        protected void performAttack(LivingEntity Enemy) {
            this.resetAttackCooldown();
            this.mob.swingHand(Hand.MAIN_HAND);
            this.mob.tryAttack(Enemy);
        }

        @Override
        public void tick() {
            super.tick();
            if(this.shouldCountTillNextAttack) {
                this.nextDelay = Math.max(this.nextDelay - 1, 0);
            }
        }

        @Override
        public void stop() {
            nautiverde.setAttacking(false);
            super.stop();
        }
    }

    static class NautiverdeSwimAroundGoal
            extends SwimAroundGoal {
        private final NautiverdeEntity nautiverde;

        public NautiverdeSwimAroundGoal(PathAwareEntity pathAwareEntity, double d, int i) {
            super(pathAwareEntity, d, i);
            this.nautiverde = (NautiverdeEntity) pathAwareEntity;
        }

        @Override
        public boolean canStart() {
            return super.canStart() && !this.nautiverde.isHiding();
        }

        @Override
        public boolean shouldContinue() {
            return super.shouldContinue() && !this.nautiverde.isHiding();
        }
    }

    static class NautiverdeLookAroundGoal
            extends LookAroundGoal {
        private final NautiverdeEntity nautiverde;

        public NautiverdeLookAroundGoal(MobEntity mob) {
            super(mob);
            this.nautiverde = (NautiverdeEntity) mob;
        }

        @Override
        public boolean canStart() {
            return super.canStart() && !this.nautiverde.isHiding();
        }

        @Override
        public boolean shouldContinue() {
            return super.shouldContinue() && !this.nautiverde.isHiding();
        }
    }
}
