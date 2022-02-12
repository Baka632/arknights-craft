package com.baka632.arknightscraft.init;

import com.baka632.arknightscraft.client.renders.MoltenFlameEntityRenderer;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

@Environment(EnvType.CLIENT)
public class ClientInit implements ClientModInitializer{
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.MOLTEN_FLAME, MoltenFlameEntityRenderer::new);
    }
}
