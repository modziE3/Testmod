package net.modzy.testmod.util;

import net.minecraft.nbt.NbtCompound;

public class GenderData {
    public static void getGender(IEntityDataSaver player) {
        NbtCompound nbt = player.getPersistentData();
        String gender = nbt.getString("gender");
    }
}
