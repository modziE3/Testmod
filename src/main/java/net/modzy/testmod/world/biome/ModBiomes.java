package net.modzy.testmod.world.biome;

import net.minecraft.client.sound.MusicType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import net.modzy.testmod.Testmod;
import net.modzy.testmod.entity.ModEntities;
import net.modzy.testmod.world.biome.surface.ModMaterialRules;
import net.modzy.testmod.world.gen.DefaultSpaceBiomeFeatures;

public class ModBiomes {
    public static final RegistryKey<Biome> LUNAR_ROCKY_GLADES = register("lunar_rocky_glades");


    public static RegistryKey<Biome> register(String name) {
        return RegistryKey.of(RegistryKeys.BIOME, new Identifier(Testmod.MOD_ID, name));
    }

    public static void bootstrap(Registerable<Biome> context) {
        context.register(LUNAR_ROCKY_GLADES, lunarRockyGlades(context));
    }

    public static void globalOverworldGeneration(GenerationSettings.LookupBackedBuilder builder) {
        DefaultBiomeFeatures.addLandCarvers(builder);
        DefaultBiomeFeatures.addAmethystGeodes(builder);
        DefaultBiomeFeatures.addDungeons(builder);
        DefaultBiomeFeatures.addMineables(builder);
        DefaultBiomeFeatures.addSprings(builder);
        DefaultBiomeFeatures.addFrozenTopLayer(builder);
    }

    public static void globalMoonGeneration(GenerationSettings.LookupBackedBuilder builder) {
        DefaultSpaceBiomeFeatures.addSpaceLandCarvers(builder);
        DefaultSpaceBiomeFeatures.addMoonMineables(builder);
        DefaultSpaceBiomeFeatures.addLunarDustedTopLayer(builder);
        DefaultSpaceBiomeFeatures.addMoonRocks(builder);
    }

    public static Biome lunarRockyGlades(Registerable<Biome> context) {
        SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();
        //spawnBuilder.spawn(SpawnGroup.UNDERGROUND_WATER_CREATURE, new SpawnSettings.SpawnEntry(ModEntities.NAUTIVERDE, 2, 3, 5));

        //spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.WOLF, 5, 4, 4));

        //DefaultBiomeFeatures.addFarmAnimals(spawnBuilder);
        //DefaultBiomeFeatures.addBatsAndMonsters(spawnBuilder);

        GenerationSettings.LookupBackedBuilder biomeBuilder =
                new GenerationSettings.LookupBackedBuilder(
                        context.getRegistryLookup(RegistryKeys.PLACED_FEATURE),
                        context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        //globalOverworldGeneration(biomeBuilder);
        globalMoonGeneration(biomeBuilder);

        //DefaultBiomeFeatures.addDefaultOres(biomeBuilder);
        //DefaultBiomeFeatures.addExtraGoldOre(biomeBuilder);

        //biomeBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.TREES_PLAINS);
        //DefaultBiomeFeatures.addForestFlowers(biomeBuilder);
        //DefaultBiomeFeatures.addLargeFerns(biomeBuilder);

        //DefaultBiomeFeatures.addDefaultMushrooms(biomeBuilder);
        //DefaultBiomeFeatures.addDefaultVegetation(biomeBuilder);

        return new Biome.Builder()
                .precipitation(false)
                .downfall(0)
                .temperature(0.0f)
                .generationSettings(biomeBuilder.build())
                .spawnSettings(spawnBuilder.build())
                .effects((new BiomeEffects.Builder())
                        .fogColor(0)
                        .waterColor(4159204)
                        .waterFogColor(329011)
                        .skyColor(0)
                        .moodSound(BiomeMoodSound.CAVE).build())
                .build();
    }
}
