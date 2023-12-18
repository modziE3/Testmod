package net.modzy.testmod.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.modzy.testmod.Testmod;
import net.modzy.testmod.block.custom.DustBlock;

public class ModBlocks {
    public static final Block TOPAZ_BLOCK = registerBlock("topaz_block", new Block(FabricBlockSettings.copyOf(Blocks.DIAMOND_BLOCK).sounds(BlockSoundGroup.AMETHYST_BLOCK).mapColor(MapColor.GOLD)));
    public static final Block MARTIAN_SILT_BLOCK = registerBlock("martian_silt_block", new Block(AbstractBlock.Settings.create().mapColor(MapColor.ORANGE).requiresTool().strength(0.2f).sounds(BlockSoundGroup.SAND)));
    public static final Block MARTIAN_SILT = registerBlock("martian_silt", new DustBlock(AbstractBlock.Settings.create().mapColor(MapColor.ORANGE).replaceable().notSolid().ticksRandomly().strength(0.1f).requiresTool().sounds(BlockSoundGroup.SAND).blockVision((state, world, pos) -> state.get(SnowBlock.LAYERS) >= 8).pistonBehavior(PistonBehavior.DESTROY)));
    public static final Block LUNAR_SILT_BLOCK = registerBlock("lunar_silt_block", new Block(AbstractBlock.Settings.create().mapColor(MapColor.WHITE_GRAY).requiresTool().strength(0.2f).sounds(BlockSoundGroup.SAND)));
    public static final Block LUNAR_SILT = registerBlock("lunar_silt", new DustBlock(AbstractBlock.Settings.create().mapColor(MapColor.WHITE_GRAY).replaceable().notSolid().ticksRandomly().strength(0.1f).requiresTool().sounds(BlockSoundGroup.SAND).blockVision((state, world, pos) -> state.get(SnowBlock.LAYERS) >= 8).pistonBehavior(PistonBehavior.DESTROY)));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(Testmod.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(Testmod.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerModBlocks() {
        Testmod.LOGGER.info("Registering ModBlocks for " + Testmod.MOD_ID)
        ;
    }
}
