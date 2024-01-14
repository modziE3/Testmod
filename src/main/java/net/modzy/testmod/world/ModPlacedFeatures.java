package net.modzy.testmod.world;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.placementmodifier.*;
import net.modzy.testmod.Testmod;

import java.util.List;

public class ModPlacedFeatures {

    public static final RegistryKey<PlacedFeature> BASALT_BOULDER_PLACED_KEY = registerKey("basalt_boulder_placed");
    public static final RegistryKey<PlacedFeature> ANORTHOSITE_BOULDER_PLACED_KEY = registerKey("anorthosite_boulder_placed");
    public static final RegistryKey<PlacedFeature> LUNAR_DUST_LAYER_PLACED_KEY = registerKey("lunar_dust_layer_placed");
    public static final RegistryKey<PlacedFeature> FROZEN_LAKE_PLACED_KEY = registerKey("frozen_lake_placed");
    public static final RegistryKey<PlacedFeature> ORE_MOON_TUFF_KEY = registerKey("ore_moon_tuff");
    public static final RegistryKey<PlacedFeature> ORE_MOON_ANORTHOSITE_KEY = registerKey("ore_moon_anorthosite");

    public static void boostrap(Registerable<PlacedFeature> context) {
        var configuredFeatureRegistryEntryLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        register(context, BASALT_BOULDER_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.BASALT_BOULDER_KEY),
                CountPlacementModifier.of(1), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());
        register(context, ANORTHOSITE_BOULDER_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.ANORTHOSITE_BOULDER_KEY),
                CountPlacementModifier.of(1), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());

        register(context, LUNAR_DUST_LAYER_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.LUNAR_DUST_LAYER_KEY),
                BiomePlacementModifier.of());
        register(context, FROZEN_LAKE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.FROZEN_LAKE_KEY),
                BiomePlacementModifier.of());

        register(context, ORE_MOON_TUFF_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.ORE_MOON_TUFF),
                modifiersWithCount(4, HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(160))));
        register(context, ORE_MOON_ANORTHOSITE_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.ORE_MOON_ANORTHOSITE),
                modifiersWithCount(4, HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(160))));
    }


    public static List<PlacementModifier> modifiers(PlacementModifier countModifier, PlacementModifier heightModifier) {
        return List.of(countModifier, SquarePlacementModifier.of(), heightModifier, BiomePlacementModifier.of());
    }

    public static List<PlacementModifier> modifiersWithCount(int count, PlacementModifier heightModifier) {
        return modifiers(CountPlacementModifier.of(count), heightModifier);
    }

    public static List<PlacementModifier> modifiersWithRarity(int chance, PlacementModifier heightModifier) {
        return modifiers(RarityFilterPlacementModifier.of(chance), heightModifier);
    }



    public static RegistryKey<PlacedFeature> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(Testmod.MOD_ID, name));
    }

    private static void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key, RegistryEntry<ConfiguredFeature<?, ?>> configuration,
                                 PlacementModifier ... modifiers) {
        context.register(key, new PlacedFeature(configuration, List.of(modifiers)));
    }

    private static void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key, RegistryEntry<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
