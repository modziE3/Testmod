package net.modzy.testmod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;
import net.modzy.testmod.block.ModBlocks;
import net.modzy.testmod.client.render.dimension.ModDimensionEffects;
import net.modzy.testmod.entity.ModEntities;
import net.modzy.testmod.entity.client.*;
import net.modzy.testmod.networking.ModPackets;

public class TestmodClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModDimensionEffects.registerDimensionEffects();
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.MIRANDA, MirandaModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.MIRANDA, MirandaRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.NAUTIVERDE, NautiverdeModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.NAUTIVERDE, NautiverdeRenderer::new);
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ALOE_VERA_CROP, RenderLayer.getCutout());
        ModPackets.registerS2CPackets();

    }
}
