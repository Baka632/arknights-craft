package com.baka632.arknightscraft.client.renders;

import net.fabricmc.api.Environment;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory.Context;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3f;

import com.baka632.arknightscraft.client.models.MoltenFlameEntityModel;
import com.baka632.arknightscraft.entity.MoltenFlameEntity;

import net.fabricmc.api.EnvType;

@Environment(EnvType.CLIENT)
public class MoltenFlameEntityRenderer extends EntityRenderer<MoltenFlameEntity> {
    public MoltenFlameEntityRenderer(Context ctx) {
        super(ctx);
        this.model = new MoltenFlameEntityModel<>(ctx.getPart(EntityModelLayers.EVOKER_FANGS));
    }

    private static final Identifier TEXTURE = new Identifier("minecraft","textures/entity/illager/evoker_fangs.png");
    private final MoltenFlameEntityModel<MoltenFlameEntity> model;

    @Override
    public void render(MoltenFlameEntity moltenFlameEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        float h = moltenFlameEntity.getAnimationProgress(g);
        if (h == 0.0f) {
            return;
        }
        float j = 2.0f;
        if (h > 0.9f) {
            j = (float)(j * ((1.0 - h) / 0.1f));
        }
        matrixStack.push();
        matrixStack.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(90.0f - moltenFlameEntity.getYaw()));
        matrixStack.scale(-j, -j, j);
        matrixStack.translate(0.0, -0.626f, 0.0);
        matrixStack.scale(0.5f, 0.5f, 0.5f);
        this.model.setAngles(moltenFlameEntity, h, 0.0f, 0.0f, moltenFlameEntity.getYaw(), moltenFlameEntity.getPitch());
        VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(this.model.getLayer(TEXTURE));
        this.model.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, 1.0f, 1.0f, 1.0f, 1.0f);
        matrixStack.pop();
        super.render(moltenFlameEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public Identifier getTexture(MoltenFlameEntity evokerFangsEntity) {
        return TEXTURE;
    }
}
