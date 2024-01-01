package net.modzy.testmod.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
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
                if (!biome.canSetSnow(structureWorldAccess, mutable)) continue;
                structureWorldAccess.setBlockState(mutable, ModBlocks.LUNAR_SILT.getDefaultState(), Block.NOTIFY_LISTENERS);
                BlockState blockState = structureWorldAccess.getBlockState(mutable2);
                if (!blockState.contains(SnowyBlock.SNOWY)) continue;
                structureWorldAccess.setBlockState(mutable2, blockState.with(SnowyBlock.SNOWY, true), Block.NOTIFY_LISTENERS);
            }
        }
        return true;
    }
}

