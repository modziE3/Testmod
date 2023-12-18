package net.modzy.testmod.entity.client;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.modzy.testmod.Testmod;
import net.modzy.testmod.entity.custom.NautiverdeEntity;

public class NautiverdeRenderer extends MobEntityRenderer<NautiverdeEntity, NautiverdeModel<NautiverdeEntity>> {

    public NautiverdeRenderer(EntityRendererFactory.Context context) {
        super(context, new NautiverdeModel<>(context.getPart(ModModelLayers.NAUTIVERDE)), 0.6f);
    }

    @Override
    public Identifier getTexture(NautiverdeEntity entity) {
        return new Identifier(Testmod.MOD_ID, "textures/entity/nautiverde.png");
    }

    @Override
    public void render(NautiverdeEntity mobEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        if(mobEntity.isBaby()) {
            matrixStack.scale(0.5f, 0.5f, 0.5f);
        } else {
            matrixStack.scale(1.0f, 1.0f, 1.0f);
        }
        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
