package net.modzy.testmod.world;

import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.*;
import net.modzy.testmod.Testmod;
import net.modzy.testmod.world.gen.ModFeatures;

public class ModConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> BASALT_BOULDER_KEY = registerKey("basalt_boulder");
    public static final RegistryKey<ConfiguredFeature<?, ?>> LUNAR_DUST_LAYER_KEY = registerKey("lunar_dust_layer");

    public static void boostrap(Registerable<ConfiguredFeature<?, ?>> context) {

        register(context, BASALT_BOULDER_KEY, ModFeatures.BASALT_BOULDER, new SingleStateFeatureConfig(Blocks.BASALT.getDefaultState()));
        register(context, LUNAR_DUST_LAYER_KEY, ModFeatures.LUNAR_DUST_TOP_LAYER, new DefaultFeatureConfig());

    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(Testmod.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context, RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }

}
