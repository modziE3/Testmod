package net.modzy.testmod.sound;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.modzy.testmod.Testmod;

public class ModSounds {

    public static final SoundEvent ENTITY_NAUTIVERDE_AMBIENT = registerSoundEvent("entity_nautiverde_ambient");
    public static final SoundEvent ENTITY_NAUTIVERDE_ANGRY = registerSoundEvent("entity_nautiverde_angry");
    public static final SoundEvent ENTITY_NAUTIVERDE_DEATH = registerSoundEvent("entity_nautiverde_death");
    public static final SoundEvent ENTITY_NAUTIVERDE_HURT = registerSoundEvent("entity_nautiverde_hurt");

    public static final BlockSoundGroup REGOLITH = new BlockSoundGroup(1f, 1f,
            SoundEvents.BLOCK_SAND_BREAK, SoundEvents.BLOCK_SUSPICIOUS_SAND_STEP, SoundEvents.BLOCK_SUSPICIOUS_SAND_PLACE,
            SoundEvents.BLOCK_SUSPICIOUS_SAND_HIT, SoundEvents.BLOCK_SAND_FALL);

    private static SoundEvent registerSoundEvent(String name) {
        Identifier Id = new Identifier(Testmod.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, Id, SoundEvent.of(Id));
    }

    public static void registerSounds() {
        Testmod.LOGGER.info("Registering Sounds for " + Testmod.MOD_ID);
    }
}
