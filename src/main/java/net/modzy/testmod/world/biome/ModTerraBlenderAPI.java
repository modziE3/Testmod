package net.modzy.testmod.world.biome;

import net.modzy.testmod.Testmod;
import net.modzy.testmod.world.biome.surface.ModMaterialRules;
import terrablender.api.SurfaceRuleManager;
import terrablender.api.TerraBlenderApi;

public class ModTerraBlenderAPI implements TerraBlenderApi {
    @Override
    public void onTerraBlenderInitialized() {
        //SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, Testmod.MOD_ID, ModMaterialRules.makeRules());
    }
}
