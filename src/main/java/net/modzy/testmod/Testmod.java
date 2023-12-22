package net.modzy.testmod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.modzy.testmod.block.ModBlocks;
import net.modzy.testmod.entity.ModEntities;
import net.modzy.testmod.entity.custom.MirandaEntity;
import net.modzy.testmod.entity.custom.NautiverdeEntity;
import net.modzy.testmod.item.ModItemGroups;
import net.modzy.testmod.item.ModItems;
import net.modzy.testmod.networking.ModPackets;
import net.modzy.testmod.sound.ModSounds;
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
		ModSounds.registerSounds();

		FabricDefaultAttributeRegistry.register(ModEntities.MIRANDA, MirandaEntity.createMirandaAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.NAUTIVERDE, NautiverdeEntity.createNautiverdeAttributes());

		ModPackets.registerC2SPackets();

	}
}