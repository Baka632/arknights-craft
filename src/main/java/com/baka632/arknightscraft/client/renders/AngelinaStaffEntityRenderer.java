package com.baka632.arknightscraft.client.renders;

import com.baka632.arknightscraft.ArknightsCraft;
import com.baka632.arknightscraft.client.models.AngelinaStaffModel;
import com.baka632.arknightscraft.entity.AngelinaStaffEntity;
import com.baka632.arknightscraft.init.ClientInit;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory.Context;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class AngelinaStaffEntityRenderer extends LivingEntityRenderer<AngelinaStaffEntity,AngelinaStaffModel> {
    private static final Identifier TEXTURE = new Identifier(ArknightsCraft.MODID,"textures/entity/angelina_staff/angelina_staff.png");

    public AngelinaStaffEntityRenderer(Context context) {
        super(context, new AngelinaStaffModel(context.getPart(ClientInit.MODEL_ANGELINA_STAFF_LAYER)), 0.5f);
    }

    @Override
    public Identifier getTexture(AngelinaStaffEntity var1) {
        return TEXTURE;
    }

}
