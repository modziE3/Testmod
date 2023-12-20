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
import net.minecraft.entity.mob.GuardianEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.WaterCreatureEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

public class NautiverdeEntity
        extends WaterCreatureEntity {

    private static final TrackedData<BlockPos> TREASURE_POS = DataTracker.registerData(NautiverdeEntity.class, TrackedDataHandlerRegistry.BLOCK_POS);
    private static final TrackedData<Boolean> HAS_FISH = DataTracker.registerData(NautiverdeEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Integer> MOISTNESS = DataTracker.registerData(NautiverdeEntity.class, TrackedDataHandlerRegistry.INTEGER);
    static final TargetPredicate CLOSE_PLAYER_PREDICATE = TargetPredicate.createNonAttackable().setBaseMaxDistance(10.0).ignoreVisibility();
    public static final Predicate<ItemEntity> CAN_TAKE = item -> !item.cannotPickup() && item.isAlive() && item.isTouchingWater();
    public static final AnimationState hidingAnimationState = new AnimationState();
    public static final float MIN_HIDE_HEALTH = 10.0f;

    public NautiverdeEntity(EntityType<? extends NautiverdeEntity> entityType, World world) {
        super(entityType, world);
        this.moveControl = new AquaticMoveControl(this, 85, 10, 0.01f, 0.1f, true);
        this.lookControl = new YawAdjustingLookControl(this, 10);
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

    public void setTreasurePos(BlockPos treasurePos) {
        this.dataTracker.set(TREASURE_POS, treasurePos);
    }

    public BlockPos getTreasurePos() {
        return this.dataTracker.get(TREASURE_POS);
    }

    public boolean hasFish() {
        return this.dataTracker.get(HAS_FISH);
    }

    public void setHasFish(boolean hasFish) {
        this.dataTracker.set(HAS_FISH, hasFish);
    }

    public int getMoistness() {
        return this.dataTracker.get(MOISTNESS);
    }

    public void setMoistness(int moistness) {
        this.dataTracker.set(MOISTNESS, moistness);
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(TREASURE_POS, BlockPos.ORIGIN);
        this.dataTracker.startTracking(HAS_FISH, false);
        this.dataTracker.startTracking(MOISTNESS, 2400);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("TreasurePosX", this.getTreasurePos().getX());
        nbt.putInt("TreasurePosY", this.getTreasurePos().getY());
        nbt.putInt("TreasurePosZ", this.getTreasurePos().getZ());
        nbt.putBoolean("GotFish", this.hasFish());
        nbt.putInt("Moistness", this.getMoistness());
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        int i = nbt.getInt("TreasurePosX");
        int j = nbt.getInt("TreasurePosY");
        int k = nbt.getInt("TreasurePosZ");
        this.setTreasurePos(new BlockPos(i, j, k));
        super.readCustomDataFromNbt(nbt);
        this.setHasFish(nbt.getBoolean("GotFish"));
        this.setMoistness(nbt.getInt("Moistness"));
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new MoveIntoWaterGoal(this));
        this.goalSelector.add(2, new HideGoal(this));
        this.goalSelector.add(4, new SwimAroundGoal(this, 1.0, 10));
        this.goalSelector.add(4, new LookAroundGoal(this));
        this.goalSelector.add(6, new MeleeAttackGoal(this, 1.2f, true));
        this.goalSelector.add(8, new PlayWithItemsGoal());
        this.targetSelector.add(1, new RevengeGoal(this, PlayerEntity.class).setGroupRevenge(new Class[0]));
    }

    public static DefaultAttributeContainer.Builder createNautiverdeAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 40.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 1.2f)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 3.0);
    }

    @Override
    protected EntityNavigation createNavigation(World world) {
        return new SwimNavigation(this, world);
    }

    @Override
    public boolean tryAttack(Entity target) {
        boolean bl = target.damage(this.getDamageSources().mobAttack(this), (int)this.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE));
        if (bl) {
            this.applyDamageEffects(this, target);
            this.playSound(SoundEvents.ENTITY_DOLPHIN_ATTACK, 1.0f, 1.0f);
        }
        return bl;
    }

    @Override
    public int getMaxAir() {
        return 4800;
    }

    @Override
    protected int getNextAirOnLand(int air) {
        return this.getMaxAir();
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
        return SoundEvents.ENTITY_DOLPHIN_HURT;
    }

    @Override
    @Nullable
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_DOLPHIN_DEATH;
    }

    @Override
    @Nullable
    protected SoundEvent getAmbientSound() {
        return this.isTouchingWater() ? SoundEvents.ENTITY_DOLPHIN_AMBIENT_WATER : SoundEvents.ENTITY_DOLPHIN_AMBIENT;
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
                this.setVelocity(this.getVelocity().add(0.0, -0.005, 0.0));
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
            this.nautiverde.getLookControl();
            this.nautiverde.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 10, 3, false, false, false));
            this.nautiverde.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 10, 10000, false, false, false));
        }

        @Override
        public void start() {
            hidingAnimationState.start(this.nautiverde.age);
        }

        @Override
        public void stop() {
            hidingAnimationState.stop();
            this.closestPlayer = null;
            this.nautiverde.clearStatusEffects();
        }
    }

    class PlayWithItemsGoal
            extends Goal {
        private int nextPlayingTime;

        PlayWithItemsGoal() {
        }

        @Override
        public boolean canStart() {
            if (this.nextPlayingTime > NautiverdeEntity.this.age) {
                return false;
            }
            List<ItemEntity> list = NautiverdeEntity.this.getWorld().getEntitiesByClass(ItemEntity.class, NautiverdeEntity.this.getBoundingBox().expand(8.0, 8.0, 8.0), CAN_TAKE);
            return !list.isEmpty() || !NautiverdeEntity.this.getEquippedStack(EquipmentSlot.MAINHAND).isEmpty();
        }

        @Override
        public void start() {
            List<ItemEntity> list = NautiverdeEntity.this.getWorld().getEntitiesByClass(ItemEntity.class, NautiverdeEntity.this.getBoundingBox().expand(8.0, 8.0, 8.0), CAN_TAKE);
            if (!list.isEmpty()) {
                NautiverdeEntity.this.getNavigation().startMovingTo(list.get(0), 1.2f);
                NautiverdeEntity.this.playSound(SoundEvents.ENTITY_DOLPHIN_PLAY, 1.0f, 1.0f);
            }
            this.nextPlayingTime = 0;
        }

        @Override
        public void stop() {
            ItemStack itemStack = NautiverdeEntity.this.getEquippedStack(EquipmentSlot.MAINHAND);
            if (!itemStack.isEmpty()) {
                this.spitOutItem(itemStack);
                NautiverdeEntity.this.equipStack(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
                this.nextPlayingTime = NautiverdeEntity.this.age + NautiverdeEntity.this.random.nextInt(100);
            }
        }

        @Override
        public void tick() {
            List<ItemEntity> list = NautiverdeEntity.this.getWorld().getEntitiesByClass(ItemEntity.class, NautiverdeEntity.this.getBoundingBox().expand(8.0, 8.0, 8.0), CAN_TAKE);
            ItemStack itemStack = NautiverdeEntity.this.getEquippedStack(EquipmentSlot.MAINHAND);
            if (!itemStack.isEmpty()) {
                this.spitOutItem(itemStack);
                NautiverdeEntity.this.equipStack(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
            } else if (!list.isEmpty()) {
                NautiverdeEntity.this.getNavigation().startMovingTo(list.get(0), 1.2f);
            }
        }

        private void spitOutItem(ItemStack stack) {
            if (stack.isEmpty()) {
                return;
            }
            double d = NautiverdeEntity.this.getEyeY() - (double)0.3f;
            ItemEntity itemEntity = new ItemEntity(NautiverdeEntity.this.getWorld(), NautiverdeEntity.this.getX(), d, NautiverdeEntity.this.getZ(), stack);
            itemEntity.setPickupDelay(40);
            itemEntity.setThrower(NautiverdeEntity.this.getUuid());
            float f = 0.3f;
            float g = NautiverdeEntity.this.random.nextFloat() * ((float)Math.PI * 2);
            float h = 0.02f * NautiverdeEntity.this.random.nextFloat();
            itemEntity.setVelocity(0.3f * -MathHelper.sin(NautiverdeEntity.this.getYaw() * ((float)Math.PI / 180)) * MathHelper.cos(NautiverdeEntity.this.getPitch() * ((float)Math.PI / 180)) + MathHelper.cos(g) * h, 0.3f * MathHelper.sin(NautiverdeEntity.this.getPitch() * ((float)Math.PI / 180)) * 1.5f, 0.3f * MathHelper.cos(NautiverdeEntity.this.getYaw() * ((float)Math.PI / 180)) * MathHelper.cos(NautiverdeEntity.this.getPitch() * ((float)Math.PI / 180)) + MathHelper.sin(g) * h);
            NautiverdeEntity.this.getWorld().spawnEntity(itemEntity);
        }
    }
}
