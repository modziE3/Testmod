package net.modzy.testmod.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.Heightmap;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.modzy.testmod.block.ModBlocks;
import net.modzy.testmod.world.gen.ModFeatures;
import org.jetbrains.annotations.NotNull;

import static net.minecraft.state.property.Properties.LAYERS;

public class LunarDustTopLayerFeature extends Feature<DefaultFeatureConfig> {
    public LunarDustTopLayerFeature(Codec<DefaultFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean generate(@NotNull FeatureContext<DefaultFeatureConfig> context) {
        StructureWorldAccess structureWorldAccess = context.getWorld();
        BlockPos blockPos = context.getOrigin();
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        for (int i = 0; i < 16; ++i) {
            for (int j = 0; j < 16; ++j) {
                int k = blockPos.getX() + i;
                int l = blockPos.getZ() + j;
                int m = structureWorldAccess.getTopY(Heightmap.Type.MOTION_BLOCKING, k, l);
                mutable.set(k, m, l).move(Direction.DOWN, 1);
                BlockState blockState = structureWorldAccess.getBlockState(mutable);
                if (ModFeatures.isRegolith(blockState)) {
                    placeDust(structureWorldAccess, new int[]{k, m, l});}}}
        return true;
    }

    public static void placeDust(StructureWorldAccess structureWorldAccess, int[] pos) {
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        mutable.set(pos[0], pos[1], pos[2]);
        double layer = 1;
        for (BlockPos neighbor : getNeighbors(1, mutable)) {
            if (structureWorldAccess.getBlockState(neighbor) != Blocks.AIR.getDefaultState() && structureWorldAccess.getBlockState(neighbor).getBlock() != ModBlocks.LUNAR_SILT) {
                layer = layer + 1.25;}}
        for (BlockPos neighbor : getNeighbors(2, mutable)) {
            if (structureWorldAccess.getBlockState(neighbor) != Blocks.AIR.getDefaultState() && structureWorldAccess.getBlockState(neighbor).getBlock() != ModBlocks.LUNAR_SILT) {
                layer = layer + 0.9;}}
        for (BlockPos neighbor : getNeighbors(3, mutable)) {
            if (structureWorldAccess.getBlockState(neighbor) != Blocks.AIR.getDefaultState() && structureWorldAccess.getBlockState(neighbor).getBlock() != ModBlocks.LUNAR_SILT) {
                layer = layer + 0.6;}}
        structureWorldAccess.setBlockState(mutable, ModBlocks.LUNAR_SILT.getDefaultState().with(LAYERS, (int) Math.min(layer, 8)), Block.NOTIFY_LISTENERS);
    }

    public static BlockPos[] getNeighbors(int distance, BlockPos.Mutable mutable) {
        return switch (distance) {
            default -> new BlockPos[]{mutable.north(), mutable.east(), mutable.south(), mutable.west()};
            case 2 -> new BlockPos[]{mutable.add(2,0,0), mutable.add(1,0,1), mutable.add(0,0,2), mutable.add(-2,0,0), mutable.add(-1,0,-1), mutable.add(0,0,-2), mutable.add(1,0,-1), mutable.add(-1,0,1)};
            case 3 -> new BlockPos[]{mutable.add(3,0,0), mutable.add(2,0,1), mutable.add(1,0,2), mutable.add(-3,0,0), mutable.add(-2,0,-1), mutable.add(-1,0,-2), mutable.add(0,0,3), mutable.add(1,0,2), mutable.add(2,0,1), mutable.add(0,0,-3), mutable.add(-1,0,-2), mutable.add(-2,0,-1)};
        };
    }
}

