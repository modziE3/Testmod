package net.modzy.testmod;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.modzy.testmod.block.ModBlocks;
import net.modzy.testmod.entity.ModEntities;
import net.modzy.testmod.entity.client.MirandaRenderer;
import net.modzy.testmod.entity.custom.MirandaEntity;
import net.modzy.testmod.item.ModItemGroups;
import net.modzy.testmod.item.ModItems;
import net.modzy.testmod.networking.ModPackets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Testmod implements ModInitializer {
	public static final String MOD_ID = "testmod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModEntities.registerModEntities();
		EntityRendererRegistry.register(ModEntities.MIRANDA, MirandaRenderer::new);
		FabricDefaultAttributeRegistry.register(ModEntities.MIRANDA, MirandaEntity.createMirandaAttributes());
		ModPackets.registerC2SPackets();
	}
}