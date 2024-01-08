package net.modzy.testmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLootTableProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.DataWriter;
import net.minecraft.loot.LootTable;
import net.minecraft.util.Identifier;
import net.modzy.testmod.block.ModBlocks;
import net.modzy.testmod.entity.ModEntities;
import net.modzy.testmod.item.ModItems;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.TOPAZ_BLOCK);
        addDrop(ModBlocks.ANORTHOSITE);
        addDrop(ModBlocks.POLISHED_ANORTHOSITE);
        addDrop(ModBlocks.MARTIAN_SILT_BLOCK);
        addDrop(ModBlocks.MUD_HYDROTHERMAL_VENT, Blocks.MUD);

    }
}

