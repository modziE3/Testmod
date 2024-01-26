package net.modzy.testmod.mixin;

import net.minecraft.item.ItemStack;
import net.modzy.testmod.util.DamageStacksUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Objects;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {

    @Shadow public abstract int getCount();
    @Shadow public abstract ItemStack copyWithCount(int count);
    @Shadow public abstract void decrement(int amount);
    @Shadow public abstract ItemStack copy();
    @Shadow public abstract void setDamage(int damage);

    /**
     * @author modzyyy
     * @reason implementing damageable stackable items
     */
    @Overwrite
    public static boolean canCombine(ItemStack stack, ItemStack otherStack) {
        if (!stack.isOf(otherStack.getItem())) {
            return false;
        }
        if (stack.isEmpty() && otherStack.isEmpty()) {
            return true;
        }
        if (DamageStacksUtil.isSameItemDamageStack(stack, otherStack) ) {
            return true;
        }
        return Objects.equals(stack.getNbt(), otherStack.getNbt());
    }

    /**
     * @author modzyyy
     * @reason implementing damageable stackable items
     */
    @Overwrite
    public ItemStack split(int amount) {
        int i = Math.min(amount, this.getCount());
        ItemStack itemStack = this.copyWithCount(i);
        this.decrement(i);
        if (DamageStacksUtil.isDamageStack(this.copy())) {
            this.setDamage(0);
        }
        return itemStack;
    }
}
