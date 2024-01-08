package net.modzy.testmod.world.gen;

import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.MiscPlacedFeatures;
import net.modzy.testmod.world.ModPlacedFeatures;

public class DefaultSpaceBiomeFeatures {
    public static void addBasaltRocks(GenerationSettings.LookupBackedBuilder builder) {
        builder.feature(GenerationStep.Feature.LOCAL_MODIFICATIONS, ModPlacedFeatures.BASALT_BOULDER_PLACED_KEY);
    }

    public static void addAnorthositeRocks(GenerationSettings.LookupBackedBuilder builder) {
        builder.feature(GenerationStep.Feature.LOCAL_MODIFICATIONS, ModPlacedFeatures.ANORTHOSITE_BOULDER_PLACED_KEY);
    }

    public static void addLunarDustedTopLayer(GenerationSettings.LookupBackedBuilder builder) {
        builder.feature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, ModPlacedFeatures.LUNAR_DUST_LAYER_PLACED_KEY);
    }
}
