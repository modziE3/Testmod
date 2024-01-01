package net.modzy.testmod.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.modzy.testmod.world.ModPlacedFeatures;

public class ModWorldGeneration {
    public static void generateFeatures() {

        //LOCAL_MODIFICATIONS
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.PLAINS),
                GenerationStep.Feature.LOCAL_MODIFICATIONS, ModPlacedFeatures.BASALT_BOULDER_PLACED_KEY);
    }
}
