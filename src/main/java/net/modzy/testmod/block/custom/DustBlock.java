package net.modzy.testmod.block.custom;

import net.minecraft.block.*;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class DustBlock
            extends Block {
        public static final int MAX_LAYERS = 8;
        public static final IntProperty LAYERS = Properties.LAYERS;
        protected static final VoxelShape[] LAYERS_TO_SHAPE = new VoxelShape[]{VoxelShapes.empty(), Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 2.0, 16.0), Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 4.0, 16.0), Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 6.0, 16.0), Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 8.0, 16.0), Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 10.0, 16.0), Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 12.0, 16.0), Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 14.0, 16.0), Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 16.0)};
        public static final int field_31248 = 5;

        public DustBlock(AbstractBlock.Settings settings) {
            super(settings);
            this.setDefaultState((BlockState)((BlockState)this.stateManager.getDefaultState()).with(LAYERS, 1));
        }

        @Override
        public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
            switch (type) {
                case LAND: {
                    return state.get(LAYERS) < 5;
                }
                case WATER, AIR: {
                    return false;
                }
            }
            return false;
        }

        @Override
        public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
            return LAYERS_TO_SHAPE[state.get(LAYERS)];
        }

        @Override
        public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
            return LAYERS_TO_SHAPE[state.get(LAYERS) - 1];
        }

        @Override
        public VoxelShape getSidesShape(BlockState state, BlockView world, BlockPos pos) {
            return LAYERS_TO_SHAPE[state.get(LAYERS)];
        }

        @Override
        public VoxelShape getCameraCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
            return LAYERS_TO_SHAPE[state.get(LAYERS)];
        }

        @Override
        public boolean hasSidedTransparency(BlockState state) {
            return true;
        }

        @Override
        public float getAmbientOcclusionLightLevel(BlockState state, BlockView world, BlockPos pos) {
            return state.get(LAYERS) == 8 ? 0.2f : 1.0f;
        }

        @Override
        public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
            BlockState blockState = world.getBlockState(pos.down());
            if (blockState.isIn(BlockTags.SNOW_LAYER_CANNOT_SURVIVE_ON)) {
                return true;
            }
            if (blockState.isIn(BlockTags.SNOW_LAYER_CAN_SURVIVE_ON)) {
                return true;
            }
            return Block.isFaceFullSquare(blockState.getCollisionShape(world, pos.down()), Direction.UP) || blockState.isOf(this) && blockState.get(LAYERS) == 8;
        }

        @Override
        public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
            if (!state.canPlaceAt(world, pos)) {
                return Blocks.AIR.getDefaultState();
            }
            return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
        }

        @Override
        public boolean canReplace(BlockState state, ItemPlacementContext context) {
            int i = state.get(LAYERS);
            if (context.getStack().isOf(this.asItem()) && i < 8) {
                if (context.canReplaceExisting()) {
                    return context.getSide() == Direction.UP;
                }
                return true;
            }
            return i == 1;
        }

        @Override
        @Nullable
        public BlockState getPlacementState(ItemPlacementContext ctx) {
            BlockState blockState = ctx.getWorld().getBlockState(ctx.getBlockPos());
            if (blockState.isOf(this)) {
                int i = blockState.get(LAYERS);
                return (BlockState)blockState.with(LAYERS, Math.min(8, i + 1));
            }
            return super.getPlacementState(ctx);
        }

        @Override
        protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
            builder.add(LAYERS);
        }
    }
