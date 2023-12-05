package net.modzy.testmod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.modzy.testmod.entity.client.MirandaModel;
import net.modzy.testmod.entity.client.ModModelLayers;

public class TestmodClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.MIRANDA, MirandaModel::getTexturedModelData);

    }
}
