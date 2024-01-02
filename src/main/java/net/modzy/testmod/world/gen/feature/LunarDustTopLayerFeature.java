package net.modzy.testmod.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SnowyBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.Heightmap;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.modzy.testmod.block.ModBlocks;
import net.modzy.testmod.world.gen.ModFeatures;

import static net.minecraft.state.property.Properties.LAYERS;

public class LunarDustTopLayerFeature extends Feature<DefaultFeatureConfig> {
    public LunarDustTopLayerFeature(Codec<DefaultFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        StructureWorldAccess structureWorldAccess = context.getWorld();
        BlockPos blockPos = context.getOrigin();
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        BlockPos.Mutable mutable2 = new BlockPos.Mutable();
        for (int i = 0; i < 16; ++i) {
            for (int j = 0; j < 16; ++j) {
                int k = blockPos.getX() + i;
                int l = blockPos.getZ() + j;
                int m = structureWorldAccess.getTopY(Heightmap.Type.MOTION_BLOCKING, k, l);
                mutable.set(k, m, l);
                mutable2.set(mutable).move(Direction.DOWN, 1);
                Biome biome = structureWorldAccess.getBiome(mutable).value();
                BlockState blockState = structureWorldAccess.getBlockState(mutable2);
                if (!biome.canSetSnow(structureWorldAccess, mutable)) continue;
                if (ModFeatures.isRegolith(blockState)) {
                    BlockPos[] neighbors1 = {mutable.north(), mutable.east(), mutable.south(), mutable.west()};
                    BlockPos[] neighbors2 = {neighbors1[0].north(), neighbors1[1].east(), neighbors1[2].south(), neighbors1[3].west(), neighbors1[1].north(), neighbors1[2].east(), neighbors1[3].south(), neighbors1[0].west()};
                    BlockPos[] neighbors3 = {
                            mutable.add(3,0,0), mutable.add(2,0,1), mutable.add(1,0,2),
                            mutable.add(-3,0,0), mutable.add(-2,0,-1), mutable.add(-1,0,-2),
                            mutable.add(0,0,3), mutable.add(1,0,2), mutable.add(2,0,1),
                            mutable.add(0,0,-3), mutable.add(-1,0,-2), mutable.add(-2,0,-1)};
                    double layer = 1;
                    for (BlockPos neighbor : neighbors1) {
                        if (structureWorldAccess.getBlockState(neighbor) == ModBlocks.LUNAR_SILT_BLOCK.getDefaultState() || structureWorldAccess.getBlockState(neighbor) == Blocks.BASALT.getDefaultState()) {
                            layer = layer + 1.25;
                        }
                    }
                    for (BlockPos neighbor : neighbors2) {
                        if (structureWorldAccess.getBlockState(neighbor) == ModBlocks.LUNAR_SILT_BLOCK.getDefaultState() || structureWorldAccess.getBlockState(neighbor) == Blocks.BASALT.getDefaultState()) {
                            layer = layer + 0.9;
                        }
                    }
                    for (BlockPos neighbor : neighbors3) {
                        if (structureWorldAccess.getBlockState(neighbor) == ModBlocks.LUNAR_SILT_BLOCK.getDefaultState() || structureWorldAccess.getBlockState(neighbor) == Blocks.BASALT.getDefaultState()) {
                            layer = layer + 0.4;
                        }
                    }
                    structureWorldAccess.setBlockState(mutable, ModBlocks.LUNAR_SILT.getDefaultState().with(LAYERS, (int) Math.min(layer, 8)), Block.NOTIFY_LISTENERS);
                    if (!blockState.contains(SnowyBlock.SNOWY)) continue;
                    structureWorldAccess.setBlockState(mutable2, blockState.with(SnowyBlock.SNOWY, true), Block.NOTIFY_LISTENERS);
                }
            }
        }
        return true;
    }
}

