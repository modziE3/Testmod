package net.modzy.testmod.world;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.floatprovider.ConstantFloatProvider;
import net.minecraft.util.math.floatprovider.TrapezoidFloatProvider;
import net.minecraft.util.math.floatprovider.UniformFloatProvider;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.carver.*;
import net.minecraft.world.gen.heightprovider.UniformHeightProvider;
import net.modzy.testmod.Testmod;
import net.modzy.testmod.util.ModTags;

public class ModConfiguredCarvers {
    public static final RegistryKey<ConfiguredCarver<?>> MOON_CAVE = registerKey("moon_cave");
    public static final RegistryKey<ConfiguredCarver<?>> MOON_CAVE_EXTRA_UNDERGROUND = registerKey("moon_cave_extra_underground");
    public static final RegistryKey<ConfiguredCarver<?>> MOON_CANYON = registerKey("moon_canyon");

    private static RegistryKey<ConfiguredCarver<?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_CARVER, new Identifier(Testmod.MOD_ID, name));
    }

    public static void bootstrap(Registerable<ConfiguredCarver<?>> carverRegisterable) {
        RegistryEntryLookup<Block> registryEntryLookup = carverRegisterable.getRegistryLookup(RegistryKeys.BLOCK);
        carverRegisterable.register(MOON_CAVE, Carver.CAVE.configure(new CaveCarverConfig(0.15f, UniformHeightProvider.create(YOffset.aboveBottom(8), YOffset.fixed(180)), UniformFloatProvider.create(0.1f, 0.9f), YOffset.aboveBottom(8), CarverDebugConfig.create(false, Blocks.CRIMSON_BUTTON.getDefaultState()), registryEntryLookup.getOrThrow(ModTags.Blocks.MOON_CARVER_REPLACEABLES), UniformFloatProvider.create(0.7f, 1.4f), UniformFloatProvider.create(0.8f, 1.3f), UniformFloatProvider.create(-1.0f, -0.4f))));
        carverRegisterable.register(MOON_CAVE_EXTRA_UNDERGROUND, Carver.CAVE.configure(new CaveCarverConfig(0.07f, UniformHeightProvider.create(YOffset.aboveBottom(8), YOffset.fixed(47)), UniformFloatProvider.create(0.1f, 0.9f), YOffset.aboveBottom(8), CarverDebugConfig.create(false, Blocks.OAK_BUTTON.getDefaultState()), registryEntryLookup.getOrThrow(ModTags.Blocks.MOON_CARVER_REPLACEABLES), UniformFloatProvider.create(0.7f, 1.4f), UniformFloatProvider.create(0.8f, 1.3f), UniformFloatProvider.create(-1.0f, -0.4f))));
        carverRegisterable.register(MOON_CANYON, Carver.RAVINE.configure(new RavineCarverConfig(0.03f, UniformHeightProvider.create(YOffset.fixed(10), YOffset.fixed(67)), ConstantFloatProvider.create(3.0f), YOffset.aboveBottom(8), CarverDebugConfig.create(false, Blocks.WARPED_BUTTON.getDefaultState()), registryEntryLookup.getOrThrow(ModTags.Blocks.MOON_CARVER_REPLACEABLES), UniformFloatProvider.create(-0.125f, 0.125f), new RavineCarverConfig.Shape(UniformFloatProvider.create(0.75f, 1.0f), TrapezoidFloatProvider.create(0.0f, 6.0f, 2.0f), 3, UniformFloatProvider.create(0.75f, 1.0f), 1.0f, 0.0f))));
    }
}
