package net.modzy.testmod.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.modzy.testmod.Testmod;
import net.modzy.testmod.entity.custom.MirandaEntity;
import net.modzy.testmod.entity.custom.NautiverdeEntity;

public class ModEntities {

    public static void registerModEntities() {
        Testmod.LOGGER.info("Registering Entities for " + Testmod.MOD_ID);
    }

    public static final EntityType<MirandaEntity> MIRANDA = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(Testmod.MOD_ID, "miranda"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, MirandaEntity::new)
                    .dimensions(EntityDimensions.fixed(0.7f, 0.4f)).build());

    public static final EntityType<NautiverdeEntity> NAUTIVERDE = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(Testmod.MOD_ID, "nautiverde"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, NautiverdeEntity::new)
                    .dimensions(EntityDimensions.fixed(0.8f, 0.8f)).build());
}
