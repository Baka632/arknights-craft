package com.baka632.arknightscraft.init;

import com.baka632.arknightscraft.ArknightsCraft;
import com.baka632.arknightscraft.client.models.AngelinaStaffModel;
import com.baka632.arknightscraft.client.renders.AngelinaStaffEntityRenderer;
import com.baka632.arknightscraft.client.renders.MoltenFlameEntityRenderer;

import org.lwjgl.glfw.GLFW;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class ClientInit implements ClientModInitializer{
    public static final EntityModelLayer MODEL_ANGELINA_STAFF_LAYER = new EntityModelLayer(new Identifier(ArknightsCraft.MODID, "angelina_staff"), "main");

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.MOLTEN_FLAME, MoltenFlameEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.ANGELINA_STAFF, AngelinaStaffEntityRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(MODEL_ANGELINA_STAFF_LAYER, AngelinaStaffModel::getTexturedModelData);

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            GameOptions options = client.options;
            boolean left = options.keyLeft.wasPressed();
            boolean right = options.keyRight.wasPressed();
            boolean forward = options.keyForward.wasPressed();
            boolean back = options.keyBack.wasPressed();
            if (left || right || forward || back) {
                sendKeyStatePack(left,right,forward,back);
            }
        });
    }

    private void sendKeyStatePack(boolean left,boolean right,boolean forward,boolean back) {
        PacketByteBuf bufToSend = PacketByteBufs.create();
        int leftInt = left ? 1 : 0;
        int rightInt = right ? 1 : 0;
        int forwardInt = forward ? 1 : 0;
        int backInt = back ? 1 : 0;
        bufToSend.writeIntArray(new int[]{leftInt,rightInt,forwardInt,backInt});
        ClientPlayNetworking.send(ArknightsCraft.SEND_KEY_STATE_PACK_ID, bufToSend);
    }
}
