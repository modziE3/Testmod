package net.modzy.testmod.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.SingleStateFeatureConfig;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.modzy.testmod.world.gen.ModFeatures;

public class BasaltBoulderFeature
        extends Feature<SingleStateFeatureConfig> {
    public BasaltBoulderFeature(Codec<SingleStateFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean generate(FeatureContext<SingleStateFeatureConfig> context) {
        BlockPos blockPos = context.getOrigin();
        StructureWorldAccess structureWorldAccess = context.getWorld();
        Random random = context.getRandom();
        SingleStateFeatureConfig singleStateFeatureConfig = context.getConfig();
        while (blockPos.getY() > structureWorldAccess.getBottomY() + 3 && (structureWorldAccess.isAir(blockPos.down()) || !ModFeatures.isRegolith(structureWorldAccess.getBlockState(blockPos.down())))) {
            blockPos = blockPos.down();
        }
        if (blockPos.getY() <= structureWorldAccess.getBottomY() + 3) {
            return false;
        }
        for (int i = 0; i < 3; ++i) {
            int j = random.nextInt(3);
            int k = random.nextInt(3);
            int l = random.nextInt(3);
            float f = (float)(j + k + l) * 0.333f + 0.5f;
            for (BlockPos blockPos2 : BlockPos.iterate(blockPos.add(-j, -k, -l), blockPos.add(j, k, l))) {
                if (!(blockPos2.getSquaredDistance(blockPos) <= (double)(f * f))) continue;
                structureWorldAccess.setBlockState(blockPos2, singleStateFeatureConfig.state, Block.NOTIFY_ALL);
            }
            blockPos = blockPos.add(-1 + random.nextInt(2), -random.nextInt(2), -1 + random.nextInt(2));
        }
        return true;
    }
}
