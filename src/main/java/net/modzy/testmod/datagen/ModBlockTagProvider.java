package net.modzy.testmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.modzy.testmod.block.ModBlocks;
import net.modzy.testmod.util.ModTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {

        getOrCreateTagBuilder(ModTags.Blocks.MOON_CARVER_REPLACEABLES)
                .add(Blocks.SMOOTH_BASALT)
                .add(Blocks.BASALT)
                .add(Blocks.WATER)
                .add(Blocks.TUFF)
                .add(ModBlocks.ANORTHOSITE)
                .add(ModBlocks.LUNAR_SILT_BLOCK)
                .add(ModBlocks.LUNAR_SILT);

        getOrCreateTagBuilder(ModTags.Blocks.REGOLITH)
                .add(ModBlocks.LUNAR_SILT_BLOCK)
                .add(ModBlocks.MARTIAN_SILT_BLOCK);

        getOrCreateTagBuilder(ModTags.Blocks.BASE_STONE_MOON)
                .add(Blocks.SMOOTH_BASALT)
                .add(Blocks.BASALT);

        //region Tool Types : PICK, AXE, HOE
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.TOPAZ_BLOCK)
                .add(ModBlocks.ANORTHOSITE)
                .add(ModBlocks.POLISHED_ANORTHOSITE);

        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE);

        getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE)
                .add(ModBlocks.MARTIAN_SILT_BLOCK)
                .add(ModBlocks.MARTIAN_SILT)
                .add(ModBlocks.MUD_HYDROTHERMAL_VENT);

        getOrCreateTagBuilder(BlockTags.HOE_MINEABLE);
        //endregion

        //region Tool mats : STONE<IRON<DIAMOND<NETHERITE
        //  Requires Stone tool
        getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL);

        //  Requires Iron tool
        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.TOPAZ_BLOCK);

        //  Requires Diamond tool
        getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL);

        //  Requires Netherite tool
        getOrCreateTagBuilder(TagKey.of(RegistryKeys.BLOCK,
                new Identifier("fabric", "needs_tool_level_4")));
        //endregion
    }
}
