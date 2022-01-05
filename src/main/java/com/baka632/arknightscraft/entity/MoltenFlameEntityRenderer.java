package com.baka632.arknightscraft.entity;


import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.util.Identifier;
import net.fabricmc.api.EnvType;

@Environment(EnvType.CLIENT)
public class MoltenFlameEntityRenderer extends EntityRenderer<MoltenFlameEntity> {
    private static final Identifier TEXTURE = new Identifier("textures/entity/illager/evoker_fangs.png");
	private final MoltenFlameEntityModel model = new MoltenFlameEntityModel();

    public MoltenFlameEntityRenderer(EntityRenderDispatcher dispatcher) {
        super(dispatcher);
    }
 
    @Override
    public Identifier getTexture(MoltenFlameEntity entity) {
        return new Identifier("arknightscraft", "textures/entity/cube/cube.png");
    }
}
