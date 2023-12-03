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

public class ModItems {

    //  Items ... Topaz added as example
    public static final Item TOPAZ = registerItem("topaz", new Item(new FabricItemSettings()));

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
