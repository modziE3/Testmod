package net.modzy.testmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.VanillaRecipeProvider;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;
import net.modzy.testmod.block.ModBlocks;
import net.modzy.testmod.item.ModItems;
import net.modzy.testmod.util.ModTags;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(RecipeExporter exporter) {

        //  Topaz Block
        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.TOPAZ, RecipeCategory.DECORATIONS, ModBlocks.TOPAZ_BLOCK);

        //  Polished Anorthosite
        offerPolishedStoneRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_ANORTHOSITE, ModBlocks.ANORTHOSITE);

        //  Topaz Armor
        offerSimpleArmorRecipe(exporter, RecipeCategory.COMBAT, ModItems.TOPAZ_HELMET, "helmet", ModItems.TOPAZ);
        offerSimpleArmorRecipe(exporter, RecipeCategory.COMBAT, ModItems.TOPAZ_CHESTPLATE, "chestplate", ModItems.TOPAZ);
        offerSimpleArmorRecipe(exporter, RecipeCategory.COMBAT, ModItems.TOPAZ_LEGGINGS, "leggings", ModItems.TOPAZ);
        offerSimpleArmorRecipe(exporter, RecipeCategory.COMBAT, ModItems.TOPAZ_BOOTS, "boots", ModItems.TOPAZ);

        //  Conduit With Nautiverde Shell and Nautilus shell
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Items.CONDUIT)
                .input('X', ModTags.Items.SHELLS)
                .input('O', Items.HEART_OF_THE_SEA)
                .pattern("XXX")
                .pattern("XOX")
                .pattern("XXX")
                .criterion(hasItem(ModItems.NAUTIVERDE_SHELL), conditionsFromItem(ModItems.NAUTIVERDE_SHELL))
                .criterion(hasItem(Items.NAUTILUS_SHELL), conditionsFromItem(Items.NAUTILUS_SHELL))
                .criterion(hasItem(Items.HEART_OF_THE_SEA), conditionsFromItem(Items.HEART_OF_THE_SEA))
                .offerTo(exporter, new Identifier(getRecipeName(Items.CONDUIT)));


    }

    public static void offerSimpleArmorRecipe(RecipeExporter exporter, RecipeCategory category, ItemConvertible output, String armorType, ItemConvertible input) {
        switch (armorType) {
            case "helmet":
                ShapedRecipeJsonBuilder.create(category, output).input('X', input)
                        .pattern("XXX")
                        .pattern("X X")
                        .criterion(hasItem(input), conditionsFromItem(input))
                        .offerTo(exporter, new Identifier(getRecipeName(output)));
                break;
            case "chestplate":
                ShapedRecipeJsonBuilder.create(category, output).input('X', input)
                        .pattern("X X")
                        .pattern("XXX")
                        .pattern("XXX")
                        .criterion(hasItem(input), conditionsFromItem(input))
                        .offerTo(exporter, new Identifier(getRecipeName(output)));
                break;
            case "leggings":
                ShapedRecipeJsonBuilder.create(category, output).input('X', input)
                        .pattern("XXX")
                        .pattern("X X")
                        .pattern("X X")
                        .criterion(hasItem(input), conditionsFromItem(input))
                        .offerTo(exporter, new Identifier(getRecipeName(output)));
                break;
            case "boots":
                ShapedRecipeJsonBuilder.create(category, output).input('X', input)
                        .pattern("X X")
                        .pattern("X X")
                        .criterion(hasItem(input), conditionsFromItem(input))
                        .offerTo(exporter, new Identifier(getRecipeName(output)));
                break;
        }
    }
}
