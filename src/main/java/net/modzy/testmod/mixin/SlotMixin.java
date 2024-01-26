package net.modzy.testmod.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import net.modzy.testmod.util.DamageStacksUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(Slot.class)
public abstract class SlotMixin {

    @Shadow public abstract boolean canInsert(ItemStack stack);
    @Shadow public abstract ItemStack getStack();
    @Shadow public abstract int getMaxItemCount(ItemStack stack);
    @Shadow public abstract void setStack(ItemStack stack);

    /**
     * @author modzyyy
     * @reason implementing damageable stackable items
     */
    @Overwrite
    public ItemStack insertStack(ItemStack stack, int count) {
        if (stack.isEmpty() || !this.canInsert(stack)) {
            return stack;
        }
        ItemStack otherStack = this.getStack();
        int i = Math.min(Math.min(count, stack.getCount()), this.getMaxItemCount(stack) - otherStack.getCount());
        if (otherStack.isEmpty()) {
            this.setStack(stack.split(i));
        } else if (ItemStack.canCombine(otherStack, stack)) {
            if (DamageStacksUtil.isDamageStack(stack)) {
                int total = stack.getMaxDamage();
                int durability = 2*total - (stack.getDamage() + otherStack.getDamage());
                int j = (durability / total) - 1;
                int damage = total - (durability % total);
                stack.setDamage(0);
                if (damage == total) {
                    otherStack.setDamage(0); j--;
                } else otherStack.setDamage(total - (durability % total));
                otherStack.increment(i+j);
            } else {
                otherStack.increment(i);
            }
            stack.decrement(i);
            this.setStack(otherStack);
        }
        return stack;
    }
}
