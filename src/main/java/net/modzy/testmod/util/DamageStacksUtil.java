package net.modzy.testmod.util;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

public class DamageStacksUtil {
    public static boolean isSameItemDamageStack(ItemStack stack, ItemStack otherStack) {
        Item item = stack.getItem();
        Item otherItem = otherStack.getItem();
        return isDamageStack(stack) && isDamageStack(otherStack) && item == otherItem;
    }

    public static boolean isDamageStack(ItemStack stack) {
        return isDamageable(stack) && isStackable(stack);
    }

    public static boolean isDamageable(ItemStack stack) {
        if (stack.isEmpty() || stack.getItem().getMaxDamage() <= 0) {
            return false;
        }
        NbtCompound nbtCompound = stack.getNbt();
        return nbtCompound == null || !nbtCompound.getBoolean("Unbreakable");
    }

    public static boolean isStackable(ItemStack stack) {
        return stack.getMaxCount() > 1;
    }

    public static ItemStack getStackWithDamage(ItemStack stack, int damage) {
        ItemStack newStack = stack.copy();
        if (isDamageStack(newStack)) {
            newStack.setDamage(damage);
        } return newStack;
    }

    public static void setStackWithDamage(ItemStack stack, int damage) {
        if (isDamageStack(stack)) {stack.setDamage(damage);}
    }

    public static ItemStack combineDamageStack(ItemStack stack, ItemStack otherStack, int amount) {
        ItemStack newStack = stack.copy();
        if (!isSameItemDamageStack(stack, otherStack)) {newStack.setCount(amount); return newStack;}
        int damage = stack.getDamage() + otherStack.getDamage();
        int count = amount - (damage / stack.getMaxDamage());
        newStack.setDamage(damage % stack.getMaxDamage());
        newStack.setCount(count);
        return newStack;
    }
}
