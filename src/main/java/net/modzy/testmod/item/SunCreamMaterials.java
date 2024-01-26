package net.modzy.testmod.item;

import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public enum SunCreamMaterials implements SunCreamMaterial {
    ALOEVERA(100, 100, SoundEvents.ITEM_HONEYCOMB_WAX_ON);

    private final int durability;
    private final int protection;
    private final SoundEvent equipSound;

    SunCreamMaterials(int durability, int protection, SoundEvent equipSound) {
        this.durability = durability;
        this.protection = protection;
        this.equipSound = equipSound;
    }

    @Override
    public int getDurability() {
        return durability;
    }

    @Override
    public int getSunProtection() {
        return this.protection;
    }

    @Override
    public SoundEvent getEquipSound() {
        return this.equipSound;
    }
}
