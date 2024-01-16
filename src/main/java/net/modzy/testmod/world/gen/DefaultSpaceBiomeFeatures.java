package net.modzy.testmod.world.gen;

import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.gen.GenerationStep;
import net.modzy.testmod.world.ModConfiguredCarvers;
import net.modzy.testmod.world.ModPlacedFeatures;

public class DefaultSpaceBiomeFeatures {
    public static void addMoonRocks(GenerationSettings.LookupBackedBuilder builder) {
        builder.feature(GenerationStep.Feature.LOCAL_MODIFICATIONS, ModPlacedFeatures.BASALT_BOULDER_PLACED_KEY);
        builder.feature(GenerationStep.Feature.LOCAL_MODIFICATIONS, ModPlacedFeatures.ANORTHOSITE_BOULDER_PLACED_KEY);
    }

    public static void addMoonLandCarvers(GenerationSettings.LookupBackedBuilder builder) {
        builder.carver(GenerationStep.Carver.AIR, ModConfiguredCarvers.MOON_CAVE);
        builder.carver(GenerationStep.Carver.AIR, ModConfiguredCarvers.MOON_CAVE);
        builder.carver(GenerationStep.Carver.AIR, ModConfiguredCarvers.MOON_CAVE);
        builder.feature(GenerationStep.Feature.LAKES, ModPlacedFeatures.FROZEN_LAKE_PLACED_KEY);
    }

    public static void addMoonMineables(GenerationSettings.LookupBackedBuilder builder) {
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.ORE_MOON_ANORTHOSITE_KEY);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.ORE_MOON_TUFF_KEY);
    }

    public static void addLunarDustedTopLayer(GenerationSettings.LookupBackedBuilder builder) {
        builder.feature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, ModPlacedFeatures.LUNAR_DUST_LAYER_PLACED_KEY);
    }
}
