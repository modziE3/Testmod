package net.modzy.testmod.event;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.Random;

public class PlayerTickHandler implements ServerTickEvents.StartTick{
    @Override
    public void onStartTick(MinecraftServer server) {

    }
}
