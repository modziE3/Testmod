package net.modzy.testmod.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.Heightmap;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.ArrayList;
import java.util.Arrays;

public class FrozenLakeFeature extends Feature<DefaultFeatureConfig> {
    public FrozenLakeFeature(Codec<DefaultFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        Random random = context.getRandom();
        if (random.nextInt(50) != 1) return false;
        StructureWorldAccess structureWorldAccess = context.getWorld();
        BlockPos blockPos = context.getOrigin();
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        int k = blockPos.getX();
        int l = blockPos.getZ();
        int m = structureWorldAccess.getTopY(Heightmap.Type.MOTION_BLOCKING, k, l);
        ArrayList<ArrayList<int[]>> lakeLayers = lakeRandomLayerGetter(random);
        for (ArrayList<int[]> layer : lakeLayers) {
            for (int[] block : layer) {
                mutable.set(k + block[0], m, l + block[1]);
                structureWorldAccess.setBlockState(mutable, Blocks.WATER.getDefaultState(), Block.NOTIFY_LISTENERS);
            } m--;
        }
        return true;
    }

    public static int randomSign(Random random) {
        if (random.nextBoolean()) return 1; else return -1;
    }

    public static double lakeShapeExpression(double var1, double var2, double size, double hori, double sque, double vert, double squi) {
        return var1*var1 + var2*var2 - (size + hori*Math.sin(sque*var1) + vert*Math.sin(squi*var2));
    }

    public static double[] descendingNumbers(double start, int layers) {
        ArrayList<Double> numbers = new ArrayList<>();
        double step = start/layers;
        double current = start;
        while (current > 0) {
            numbers.add(current);
            current -= step;}
        numbers.add(0.0);
        return numbers.stream().mapToDouble(Double::doubleValue).toArray();
    }

    public static ArrayList<ArrayList<int[]>> lakeRandomLayerGetter(Random random) {
        double lakeSize = (random.nextDouble() * 10) + 20;
        double hori = (random.nextDouble()*6 + 2) * randomSign(random);
        double vert = (random.nextDouble()*6 + 2) * randomSign(random);
        double sque = (random.nextDouble() + 1) * randomSign(random);
        double squi = (random.nextDouble() + 1) * randomSign(random);
        double[] layerSizes = descendingNumbers(lakeSize, random.nextInt(5) + 5);
        ArrayList<ArrayList<int[]>> Layers = new ArrayList<>();
        for (double size : layerSizes) {
            ArrayList<int[]> layer = new ArrayList<>();
            for (int x = -32 ; x <= 32 ; x++) {
                for (int y = -32 ; y <= 32 ; y++) {
                    double x_val = (double) x / 4;
                    double y_val = (double) y / 4;
                    double value = lakeShapeExpression(x_val, y_val, size, hori, sque, vert, squi);
                    if (value <= 0) {
                        layer.add(new int[]{x, y});}}}
            Layers.add(layer);}
        return Layers;
    }
}
