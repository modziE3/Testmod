package net.modzy.testmod.client.render.dimension;

import net.fabricmc.fabric.api.client.rendering.v1.DimensionRenderingRegistry;
import net.modzy.testmod.world.dimension.ModDimensions;

public class ModDimensionEffects {
    public static void registerDimensionEffects() {
        DimensionRenderingRegistry.registerDimensionEffects(ModDimensions.MOON_LEVEL_KEY.getValue(), MoonDimensionEffects.INSTANCE);
        DimensionRenderingRegistry.registerCloudRenderer(ModDimensions.MOON_LEVEL_KEY, EmptyCloudRenderer.INSTANCE);
        DimensionRenderingRegistry.registerWeatherRenderer(ModDimensions.MOON_LEVEL_KEY, EmptyWeatherRenderer.INSTANCE);

    }
}
