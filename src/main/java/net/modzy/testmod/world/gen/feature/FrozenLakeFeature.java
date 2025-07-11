package net.modzy.testmod.world.gen.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.Heightmap;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.modzy.testmod.block.ModBlocks;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FrozenLakeFeature extends Feature<FrozenLakeFeature.Config> {
    public static final List<BlockState> WATERS = Arrays.stream(new BlockState[]{Blocks.WATER.getDefaultState(), Blocks.ICE.getDefaultState(), Blocks.PACKED_ICE.getDefaultState(), Blocks.BLUE_ICE.getDefaultState()}).toList();
    public static final List<Integer> LEVELS = Arrays.stream(new Integer[]{50,60,70,80,120,130,140,150}).toList();

    public FrozenLakeFeature(Codec<FrozenLakeFeature.Config> codec) {
        super(codec);
    }

    @Override
    public boolean generate(@NotNull FeatureContext<FrozenLakeFeature.Config> context) {
        Random random = context.getRandom();
        StructureWorldAccess structureWorldAccess = context.getWorld();
        BlockPos blockPos = context.getOrigin();
        int k = blockPos.getX()+8;
        int l = blockPos.getZ()+8;
        int m = structureWorldAccess.getTopY(Heightmap.Type.MOTION_BLOCKING, k, l); if (!LEVELS.contains(m)) return false;
        if (!isFlat(structureWorldAccess, k, m, l)) return false;
        FrozenLakeFeature.Config frozenLakeFeatureConfig = context.getConfig();
        double[] structureShape = getShape(random);
        double lakeSize = getLakeSize(random, 16, 12);
        constructTable(structureWorldAccess, new int[]{k, m, l}, structureShape, lakeSize, frozenLakeFeatureConfig.ground());
        constructWaterBowl(structureWorldAccess, new int[]{k, m, l}, random, structureShape, lakeSize, frozenLakeFeatureConfig.dust());
        return true;
    }

    public static double getLakeSize(@NotNull Random random, int max_size, int min_size) {
        return (random.nextDouble() * (max_size - min_size)) + min_size;
    }

    public static void constructWaterBowl(StructureWorldAccess structureWorldAccess, int @NotNull [] cent_pos, Random random, double[] shape, double size, BlockState dustState) {
        int X = cent_pos[0]; int Y = cent_pos[1]; int Z = cent_pos[2];
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        ArrayList<ArrayList<int[]>> lakeLayers = lakeLayerGetter(random, 10, 7, shape, size);
        for (ArrayList<int[]> layer : lakeLayers) {
            for (int[] block : layer) {
                mutable.set(X + block[0], Y, Z + block[1]);
                structureWorldAccess.setBlockState(mutable, getLakeTopLayer(random, cent_pos[1] - Y), Block.NOTIFY_NEIGHBORS);
                mutable.set(X + block[0], Y - 1, Z + block[1]);
                if (!WATERS.contains(structureWorldAccess.getBlockState(mutable))) {
                    structureWorldAccess.setBlockState(mutable, (cent_pos[1] - Y <= 1) ? Blocks.MUD.getDefaultState() : getMudTopLayer(random), Block.NOTIFY_NEIGHBORS);}
                mutable.set(X + block[0], Y - 2, Z + block[1]);
                if (!WATERS.contains(structureWorldAccess.getBlockState(mutable))) {
                    structureWorldAccess.setBlockState(mutable, Blocks.MUD.getDefaultState(), Block.NOTIFY_NEIGHBORS);}} Y--;}
        for (int[] block1 : getLayer(23, shape)) {
            int x = block1[0] + cent_pos[0];
            int z = block1[1] + cent_pos[2];
            int y = structureWorldAccess.getTopY(Heightmap.Type.MOTION_BLOCKING, x, z);
            DustTopLayerFeature.placeDust(structureWorldAccess, new int[]{x, y, z}, dustState);}
    }

    public static void constructTable(StructureWorldAccess structureWorldAccess, int @NotNull [] cent_pos, double[] shape, double size, BlockState groundState) {
        int X = cent_pos[0]; int Y = cent_pos[1]; int Z = cent_pos[2]; int offset = -3;
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        for (int i = 0 ; i <= 4 ; i++) {
            ArrayList<int[]> layer = blockArraySubtract(getLayer(23 - i, shape), getLayer(size, shape));
            for (int[] block : layer) {
                mutable.set(X + block[0], Y+offset, Z + block[1]);
                if (!WATERS.contains(structureWorldAccess.getBlockState(mutable)) && structureWorldAccess.getBlockState(mutable) != Blocks.MUD.getDefaultState()) {
                    structureWorldAccess.setBlockState(mutable, groundState,  Block.NOTIFY_NEIGHBORS);}}offset++;}
    }

    public static @NotNull ArrayList<int[]> blockArraySubtract(@NotNull ArrayList<int[]> array_1, ArrayList<int[]> array_2) {
        ArrayList<int[]> Array = new ArrayList<>();
        for (int[] pos : array_1) {
            if (!containsBlockPos(array_2, pos)) {
                Array.add(pos);}}
        return Array;
    }

    @Contract(pure = true)
    public static boolean containsBlockPos(@NotNull ArrayList<int[]> array, int[] pos) {
        for (int[] list_pos : array) {
            if (list_pos[0] == pos[0] & list_pos[1] == pos[1]) return true;
        } return false;
    }

    public static int randomSign(@NotNull Random random) {
        return random.nextBoolean() ? 1 : -1;
    }

    public static double lakeShapeExpression(double var1, double var2, double size, double hori, double sque, double vert, double squi) {
        return var1*var1 + var2*var2 - (size + hori*Math.sin(sque*var1) + vert*Math.sin(squi*var2));
    }

    public static BlockState getMudTopLayer(@NotNull Random random) {
        if (random.nextInt(12) == 1) return ModBlocks.MUD_HYDROTHERMAL_VENT.getDefaultState();
        else return Blocks.MUD.getDefaultState();
    }

    public static BlockState getLakeTopLayer(Random random, int layer) {
        return switch (layer) {
            case 0 -> Blocks.PACKED_ICE.getDefaultState();
            case 1 -> random.nextBoolean() ? Blocks.PACKED_ICE.getDefaultState() : Blocks.BLUE_ICE.getDefaultState();
            case 2 -> Blocks.BLUE_ICE.getDefaultState();
            case 3 -> random.nextBoolean() ? Blocks.BLUE_ICE.getDefaultState() : Blocks.WATER.getDefaultState();
            default -> Blocks.WATER.getDefaultState();
        };
    }

    public static double[] descendingNumbers(double start, int layers) {
        ArrayList<Double> numbers = new ArrayList<>();
        double step = start/layers;
        double current = start;
        while (current > 0) {
            numbers.add(current);
            current -= step;}
        return numbers.stream().mapToDouble(Double::doubleValue).toArray();
    }

    public static @NotNull ArrayList<int[]> getLayer(double size, double[] shape) {
        ArrayList<int[]> layer = new ArrayList<>();
        for (int x = -32 ; x <= 32 ; x++) {
            for (int y = -32 ; y <= 32 ; y++) {
                double x_val = (double) x / 4;
                double y_val = (double) y / 4;
                double value = lakeShapeExpression(x_val, y_val, size, shape[0], shape[2], shape[1], shape[3]);
                if (value <= 0) {
                    layer.add(new int[]{x, y});}}}
        return layer;
    }

    public static @NotNull ArrayList<ArrayList<int[]>> lakeLayerGetter(@NotNull Random random, int max_layer, int min_layer, double[] shape, double lakeSize) {
        double[] layerSizes = descendingNumbers(lakeSize, max_layer - min_layer == 0 ? 0 : random.nextInt(max_layer - min_layer) + min_layer);
        ArrayList<ArrayList<int[]>> Layers = new ArrayList<>();
        for (double size : layerSizes) {
            Layers.add(getLayer(size, shape));}
        return Layers;
    }

    public static double @NotNull [] getShape(@NotNull Random random) {
        double hori = (random.nextDouble()*6 + 2) * randomSign(random);
        double vert = (random.nextDouble()*6 + 2) * randomSign(random);
        double sque = (random.nextDouble() + 1) * randomSign(random);
        double squi = (random.nextDouble() + 1) * randomSign(random);
        return new double[]{hori,vert,sque,squi};
    }

    public static boolean isFlat(StructureWorldAccess structureWorldAccess, int cent_X, int cent_Y, int cent_Z) {
        for (int x = -24 ; x <= 24 ; x++){
            if (Math.abs(structureWorldAccess.getTopY(Heightmap.Type.MOTION_BLOCKING, x + cent_X, cent_Z) - cent_Y) >= 4) return false;}
        for (int z = -24 ; z <= 24 ; z++){
            if (Math.abs(structureWorldAccess.getTopY(Heightmap.Type.MOTION_BLOCKING, cent_X, z + cent_Z) - cent_Y) >= 4) return false;}
        return true;
    }

    public record Config(BlockState ground, BlockState dust) implements FeatureConfig {
        public static final Codec<Config> CODEC = RecordCodecBuilder.create((instance) -> instance.group(BlockState.CODEC.fieldOf("ground").forGetter(Config::ground), BlockState.CODEC.fieldOf("dust").forGetter(Config::dust)).apply(instance, Config::new));

        public Config(BlockState ground, BlockState dust) {
            this.ground = ground;
            this.dust = dust;
        }

        public BlockState ground() {
            return this.ground;
        }

        public BlockState dust() {
            return this.dust;
        }
    }
}