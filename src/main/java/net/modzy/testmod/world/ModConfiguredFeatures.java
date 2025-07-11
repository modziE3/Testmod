package net.modzy.testmod.world;

import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.*;
import net.modzy.testmod.Testmod;
import net.modzy.testmod.block.ModBlocks;
import net.modzy.testmod.util.ModTags;
import net.modzy.testmod.world.gen.ModFeatures;
import net.modzy.testmod.world.gen.feature.FrozenLakeFeature;

public class ModConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> BASALT_BOULDER_KEY = registerKey("basalt_boulder");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ANORTHOSITE_BOULDER_KEY = registerKey("anorthosite_boulder");
    public static final RegistryKey<ConfiguredFeature<?, ?>> LUNAR_DUST_LAYER_KEY = registerKey("lunar_dust_layer");
    public static final RegistryKey<ConfiguredFeature<?, ?>> MARTIAN_DUST_LAYER_KEY = registerKey("martian_dust_layer");
    public static final RegistryKey<ConfiguredFeature<?, ?>> LUNAR_FROZEN_LAKE_KEY = registerKey("lunar_frozen_lake");
    public static final RegistryKey<ConfiguredFeature<?, ?>> MARTIAN_FROZEN_LAKE_KEY = registerKey("martian_frozen_lake");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_MOON_ANORTHOSITE = registerKey("ore_moon_anorthosite");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_MOON_TUFF = registerKey("ore_moon_tuff");

    public static void boostrap(Registerable<ConfiguredFeature<?, ?>> context) {

        TagMatchRuleTest moonStoneReplaceables = new TagMatchRuleTest(ModTags.Blocks.BASE_STONE_MOON);

        register(context, BASALT_BOULDER_KEY, ModFeatures.BOULDER, new SingleStateFeatureConfig(Blocks.BASALT.getDefaultState()));
        register(context, ANORTHOSITE_BOULDER_KEY, ModFeatures.BOULDER, new SingleStateFeatureConfig(ModBlocks.ANORTHOSITE.getDefaultState()));
        register(context, LUNAR_DUST_LAYER_KEY, ModFeatures.DUST_TOP_LAYER, new SingleStateFeatureConfig(ModBlocks.LUNAR_SILT.getDefaultState()));
        register(context, MARTIAN_DUST_LAYER_KEY, ModFeatures.DUST_TOP_LAYER, new SingleStateFeatureConfig(ModBlocks.MARTIAN_SILT.getDefaultState()));

        register(context, LUNAR_FROZEN_LAKE_KEY, ModFeatures.FROZEN_LAKE_FEATURE, new FrozenLakeFeature.Config(ModBlocks.LUNAR_SILT_BLOCK.getDefaultState(), ModBlocks.LUNAR_SILT.getDefaultState()));
        register(context, MARTIAN_FROZEN_LAKE_KEY, ModFeatures.FROZEN_LAKE_FEATURE, new FrozenLakeFeature.Config(ModBlocks.MARTIAN_SILT_BLOCK.getDefaultState(), ModBlocks.MARTIAN_SILT.getDefaultState()));

        register(context, ORE_MOON_ANORTHOSITE, Feature.ORE, new OreFeatureConfig(moonStoneReplaceables, ModBlocks.ANORTHOSITE.getDefaultState(), 64));
        register(context, ORE_MOON_TUFF, Feature.ORE, new OreFeatureConfig(moonStoneReplaceables, Blocks.TUFF.getDefaultState(), 64));

    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(Testmod.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context, RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }

}
