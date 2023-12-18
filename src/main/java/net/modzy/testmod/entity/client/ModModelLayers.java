package net.modzy.testmod.entity.client;

import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import net.modzy.testmod.Testmod;

public class ModModelLayers {
    public static final EntityModelLayer MIRANDA =
            new EntityModelLayer(new Identifier(Testmod.MOD_ID, "miranda"), "main");
    public static final EntityModelLayer NAUTIVERDE =
            new EntityModelLayer(new Identifier(Testmod.MOD_ID, "nautiverde"), "main");
}
