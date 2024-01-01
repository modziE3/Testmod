package net.modzy.testmod.world.dimension;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.DimensionTypes;
import net.modzy.testmod.Testmod;

import java.util.OptionalLong;

public class ModDimensions {
    public static final RegistryKey<DimensionOptions> MOON_KEY = RegistryKey.of(RegistryKeys.DIMENSION,
            new Identifier(Testmod.MOD_ID, "moon"));
    public static final RegistryKey<World> MOON_LEVEL_KEY = RegistryKey.of(RegistryKeys.WORLD,
            new Identifier(Testmod.MOD_ID, "moon"));
    public static final RegistryKey<DimensionType> MOON_DIM_TYPE = RegistryKey.of(RegistryKeys.DIMENSION_TYPE,
            new Identifier(Testmod.MOD_ID, "moon_dim_type"));

    public static void bootstrapType(Registerable<DimensionType> context) {
        context.register(MOON_DIM_TYPE, new DimensionType(
                OptionalLong.of(36000), // fixedTime
                false, // hasSkylight
                false, // hasCeiling
                false, // ultraWarm
                true, // natural
                4.0, // coordinateScale
                true, // bedWorks
                false, // respawnAnchorWorks
                -64, // minY
                384, // height
                384, // logicalHeight
                BlockTags.INFINIBURN_OVERWORLD, // infiniburn
                DimensionTypes.OVERWORLD_ID, // effectsLocation
                0f, // ambientLight
                new DimensionType.MonsterSettings(false, false, UniformIntProvider.create(0, 0), 0)));
    }
}
