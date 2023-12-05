package net.modzy.testmod.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.modzy.testmod.Testmod;
import net.modzy.testmod.block.ModBlocks;

public class ModItemGroups {

    public static final ItemGroup TOPAZ_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(Testmod.MOD_ID, "topaz"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.topaz"))
                    .icon(() -> new ItemStack(ModItems.TOPAZ)).entries((displayContext, entries) -> {

                        //  Items
                        entries.add(ModItems.TOPAZ);

                        //  Blocks
                        entries.add(ModBlocks.TOPAZ_BLOCK);

                        //  Tools

                        //  Armor
                        entries.add(ModItems.TOPAZ_HELMET);
                        entries.add(ModItems.TOPAZ_CHESTPLATE);
                        entries.add(ModItems.TOPAZ_LEGGINGS);
                        entries.add(ModItems.TOPAZ_BOOTS);


                    }).build());



    public static void registerItemGroups() {
        Testmod.LOGGER.info("Registering Mod Item Groups for " + Testmod.MOD_ID);
    }
}
