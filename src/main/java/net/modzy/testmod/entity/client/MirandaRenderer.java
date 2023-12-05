package net.modzy.testmod.entity.client;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.modzy.testmod.Testmod;
import net.modzy.testmod.entity.custom.MirandaEntity;

public class MirandaRenderer extends MobEntityRenderer<MirandaEntity, MirandaModel<MirandaEntity>> {
    private static final Identifier TEXTURE = new Identifier(Testmod.MOD_ID, "textures/entity/miranda.png");

    public MirandaRenderer(EntityRendererFactory.Context context) {
        super(context, new MirandaModel<>(context.getPart(ModModelLayers.MIRANDA)), 0.2f);
    }

    @Override
    public Identifier getTexture(MirandaEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(MirandaEntity mobEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {
        if(mobEntity.isBaby()) {
            matrixStack.scale(0.5f, 0.5f, 0.5f);
        } else {
            matrixStack.scale(1.4f, 1.4f, 1.4f);
        }

        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
