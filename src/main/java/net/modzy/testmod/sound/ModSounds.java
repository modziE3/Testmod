package net.modzy.testmod.sound;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.modzy.testmod.Testmod;

public class ModSounds {

    public static final SoundEvent ENTITY_NAUTIVERDE_AMBIENT = registerSoundEvent("entity_nautiverde_ambient");

    private static SoundEvent registerSoundEvent(String name) {
        Identifier Id = new Identifier(Testmod.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, Id, SoundEvent.of(Id));
    }

    public static void registerSounds() {
        Testmod.LOGGER.info("Registering Sounds for " + Testmod.MOD_ID);
    }
}
