package net.modzy.testmod.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.enums.Instrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.modzy.testmod.Testmod;
import net.modzy.testmod.block.custom.AloeVeraBlock;
import net.modzy.testmod.block.custom.DustBlock;
import net.modzy.testmod.block.custom.GeothermalVentBlock;
import net.modzy.testmod.sound.ModSounds;

public class ModBlocks {
    public static final Block TOPAZ_BLOCK = registerBlock("topaz_block", new Block(FabricBlockSettings.copyOf(Blocks.DIAMOND_BLOCK).sounds(BlockSoundGroup.AMETHYST_BLOCK).mapColor(MapColor.GOLD)));
    public static final Block MARTIAN_SILT_BLOCK = registerBlock("martian_silt_block", new SandBlock(11098145, AbstractBlock.Settings.create().mapColor(MapColor.ORANGE).strength(0.5f).instrument(Instrument.SNARE).sounds(ModSounds.REGOLITH)));
    public static final Block MARTIAN_SILT = registerBlock("martian_silt", new DustBlock(AbstractBlock.Settings.create().mapColor(MapColor.ORANGE).replaceable().notSolid().ticksRandomly().strength(0.1f).sounds(ModSounds.REGOLITH).blockVision((state, world, pos) -> state.get(SnowBlock.LAYERS) >= 8).pistonBehavior(PistonBehavior.DESTROY)));
    public static final Block LUNAR_SILT_BLOCK = registerBlock("lunar_silt_block", new SandBlock(12171705, AbstractBlock.Settings.create().mapColor(MapColor.WHITE_GRAY).strength(0.5f).instrument(Instrument.SNARE).sounds(ModSounds.REGOLITH)));
    public static final Block LUNAR_SILT = registerBlock("lunar_silt", new DustBlock(AbstractBlock.Settings.create().mapColor(MapColor.WHITE_GRAY).replaceable().notSolid().ticksRandomly().strength(0.1f).sounds(ModSounds.REGOLITH).blockVision((state, world, pos) -> state.get(SnowBlock.LAYERS) >= 8).pistonBehavior(PistonBehavior.DESTROY)));

    public static final Block ANORTHOSITE = registerBlock("anorthosite", new Block(AbstractBlock.Settings.create().mapColor(MapColor.LICHEN_GREEN).instrument(Instrument.BASEDRUM).requiresTool().strength(1.5f, 6.0f).sounds(BlockSoundGroup.TUFF)));
    public static final Block POLISHED_ANORTHOSITE = registerBlock("polished_anorthosite", new Block(AbstractBlock.Settings.create().mapColor(MapColor.LICHEN_GREEN).instrument(Instrument.BASEDRUM).requiresTool().strength(1.5f, 6.0f)));


    public static final Block MUD_HYDROTHERMAL_VENT = registerBlock("mud_hydrothermal_vent", new GeothermalVentBlock(AbstractBlock.Settings.copy(Blocks.DIRT).mapColor(MapColor.TERRACOTTA_CYAN).allowsSpawning(Blocks::always).solidBlock(Blocks::always).blockVision(Blocks::always).suffocates(Blocks::always).sounds(BlockSoundGroup.MUD).luminance(state -> 9)));

    public static final Block ALOE_VERA_CROP = Registry.register(Registries.BLOCK, new Identifier(Testmod.MOD_ID, "aloe"), new AloeVeraBlock(FabricBlockSettings.copyOf(Blocks.WHEAT).sounds(BlockSoundGroup.AZALEA)));

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
