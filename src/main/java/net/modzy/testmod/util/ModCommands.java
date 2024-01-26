package net.modzy.testmod.util;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.modzy.testmod.Testmod;
import net.modzy.testmod.command.SpaceTeleport;

public class ModCommands {
    public static void registerCommands() {
        Testmod.LOGGER.info("Registering Mod commands for "+Testmod.MOD_ID);
        CommandRegistrationCallback.EVENT.register(SpaceTeleport::register);
    }
}
