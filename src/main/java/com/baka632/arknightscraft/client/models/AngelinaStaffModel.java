package com.baka632.arknightscraft.client.models;

import java.util.List;

import com.baka632.arknightscraft.entity.AngelinaStaffEntity;

import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.client.util.math.MatrixStack;

public class AngelinaStaffModel extends EntityModel<AngelinaStaffEntity>{
    private final ModelPart base;

    public AngelinaStaffModel(ModelPart modelPart) {
        base = modelPart.getChild(EntityModelPartNames.CUBE);
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
    	ModelPartData modelPartData = modelData.getRoot();
        modelPartData.addChild(EntityModelPartNames.CUBE, ModelPartBuilder.create().uv(0, 0).cuboid(-6F, 12F, -6F, 12F, 12F, 12F), ModelTransform.pivot(0F, 0F, 0F));
        return TexturedModelData.of(modelData, 64, 64);
    }

    @Override
    public void setAngles(AngelinaStaffEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
      // Not used
    }
 
    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        List.of(this.base).forEach(
            modelRenderer -> modelRenderer.render(matrices, vertices, light, overlay, red, green, blue, alpha));
    }
}
