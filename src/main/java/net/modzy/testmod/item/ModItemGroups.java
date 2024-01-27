package net.modzy.testmod.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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

                        entries.add(ModItems.TOPAZ);
                        entries.add(ModBlocks.TOPAZ_BLOCK);
                        entries.add(ModItems.TOPAZ_HELMET);
                        entries.add(ModItems.TOPAZ_CHESTPLATE);
                        entries.add(ModItems.TOPAZ_LEGGINGS);
                        entries.add(ModItems.TOPAZ_BOOTS);


                    }).build());

    public static final ItemGroup ANIMAL_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(Testmod.MOD_ID, "animal_group"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.animal_group"))
                    .icon(() -> new ItemStack(ModItems.NAUTIVERDE_SHELL)).entries((displayContext, entries) -> {

                        entries.add(ModItems.MIRANDA_SPAWN_EGG);
                        entries.add(ModItems.NAUTIVERDE_SPAWN_EGG);
                        entries.add(ModItems.NAUTIVERDE_SHELL);
                        entries.add(Items.NAUTILUS_SHELL);
                        entries.add(ModItems.ALOE_VERA);
                        entries.add(ModItems.ALOE_VERA_SUN_CREAM);

                    }).build());

    public static final ItemGroup TERRAIN_BLOCKS_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(Testmod.MOD_ID, "terrain_blocks_group"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.terrain_blocks_group"))
                    .icon(() -> new ItemStack(ModBlocks.POLISHED_ANORTHOSITE)).entries((displayContext, entries) -> {

                        entries.add(ModBlocks.LUNAR_SILT_BLOCK);
                        entries.add(ModBlocks.LUNAR_SILT);
                        entries.add(ModBlocks.MARTIAN_SILT_BLOCK);
                        entries.add(ModBlocks.MARTIAN_SILT);
                        entries.add(ModBlocks.MUD_HYDROTHERMAL_VENT);
                        entries.add(ModBlocks.ANORTHOSITE);
                        entries.add(ModBlocks.POLISHED_ANORTHOSITE);


                    }).build());



    public static void registerItemGroups() {
        Testmod.LOGGER.info("Registering Mod Item Groups for " + Testmod.MOD_ID);
    }
}
