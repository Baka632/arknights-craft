package com.baka632.arknightscraft.init;

import com.baka632.arknightscraft.ArknightsCraft;
import com.baka632.arknightscraft.client.models.AngelinaStaffModel;
import com.baka632.arknightscraft.client.renders.AngelinaStaffEntityRenderer;
import com.baka632.arknightscraft.client.renders.MoltenFlameEntityRenderer;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class ClientInit implements ClientModInitializer{
    public static final EntityModelLayer MODEL_ANGELINA_STAFF_LAYER = new EntityModelLayer(new Identifier(ArknightsCraft.MODID, "angelina_staff"), "main");
    public static final Identifier ANGELINA_STAFF_MOVE_PACKET_ID = new Identifier(ArknightsCraft.MODID,"angelina_staff_move");

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.MOLTEN_FLAME, MoltenFlameEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.ANGELINA_STAFF, AngelinaStaffEntityRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(MODEL_ANGELINA_STAFF_LAYER, AngelinaStaffModel::getTexturedModelData);
    }
}
