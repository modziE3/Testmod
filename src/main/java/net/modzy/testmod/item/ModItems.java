package net.modzy.testmod.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.*;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.modzy.testmod.Testmod;
import net.modzy.testmod.block.ModBlocks;
import net.modzy.testmod.entity.ModEntities;

public class ModItems {

    //  Items ... Topaz added as example
    public static final Item TOPAZ = registerItem("topaz", new Item(new FabricItemSettings()));
    public static final Item NAUTIVERDE_SHELL = registerItem("nautiverde_shell", new Item(new FabricItemSettings()));
    public static final Item MIRANDA_SPAWN_EGG = registerItem("miranda_spawn_egg",
            new SpawnEggItem(ModEntities.MIRANDA, 0xB05716, 0x219358, new FabricItemSettings()));
    public static final Item NAUTIVERDE_SPAWN_EGG = registerItem("nautiverde_spawn_egg",
            new SpawnEggItem(ModEntities.NAUTIVERDE, 0xa8b02a, 0xd2b64f, new FabricItemSettings()));

    //  Tools ... Topaz pickaxe, axe and so on

    //  Armor ... Topaz boots, legs and so on
    public static final Item TOPAZ_HELMET = registerItem("topaz_helmet",
            new ArmorItem(ModArmorMaterials.TOPAZ, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item TOPAZ_CHESTPLATE = registerItem("topaz_chestplate",
            new ArmorItem(ModArmorMaterials.TOPAZ, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item TOPAZ_LEGGINGS = registerItem("topaz_leggings",
            new ArmorItem(ModArmorMaterials.TOPAZ, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
    public static final Item TOPAZ_BOOTS = registerItem("topaz_boots",
            new ArmorItem(ModArmorMaterials.TOPAZ, ArmorItem.Type.BOOTS, new FabricItemSettings()));

    public static final Item ALOE_VERA = registerItem("aloe_vera",
            new AliasedBlockItem(ModBlocks.ALOE_VERA_CROP, new FabricItemSettings().food(ModFoodComponents.ALOE_VERA)));
    public static final Item ALOE_VERA_SUN_CREAM = registerItem("aloe_sun_cream",
            new SunCreamItem(SunCreamMaterials.ALOEVERA, new DamageStackItemSettings().maxCount(16)));


    public static void addItemsToIngredientItemGroup(FabricItemGroupEntries entries) {
        entries.add(TOPAZ);
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(Testmod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        Testmod.LOGGER.info("Registering Mod Items for "+Testmod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientItemGroup);
    }
}
