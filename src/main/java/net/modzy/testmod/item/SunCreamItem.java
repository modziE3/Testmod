package net.modzy.testmod.item;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Equipment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class SunCreamItem extends Item {//implements Equipment {

    private final int protection;
    protected final SunCreamMaterial material;

    public SunCreamItem(SunCreamMaterial material, Settings settings) {
        super(settings.maxDamageIfAbsent(material.getDurability()));
        this.material = material;
        this.protection = material.getSunProtection();
    }

    public int getProtection() {
        return this.protection;
    }

    public SunCreamMaterial getMaterial() {
        return this.material;
    }
/*
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return this.equipAndSwap(this, world, user, hand);
    }

    @Override
    public SoundEvent getEquipSound() {
        return this.getMaterial().getEquipSound();
    }

    @Override
    public EquipmentSlot getSlotType() {
        return null;
    }

 */
}
