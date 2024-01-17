package net.modzy.testmod.world.gen;

import net.minecraft.block.BlockState;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.SingleStateFeatureConfig;
import net.modzy.testmod.Testmod;
import net.modzy.testmod.util.ModTags;
import net.modzy.testmod.world.gen.feature.BoulderFeature;
import net.modzy.testmod.world.gen.feature.FrozenLakeFeature;
import net.modzy.testmod.world.gen.feature.DustTopLayerFeature;

public abstract class ModFeatures {

    public static final Feature<SingleStateFeatureConfig> DUST_TOP_LAYER = registerFeature("lunar_dust_top_layer",
            new DustTopLayerFeature(SingleStateFeatureConfig.CODEC));
    public static final Feature<SingleStateFeatureConfig> BOULDER = registerFeature("basalt_boulder",
            new BoulderFeature(SingleStateFeatureConfig.CODEC));
    public static final Feature<FrozenLakeFeature.Config> FROZEN_LAKE_FEATURE = registerFeature("frozen_lake_feature",
            new FrozenLakeFeature(FrozenLakeFeature.Config.CODEC));

    private static <C extends FeatureConfig, F extends Feature<C>> F registerFeature(String name, F feature) {
        return Registry.register(Registries.FEATURE, new Identifier(Testmod.MOD_ID, name), feature);
    }

    public static boolean isRegolith(BlockState state) {
        return state.isIn(ModTags.Blocks.REGOLITH);
    }

    public static void registerModFeatures() {
        Testmod.LOGGER.info("Registering Mod Features for "+Testmod.MOD_ID);
    }
}
