package net.modzy.testmod.world.biome.surface;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;
import net.minecraft.world.gen.surfacebuilder.VanillaSurfaceRules;
import net.modzy.testmod.block.ModBlocks;
import net.modzy.testmod.world.biome.ModBiomes;

import java.util.List;

public class ModMaterialRules {
    private static final MaterialRules.MaterialRule SMOOTH_BASALT = makeStateRule(Blocks.SMOOTH_BASALT);
    private static final MaterialRules.MaterialRule BASALT = makeStateRule(Blocks.BASALT);
    private static final MaterialRules.MaterialRule BEDROCK = makeStateRule(Blocks.BEDROCK);
    private static final MaterialRules.MaterialRule LUNAR_SILT_BLOCK = makeStateRule(ModBlocks.LUNAR_SILT_BLOCK);

    public static MaterialRules.MaterialRule makeRules() {

        MaterialRules.MaterialCondition isAtAndAboveSeaLevel = MaterialRules.aboveY(YOffset.fixed(62), 0);

        return surfChain(
                surfIf(MaterialRules.biome(ModBiomes.LUNAR_ROCKY_GLADES), surfChain(
                        surfIf(MaterialRules.verticalGradient("bedrock_floor", YOffset.getBottom(), YOffset.aboveBottom(5)), BEDROCK),
                        MaterialRules.condition(MaterialRules.verticalGradient("deep_basalt", YOffset.fixed(0), YOffset.fixed(8)), BASALT),
                        surfIf(MaterialRules.STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH, surfIf(isAtAndAboveSeaLevel, LUNAR_SILT_BLOCK)), SMOOTH_BASALT

                ))
        );
    }

    public static MaterialRules.MaterialCondition surfNot(MaterialRules.MaterialCondition condition) {
        return MaterialRules.not(condition);
    }
    public static MaterialRules.MaterialRule surfIf(MaterialRules.MaterialCondition condition, MaterialRules.MaterialRule rule) {
        return MaterialRules.condition(condition, rule);
    }
    public static MaterialRules.MaterialRule surfChain(MaterialRules.MaterialRule... rules) {
        return MaterialRules.sequence(rules);
    }

    private static MaterialRules.MaterialRule makeStateRule(Block block) {
        return MaterialRules.block(block.getDefaultState());
    }
}