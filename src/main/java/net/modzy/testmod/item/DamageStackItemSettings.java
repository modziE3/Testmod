package net.modzy.testmod.item;

import net.minecraft.item.Item;

import java.lang.reflect.Field;

public class DamageStackItemSettings extends Item.Settings {

    @Override
    public Item.Settings maxCount(int maxCount) {
        try {
            Field field = Item.Settings.class.getDeclaredField("maxCount");
            field.setAccessible(true);
            field.set(this, maxCount);
        } catch (NoSuchFieldException | IllegalAccessException ignored) {}
        return this;
    }

    @Override
    public Item.Settings maxDamage(int maxDamage) {
        try {
            Field field = Item.Settings.class.getDeclaredField("maxDamage");
            field.setAccessible(true);
            field.set(this, maxDamage);
        } catch (NoSuchFieldException | IllegalAccessException ignored) {}
        return this;
    }
}

