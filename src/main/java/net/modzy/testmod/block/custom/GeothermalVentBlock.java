package net.modzy.testmod.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class GeothermalVentBlock extends Block {

    protected static final VoxelShape COLLISION_SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 14.0, 16.0);

    public GeothermalVentBlock(Settings settings) {
        super(settings);
    }

    //region PARTICLES
    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        super.randomDisplayTick(state, world, pos, random);
        emitSmokeParticles(world, pos);
    }

    private void emitSmokeParticles(World world, BlockPos pos) {
        for (int i = 0; i < 5; i++) {
            double offsetX = world.random.nextGaussian() * 0.02D;
            double offsetY = world.random.nextGaussian() * 0.02D;
            double offsetZ = world.random.nextGaussian() * 0.02D;
            world.addImportantParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, true,
                    (double)pos.getX() + 0.5 + world.random.nextDouble() / 3.0 * (double)(world.random.nextBoolean() ? 1 : -1),
                    (double)pos.getY() + world.random.nextDouble() + world.random.nextDouble(),
                    (double)pos.getZ() + 0.5 + world.random.nextDouble() / 3.0 * (double)(world.random.nextBoolean() ? 1 : -1),
                    offsetX*0.25, offsetY+0.02, offsetZ*0.25);


        }
    }
    //endregion

    //region COLLISION
    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        if (!entity.bypassesSteppingEffects() && entity instanceof LivingEntity && !EnchantmentHelper.hasFrostWalker((LivingEntity)entity)) {
            entity.damage(world.getDamageSources().hotFloor(), 1.0f);
        }
        super.onSteppedOn(world, pos, state, entity);
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return COLLISION_SHAPE;
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getSidesShape(BlockState state, BlockView world, BlockPos pos) {
        return VoxelShapes.fullCube();
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getCameraCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.fullCube();
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
    }

    @Override
    @SuppressWarnings("deprecation")
    public float getAmbientOcclusionLightLevel(BlockState state, BlockView world, BlockPos pos) {
        return 1.0f;
    }
    //endregion
}
