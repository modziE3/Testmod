package net.modzy.testmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.client.*;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.modzy.testmod.block.ModBlocks;
import net.modzy.testmod.block.custom.AloeVeraBlock;
import net.modzy.testmod.item.ModItems;

import java.util.Optional;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        // Simples
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.TOPAZ_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ANORTHOSITE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.POLISHED_ANORTHOSITE);
        blockStateModelGenerator.registerSingleton(ModBlocks.MUD_HYDROTHERMAL_VENT, TexturedModel.CUBE_TOP);
        // Customs
        registerDustTypeBlock(blockStateModelGenerator, ModBlocks.MARTIAN_SILT_BLOCK, ModBlocks.MARTIAN_SILT);
        registerDustTypeBlock(blockStateModelGenerator, ModBlocks.LUNAR_SILT_BLOCK, ModBlocks.LUNAR_SILT);

        blockStateModelGenerator.registerCrop(ModBlocks.ALOE_VERA_CROP, AloeVeraBlock.AGE, 0,1,2,3,4,5,6,7);

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.TOPAZ, Models.GENERATED);
        itemModelGenerator.register(ModItems.TOPAZ_BOOTS, Models.GENERATED);
        itemModelGenerator.register(ModItems.TOPAZ_CHESTPLATE, Models.GENERATED);
        itemModelGenerator.register(ModItems.TOPAZ_HELMET, Models.GENERATED);
        itemModelGenerator.register(ModItems.TOPAZ_LEGGINGS, Models.GENERATED);

        itemModelGenerator.register(ModItems.NAUTIVERDE_SHELL, Models.GENERATED);
        itemModelGenerator.register(ModItems.ALOE_VERA_SUN_CREAM, Models.GENERATED);

        itemModelGenerator.register(ModItems.MIRANDA_SPAWN_EGG,
                new Model(Optional.of(new Identifier("item/template_spawn_egg")), Optional.empty()));
        itemModelGenerator.register(ModItems.NAUTIVERDE_SPAWN_EGG,
                new Model(Optional.of(new Identifier("item/template_spawn_egg")), Optional.empty()));

    }

    public void registerDustTypeBlock(BlockStateModelGenerator blockStateModelGenerator, Block allBlock, Block shortBlock) {
        TextureMap textureMap = TextureMap.all(shortBlock);
        Identifier identifier = Models.CUBE_ALL.upload(allBlock, textureMap, blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(shortBlock).coordinate(BlockStateVariantMap.create(Properties.LAYERS).register(height -> BlockStateVariant.create().put(VariantSettings.MODEL, height < 8 ? ModelIds.getBlockSubModelId(shortBlock, "_height" + height * 2) : identifier))));
        blockStateModelGenerator.registerParentedItemModel(shortBlock, ModelIds.getBlockSubModelId(shortBlock, "_height2"));
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(allBlock, identifier));
    }
}
