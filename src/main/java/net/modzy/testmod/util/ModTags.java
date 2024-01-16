package net.modzy.testmod.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.modzy.testmod.Testmod;

public class ModTags {

    public static class Blocks {

        public static final TagKey<Block> REGOLITH = createTag("regolith");
        public static final TagKey<Block> MOON_CARVER_REPLACEABLES = createTag("moon_carver_replaceables");
        public static final TagKey<Block> BASE_STONE_MOON = createTag("base_stone_moon");

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK,  new Identifier(Testmod.MOD_ID, name));
        }
    }

    public static class Items {

        public static final TagKey<Item> SHELLS = createTag("shells");


        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, new Identifier(Testmod.MOD_ID, name));
        }
    }

}
